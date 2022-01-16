Nom Eudiants : 

- TONNERRE Aubry - 20202706
- VINCENT Theo - 20203516


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
        <k> : Tous les monstres de la salle meurt instentanement
        <p> : Le Hero est puissant et One Shot les monstres
        <o> : Offre 10 pieces aux Hero
        <n> : change le Hero en mode "number one" ( voir bonus plus bas)
        <m> : ajoute des clees au joueur 

II - BONUS :
    differents bonus on ete ajoutee et seront decrit dans les parties suivantes :
        - un Menu 
        - 2 Personnages supplementaires (Magdelene et Lilith)
        - des objets (Number One, les key et les projectiles lancee par chaque personnage)
        - 2 types de salles (Salle secrete, salle objet)
        - 1 obstacles supplementaire (les poops destructible)

III - COMPOSITION DU PROJET :

     A) Personnages
        1) Isaac
            La protagoniste principale de l'histoire
                PV : 6
                Degat : 1
                vitesse : 0.01
                tmpRecharge : 0.5 cycle

        2) Magdelene
                PV : 8
                Degat : 4
                vitesse : 0.0085
                tmpRecharge : 1.25 cycle

        3) Lilith
                PV : 2
                Degat : 4
                vitesse : 0.025
                tmpRecharge : 0.38 cycle


    B) Creation des monstres
        Les monstres ne seront genere que dans les salle de monstre ainsi que dans la salle du boss de maniere aleatoire.
       
        Chaque monstres a ses caracteristiques

        1) Fly
            La mouche poursuit le Hero. Elle peut lui faire des degats au corps a corps. Lors de l'impact on remarquera que la mouche arrète de se deplacer mais continue a tire des projectiles.
            Elle peut aussi faire des degats a distance grace a ses projectiles.

        2) Spider
            L'araignee se mets en mouvement tout les cycle suivant un paterne aleatoire determine a l'aide d'un entier random. Elle fera cependant des degats aux Hero que au corps a corps. 
       
        3) Boss
            Le boss est un monstre speciale qui bouge a chaque cycle en direction du Hero en generant de maniere aleatoire un monstre differents.
    
    C) Generation de la Map

        1) Creation de different type de room
            
            Il existe 6 types de room differentes
            
            a) SpawnRoom
                Cette room est la room d'aparition du joueur. il n'as pas de comportement particulier. elle ne fait apparaitre aucun objet ni aucun monstre

            b) MonsterRoom
                Dans cette room, on retrouve des monstre genere aleatoirement ainsi que des obstables(Caracteristique decrite dans une prochaine partie)

            c) ShopRoom
                Dans cette room aucun monstre ni obstacle apparait. Cependant, 3 objets sont disponible a la vente. Le joueur pourra utilise ses pices pour les acheter.

            d) BossRoom
                Cette room a la meme comportement que la Monster Room. la seul difference c'est le monstre creer qui est le boss. Le boss a pour particularite de generer des monstre de maniere aleatoire.
                De plus, la salle de boss peut-etre la derniere de la partie ou la derniere de l'etage de jeu
                    - Si c'est la derniere de l'etage : elle donnera acces a un porte-loins
                    - Si c'est la derniere du jeu : elle donnera acces a un coffre au tresor

            e) SecretRoom
                une room cacher qui permet au joueur de recuperer un objet pour l'epauler dans sa quete
                l'acces a cette salle se fait en traversant un mur
            f)ObjetRoom
            	une room a objet , elle permet au joueur , si il est dote d'une clee de pouvoir y rentrer . Il y trouvera un objet
            	qui sera sois une blood of the martyr ou alors un number one (presente plus bas)

        2) Creation des Etages

            Les Rooms sont stocke dans une map de room. elle sont accesible avec un identifiant.
            Chaque salle possede une liste de porte qui permet au joueur de se deplacer d'une room a l'autre. 
            La liaison entre les etage se fais par l'intermediaire d'un porte loin.

    
    D) Creation des Projectile

        1) Tear
            Une larmes ne pourra etre tire que pars le Hero et ne pourra entree en collision que avec un monstre ou un mur.
            Elle a une porte qui defini sa "duree de vie"; Elle retira au monstre touchee l'equivalent des degats du hero
        2) FlyProjectile
            Ce projectile ne pourra etre tire que par les monstres et plus particulierement par les mouches.
        3) Bomb
            Lilith a pour capacite de pouvoir lancer des bomb qui font plus de degats que les larmes
        4) BabyProjectile
            Magdelene combats les monstres en lancant des bebes
        5) NumberOneprojectile
            Apres avoir recupere l'objet  number one , le hero tire ces projectiles qui on une cadance plus importante que les larmes 
            mais qui on une plus petite portee
    
    E) Creation des Objets

        1) ConsommableObject

            a) Coeur
                Les coeurs permette au Hero de se regenere
            b) Piece
                les pieces permette au Hero d'effectuer des achats in game pour augmenter ses performance.
            c) Key
                les clee servent a ouvrir les portes des salle a objet , on peut observer un compteur
        2) PassifObject

            a) CoeurSup 
                Le coeurSup permet d'augmenter la vie maximale du hero de 2 points (l'equivalent d'un coeurs).
            b) BloodOfMartyr
                Le bloodOfMartyr a pour capacite d'augmenter les degats du joueur.
            c) Number_one 
            	Le number_one a pour capacite de changer la porte ( de facon negative) mais d'augmenter la cadance des tire
            	. De plus il change les larmes en projectiles de numberOne
                
    F) Creation des obstacles
    
    	il existe 3 obstacles different
    	a noter que seulement les spider, boss et heros on des collision avec les obstacle 
    	En effet les mouches passent au-dessus
    	
    	1) Rock ou rocher
    		ce dernier est indestructible , de taille variables.
    	2) Spikes 
    		les spikes ne bloque pas le joueur, cependant lorsqu'il marche dessus il prends des degats 
    		Pour eviter de mourir trop rapidement une invincibilite temporaire a ete mis en place .
    	3) Poop
    		Les Poop sont des obstacles au meme titre que les Rock mais ils sont destructible par le joueur


    G) Critique generale de notre projet

        On a pris du plaisir a programmer ce jeu, motivation plus importante qu'en tp.
        Cependant, on aurait aimee ameliorer certains point comme la generation automatise des room qui a ete commencee mais qui n'as pas abouti.
        Un bug majeur a ete trouver (peu frequent) : 
            - lors de la mort du hero : si elle se passe trop vite (supposition) le menu de fin n'apparait pas et le jeu se fige. 
              On suppose que c'est STDdraw qui provoque cela cependant on en connait pas la raison de ce bug.
    		
    
    
    	    