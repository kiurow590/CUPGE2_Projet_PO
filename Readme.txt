Nom Eudiants : 

- TONNERRE Aubry - 20202706
- VINCENT Théo - ********


I- CONTROLE DU JEU :
    a) controle du personnage :

        <z> : mouvement vers le haut 
        <s> : mouvement vers le bas
        <q> : mouvement vers la gauche
        <d> : mouvement vers la droite

    b) Controle Tir :

        <Fleche Haut>   : Tir vers le haut
        <Fleche Bas>    : Tir vers le bas
        <Fleche Gauche> : Tir vers la gauche
        <Fleche Droite> : Tir vers la droite

    c) Code de triche

        <i> : Le Hero est invincible
        <l> : Le Hero est plus rapide
        <k> : Tous les monstres de la salle meurt instentanément
        <p> : Le Hero est puissant et One Shot les monstres
        <o> : Offre 10 pièces aux Hero

II - BONUS :


III - ETAPE DU PROJET :

    A) Creation des monstres
        Les monstres ne seront généré quand dans les salle de monstre ainsi que dans la salle du boss de maniere aléatoire.

        Chaque monstres a ses caractéristiques

        1) Fly
            La mouche poursuit le Hero. Elle peut lui faire des dégats au corps a corps. Lors de l'impact on remarquera que la mouche arrète de se déplacer mais continue a tiré des projectiles.
            Elle peut aussi faire des dégats a distance grace a ses projectile.    
        2) Spider
            L'araignée se mets en mouvement tout les cycle suivant un paterne aléatoire. Déterminé a l'aide d'un entier random. Elle fera cependant des dégats aux Hero que au corps a corps. 
        3) Boss
            Le boss est une arraignée spéciale qui bouge a chaque cycle en direction du Hero en générant de maniere aléatoire un monstre différents.
    B) Generation de la Map

        1) Creation de different type de room
            
            Il existe 4 type de room differentes
            
            a) SpawnRoom
                Cette room est la room d'aparition du joueur. il n'as pas de comportement particulier. elle ne fait apparaitre aucun objet ni aucun monstre

            b) MonsterRoom
                Dans cette room, on retrouve des monstre generé aleatoirement ainsi que des obstables(Caractéristique décrite dans une prochaine partie)

            c) ShopRoom
                Dans cette room aucun monstre ni obstacle apparait. Cependant, 3 objets sont disponible a la vente. Le joueur pourra utilisé ses pices pour les acheter.

            d) BossRoom
                Cette room a la meme comportement que la Monster Room. la seul difference c'est le monstre creer qui est le boss. Le boss a pour particularité de generer des monstre de maniere aléatoire.


            e) [secretRoom]

        2) Creation des Etages

            Les Rooms sont stocké dans une map de room. elle sont accesible avec un identifiant quelles ont.
            Chaque salle possede une liste de porte qui permet au joueur de se déplacer d'une room a l'autre. 

      

        [prevoir l'ajout d'etage]
    
    C) Creation des Projectile

        1) Larmes
            Une larmes ne pourra etre tiré que pars le Hero et ne pourra entrée en collision que avec un monstre ou un mur.
            Elle a une porté qui defini sa "durée de vie"; Elle retira au monstre touchée l'equivalent des dégats du héro
        2) FlyProjectile
            Ce projectile ne pourra etre tiré que par les monstres et plus particulierement par les mouches.
    D) Creation des Objets

        1) ConsommableObject

            a) Coeur
                Les coeurs permette au Hero de se régénéré
            b) Piece
                les pièces permette au Hero d'effectuer des achats in game pour augmenter ses performance.
        2) PassifObject

            a) CoeurSup 
                Le coeurSup permet d'augmenter la vie maximale du hero de 2 points (l'equivalent d'un coeurs).
            b) BloodOfMartyr
                Le bloodOfMartyr a pour capacité d'augmenter les dégats du joueur.
                
    E) Creation des obstacles
    
    1) creation de la structure des obstacles (Poop , Spikes , Rock)
    
    	a) creation des differentes class sous classe de obstacle
    	les Spikes font des degats au joueur , les poop sont destructible a l'aide des larmes du joueur .
    	
    2) gerer les collisions avec les obstacles .
    	a) creation des 
    
    	    