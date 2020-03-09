package unsw.tests;

import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entity.Enemy;
import unsw.entity.Entity;
import unsw.entity.Player;
import unsw.player.FightEnemyInteract;
import unsw.player.IgnoreEnemyInteract;

public class IgnoreEnemyInteractTest {

	@Test
	void ignoreEnemyInteractTest() {
		
		
		Dungeon d = new Dungeon(10,10);
		Player p = new Player(d,1,1);
		

		p.changeInteract(new IgnoreEnemyInteract(p));	
		Enemy enemy = new Enemy(d, 2, 2);
	
		p.moveDown();
		p.moveRight();
		
		List<Entity> list = d.getEntities();
		
		assert(list.contains(enemy));
		
	}
	
}
