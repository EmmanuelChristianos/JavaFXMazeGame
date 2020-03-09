package unsw.Inventory;

import java.util.ArrayList;
import java.util.List;
import unsw.entity.Entity;
import unsw.entity.InvincibilityPotion;
import unsw.entity.Key;
import unsw.entity.Sword;
import unsw.entity.Treasure;

/**
 * Inventory for a player, holds items they pick up from the map
 * @author emmanuelchristianos, Adam Rabiya
 *
 */
public class Inventory {
	
	private List<Entity> inventory;
	
	public Inventory() {
		inventory = new ArrayList<>();
	}
	
	public List<Entity> getInventory() {
		return inventory;
	}
	/**
	 * Adds another entity to the inventory
	 * @param entity
	 */
	public void addEntity(Entity entity) {
		if(hasEntity(entity) == null) {			
			inventory.add(entity);	
			if (entity instanceof Key) {
				((Key) entity).setBoolean(true);
			} else if (entity instanceof InvincibilityPotion) {
				((InvincibilityPotion) entity).setBoolean(true);
			} else if (entity instanceof Sword) {
				((Sword) entity).setBoolean(true);
			} else if (entity instanceof Treasure) {
				inventory.add(entity);	
				((Treasure) entity).setPicked(true);
			}

		} else {
			if (entity instanceof Treasure) {
				inventory.add(entity);	
				((Treasure) entity).setPicked(true);
			}
		}
    }
	
	public void removeEntity(Entity entity) {
		inventory.remove(entity);		
	}
	
	/**
	 * Checks if an item is already in our inventory.
	 * @param entity
	 * @return
	 */
	public Entity hasEntity(Entity entity) {
		
		if(entity instanceof Key) {
			return null;	
		}
		for (Entity en: inventory) {			
			if (en.getClass().equals(entity.getClass())) {
				return en;
			}
		}
		
		return null;
	}
}
