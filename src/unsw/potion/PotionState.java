package unsw.potion;

/**
 * Potion State Interface for potion usage State Strategy
 * @author emmanuelchristianos
 *
 */
public interface PotionState {

	/**
	 * Drinks potions according to what state you are in.
	 * @return Boolean
	 */
	public boolean drinkPotion();
	
	
}
