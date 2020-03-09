package unsw.player;

import java.util.List;
import unsw.entity.Enemy;
import unsw.entity.Entity;
import unsw.entity.Player;

/**
 * IgnoreEnemyState for the player-enemy-interact state pattern.
 * @author emmanuelchristianos
 *
 */
public class IgnoreEnemyInteract implements PlayerEnemyInteract{

	Player player;

	public IgnoreEnemyInteract(Player player) {
		super();
		this.player = player;
	}

	/**
	 * Removes Enemy from the board because they were killed by the player
	 * @param destination - (direction of movement the player is intending to go)
	 */
	@Override
	public void interact(String direction) {

		int playerX = 0;
		int playerY = 0;
		int DestX = 0;
		int DestY = 0;
		
		for(Entity en: player.getDungeon().getEntities()) {
			
			if(en instanceof Player) {
				
				playerX = en.getX();
				playerY = en.getY();
				break;
			}
		}
		
		switch(direction) {
		
		case "left":
			DestX--;
			break;
		case "right":
			DestX++;
			break;
		case "up":
			DestY--;
			break;
		case "down":
			DestY++;
			break;
		}
		
		DestX += playerX;
		DestY += playerY;
		
		List<Entity> entities = player.getDungeon().getEntities();

		for(int i = 0; i<entities.size(); i++) {
			
			if (entities.get(i) instanceof Enemy) {
				
				int enX = entities.get(i).getX(); 
				int enY = entities.get(i).getY();

				if (enX == DestX && enY == DestY) {
					
					player.getDungeon().getEntities().remove(entities.get(i));
					entities.get(i).dissapear(this.player);
					player.getDungeon().checkGoals();
				}
			}
		}		
	}
}
