package unsw.entity;

import unsw.dungeon.Dungeon;

public class Portal extends Entity {
	
	private Dungeon dungeon;
	private int id;
	
	/**
	 * Portal constructor
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param id
	 */
	public Portal(Dungeon dungeon, int x, int y, int id) {
		super(x, y);
		super.setCollide(true);
		this.dungeon = dungeon;
		this.id = id;
	}
	
	/**
	 * Gets new value x of position for player
	 * @return
	 */
	public int newX() {
		for (Entity entity: dungeon.getEntities()) {
			if (entity != null) {
				if (entity instanceof Portal && entity != this) {
					if (((Portal) entity).getId() == id)
						return entity.getX();
				}
			}
		}
		
		return -1;
	}
	
	/**
	 * Gets new value y of position for player
	 * @return
	 */
	public int newY() {
		for (Entity entity: dungeon.getEntities()) {
			if (entity != null) {
				if (entity instanceof Portal && entity != this) {
					if (((Portal) entity).getId() == id)
						return entity.getY();
				}
			}
		}
		
		return -1;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
}
