package unsw.entity;

public class Exit extends Entity {

	/**
	 * Exit constructor
	 * @param x
	 * @param y
	 */
	public Exit(int x, int y) {
		super(x, y);
		super.setCollide(true);
	}
	
	
	/**
	 * Sets collide to false so player can enter
	 */
	public void canFinish() {
		super.setCollide(false);
	}
}
