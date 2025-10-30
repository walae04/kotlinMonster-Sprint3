
# COMPTE RENDU DU PROJET KOTLIN MONSTER SPRINT 1 et 3

## Introduction :

Le projet **Kotlin Monster** est un mini-jeu inspiré des mécaniques de Pokémon, développé en **Kotlin**.  
Le joueur incarne un **dresseur** qui capture, entraîne et fait combattre des monstres dans différentes zones.  
L’objectif principal de ce sprint était de construire l’architecture de base du jeu en respectant la **programmation orientée objet** (POO) et en répartissant les classes dans des **packages cohérents**.

---
## Sprint 1 : Création du projet & classes de base
## Structure du projet :

Le projet est organisé en **5 packages principaux** :

| Package | Contenu | Rôle principal |
|----------|----------|----------------|
| **monstre** | `EspeceMonstre`, `IndividuMonstre` | Définit les caractéristiques et comportements des monstres. |
| **monde** | `Zone` | Représente les zones du jeu où les rencontres et combats ont lieu. |
| **jeu** | `CombatMonstre`, `Partie` | Gère la logique du jeu : combats, déroulement d’une partie. |
| **dresseur** | `Entraineur` | Représente le joueur ou les adversaires avec leurs monstres et objets. |
| **item** | `Item`, `Badge`, `MonsterKube`, `Utilisable` | Définit les objets utilisables et les récompenses. |

---

## Détails par package :

### 1️⃣ Package **monstre**

#### 🔹 Classe `EspeceMonstre`
- Représente le **modèle générique d’un monstre** (comme une espèce dans Pokémon).
- Contient les statistiques de base : attaque, défense, PV, vitesse, etc.
- Possède une méthode `afficheArt()` qui lit un fichier texte ASCII pour afficher visuellement le monstre.

> **Rôle :** définir la base commune à tous les monstres d’une même espèce.  
> **Difficulté : 🟢 Facile** – structure simple et descriptive.

#### 🔹 Classe `IndividuMonstre`
- Représente un **monstre individuel** appartenant à un dresseur.
- Chaque individu possède :
    - des **caractéristiques uniques** (aléatoires) dérivées de son espèce ;
    - un **niveau**, des **points de vie**, et une **expérience** ;
    - un lien optionnel vers son **entraineur**.
- Méthodes clés :
    - `palierExp(niveau)` → calcule l’expérience nécessaire pour monter de niveau ;
    - `levelUp()` → augmente le niveau et les statistiques ;
    - `attaquer(monstre)` → inflige des dégâts selon la formule simplifiée ;
    - `renommer()` et `afficherDetail()` → interaction et affichage.

> **Rôle :** gérer la progression, les combats et la croissance des monstres.  
> **Difficulté : 🟡 Moyenne** – nécessite une bonne gestion des variables et du setter `exp`.

---

### 2️⃣ Package **monde**

#### 🔹 Classe `Zone`
- Représente une **zone géographique** du jeu contenant plusieurs espèces de monstres.
- Contient :
    - une liste `especesMonstres`,
    - des liens vers la `zoneSuivante` et `zonePrecedente`,
    - un niveau d’expérience `expZone`.
- Méthodes principales :
    - `genereMonstre()` → crée un monstre sauvage aléatoire selon la zone ;
    - `rencontreMonstre()` → déclenche un **combat contre un monstre sauvage**.

> **Rôle :** gérer la génération et la rencontre de monstres selon la zone.  
> **Difficulté : 🟢 Facile** – logique simple, mais nécessite de comprendre la relation entre les packages.

---

### 3️⃣ Package **jeu**

#### 🔹 Classe `CombatMonstre`
- Gère un **combat entre un monstre du joueur et un monstre sauvage**.
- Fonctions principales :
    - `gameOver()` → vérifie si le joueur n’a plus de monstres valides ;
    - `jouerGagne()` → gère la victoire et l’attribution d’expérience ;
    - `actionJoueur()` et `actionAdversaire()` → définissent les tours ;
    - `afficherCombat()` → affiche l’état du combat avec les ASCII des monstres ;
    - `lanceCombat()` → boucle principale du combat jusqu’à victoire ou défaite.

> **Rôle :** cœur du gameplay, gère la logique du combat et les tours de jeu.  
> **Difficulté : 🔴 Difficile** – plusieurs conditions, interactions entre classes et gestion d’états complexes.

#### 🔹 Classe `Partie`
- Représente une **session de jeu complète**.
- Permet :
    - de choisir un monstre de départ (`choixStarter()`),
    - de gérer son équipe (`examineEquipe()`, `modifierOrdreEquipe()`),
    - et d’explorer ou combattre (`jouer()`).
- Sert de **point d’entrée logique** du jeu.

> **Rôle :** coordonner toutes les actions principales du joueur.  
> **Difficulté : 🟠 Assez difficile** – coordination entre zones, monstres, combats et affichages.

---

### 4️⃣ Package **dresseur**

#### 🔹 Classe `Entraineur`
- Représente le joueur ou les adversaires.
- Contient :
    - une **équipe de monstres actifs** (`equipeMonstre`),
    - une **boîte de stockage** (`boiteMonstre`),
    - et un **sac d’objets** (`sacAItems`).
- Méthode : `afficheDetail()` → affiche nom et argent du dresseur.

> **Rôle :** regrouper toutes les ressources du joueur.  
> **Difficulté : 🟡 Moyenne** – gestion de plusieurs listes et composition d’objets.

---

### 5️⃣ Package **item**

#### 🔹 Classe `Item`
- Classe de base pour tous les objets.
- Contient simplement un `id`, un `nom`, et une `description`.

> **Rôle :** classe mère pour les objets du jeu.  
> **Difficulté : 🟢 Facile**

#### 🔹 Classe `Badge`
- Hérite de `Item`, ajoute la propriété `champion` (type `Entraineur`).

> **Rôle :** représenter les badges gagnés.  
> **Difficulté : 🟢 Facile**

#### 🔹 Interface `Utilisable`
- Interface qui définit la méthode `utiliser(cible: IndividuMonstre): Boolean`.

> **Rôle :** forcer les classes à implémenter une action concrète.  
> **Difficulté : 🟡 Moyenne**

#### 🔹 Classe `MonsterKube`
- Hérite de `Item` et implémente `Utilisable`.
- Permet de **capturer un monstre sauvage** :
    - calcule une **chance de capture** selon les PV restants du monstre ;
    - ajoute le monstre capturé à l’équipe ou à la boîte.

> **Rôle :** gestion d’un objet complexe et d’interactions avec le joueur.  
> **Difficulté : 🔴 Difficile** – calculs aléatoires, gestion de listes et logique de capture.

---

##  Niveau de difficulté global :

| Élément | Difficulté | Type de complexité |
|----------|-------------|-------------------|
| `Item`, `Badge`, `Zone`, `EspeceMonstre` | 🟢 Facile | Structures de données simples |
| `Entraineur`, `IndividuMonstre`, `Utilisable` | 🟡 Moyen | Relations entre classes |
| `MonsterKube`, `CombatMonstre`, `Partie` | 🔴 Difficile | Logique algorithmique et gameplay |

---

## Analyse personnelle :

Ce projet m’a permis de :
- Mettre en pratique la **programmation orientée objet** : héritage, composition, interface, encapsulation.
- Comprendre la **répartition logique en packages** pour structurer un grand projet.
- Développer des **interactions entre objets complexes** (combat, capture, montée de niveau).
- Travailler avec des **structures de données dynamiques** (`MutableList`) et des **valeurs aléatoires** (`Random`).

### Bilan :
Certaines classes comme `MonsterKube` et `CombatMonstre` m’ont posé des difficultés car elles demandaient une logique complète (conditions, boucles, retours de fonctions).  
Mais elles m’ont permis d’approfondir ma compréhension du **cycle de jeu** et de la **gestion d’état en Kotlin**.

Globalement, ce projet m’a donné une vision complète d’un jeu orienté objet et m’a permis de progresser dans l’écriture d’un **code structuré, cohérent et maintenable**.

---
## Sprint 3 : Base de Données & DAO

##  Contexte :

Le troisième sprint a pour objectif d’introduire une **connexion entre le projet Kotlin Monsters et une base de données relationnelle MySQL/MariaDB**.  
Auparavant, les données (entraîneurs, monstres, espèces…) étaient créées directement dans le code. Ce sprint permet de :

- Centraliser et stocker les données dans une **base de données**.
- Automatiser la création, lecture, mise à jour et suppression (**CRUD**) via des objets Kotlin.
- Utiliser un **DAO (Data Access Object)** pour simplifier les interactions entre le code et la BDD.

À l’issue du sprint, le projet peut charger automatiquement les entraîneurs, espèces et monstres depuis la base de données, sans recréation manuelle dans le code.

---

##  Étapes réalisées :

### 1. Création de la base de données et des tables

- Base de données créée : `db_monsters_wouahi`
- Tables principales créées :
    - `Entraineurs` (id, nom, argents)
    - `EspecesMonstre` (attributs, stats, description…)
    - `IndividusMonstre` (niveau, PV, EXP, référence à une espèce, entraineur)

- Fichier **tables.sql** ajouté avec toutes les requêtes SQL de création et d’insertion.
- Diagramme **ERD** réalisé avec **PlantUML** pour représenter les relations entre les entités.

---

### 2. Insertion des données de base

- **Entraîneurs ajoutés** : Bob, Alice, Clara (NPC)
- **Espèces ajoutées** : Springleaf, Flamkip, Pyrokip, Aquamy, Laoumi, Bugsyface, Galum
- **Individus ajoutés** :
    - Alice → Springleaf
    - Bob → Aquamy, Bugsyface
    - Clara → Galum, Flamkip

Toutes les requêtes SQL sont conservées dans `tables.sql`.

---

### 3. Création et explication des DAO

#### ⚠ Difficultés rencontrées

- La gestion des DAO a été **complexe**, surtout pour les relations entre les tables (ex. `IndividusMonstre` référant `Entraineur` et `EspeceMonster`).
- Il a fallu gérer les **clés étrangères** et s’assurer que les objets liés soient bien récupérés avant d’instancier les objets Kotlin.
- Les méthodes de **CRUD** doivent être sécurisées et éviter les injections SQL.

---

#### 3.1 EntraineurDAO

- **Responsabilité :** Gérer les opérations sur la table `Entraineurs`.
- **Méthodes principales :**
    - `findAll()` : récupère tous les entraîneurs.
    - `findById(id: Int)` : recherche par ID.
    - `findByNom(nom: String)` : recherche par nom.
    - `save(entraineur: Entraineur)` : insère ou met à jour un entraîneur.
    - `deleteById(id: Int)` : supprime un entraîneur par ID.
    - `saveAll(entraineurs: Collection<Entraineur>)` : sauvegarde plusieurs entraîneurs à la fois.

**Explication du code :**

- Chaque méthode utilise **`PreparedStatement`** pour sécuriser les requêtes.
- `findAll()` parcourt le résultat de la requête et crée un objet `Entraineur` par ligne.
- `save()` vérifie si l’ID est zéro pour savoir s’il faut faire un **INSERT** ou un **UPDATE**.
- `deleteById()` renvoie `true` si la suppression a fonctionné, sinon `false`.

---

#### 3.2 EspeceMonsterDAO

- **Responsabilité :** Gérer la table `EspeceMonstre`.
- Méthodes similaires à `EntraineurDAO` : `findAll`, `findById`, `findByNom`, `save`, `deleteById`, `saveAll`.
- **Particularité :** Les espèces ont beaucoup de champs (stats et modificateurs), ce qui rend l’insertion et la mise à jour plus longues et sensibles aux erreurs.

**Explication du code :**

- Pour les inserts, on utilise `Statement.RETURN_GENERATED_KEYS` afin de récupérer l’ID généré automatiquement.
- La mise à jour (`UPDATE`) inclut tous les champs pour refléter les modifications dans la BDD.
- `saveAll()` parcourt la liste d’espèces et appelle `save()` sur chaque élément.

---

#### 3.3 IndividuMonstreDAO

- **Responsabilité :** Gérer la table `IndividusMonstre` et ses relations avec `Entraineur` et `EspeceMonster`.
- Méthodes principales : `findAll`, `findById`, `save`, `deleteById`, `saveAll`.

**Explication du code :**

- `findAll()` récupère tous les individus, puis utilise `EspeceMonsterDAO` et `EntraineurDAO` pour lier les objets.
- `save()` décide entre **INSERT** et **UPDATE**, et remplit tous les paramètres, y compris les références aux autres tables.
- La complexité réside dans la **gestion des relations**, où chaque objet doit être récupéré correctement avant de créer l’objet `IndividuMonstre`.

---

### 4. Intégration dans le projet

- Connexion à la BDD établie en début de `Main.kt`.
- Récupération automatique des listes d’entraîneurs, espèces et individus depuis la base.
- Nouvelle partie et sauvegarde automatique du joueur avec `entraineurDAO.save(joueur)`.
- Tests réalisés pour vérifier que les données sont bien chargées depuis la BDD.

---
### Bilan global
- Projet structuré et maintenable grâce à la **POO** et aux **DAO**.
- Approfondissement de la compréhension des **relations entre objets**, de la **gestion d’état**, et de la **connexion à une base de données**.
- Les difficultés rencontrées ont été une **opportunité d’apprentissage**, notamment dans la manipulation d’objets complexes et la sécurisation des requêtes SQL.
- Le projet est maintenant capable de **fonctionner de manière autonome**, avec des données persistantes et une architecture solide pour les prochaines évolutions.

**Conclusion personnelle :**  
Ces deux sprints m’ont permis de passer d’un **jeu entièrement codé en mémoire** à un **système connecté à une BDD**, améliorant la robustesse et la maintenabilité du code. Le projet est prêt pour des fonctionnalités avancées comme les combats multijoueur, la sauvegarde automatique ou l’ajout de nouvelles zones et monstres.
