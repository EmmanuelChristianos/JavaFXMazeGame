package unsw.entity;

public class FloorSwitch extends Entity {

	private boolean activated = false;
	
	public FloorSwitch(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}
	
	public boolean canActivate(int x, int y) {
		if (x == getX() && y == getY()) {
			return true;
		}
		
		return false;
	}

	/**
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
		
		if (this.activated) {
			System.out.println("Floor switch activated");
		} else {
			System.out.println("Floor switch not activated");
		}
	}
	
}
