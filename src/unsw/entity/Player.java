package unsw.entity;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import unsw.Inventory.Inventory;
import unsw.dungeon.Dungeon;
import unsw.potion.*;
import unsw.player.*;

/**
 * The player entity
 * @author Robert Clifton-Everest, Emmanuel Christianos, Adam Rabiya
 *
 */
public class Player extends Entity {

	private Dungeon dungeon;

	PotionState hasDrunkPotionState;
	PotionState hasNotDrunkPotionState;
	PotionState state;

	PlayerEnemyInteract runAwayEnemyInteract;
	PlayerEnemyInteract ignoreEnemyInteract;
	PlayerEnemyInteract fightEnemyInteract;
	PlayerEnemyInteract interact;

	private Inventory inventory;
	private BooleanProperty playerCanMove = new SimpleBooleanProperty(true);

	/**
	 * Create a player positioned in square (x,y)
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.dungeon.addEntity(this);
		this.inventory = new Inventory();
		
		this.hasNotDrunkPotionState = new HasNotDrunkPotionState(this);
		this.hasDrunkPotionState = new HasDrunkPotionState(this);
		this.state = this.hasNotDrunkPotionState;
		
		this.runAwayEnemyInteract = new RunAwayEnemyInteract(this);
		this.ignoreEnemyInteract = new IgnoreEnemyInteract(this);
		this.fightEnemyInteract = new FightEnemyInteract(this);
		this.interact = this.runAwayEnemyInteract;
	}
	

	//===== Potion State Getters and Setters and Related Functions =====

	public void setCanMove(boolean value) {
		this.playerCanMove.set(value);
	}

	
	public PotionState getPotionState() {
		
		return state;
	}

	public PotionState getHasDrunkPotionState() {
		return hasDrunkPotionState;
	}

	public PotionState getHasNotDrunkPotionState() {
		return hasNotDrunkPotionState;
	}

	public void setState(PotionState state) {

		this.state = state;
	}
	/**
	 * Drinks potion depending on state.
	 * @return
	 */
	public boolean drinkPotion() {
		
		if(state != null) {
			
			return state.drinkPotion();
			
		} else {
			
			return false;
		}
	}
	/**
	 * Ticks potions down by one value. 
	 */
	public void tickPotion() {
		
		List<Entity> items = this.getInventory().getInventory();
		
		if(items.size() > 0) {
			
			for(int i = 0; i <items.size(); i++) {
				
				if(items.get(i) instanceof InvincibilityPotion) {
					
					((InvincibilityPotion) items.get(i)).tickTimer();	
				}
			}
		}
	}
	
	//===== Player-Enemy-Interact Getters and Setters and Related Functions =====
	
	public PlayerEnemyInteract getRunAwayEnemyInteract() {
		return runAwayEnemyInteract;
	}

	public PlayerEnemyInteract getIgnoreEnemyInteract() {
		return ignoreEnemyInteract;
	}

	public PlayerEnemyInteract getFightEnemyInteract() {
		return fightEnemyInteract;
	}

	public void changeInteract(PlayerEnemyInteract interact) {

		this.interact = interact;
	}

	public PlayerEnemyInteract getInteractStatus() {

		return this.interact;
	}
	
	// ===== Dungeon Getter =====
	
	public Dungeon getDungeon() {

		return this.dungeon;
	}

	// ===== inventory Related Functions ===== 
	
	/**
	 * Find item in invnetory.
	 */
	public void findItem() {
		for (Entity en: dungeon.getEntities()) {
			if (en.getX() == this.getY() && en.getY() == this.getY()) {
				if (en.canPickup() && inventory.hasEntity(en) == null) {
					this.inventory.addEntity(en);
				}
			}
		}
	}

	public Inventory getInventory() {
		return this.inventory;
	}
	
	//===== Player Movement Functions =====
	

	public BooleanProperty booleanProperty() {
		return playerCanMove;
	}
	/**
	 * Moves player up the board on position if it is legal
	 */
	public void moveUp() {
		if (getY() > 1 && playerCanMove.get()) {
			if (!findCollision(this.dungeon, getX(), getY(),"up")) {
				y().set(getY() - 1);
				tickPotion();
			}            
		}
	}
	/**
	 * Moves player Down the board on position if it is legal
	 */
	public void moveDown() {
		if (getY() < dungeon.getHeight() - 2 && playerCanMove.get()) {

			if(!findCollision(this.dungeon,getX(),getY(),"down")) {
				y().set(getY() + 1);
				tickPotion();
			}
		}
	}
	/**
	 * Moves player Left the board on position if it is legal
	 */
	public void moveLeft() {
		if (getX() > 1 && playerCanMove.get()) {        	
			if(!findCollision(this.dungeon,getX(),getY(),"left")) {
				x().set(getX() - 1);
				tickPotion();
			}
		}
	}
	/**
	 * Moves player Right
	 *  the board on position if it is legal
	 */
	public void moveRight() {
		if (getX() < dungeon.getWidth() - 2 && playerCanMove.get()) {

			if(!findCollision(this.dungeon,getX(),getY(),"right")) {        		            
				x().set(getX() + 1);
				tickPotion();
			}
		}
	}
/**
 * Finds collsionms
 * @param d
 * @param x
 * @param y
 * @param direction
 * @return
 */
	public boolean findCollision(Dungeon d, int x, int y, String direction){

		boolean collision = false;

		List<Entity> entities = dungeon.getEntities();
		
		for(int i = 0; i < entities.size(); i++) {
			
			if (entities.get(i) != null) {
			
				switch(direction) {

				case "up":

					if(entities.get(i).getX() == x && entities.get(i).getY() == y-1) {
						collision = instanceofEnemyOrCollision(entities.get(i), "up");
					} else if (((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))) != null) {
						
						((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))).usePortalGun("up");
					}
					
					break;

				case "down":

					if(entities.get(i).getX() == x && entities.get(i).getY() == y+1) {
						collision = instanceofEnemyOrCollision(entities.get(i), "down");
					}else if (((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))) != null) {
						
						((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))).usePortalGun("down");
					}
					break;
				case "left":

					if(entities.get(i).getX() == x-1 && entities.get(i).getY() == y) {
						collision = instanceofEnemyOrCollision(entities.get(i), "left");
					}else if (((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))) != null) {
						
						((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))).usePortalGun("left");
					}
					break;
				case "right":

					if(entities.get(i).getX() == x+1 && entities.get(i).getY() == y) {
						collision = instanceofEnemyOrCollision(entities.get(i), "right");
						//System.out.println("Move = " + !collision);
					}else if (((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))) != null) {
						
						((Portalgun) this.getInventory().hasEntity(new Portalgun(0,0))).usePortalGun("right");
					}
					break;
				default:
					//System.out.println("You have entered in a invalid direction\n");
					break;
				}
			}
		}

		return collision;
	}
	
/**
 * Determines if anemy or collisons
 * @param en
 * @param direction
 * @return
 */
	public boolean instanceofEnemyOrCollision(Entity en, String direction) {
		
		boolean collided = en.isCollide();
		System.out.println("I am called");
		
		if(en instanceof Enemy) {
			
			interact.interact(direction);
			
		} else if (en instanceof Boulder) {
			
			//Push the boulder in the direction your are walking.
			if(!((Boulder) en).moveBoulderInDirection(en, direction)) {
				//System.out.println("Boulder has not been moved");
			} else {
				//System.out.println("Boulder has been moved");
				collided = false;
			}

		} else if (en instanceof Portal) {
			
			x().set(((Portal) en).newX());
			y().set(((Portal) en).newY());
			collided = true;
			
		} else if (en instanceof Door){
			System.out.println("It is a door");
			int id = ((Door) en).getId();
			System.out.println("The door id is: " + id);
			
			List<Entity> items = this.getInventory().getInventory();
			
			for(int i = 0; i <items.size(); i++) {
				
				if(items.get(i) instanceof Key) {
					
					if(((Key) items.get(i)).getId() == id) {
						
						((Door) en).openDoor();
					}
				}
			}
			
			
		} else if (en.canPickup()){
			int old = inventory.getInventory().size();
			this.inventory.addEntity(en);
			
			if (old < inventory.getInventory().size() || en instanceof Pickup) {
				dungeon.checkGoals();
				en.dissapear(this);
			}
			
			collided = true;
		} else if (en instanceof Exit) {
			if (!en.isCollide()) {
				this.playerCanMove.set(false);
			}
			
			collided = true;
		}

		return collided;
	}
}