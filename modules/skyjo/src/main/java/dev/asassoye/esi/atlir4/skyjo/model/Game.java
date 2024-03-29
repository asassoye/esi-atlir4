/*
 * Copyright (c) 2021 Andrew SASSOYE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.asassoye.esi.atlir4.skyjo.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game class.
 */
public class Game implements ModelInterface {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final List<Player> players;
    private Deck deck;
    private Deck discard;
    private GameStatus status;
    private Player playing;
    private Card chosenCard;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.status = GameStatus.NOT_STARTED;
        this.players = new ArrayList<>();
        this.playing = null;
        this.deck = new Deck(Deck.basicDeck);
        this.deck.shuffle();
        this.discard = new Deck();
        this.chosenCard = null;
    }

    /**
     * {@inheritDoc}
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param newStatus the new status
     */
    public void setStatus(GameStatus newStatus) {
        GameStatus oldStatus = this.status;
        this.status = newStatus;

        this.pcs.firePropertyChange("STATUS", oldStatus, newStatus);
    }

    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Gets player.
     *
     * @param id the id of the player
     * @return the player if exists
     */
    public Player getPlayer(int id) {
        for (var player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void addPlayer(String name) {
        if (status != GameStatus.NOT_STARTED) {
            throw new IllegalStateException("You cannot add players during a game");
        }

        if (name == null) {
            players.add(new Player(players.size() + 1));
        } else {
            players.add(new Player(players.size() + 1, name));
        }
    }

    /**
     * Add player without name.
     */
    public void addPlayer() {
        addPlayer(null);
    }

    /**
     * Number of players
     *
     * @return nb players
     */
    public int nbPlayers() {
        return players.size();
    }

    /**
     * Next player.
     *
     * @return the next player after playing
     */
    public Player nextPlayer() {
        if (playing == null) {
            throw new IllegalStateException("No actual playing player");
        }

        if (playing == players.get(players.size() - 1)) {
            return getPlayer(1);
        }

        return getPlayer(playing.getId() + 1);
    }

    /**
     * Switch to next player.
     */
    public void switchToNextPlayer() {
        setPlaying(nextPlayer());

        if (playing.isCompletelyReveled()) {
            revealAllPlayers();
            updateAllScores(playing, getMinimumPointsPlayer());
            setStatus(GameStatus.ROUND_OVER);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void distributeCards() {
        if (status != GameStatus.NOT_STARTED) {
            throw new IllegalStateException("You cannot distribute cards during a game");
        }

        if (players.size() < 2) {
            throw new IllegalStateException("Minimum players is 2.");
        }

        for (var player : players) {
            player.placeCards(deck);
        }

        Card discardCard = deck.pop();
        discardCard.show();

        discard.add(discardCard);
        discard.add(discardCard);

        setPlaying(players.get(0));
        setStatus(GameStatus.CHOOSING_INIT_CARDS);
    }

    /**
     * {@inheritDoc}
     */
    public void chooseTableCard(int x, int y) {
        switch (status) {
            case CHOOSING_INIT_CARDS:
                playing.showCard(x, y);

                if (playing.shownCards() == 2) {
                    if (playing == players.get(players.size() - 1)) {
                        setPlaying(getMinimumPointsPlayer());
                        setStatus(GameStatus.CHOOSING_CARD);
                    } else {
                        switchToNextPlayer();
                    }
                }
                break;
            case CHOSEN_TO_DISCARD:
                if (playing.getCard(x, y).isVisible()) {
                    throw new IllegalStateException("This card is already shown");
                }
                playing.showCard(x, y);
                setStatus(GameStatus.CHOOSING_CARD);
                switchToNextPlayer();
                break;
            case CHOSEN_FROM_DISCARD:
            case CHOSEN_FROM_DECK:
                Card exchanged = playing.exchangeCard(chosenCard, x, y);
                exchanged.show();
                discard.add(exchanged);
                this.pcs.firePropertyChange("DISCARD", null, discard.top());
                this.pcs.firePropertyChange("DECK", null, deck.top());
                chosenCard = null;
                setStatus(GameStatus.CHOOSING_CARD);

                switchToNextPlayer();

                break;
            default:
                throw new IllegalStateException("It is not the moment to choose a card on the table");
        }
        this.pcs.firePropertyChange("BOARD", null, this);
    }

    /**
     * {@inheritDoc}
     */
    public void chooseDiscard() {
        if (status == GameStatus.CHOOSING_CARD) {
            chosenCard = discard.pop();
            this.pcs.firePropertyChange("DISCARD", chosenCard, discard.top());
            setStatus(GameStatus.CHOSEN_FROM_DISCARD);
            return;
        }

        if (status == GameStatus.CHOSEN_FROM_DECK) {
            chosenCard.show();
            discard.add(chosenCard);
            chosenCard = null;
            this.pcs.firePropertyChange("DISCARD", null, discard.top());
            setStatus(GameStatus.CHOSEN_TO_DISCARD);
            return;
        }

        throw new IllegalStateException("It is not the moment to choose the discard.");
    }

    /**
     * {@inheritDoc}
     */
    public void chooseDeck() {
        if (status == GameStatus.CHOOSING_CARD) {
            chosenCard = deck.pop();
            this.pcs.firePropertyChange("DECK", chosenCard, deck.top());
            setStatus(GameStatus.CHOSEN_FROM_DECK);
            return;
        }

        throw new IllegalStateException("It is not the moment to choose the deck.");
    }

    /**
     * Reveal all players.
     */
    public void revealAllPlayers() {
        for (var player : players) {
            player.showAllCards();
        }
    }

    /**
     * Update all scores. (validate round score in total)
     *
     * @param initiator       the initiator player (who have to have min to not double)
     * @param minPointsPlayer the min points player
     */
    public void updateAllScores(Player initiator, Player minPointsPlayer) {
        for (var player : players) {
            player.validatePoints(player == initiator && initiator != minPointsPlayer);
        }
    }

    /**
     * Is game over boolean.
     *
     * @return the boolean
     */
    public boolean isGameOver() {
        return getMaximumPointsPlayer().getTotalScore() >= 100;
    }

    /**
     * Next round init.
     */
    public void next() {
        this.deck = new Deck(Deck.basicDeck);
        this.discard = new Deck();
    }

    /**
     * Gets minimum points' player.
     *
     * @return the minimum points player
     */
    public Player getMinimumPointsPlayer() {
        Player minPlayer = null;
        int minPoints = Integer.MAX_VALUE;

        for (var player : players) {
            if (player.getPoints() < minPoints) {
                minPlayer = player;
                minPoints = player.getPoints();
            }
        }

        return minPlayer;
    }

    /**
     * Gets maximum points player.
     *
     * @return the maximum points player
     */
    public Player getMaximumPointsPlayer() {
        Player maxPlayer = null;
        int maxPoints = Integer.MIN_VALUE;

        for (var player : players) {
            if (player.getPoints() > maxPoints) {
                maxPlayer = player;
                maxPoints = player.getPoints();
            }
        }

        return maxPlayer;
    }

    /**
     * Gets deck.
     *
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * {@inheritDoc}
     */
    public Deck getDiscard() {
        return discard;
    }

    /**
     * {@inheritDoc}
     */
    public Card getChosenCard() {
        return chosenCard;
    }

    /**
     * Sets chosen card.
     *
     * @param chosenCard the chosen card
     */
    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }

    public Player getPlaying() {
        return playing;
    }

    /**
     * Sets playing.
     *
     * @param playing the playing
     */
    public void setPlaying(Player playing) {
        var old = this.playing;
        this.playing = playing;
        this.pcs.firePropertyChange("PLAYING", old, this.playing);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInfo() {
        switch (status) {
            case NOT_STARTED:
                return "La partie n'a pas encore commencée.";
            case CHOOSING_INIT_CARDS:
                return String.format("%s, veuillez retourner deux cartes pour commencer la partie.", playing.getName());
            case CHOOSING_CARD:
                return String.format("%s, veuillez prendre une carte dans le deck ou dans la defausse.", playing.getName());
            case CHOSEN_FROM_DISCARD:
                return String.format("%s, où voulez-vous placer la carte de la défausse de valeur %d?", playing.getName(), chosenCard.getValue());
            case CHOSEN_FROM_DECK:
                return String.format("%s, où voulez-vous placer la carte du deck de valeur %d?", playing.getName(), chosenCard.getValue());
            case CHOSEN_TO_DISCARD:
                return String.format("%s, vous avez mis la carte dans la défausse. Veuillez choisir une carte a retourner", playing.getName());
            case ROUND_OVER:
                return String.format("Le tour est terminé. Le meilleur joueur est actuellement %s", getMinimumPointsPlayer().getName());
            case GAME_OVER:
                return String.format("La partie est terminée. %s a gagné", getMinimumPointsPlayer().getName());
            default:
        }
        return "Etat inconnu.";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardInterface getDeckCard() {
        return deck.top();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardInterface getDiscardCard() {
        return discard.top();
    }
}
