package unsw.entity;

import unsw.dungeon.Dungeon;
import unsw.entity.FloorSwitch;

public class Boulder extends Entity {
	
	private Dungeon dungeon;

	/**
	 * Boulder constructor
	 * @param dungeon - current dungeon
	 * @param x - x pos
	 * @param y - y pos
	 */
	public Boulder(Dungeon dungeon, int x, int y) {
		super(x, y);
		super.setCollide(true);
		this.dungeon = dungeon;
		onSwitch(x, y, -1, -1);
	}
	
	/**
	 * Checks if the boulder is on a floor switch.
	 * Then sets activation of floor switch.
	 * @param newX - current x pos
	 * @param newY - current y pos
	 * @param oldX - prev x pos
	 * @param oldY - prev y pos
	 */
	public void onSwitch(int newX, int newY, int oldX, int oldY) {
		for(Entity entity: this.dungeon.getEntities()) {
			if (entity != null) {
				if (entity instanceof FloorSwitch) {					
					if (((FloorSwitch) entity).canActivate(oldX, oldY)) {
						((FloorSwitch) entity).setActivated(false);
						dungeon.checkGoals();
					} else if (((FloorSwitch) entity).canActivate(newX, newY)) {
						((FloorSwitch) entity).setActivated(true);
						dungeon.checkGoals();
					}
				}
			}
		}
	}
	
	/**
	 * Checks then moves the boulder up if able to do so.
	 * @return If the boulder is able to moveUp
	 */
	public boolean moveUp() {
        if (getY() > 0) {
        	if (!findCollision(this.dungeon, getX(), getY(),"up")) {
        		onSwitch(getX(), getY()-1, getX(), getY());
        		y().set(getY() - 1);
        		return true;
        	}            
        }
        return false;
    }

	/**
	 * Checks then moves the boulder down if able to do so.
	 * @return If the boulder is able to moveDown
	 */
	public boolean moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
        	if(!findCollision(this.dungeon,getX(),getY(),"down")) {
        		onSwitch(getX(), getY()+1, getX(), getY());
        		y().set(getY() + 1);
        		return true;
        	}
        }
        return false;
    }

	/**
	 * Checks then moves the boulder left if able to do so.
	 * @return If the boulder is able to moveLeft
	 */
    public boolean moveLeft() {
    	if (getX() > 0) {        	
        	if(!findCollision(this.dungeon,getX(),getY(),"left")) {
        		onSwitch(getX()-1, getY(), getX(), getY());
        		x().set(getX() - 1);
        		return true;
        	}
        }
    	return false;
    }

    /**
	 * Checks then moves the boulder right if able to do so.
	 * @return If the boulder is able to moveRight
	 */
    public boolean moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
        	
        	if(!findCollision(this.dungeon,getX(),getY(),"right")) {
        		onSwitch(getX()+1, getY(), getX(), getY());
        		x().set(getX() + 1);
        		return true;
        	}
        }
        return false;
    }
    
    /**
     * Moves the boulder in the direction given and returns boolean if boulder was able to move
     * @param en - entity
     * @param direction - direction to move boulder
     * @return If the boulder was able to move
     */
    public boolean moveBoulderInDirection(Entity en, String direction) {
    	
    	boolean coll = false;
    	
    	switch(direction) {

		case "left":
			coll = ((Boulder) en).moveLeft();
			break;
		case "right":
			coll = ((Boulder) en).moveRight();
			break;
		case "up":
			coll = ((Boulder) en).moveUp();
			break;
		case "down":
			coll = ((Boulder) en).moveDown();
			break;
		default:
			break;
		}
    	return coll;
    }
    

	/**
	 * Returns if there would be a collision if the boulder is moved in given direction.
	 * @param d - dungeon
	 * @param x - x pos
	 * @param y - y pos
	 * @param direction - Direction of movement
	 * @return If the boulder has a collision
	 */
	public boolean findCollision(Dungeon d, int x, int y, String direction){
		
	    boolean collision = false;
		
		for(Entity en: dungeon.getEntities()) {
			if (en != null) {
				if(en.isCollide() || en instanceof Enemy) {
					
					switch(direction) {
					
					case "up":
						
						if(en.getX() == x && en.getY() == y-1) {
							collision = instanceofEnemyOrCollision(en);
		    			}
						break;
						
					case "down":
						
						if(en.getX() == x && en.getY() == y+1) {
							collision = instanceofEnemyOrCollision(en);
		    			}
						break;
					case "left":
						
						if(en.getX() == x-1 && en.getY() == y) {
							collision = instanceofEnemyOrCollision(en);
		    			}
						break;
					case "right":
						
						if(en.getX() == x+1 && en.getY() == y) {
							collision = instanceofEnemyOrCollision(en);
		    			}
						break;
					default:
						System.out.println("Invalid direction to move boulder\n");
						break;
					}
	    		} 
			}
		}
		
		return collision;
	}
	
	/**
	 * Checks if the given entity would collide with boulder
	 * @param en
	 * @return Always returns true
	 */
	public boolean instanceofEnemyOrCollision(Entity en) {
		if (en instanceof Enemy) {
			return true;
		} else {
			return true;
		}
	}
}
