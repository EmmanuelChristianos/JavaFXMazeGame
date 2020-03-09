package unsw.entity;



public class Door extends Entity {
	
	private boolean isOpen = false;
	private int id;
	
	/**
	 * Door constructor
	 * @param x
	 * @param y
	 * @param id
	 */
	public Door(int x, int y, int id) {
		super(x, y);
		super.setCollide(true);
		this.id = id;
	}

	/**
	 * @return the isOpen
	 */
	public boolean isOpen() {
		return isOpen;
	}
	
	public int getId() {
		
		return this.id;
	}

	/**
	 * @param isOpen the isOpen to set
	 */
	public void setOpen(int key) {
		if (id == key) {
			openDoor();
			System.out.println("Door is opened");
		} else {
			System.out.println("Door is closed");
		}
	}
	
	/**
	 * Changes the door to open
	 */
	public void openDoor() {
		isOpen = true;
		super.setCollide(false);		
	}
}
