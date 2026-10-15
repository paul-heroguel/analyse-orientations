<h1 align="center">Analyse des orientations</h1>

<p align="center">
  Application Java d'analyse d'un réseau d'orientations de patients entre médecins.<br>
  <i>Java app analysing a network of patient referrals between doctors.</i>
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java_21-ED8B00?style=flat-square&logo=openjdk&logoColor=white" alt="Java 21">
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=flat-square&logo=eclipseide&logoColor=white" alt="Eclipse">
</p>

---

## 🇫🇷 Présentation

Le programme charge un ensemble de médecins et les orientations de patients entre eux
(qui envoie combien de patients à qui) depuis des fichiers CSV. Il modélise ces données
comme un **graphe orienté** : chaque médecin est un sommet, chaque orientation une arête
pondérée par le nombre de patients.

Un menu en console permet ensuite d'explorer le réseau.

### Fonctionnalités

- 📂 Chargement de trois jeux de données de taille croissante (A, B, C)
- 🔎 Recherche d'un médecin par identifiant
- 📊 Statistiques : total des patients orientés, médecin qui oriente le plus, médecin qui reçoit le plus
- ➡️ Médecins vers lesquels un médecin oriente, et médecins qui orientent vers lui
- 🧭 **Recherche d'un chemin d'orientation** entre deux médecins (parcours en largeur)
- 🌐 Nombre de médecins atteignables depuis un médecin donné
- 🏝️ Détection des médecins isolés

### Points techniques

- Programmation orientée objet : `Medecin`, `Orientation`, `ReseauMedecins`
- Parcours de graphe en largeur (BFS) pour les chemins et l'accessibilité
- Lecture de fichiers CSV embarqués comme ressources (`LecteurCSV`)
- Saisies utilisateur contrôlées (gestion des entrées invalides)

### Lancer le projet

**Avec Eclipse** : *File → Import → Existing Projects into Workspace*, puis lancer `AnalyseOrientations`.

**En ligne de commande** (Java 21) :

```bash
git clone https://github.com/heroguelp-collab/analyse-orientations.git
cd analyse-orientations
javac -d bin src/analyse_orientations_26/*.java
cp src/analyse_orientations_26/*.csv bin/analyse_orientations_26/
java -cp bin analyse_orientations_26.AnalyseOrientations
```

### Structure

```
src/analyse_orientations_26/
├── AnalyseOrientations.java   Point d'entrée et menu console
├── ReseauMedecins.java        Graphe, statistiques et parcours
├── Medecin.java               Modèle d'un médecin
├── Orientation.java           Modèle d'une orientation (arête pondérée)
├── LecteurCSV.java            Lecture des fichiers CSV
├── medecins_[A|B|C].csv       Jeux de données : médecins
└── orientations_[A|B|C].csv   Jeux de données : orientations
```

> Les jeux de données sont fictifs.

---

## 🇬🇧 Overview

A console Java application that loads doctors and patient referrals from CSV files and
models them as a weighted directed graph. It computes referral statistics, lists incoming
and outgoing referrals, finds referral paths between two doctors using breadth-first search,
counts reachable doctors and detects isolated ones.

---

<p align="center"><i>Projet réalisé à l'IUT de Lens · Paul Heroguel</i></p>
