package unsw.entity;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Treasure extends Entity implements Pickup {
	
	private BooleanProperty picked = new SimpleBooleanProperty(false);
	
	public Treasure(int x, int y) {
		super(x, y);
		super.setCanPickup(true);
	}

	/**
	 * @return the picked
	 */
	public boolean isPicked() {
		return picked.get();
	}

	/**
	 * @param picked the picked to set
	 */
	public void setPicked(boolean picked) {
		this.picked.set(picked);
	}
	
	public BooleanProperty pickedProperty() {
		return picked;
	}
	
	public void dissapear(Player player) {
		super.dissapear(player);
	}
}
