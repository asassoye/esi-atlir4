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

public class Game implements ModelInterface {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private final List<Player> players;
    private Deck deck;
    private Deck discard;
    private GameStatus status;
    private Player playing;
    private Card chosenCard;

    public Game() {
        this.status = GameStatus.NOT_STARTED;
        this.players = new ArrayList<>();
        this.playing = null;
        this.deck = new Deck(Deck.basicDeck);
        this.deck.shuffle();
        this.discard = new Deck();
        this.chosenCard = null;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }


    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus newStatus) {
        GameStatus oldStatus = this.status;
        this.status = newStatus;

        this.pcs.firePropertyChange("STATUS", oldStatus, newStatus);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int id) {
        for (var player : players) {
            if (player.getId() == id) {
                return player;
            }
        }
        return null;
    }

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

    public void addPlayer() {
        addPlayer(null);
    }

    public int nbPlayers() {
        return players.size();
    }

    public Player nextPlayer() {
        if (playing == null) {
            throw new IllegalStateException("No actual playing player");
        }

        if (playing == players.get(players.size() - 1)) {
            return getPlayer(1);
        }

        return getPlayer(playing.getId() + 1);
    }

    public void switchToNextPlayer() {
        setPlaying(nextPlayer());

        if (playing.isCompletelyReveled()) {
            setStatus(GameStatus.GAME_OVER);
        } else {
            setStatus(GameStatus.CHOOSING_CARD);
        }

    }

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

        setPlaying(players.get(0));
        setStatus(GameStatus.CHOOSING_INIT_CARDS);
    }

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

                if (playing.isCompletelyReveled()) {
                    revealAllPlayers();
                    updateAllScores(playing, getMinimumPointsPlayer());
                    setStatus(GameStatus.ROUND_OVER);
                    return;
                }

                switchToNextPlayer();
                break;
            default:
                throw new IllegalStateException("It is not the moment to choose a card on the table");
        }
    }

    public void revealAllPlayers() {
        for (var player : players) {
            player.showAllCards();
        }
    }

    public void updateAllScores(Player initiator, Player minPointsPlayer) {
        for (var player : players) {
            player.validatePoints(player == initiator && initiator != minPointsPlayer);
        }
    }

    public boolean isGameOver() {
        return getMaximumPointsPlayer().getTotalScore() >= 100;
    }

    public void next() {
        this.deck = new Deck(Deck.basicDeck);
        this.discard = new Deck();
    }

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

    public Deck getDeck() {
        return deck;
    }

    public Deck getDiscard() {
        return discard;
    }

    public Card getChosenCard() {
        return chosenCard;
    }

    public void setChosenCard(Card chosenCard) {
        this.chosenCard = chosenCard;
    }

    public Player getPlaying() {
        return playing;
    }

    public void setPlaying(Player playing) {
        this.playing = playing;
    }
}
