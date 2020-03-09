package unsw.tests;

import unsw.entity.*;
import unsw.dungeon.Dungeon;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FloorSwitchTest {

	@Test
	void testFloorSwitchActivate() {
		Dungeon dungeon = new Dungeon(10, 10);
		FloorSwitch floorSwitch = new FloorSwitch(2, 2);		
		dungeon.addEntity(floorSwitch);
		Boulder boulder = new Boulder(dungeon, 2, 2);
		dungeon.addEntity(boulder);
				
		assertEquals(true, floorSwitch.isActivated());
		boulder.moveRight();
		assertEquals(false, floorSwitch.isActivated());
		boulder.moveLeft();
		assertEquals(true, floorSwitch.isActivated());
	}
	
	@Test
	void testFloorSwitchOthers() {
		Dungeon dungeon = new Dungeon(10, 10);
		FloorSwitch floorSwitch = new FloorSwitch(2, 2);
		Player player = new Player(dungeon, 1, 2);
		dungeon.addEntity(floorSwitch);
		dungeon.addEntity(player);
		
		player.moveRight();
		assertEquals(false, floorSwitch.isActivated());
	}
}
