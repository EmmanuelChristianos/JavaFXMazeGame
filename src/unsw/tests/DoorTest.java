package unsw.tests;

import unsw.entity.*;
import unsw.dungeon.Dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DoorTest {

	@Test
	void testRightKey() {
		Door door = new Door(2, 2, 0);
		Key key = new Key(1, 1, 0);
		
		assertEquals(false, door.isOpen());
		door.setOpen(key.getId());
		assertEquals(true, door.isOpen());
	}
	
	@Test
	void testWrongKey() {
		Door door = new Door(2, 2, 0);
		Key key = new Key(1, 1, 1);
		
		assertEquals(false, door.isOpen());
		door.setOpen(key.getId());
		assertEquals(false, door.isOpen());
	}
	
	@Test
	void test3doors() {
		Dungeon dungeon = new Dungeon(10, 10);
		Door door1 = new Door(1, 1, 0);
		Door door2 = new Door(2, 2, 1);
		Door door3 = new Door(3, 3, 2);
		dungeon.addEntity(door3);
		dungeon.addEntity(door2);
		dungeon.addEntity(door1);
		
		assertEquals(3, dungeon.getEntities().size());
	}

}
