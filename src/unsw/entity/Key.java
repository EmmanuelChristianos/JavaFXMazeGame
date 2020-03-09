package unsw.entity;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Key extends Entity implements Pickup {
	
	private int id;
	private BooleanProperty bool;
	
	/**
	 * Key constructor
	 * @param x
	 * @param y
	 * @param id
	 */
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
		super.setCanPickup(true);
		this.bool = new SimpleBooleanProperty();
	}

	/**
	 * @return id of key
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Removes key from dungeon
	 */
	public void dissapear(Player player) {
		
		super.dissapear(player);
		System.out.println("id is: " + this.id);
	}

	/**
	 * @return booleanProperty
	 */
	public BooleanProperty booleanProperty() {
		return bool;
	}
	
	/**
	 * @param bool
	 */
	public void setBoolean(Boolean bool) {
		this.bool.set(bool);
	}
}
