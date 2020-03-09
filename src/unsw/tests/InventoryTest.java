package unsw.tests;

import unsw.entity.*;
import unsw.dungeon.Dungeon;
import unsw.Inventory.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InventoryTest {

	@Test
	void testPickup() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 1, 2);
		Key key = new Key(4, 4, 0);
		player.getInventory().addEntity(key);
		
		assertEquals(true, player.getInventory().hasEntity(key));
	}

}
