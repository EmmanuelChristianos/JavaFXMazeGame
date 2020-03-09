package unsw.tests;

import unsw.entity.*;
import unsw.dungeon.Dungeon;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BoulderTest {
	
	@Test
	void testCollideBoulder() {
		Dungeon dungeon = new Dungeon(10, 10);
		Boulder boulder = new Boulder(dungeon, 2, 2);
		Boulder dummy = new Boulder(dungeon, 3, 2);
		dungeon.addEntity(dummy);
		dungeon.addEntity(boulder);
		
		assertEquals(true, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
		assertEquals(false, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "left"));
		assertEquals(false, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "up"));
		assertEquals(false, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "down"));
	}
	
	@Test
	void testCollidePlayer() {
		Dungeon dungeon = new Dungeon(10, 10);
		Boulder b = new Boulder(dungeon, 2, 2);
		Player c = new Player(dungeon, 1, 2);
		dungeon.addEntity(b);
		dungeon.addEntity(c);
		
		assertEquals(false, c.findCollision(dungeon, c.getX(), c.getY(), "right"));
		
		Boulder dummy = new Boulder(dungeon, 3, 2);
		dungeon.addEntity(dummy);
		assertEquals(true, c.findCollision(dungeon, c.getX(), c.getY(), "right"));
	}
	
	@Test
	void testCollideEnemy() {
		Dungeon dungeon = new Dungeon(10, 10);
		Boulder boulder = new Boulder(dungeon, 2, 2);
		Enemy enemy = new Enemy(dungeon, 3, 2);
		dungeon.addEntity(enemy);
		dungeon.addEntity(boulder);
		
		assertEquals(true, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
	}
	
	@Test
	void testCollideDoor() {
		Dungeon dungeon = new Dungeon(10, 10);
		Boulder boulder = new Boulder(dungeon, 2, 2);
		Door door = new Door(3, 2, 0);
		dungeon.addEntity(door);
		dungeon.addEntity(boulder);
		
		assertEquals(true, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
		
		door.setOpen(0);
		
		assertEquals(false, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
	}
	
	@Test
	void testCollidePortal() {
		Dungeon dungeon = new Dungeon(10, 10);
		Boulder boulder = new Boulder(dungeon, 2, 2);
		Portal portal = new Portal(dungeon, 3, 2, 0);
		dungeon.addEntity(portal);
		dungeon.addEntity(boulder);
		
		assertEquals(true, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
	}
	
	@Test
	void testCollideItems() {
		Dungeon dungeon = new Dungeon(10, 10);
		Boulder boulder = new Boulder(dungeon, 2, 2);
		Sword sword = new Sword(3, 2,dungeon);
		InvincibilityPotion pot = new InvincibilityPotion(4, 2);
		Treasure treasure = new Treasure(5, 2);
		dungeon.addEntity(sword);
		dungeon.addEntity(boulder);
		dungeon.addEntity(pot);
		dungeon.addEntity(treasure);
		
		assertEquals(false, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
		boulder.moveRight();
		assertEquals(false, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
		boulder.moveRight();
		assertEquals(false, boulder.findCollision(dungeon, boulder.getX(), boulder.getY(), "right"));
	}
	
	@Test
	void testMove() {
		Dungeon dungeon = new Dungeon(10, 10);
		Boulder boulder = new Boulder(dungeon, 2, 2);
		dungeon.addEntity(boulder);
		
		boulder.moveDown();
		assertEquals(3, boulder.getY());
		boulder.moveLeft();
		assertEquals(1, boulder.getX());
		boulder.moveRight();
		assertEquals(2, boulder.getX());
		boulder.moveUp();
		assertEquals(2, boulder.getY());
	}
}
