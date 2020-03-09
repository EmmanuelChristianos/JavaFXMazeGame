package unsw.entity;

import java.util.List;

/**
 * Portal Gun Fuel
 * @author emmanuelchristianos
 *
 */
public class Fuel extends Entity implements Pickup{

	private Player player;
	
	public Fuel(int x, int y) {
		super(x, y);
		super.setCanPickup(true);

		
	}
	/**
	 * Removes fuel from the map and if the player already has
	 * a portal gun then they top up their fuel if not, then
	 * they hold the fuel in their inventory.
	 * @param player (player picking up the fuel)
	 */
	public void dissapear(Player player) {
		// TODO Auto-generated method stub
		this.player = player;
		super.dissapear(this.player);
		
		List<Entity> items = player.getInventory().getInventory();
		
		for(int i = 0; i < items.size(); i++) {
			
			if(items.get(i) instanceof Portalgun) {
				
				((Portalgun) items.get(i)).setUses(((Portalgun) items.get(i)).getUses().intValue()+1);
				items.remove(this);
			}
		}
	}
}
