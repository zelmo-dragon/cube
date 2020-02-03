# cube

## Présentation

Un simple prototype de moteur de jeu 2D basé sur JavaFX.
Ce projet est sous licence **CeCILL** (**CE**A **C**NRS **I**NRIA **L**ogiciel **L**ibre),
une licence de logicielle libre compatible avec la **GNU GPL**.

> En savoir plus sur la licence [CeCILL](http://cecill.info/index.fr.html)

## Module

Le projet est découpé en modules:
* **cube**
    * Projet de référence Maven pour la configuration de tous les autres modules.
* **cube-core**
    * Le cœur du projet, comporte toute la logique de traitement en pur Java sans dépendance externe. 
    * Comporte par défaut des fonctions de rendu graphique basé sur la manipulation de tableaux de pixels.
* **cube-fx**
    * L'implémentation principale basé sur JavaFX, comporte le moteur du jeu.
    
## Architecture

L'architecture logicielle mis en place est **ECS** *(Entity Component System)* en respectant au mieux les règles d'élégance du code *(clean code)* et *S.O.L.I.D.*.

* **Entity**
    * Représente un élément métier du jeu 
    * Exemple: un personnage, un monstre, un projectile, etc...
* **Component**
    * Représente une propriété d'une d'entité
    * Exemple: une position, un vecteur, une barre de vie, etc...
* **System**
    * Représente la logique de traitement, un système manipule des entités
    * Exemple: un système de collision, un système de déplacement, un système de rendu graphique.
    
Le code n'est pas conçu pour être performant à la base, mais structuré!
Ainsi, il devient plus facile de le faire évoluer.
    
## Environnement

Ce projet est réalisé en **Java 13** *(OpenJDK)*. et **JavaFX 13** *(OpenJFX)*.
Il utilise l'outil **Maven** en version 3.6.2.

### Exécution

~~~
    git clone https://github.com/ZelmoTheDragon/cube.git
    mvn install
    cd cube-fx
    mvn javafx:run
~~~

> Il n'est pas encore possible d'empaqueter le projet sous forme de binaire Java exécutable avec *jlink*.
