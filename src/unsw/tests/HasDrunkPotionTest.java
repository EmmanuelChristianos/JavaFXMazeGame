package unsw.tests;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.entity.Player;
import unsw.potion.HasDrunkPotionState;

public class HasDrunkPotionTest {
	
	
	@Test
	void hasDrunkFunction() {
		
		Dungeon dungeon = new Dungeon(0, 0);
		Player player = new Player(dungeon,1,1);
		player.setState(player.getHasDrunkPotionState());
		HasDrunkPotionState state = new HasDrunkPotionState(player);
		boolean posAnswer = state.drinkPotion();
		assert(posAnswer == false);
			
	}

}
