package unsw.player;

import java.util.List;

import unsw.entity.Entity;
import unsw.entity.Player;

/**
 * RunAwayState for the player-enemy-interact state pattern.
 * @author emmanuelchristianos
 *
 */
public class RunAwayEnemyInteract implements PlayerEnemyInteract{

	Player player;

	public RunAwayEnemyInteract(Player player) {
		super();
		this.player = player;
	}

	/**
	 * Removes player from the board because they were killed by an enemy
	 * Dont allow the player to move anymore, game is over.
	 * @param destination - (direction of movement the player is intending to go)
	 */
	@Override
	public void interact(String destination) {
		
		List<Entity> entities = player.getDungeon().getEntities();
		for(int i =0; i< entities.size(); i++) {
			
			if(entities.get(i) instanceof Player) {
				System.out.println("Called for direction: " + destination);
				player.getDungeon().getEntities().remove(entities.get(i));
				entities.get(i).dissapear(this.player);
				player.setCanMove(false);

			}
		}
	}
}
