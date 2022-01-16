package gameWorld.rooms;

import java.util.HashMap;
import java.util.Map;

import gameWorld.rooms.portes.BottomDoor;
import gameWorld.rooms.portes.CarriesAway;
import gameWorld.rooms.portes.LeftDoor;
import gameWorld.rooms.portes.RightDoor;
import gameWorld.rooms.portes.RightKeyDoor;
import gameWorld.rooms.portes.TopDoor;

import gameobjects.objets.consommables.Key;

import libraries.Vector2;

import gameobjects.personnages.hero.Hero;

import resources.ImagePaths;

/**
 * Classe qui genere les etage du jeu
 */
public class Etage {
    Map<Integer, Room> mapDeRoom = new HashMap<>();
    private Hero hero;

    /**
     * Constructeur Etage
     * 
     * @param mapDeRoom map
     * @param hero      le hero
     */
    public Etage(Map<Integer, Room> mapDeRoom, Hero hero) {
        this.mapDeRoom = mapDeRoom;
        this.hero = hero;

        generateStage1();
        generateStage2();
        generateStage3();

        mapDeRoom.get(5).getLstPorte().add(new CarriesAway(6));

        mapDeRoom.get(14).getLstPorte().add(new CarriesAway(15));
    }

    /**
     * genere le premiere etage du jeu
     */
    public void generateStage1() {
        // Generation des rooms
        Room spawn = new SpawnRoom(hero, 0);
        Room monster1 = new MonsterRoom(hero, 1);
        Room monster2 = new MonsterRoom(hero, 2);
        Room monster3 = new MonsterRoom(hero, 3);
        Room commerce1 = new ShopRoom(hero, 4);
        Room boss = new BossRoom(hero, 5);
        Room objet = new RoomObjet(hero, -12);

        Room secretRoom = new SecretRoom(hero, -1);

        // on ajoute les porte au differente salle

        spawn.getLstPorte().add(new RightDoor(secretRoom.getId()));
        secretRoom.getLstPorte().add(new LeftDoor(spawn.getId()));
        spawn.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);
        secretRoom.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);

        spawn.getLstPorte().add(new LeftDoor(monster1.getId()));
        monster1.getLstPorte().add(new RightDoor(spawn.getId()));
        monster1.getLstPorte().add(new LeftDoor(monster2.getId()));
        monster2.getLstPorte().add(new RightDoor(monster1.getId()));
        monster2.getLstObjet().add(new Key(new Vector2(0.5, 0.5), 0, 0));
        monster2.getLstPorte().add(new RightDoor(monster1.getId()));

        monster1.getLstPorte().add(new BottomDoor(monster3.getId()));

        monster3.getLstPorte().add(new TopDoor(monster1.getId()));
        monster3.getLstPorte().add(new BottomDoor(commerce1.getId()));

        monster3.getLstPorte().add(new RightKeyDoor(objet.getId()));
        objet.getLstPorte().add(new LeftDoor(monster3.getId()));
        commerce1.getLstPorte().add(new TopDoor(monster3.getId()));

        commerce1.getLstPorte().add(new RightDoor(boss.getId()));
        commerce1.getLstPorte().get(1).setImagePaths(ImagePaths.PORTE_BOSS_FERME);
        boss.getLstPorte().add(new LeftDoor(commerce1.getId()));
        // boss.getLstPorte().get(0).setImagePaths(ImagePaths.PORTE_BOSS_FERME);
        boss.getLstObjet().remove(0);

        // on ajoute a la map
        mapDeRoom.put(spawn.getId(), spawn);
        mapDeRoom.put(secretRoom.getId(), secretRoom);

        mapDeRoom.put(monster1.getId(), monster1);
        mapDeRoom.put(monster2.getId(), monster2);
        mapDeRoom.put(monster3.getId(), monster3);
        mapDeRoom.put(commerce1.getId(), commerce1);
        mapDeRoom.put(objet.getId(), objet);
        mapDeRoom.put(boss.getId(), boss);
    }

    /**
     * genere le deuxieme etage du jeu
     */
    public void generateStage2() {
        // Generation des rooms
        Room spawn = new SpawnRoom(hero, 6);
        Room monster1 = new MonsterRoom(hero, 7);
        Room monster2 = new MonsterRoom(hero, 8);
        Room monster3 = new MonsterRoom(hero, 9);
        Room commerce1 = new ShopRoom(hero, 10);
        Room monster4 = new MonsterRoom(hero, 11);
        Room monster5 = new MonsterRoom(hero, 12);
        Room commerce2 = new ShopRoom(hero, 13);
        Room boss = new BossRoom(hero, 14);

        Room secretRoom1 = new SecretRoom(hero, -2);
        Room secretRoom2 = new SecretRoom(hero, -3);

        monster2.getLstPorte().add(new LeftDoor(secretRoom1.getId()));
        secretRoom1.getLstPorte().add(new RightDoor(monster2.getId()));
        monster2.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);
        secretRoom1.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);

        monster5.getLstPorte().add(new TopDoor(secretRoom2.getId()));
        secretRoom2.getLstPorte().add(new BottomDoor(monster5.getId()));
        monster5.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);
        secretRoom2.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);

        // on ajoute les porte au differente salle
        spawn.getLstPorte().add(new RightDoor(monster1.getId()));
        monster1.getLstPorte().add(new LeftDoor(spawn.getId()));

        monster1.getLstPorte().add(new BottomDoor(monster2.getId()));
        monster2.getLstPorte().add(new TopDoor(monster1.getId()));

        monster2.getLstPorte().add(new RightDoor(monster3.getId()));
        monster3.getLstPorte().add(new LeftDoor(monster2.getId()));

        monster3.getLstPorte().add(new BottomDoor(commerce1.getId()));
        commerce1.getLstPorte().add(new TopDoor(monster3.getId()));

        commerce1.getLstPorte().add(new BottomDoor(monster4.getId()));
        monster4.getLstPorte().add(new TopDoor(commerce1.getId()));

        monster4.getLstPorte().add(new LeftDoor(monster5.getId()));
        monster5.getLstPorte().add(new RightDoor(monster4.getId()));

        monster5.getLstPorte().add(new BottomDoor(commerce2.getId()));
        commerce2.getLstPorte().add(new TopDoor(monster5.getId()));

        commerce2.getLstPorte().add(new BottomDoor(boss.getId()));
        commerce2.getLstPorte().get(1).setImagePaths(ImagePaths.PORTE_BOSS_FERME);
        boss.getLstPorte().add(new TopDoor(commerce2.getId()));
        // boss.getLstPorte().get(0).setImagePaths(ImagePaths.PORTE_BOSS_FERME);
        boss.getLstObjet().remove(0);

        // on ajoute a la map
        mapDeRoom.put(spawn.getId(), spawn);

        mapDeRoom.put(secretRoom1.getId(), secretRoom1);
        mapDeRoom.put(secretRoom2.getId(), secretRoom2);

        mapDeRoom.put(monster1.getId(), monster1);
        mapDeRoom.put(monster2.getId(), monster2);
        mapDeRoom.put(monster3.getId(), monster3);
        mapDeRoom.put(monster4.getId(), monster4);
        mapDeRoom.put(monster5.getId(), monster5);
        mapDeRoom.put(commerce1.getId(), commerce1);
        mapDeRoom.put(commerce2.getId(), commerce2);
        mapDeRoom.put(boss.getId(), boss);
    }

    /**
     * genere le troisieme etage du jeu
     */
    public void generateStage3() {
        // Generation des rooms
        Room spawn = new SpawnRoom(hero, 15);
        Room monster1 = new MonsterRoom(hero, 16);
        Room monster2 = new MonsterRoom(hero, 17);
        Room monster3 = new MonsterRoom(hero, 18);
        Room monster4 = new MonsterRoom(hero, 19);
        Room monster5 = new MonsterRoom(hero, 20);
        Room commerce1 = new ShopRoom(hero, 21);
        Room monster6 = new MonsterRoom(hero, 22);
        Room monster7 = new MonsterRoom(hero, 23);
        Room monster8 = new MonsterRoom(hero, 24);
        Room monster9 = new MonsterRoom(hero, 25);
        Room commerce2 = new ShopRoom(hero, 26);
        Room boss = new BossRoom(hero, 27);

        Room secretRoom1 = new SecretRoom(hero, -4);
        Room secretRoom2 = new SecretRoom(hero, -5);
        Room secretRoom3 = new SecretRoom(hero, -6);

        monster3.getLstPorte().add(new RightDoor(secretRoom1.getId()));
        secretRoom1.getLstPorte().add(new LeftDoor(monster3.getId()));
        monster3.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);
        secretRoom1.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);

        monster6.getLstPorte().add(new LeftDoor(secretRoom2.getId()));
        secretRoom2.getLstPorte().add(new RightDoor(monster6.getId()));
        monster6.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);
        secretRoom2.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);

        monster9.getLstPorte().add(new TopDoor(secretRoom3.getId()));
        secretRoom3.getLstPorte().add(new BottomDoor(monster9.getId()));
        monster9.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);
        secretRoom3.getLstPorte().get(0).setImagePaths(ImagePaths.SECRET_ENTRY);

        // on ajoute les porte au differente salle
        spawn.getLstPorte().add(new TopDoor(monster1.getId()));
        monster1.getLstPorte().add(new BottomDoor(spawn.getId()));

        monster1.getLstPorte().add(new RightDoor(monster2.getId()));
        monster2.getLstPorte().add(new LeftDoor(monster1.getId()));

        monster2.getLstPorte().add(new BottomDoor(monster3.getId()));
        monster3.getLstPorte().add(new TopDoor(monster2.getId()));

        monster3.getLstPorte().add(new BottomDoor(monster4.getId()));
        monster4.getLstPorte().add(new TopDoor(monster3.getId()));

        monster4.getLstPorte().add(new LeftDoor(monster5.getId()));
        monster5.getLstPorte().add(new RightDoor(monster4.getId()));

        monster5.getLstPorte().add(new LeftDoor(commerce1.getId()));
        commerce1.getLstPorte().add(new RightDoor(monster5.getId()));

        commerce1.getLstPorte().add(new TopDoor(monster6.getId()));
        monster6.getLstPorte().add(new BottomDoor(commerce1.getId()));

        monster6.getLstPorte().add(new TopDoor(monster7.getId()));
        monster7.getLstPorte().add(new BottomDoor(monster6.getId()));

        monster7.getLstPorte().add(new TopDoor(monster8.getId()));
        monster8.getLstPorte().add(new BottomDoor(monster7.getId()));

        monster8.getLstPorte().add(new RightDoor(monster9.getId()));
        monster9.getLstPorte().add(new LeftDoor(monster8.getId()));

        monster9.getLstPorte().add(new RightDoor(commerce2.getId()));
        commerce2.getLstPorte().add(new LeftDoor(monster9.getId()));

        commerce2.getLstPorte().add(new RightDoor(boss.getId()));
        boss.getLstPorte().add(new LeftDoor(commerce2.getId()));

        // on ajoute a la map
        mapDeRoom.put(spawn.getId(), spawn);

        mapDeRoom.put(secretRoom1.getId(), secretRoom1);
        mapDeRoom.put(secretRoom2.getId(), secretRoom2);
        mapDeRoom.put(secretRoom3.getId(), secretRoom3);

        mapDeRoom.put(monster1.getId(), monster1);
        mapDeRoom.put(monster2.getId(), monster2);
        mapDeRoom.put(monster3.getId(), monster3);
        mapDeRoom.put(monster4.getId(), monster4);
        mapDeRoom.put(monster5.getId(), monster5);
        mapDeRoom.put(commerce1.getId(), commerce1);
        mapDeRoom.put(monster6.getId(), monster6);
        mapDeRoom.put(monster7.getId(), monster7);
        mapDeRoom.put(monster8.getId(), monster8);
        mapDeRoom.put(monster9.getId(), monster9);
        mapDeRoom.put(commerce2.getId(), commerce2);
        mapDeRoom.put(boss.getId(), boss);
    }

    /**
     * GETTER SETTER
     */

    public Map<Integer, Room> getMapDeRoom() {
        return this.mapDeRoom;
    }

    public void setMapDeRoom(Map<Integer, Room> mapDeRoom) {
        this.mapDeRoom = mapDeRoom;
    }

    public Hero getHero() {
        return this.hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

}
