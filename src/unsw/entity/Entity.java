package unsw.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
	private Dungeon dungeon;
    private IntegerProperty x, y;
    private boolean collide = false;
    private boolean canPickup = false;
    private IntegerProperty visible;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */ 
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.visible = new SimpleIntegerProperty(1);
    }

    public IntegerProperty visible() {
    	return visible;
    }

    public int getVisible() {
        return visible().get();
    }

    public boolean isCollide() {    	
		return collide;
	}

	public void setCollide(boolean collide) {
		this.collide = collide;
	}

	public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public Dungeon getDungeon() {
    	
    	return dungeon;
    }
    
    public void setDungeon(Dungeon dungeon) {
    	this.dungeon = dungeon;
    }

	/**
	 * @return the canPickup
	 */
	public boolean canPickup() {
		System.out.println("Called");
		return this.canPickup;
	}

	/**
	 * @param canPickup the canPickup to set
	 */
	public void setCanPickup(boolean canPickup) {
		this.canPickup = canPickup;
	}
	
	public void removeEntity() {
		
		this.dungeon.getEntities().remove(this);
		
	}

	public void dissapear(Player player) {
		// TODO Auto-generated method stub
		this.visible().set(0);
		this.x().set(0);
		this.y().set(0);
	}
}
