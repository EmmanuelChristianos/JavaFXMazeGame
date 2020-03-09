package unsw.tests;

import unsw.entity.*;
import unsw.dungeon.Dungeon;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExitTest {

	@Test
	void testPlayer() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 1, 2);
		Exit exit = new Exit(2, 2);
		dungeon.addEntity(exit);
		dungeon.addEntity(player);
		
		player.moveRight();
		assertEquals(1, player.getX());
		exit.canFinish();
		player.moveRight();
		assertEquals(2, player.getX());
	}

}
