package unsw.tests;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entity.Player;
import unsw.potion.HasNotDrunkPotionState;

public class HasNotDrunkPotionTest {
	
	
	@Test
	void hasNotDrunkFunction() {
		
		Dungeon dungeon = new Dungeon(0, 0);
		Player player = new Player(dungeon,1,1);
		player.setState(player.getHasNotDrunkPotionState());
		HasNotDrunkPotionState state = new HasNotDrunkPotionState(player);
		boolean posAnswer = state.drinkPotion();
		assert(posAnswer == true);
			
	}

}
