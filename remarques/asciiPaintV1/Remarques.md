# AsciiPaint

## Divers

- Git : **gitignore** en ordre
- Javadoc :  attention, l'utilisation du générateur automatique ne fait pas tout. La documentation de la méthode move de la classe Point n’apporte aucune aide par exemple
- lors du  lancement d'une exception, un message aide le développeur à comprendre l'origine de l'erreur quand il apparaît dans la StackTrace
- supprimer un tag placer par un utilisateur sur un dépôt sans en demander l'autorisation est **interdit**

## Application

- Méthode `start` trop longue, si une modification doit y être apportée, il y a un risque d'erreur suite à un copier-coller malheureux.  le `switch` pourrait être déplacer dans une casse `ShapeFactory` afin d'alléger la classe `Application`
- le cas `square` demande la création d'un rectangle et non d'un carré

## Analyse de code

Commence par jeter un œil aux remarques générées par Intelliji dans la page index.html

Reviens-vers moi si il y a des remarques que tu ne comprends pas ou que tu ne trouves pas appropriées

## Réflexion

Dans la classe `Point`, faut-il définir une méthode `boolean isLeftTo(Point other)` qui retourne vrai si le point est à gauche du point passé en paramètre ?

La méthode `isInside` de la classe `Rectangle` ne serait-elle pas plus simple ?

La responsabilisé de la classe `Point` sur ses attributs `x` et `y` ne serait-elle pas respectée de cette façon ?

## Fonctionnement

- message d'aide pour informer l'utilisateur des commandes à introduire serait utile





