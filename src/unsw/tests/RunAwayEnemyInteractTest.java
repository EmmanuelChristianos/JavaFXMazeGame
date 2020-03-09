package unsw.tests;

import java.util.List;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entity.Enemy;
import unsw.entity.Entity;
import unsw.entity.Player;
import unsw.player.FightEnemyInteract;
import unsw.player.RunAwayEnemyInteract;

public class RunAwayEnemyInteractTest {

	@Test
	void runAwayEnemyInteractTest() {
		
		
		Dungeon d = new Dungeon(10,10);
		Player p = new Player(d,1,1);
		

		p.changeInteract(new RunAwayEnemyInteract(p));	
		Enemy enemy = new Enemy(d, 3, 3);
	
		p.moveDown();
		p.moveRight();
		
		List<Entity> list = d.getEntities();
		
		assert(list.contains(p));
		
	}
	
}
