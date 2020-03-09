package unsw.tests;

import unsw.entity.*;
import unsw.dungeon.Dungeon;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void testPlayerMove() {
		Dungeon dungeon = new Dungeon(10, 10);
		Player player = new Player(dungeon, 1, 2);
		dungeon.addEntity(player);
		player.moveRight();
		assertEquals(2, player.getX());
		player.moveLeft();
		assertEquals(1, player.getX());
		player.moveUp();
		assertEquals(1, player.getY());
		player.moveDown();
		assertEquals(2, player.getY());
	}
	
	@Test
	void PlayerCollideEnemy() {
		Dungeon dungeon = new Dungeon(10, 10);
		Enemy enemy = new Enemy(dungeon, 2, 2);
		Player player = new Player(dungeon, 1, 2);
		dungeon.addEntity(player);
		dungeon.addEntity(enemy);
		player.moveRight();
		assertEquals(false, dungeon.getEntities().contains(player));
	}

}
