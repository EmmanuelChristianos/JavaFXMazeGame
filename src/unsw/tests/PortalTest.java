package unsw.tests;

import unsw.entity.*;
import unsw.dungeon.Dungeon;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PortalTest {

	@Test
	void testMoveThroughPortal() {
		Dungeon dungeon = new Dungeon(10, 10);
		Portal portal1 = new Portal(dungeon, 2, 2, 0);
		Portal portal2 = new Portal(dungeon, 5, 5, 0);
		Player player = new Player(dungeon, 1, 2);
		dungeon.addEntity(player);
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		player.moveRight();
		assertEquals(5, player.getX());
		assertEquals(5, player.getY());
		player.moveDown();
		player.moveUp();
		assertEquals(2, player.getX());
		assertEquals(2, player.getY());
	}
	
	@Test
	void testEnemy() {
		Dungeon dungeon = new Dungeon(10, 10);
		Portal portal1 = new Portal(dungeon, 2, 2, 0);
		Portal portal2 = new Portal(dungeon, 5, 5, 0);
		Enemy enemy = new Enemy(dungeon, 1, 2);
		dungeon.addEntity(enemy);
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		enemy.moveRight();
		assertEquals(1, enemy.getX());
		assertEquals(2, enemy.getY());
	}
	
	@Test
	void testBoulder() {
		Dungeon dungeon = new Dungeon(10, 10);
		Portal portal1 = new Portal(dungeon, 2, 2, 0);
		Portal portal2 = new Portal(dungeon, 5, 5, 0);
		Boulder boulder = new Boulder(dungeon, 1, 2);
		dungeon.addEntity(boulder);
		dungeon.addEntity(portal1);
		dungeon.addEntity(portal2);
		
		boulder.moveRight();
		assertEquals(1, boulder.getX());
		assertEquals(2, boulder.getY());
	}

}
