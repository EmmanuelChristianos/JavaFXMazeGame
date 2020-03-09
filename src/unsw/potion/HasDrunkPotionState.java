package unsw.potion;

import unsw.entity.Entity;
import unsw.entity.InvincibilityPotion;
import unsw.entity.Player;
import unsw.player.FightEnemyInteract;
import unsw.player.IgnoreEnemyInteract;

/**
 * The hasDrunkPotion State for the potion state pattern
 * @author emmanuelchristianos
 *
 */

public class HasDrunkPotionState implements PotionState{

	Player player;
	
	public HasDrunkPotionState(Player player) {
		super();
		this.player = player;
	}

	/**
	 * Changes the Player-Enemy-Interact state depending if the player
	 * Has picked up a sword or not.
	 * Then changes the players potion state to ensure it is still hasDrunk
	 * Potion and tops up 20 points for the potions they are currently using.
	 * @return Boolean
	 */
	@Override
	public boolean drinkPotion() {
		
		//System.out.println("Topped Up!!");
		
		if(player.getInteractStatus() instanceof FightEnemyInteract) {
		} else {
			
			player.changeInteract(new IgnoreEnemyInteract(player));
			
		}
		
		player.setState(player.getHasDrunkPotionState());
		
		for(Entity en: player.getInventory().getInventory()) {
			
			if(en instanceof InvincibilityPotion) {
				((InvincibilityPotion) en).setTimer(((InvincibilityPotion) en).getTimer()+10);
			}
		}
		
		return false;
	}
}
