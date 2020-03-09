package unsw.entity;

import unsw.dungeon.Dungeon;

public class Wall extends Entity {
	
    private boolean canPickup = false;
 

    public Wall(int x, int y, Dungeon dungeon) {
        super(x, y);
        super.setCollide(true);
        this.canPickup = false;
        super.setCanPickup(false);
        super.setDungeon(dungeon);
    }
    
    public Dungeon getDungeon() {
    	
    	return super.getDungeon();
    }
}
