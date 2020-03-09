package unsw.entity;

import unsw.dungeon.Dungeon;

public class Enemy extends Entity {

	private Dungeon dungeon;
	
	/**
	 * Enemy constructor
	 * @param dungeon
	 * @param x
	 * @param y
	 */
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.dungeon.addEntity(this);
	}
	
	/**
	 * Moves the enemy up
	 */
	public void moveUp() {
        if (getY() > 0) {
        	if (!findCollision(this.dungeon, getX(), getY(),"up")) {
        		y().set(getY() - 1);
        	}            
        }
    }

	/**
	 * Moves the enemy down
	 */
	public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {        	
        	if(!findCollision(this.dungeon,getX(),getY(),"down")) {
        		y().set(getY() + 1);
        	}
        }
    }

	/**
	 * Moves the enemy left
	 */
    public void moveLeft() {
    	if (getX() > 0) {        	
        	if(!findCollision(this.dungeon,getX(),getY(),"left")) {
        		x().set(getX() - 1);
        	}
        }
    }

    /**
	 * Moves the enemy right
	 */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {        	
        	if(!findCollision(this.dungeon,getX(),getY(),"right")) {        		            	
        		x().set(getX() + 1);
        	}
        }
    }

    /**
	 * Returns if there would be a collision if the enemy is moved in given direction.
	 * @param d - dungeon
	 * @param x - x pos
	 * @param y - y pos
	 * @param direction - Direction of movement
	 * @return If the enemy has a collision
	 */
	public boolean findCollision(Dungeon d, int x, int y, String direction){
		
	    boolean collision = false;
		
		for(Entity en: dungeon.getEntities()) {
			if (en != null) {
				if(en.isCollide()) {
					
					switch(direction) {
					
					case "up":
						
						if(en.getX() == x && en.getY() == y-1) {
							collision = true;
		    			}
						break;
						
					case "down":
						
						if(en.getX() == x && en.getY() == y+1) {
							collision = true;
		    			}
						break;
					case "left":
						
						if(en.getX() == x-1 && en.getY() == y) {
							collision = true;
		    			}
						break;
					case "right":
						
						if(en.getX() == x+1 && en.getY() == y) {
							collision = true;
		    			}
						break;
					default:
						System.out.println("Enemy is moving in invalid direction\n");
						break;
					}
	    		} 
			}
		}
		
		return collision;
	}
	
}
