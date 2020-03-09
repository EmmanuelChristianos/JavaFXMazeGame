package unsw.player;

/**
 * Interface for the player-enemy-interact state pattern.
 * @author emmanuelchristianos
 *
 */
public interface PlayerEnemyInteract {
	
	/**
	 * Interact for the player-enemy-interact state pattern.
	 * Reacts Differently depending on what state the player
	 * is in.
	 * @author emmanuelchristianos
	 *
	 */
	public void interact(String Direction);
}
