# cube

## Présentation

Un simple moteur de jeu 2D basé sur JavaFX (OpenJFX).

## Module

Le projet est découpé en modules:
* **cube**
    * Projet de référence Maven pour la configuration de tous les autres modules.
* **cube-core**
    * Le cœur du projet, comporte toute la logique de traitement en pur Java sans dépendance externe. 
    * Comporte par défaut des fonctions de rendu graphique basé sur la manipulation de pixels.
* **cube-fx**
    * L'implémentation principale basé sur JavaFX, comporte le moteur du jeu.
    
## Architecture

L'architecture logicielle mis en place est **ESC** *(Entity Component System)* en respectant au mieux les règles d'élégance du code *(clean code)*.

* **Entity**
    * Représente un élément métier du jeu 
    * Exemple: un personnage, un monstre, un projectile, etc...
* **Component**
    * Représente une propriété d'une d'entité
    * Exemple: une position, un vecteur, une barre de vie, etc...
* **System**
    * Représente la logique de traitement, un système manipule des entités
    * Exemple: un système de collision, un système de déplacement, un système de rendu graphique.
