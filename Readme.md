# 🎮 Projet de Jeu Vidéo Isaac - ESIR - CUPGE2 (2021)

**Étudiants :**  
- TONNERRE Aubry — 20202706  
- VINCENT Théo — 20203516

---

## I. Contrôles du jeu

### a) Contrôle du personnage

- `Z` : déplacement vers le haut  
- `S` : déplacement vers le bas  
- `Q` : déplacement vers la gauche  
- `D` : déplacement vers la droite  

### b) Contrôle des tirs

- `Flèche Haut` : tir vers le haut  
- `Flèche Bas` : tir vers le bas  
- `Flèche Gauche` : tir vers la gauche  
- `Flèche Droite` : tir vers la droite  

### c) Codes de triche

- `I` : héros invincible  
- `L` : héros plus rapide  
- `K` : tue instantanément tous les monstres de la salle  
- `P` : héros puissant (tue en un coup)  
- `O` : ajoute 10 pièces au héros  
- `N` : active le mode "Number One" (voir bonus)  
- `M` : ajoute des clés au héros  

---

## II. Bonus

Des bonus supplémentaires ont été ajoutés :

- Un **menu**
- Deux **personnages supplémentaires** : *Magdelene* et *Lilith*
- Des **objets spéciaux** : *Number One*, *Blood of the Martyr*, clés, projectiles uniques
- Deux **types de salles supplémentaires** : *salle secrète* et *salle à objet*
- Un **obstacle destructible** : *les Poops*

---

## III. Composition du projet

### A) Personnages jouables

| Personnage | PV | Dégâts | Vitesse | Temps de recharge |
|-----------|----|--------|---------|-------------------|
| Isaac     | 6  | 1      | 0.010   | 0.5 cycle         |
| Magdelene | 8  | 4      | 0.0085  | 1.25 cycle        |
| Lilith    | 2  | 4      | 0.025   | 0.38 cycle        |

---

### B) Monstres

- **Fly** : suit le héros et attaque au corps-à-corps. Tire aussi des projectiles. Elle cesse de se déplacer à l’impact mais continue à tirer.
- **Spider** : bouge aléatoirement chaque cycle. Inflige des dégâts uniquement en corps-à-corps.
- **Boss** : suit le héros et invoque d'autres monstres aléatoirement. Apparaît dans une salle spécifique.

---

### C) Génération de la carte

#### 1) Types de salles

- **SpawnRoom** : salle de départ, vide.
- **MonsterRoom** : contient des monstres générés aléatoirement et des obstacles.
- **ShopRoom** : contient 3 objets à acheter (aucun ennemi).
- **BossRoom** : contient un boss. Peut déboucher sur :
  - un **portail** vers un autre étage
  - un **coffre au trésor** en fin de partie
- **SecretRoom** : salle cachée accessible en traversant un mur. Contient un objet.
- **ObjetRoom** : nécessite une clé pour y entrer. Contient un objet aléatoire (*Blood of the Martyr* ou *Number One*).

#### 2) Étages

Les salles sont stockées dans une map identifiée par ID. Chaque salle contient des portes menant à d'autres salles. La progression entre les étages se fait via un *porte-loin*.

---

### D) Projectiles

- **Tear** : projectile de base du héros. Collision avec monstres et murs.
- **FlyProjectile** : projectile des mouches.
- **Bomb** : projectile lancé par Lilith, infligeant plus de dégâts.
- **BabyProjectile** : lancé par Magdelene.
- **NumberOneProjectile** : activé avec l’objet *Number One*. Portée réduite mais cadence de tir augmentée.

---

### E) Objets

#### 1) Objets consommables

- **Cœur** : régénère la vie.
- **Pièce** : monnaie pour les achats.
- **Clé** : permet d’ouvrir les salles à objets.

#### 2) Objets passifs

- **CœurSup** : augmente la vie maximale (+2 PV).
- **Blood of the Martyr** : augmente les dégâts du héros.
- **Number One** : réduit la portée mais augmente la cadence de tir. Remplace les larmes par des projectiles spécifiques. Non cumulable.

---

### F) Obstacles

Seuls les spiders, boss et héros ont des collisions avec les obstacles. Les mouches les survolent.

- **Rock** : indestructible, tailles variables.
- **Spikes** : inflige des dégâts au contact. Une invincibilité temporaire est accordée après impact.
- **Poop** : semblable au Rock, mais destructible.

---

### G) Retour sur le projet

> Ce projet nous a motivés bien plus que les TP classiques, car il mêle créativité et programmation ludique.  
> Nous avons pris plaisir à le réaliser.

Cependant, certaines améliorations auraient été bienvenues :

- **Génération automatique des salles** : amorcée mais incomplète.
- **Bug majeur** (rare) :  
    - Si le héros meurt trop rapidement, le menu de fin ne s'affiche pas et le jeu se fige.  
    - Suspect : un souci avec `StdDraw`, mais la cause exacte est inconnue.

> N’hésitez pas à utiliser les codes de triche si la difficulté est trop élevée !

---

**Merci d’avoir joué, amusez-vous bien ! 🎉**
