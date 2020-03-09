package unsw.potion;

import unsw.entity.Player;
import unsw.player.FightEnemyInteract;
import unsw.player.IgnoreEnemyInteract;

/**
 * The hasNotDrunkPotion State for the potion state pattern
 * @author emmanuelchristianos
 *
 */
public class HasNotDrunkPotionState implements PotionState {

	Player player;
	
	public HasNotDrunkPotionState(Player player) {
		super();
		this.player = player;
	}

	
	/**
	 * Changes the Player-Enemy-Interact state depending if the player
	 * Has picked up a sword or not.
	 * @return Boolean
	 */
	@Override
	public boolean drinkPotion() {
		// TODO Make so the player drinks the potion
		System.out.println("Player drunk potion");
		player.setState(player.getHasDrunkPotionState());
		
		if(player.getInteractStatus() instanceof FightEnemyInteract) {
		} else {
			
			player.changeInteract(new IgnoreEnemyInteract(player));
			
		}
		return true;
	}
}
