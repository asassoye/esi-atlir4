# BlackJack

## Divers

- Git : gitignore en ordre
- Readme : description a ajouter
- Javadoc : absente
- Couverture de test : 
  - aucun test du blackjack, 
  - test des cartes : couverture insuffisante
- attention aux conventions de nommages des packages (Exception) : https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html
- attention, utilise une langue unique pour tes classes, attributs, méthodes (ex : mise, variable en français)

## Analyse de code

Commence par jeter un œil aux remarques générées par Intelliji dans la page index.html

Reviens-vers moi si il y a des remarques que tu ne comprends pas ou que tu ne trouves pas appropriées

## Controller

- le constructeur doit garantir que l'état de l'objet est sans erreur lors de son instanciation, ce qui n'est pas le cas si on le construit via `new Controller(null,null)`
- Les instructions `System.out.println();` n'ont pas leur place dans le contrôleur. Un exemple est que si tu dois changer la langue de ton application, tu vas modifier le contenu du package vue et il y a un risque que tu oublies de vérifier le contenu du package controller

## View

- `card.toString(false)` l'appel à cette méthode ne se différencie que très peu de la méthode `toString`, en quoi cela règle le problème de séparation des responsabilités vue/modèle ?  

## Model

- Facade : en ordre
- Game : 
  - le montant accordé au joueur/banque en début de partie devrait être placé dans une constante afin d'être modifiable plus facilement (si jamais cette valeur est utilisée à d'autres endroits du code)
  - qu'elle est l'utilité des retours de l’instance courante pour la méthode `start` ? aucun appel chaîné n'est utilisé
  - la méthode `user.hit` est obligée d'utiliser un mécanisme d’exception au lieu de retourner un booléen, la lisibilité est rendue plus difficile pour permettre des appels chaînés jamais utilisés
  - est ce que la méthode `getPlayer` doit retourner une copie du joueur ?
  - la question se pose pour les différents accesseurs
- Player
  - une erreur de la méthode hit affiche une information dans la console en dehors de la vue, aucun traitement ne suit l'apparition de cette erreur
  - comment la double valeur d'un as est-elle gérée ?

## Dépendances inutiles

Certains appels de méthodes créent des dépendances entre classes qui ne sont pas utiles.

Par exemple dans la classe `Controller`, l'instruction `this.game.getPlayer().transferMoneyFrom(this.game.getBank(), mise)`

Le `Controller` dépend maintenant de la classe `Player` alors qu'au départ il dépendant uniquement de `Game`. Ce choix est-il justifié ? (la dépendance à la classe `Player` était déjà présente était dans ce cas pour gérer l'affichage).

Ne faudrait-il pas avoir une instruction du type `game.transfertToBank()`. Aucun paramètre ne serait nécessaire en plus car la classe `Game` peut accéder à l'instance de `Bank`.

Voir la loi de Déméter pour plus d'informations sur les limites de cette pratique : https://fr.wikipedia.org/wiki/Loi_de_D%C3%A9m%C3%A9ter

