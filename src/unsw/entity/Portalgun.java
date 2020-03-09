package unsw.entity;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;
import unsw.player.FightEnemyInteract;

/**
 * Portal Gun to jump through walls. 
 * @author emmanuelchristianos
 *
 */
public class Portalgun extends Entity implements Pickup {

	private IntegerProperty uses = new SimpleIntegerProperty(1);
	private Player player;
	
	public Portalgun(int x, int y) {
		super(x, y);
		super.setCanPickup(true);
	
	}
	
	/**
	 * Depedning on where they player is tryng to walk when they
	 * have a loaded portal gun in their hands. Then they will
	 * either be able or not be able to jump through one single
	 * block of wall, that doesnt land them outside the map and
	 * doesnt land them inside a wall. 
	 * @param direction
	 */
	public void usePortalGun(String direction) {
		
		if(getUses().intValue() >= 1) {
			
			
			int playerX = player.getX();
			int playerY = player.getY();
			int DestX = 0;
			int DestY = 0;
			int finX = 0;
			int finY = 0;
			
			switch(direction) {
			
			case "left":
				DestX--;
				finX-=2;
				break;
			case "right":
				DestX++;
				finX+=2;
				break;
			case "up":
				DestY--;
				finY-=2;
				break;
			case "down":
				DestY++;
				finY+=2;
				break;
			}
			
			DestX += playerX;
			DestY += playerY;
			
			finX += playerX;
			finY += playerY;
			
			//System.out.println("x: " + finX + " Y: "+ finY + " ---" + this.uses.intValue());
			
			List<Entity> entities = player.getDungeon().getEntities();

			for(int i = 0; i<entities.size(); i++) {
				
				if (entities.get(i) instanceof Wall) {
					
					int enX = entities.get(i).getX(); 
					int enY = entities.get(i).getY();

					
					if (enX == DestX && enY == DestY) {
						
						if(DestX > 1 && DestX < player.getDungeon().getWidth()-2) {

							if(DestY > 1 && DestY < player.getDungeon().getHeight()-2) {

								if(finX >= 1 && finX <= player.getDungeon().getWidth()-2) {
									
									if(finY >= 1 && finY <= player.getDungeon().getHeight()-2) {
										
										boolean found = false;
										
										for (int k = 0; k < entities.size(); k++) {
											
											if(entities.get(k).getX() == finX && entities.get(k).getY() == finY) {
												
												found = true;
											}
										}
										
										if(!found) {
											
											setUses(getUses().intValue()-1);
											player.x().set(finX);
											player.y().set(finY);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
		
	public IntegerProperty getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses.set(uses);
	}
	/**
	 * Removes the portal gun from the map and removes
	 * the fuel in the inventory if there is some. 
	 */
	public void dissapear(Player player) {
		
		this.player = player;
		super.dissapear(this.player);
		
		List<Entity> items = player.getInventory().getInventory();
		
		for(int i = 0; i < items.size(); i++) {
			
			if(items.get(i) instanceof Fuel) {
				
				for(int k= 0; k < items.size(); k++) {
					
					if(items.get(k) instanceof Portalgun) {
						
						((Portalgun) items.get(k)).setUses(((Portalgun) items.get(k)).getUses().intValue()+1);
						items.remove(i);
					}
				}
			}
		}
	}
}
