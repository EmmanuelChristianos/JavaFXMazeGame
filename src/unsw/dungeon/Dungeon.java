/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import unsw.entity.Enemy;
import unsw.entity.Entity;
import unsw.entity.Exit;
import unsw.entity.FloorSwitch;
import unsw.entity.Player;
import unsw.entity.Treasure;
import unsw.goal.GoalComponant;
import unsw.goal.GoalLeaf;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private GoalComponant goals;
    DungeonLoader loader;

    public Dungeon(int width, int height, DungeonLoader loader) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = null;
        this.loader = loader;
    }
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goals = null;
    }
    
    public DungeonLoader getLoader() {
		return this.loader;
	}

	public void setList(List<Entity> list) {
    	this.entities = list;
    }
     
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void setGoals(GoalComponant goal) {
    	this.goals = goal;
    }
    
    public List<Entity> getEntities(){
    	
    	return this.entities;
    }

	public GoalComponant getGoals() {
		return goals;
	}
	
	public void checkGoals() {
		if (goals != null) {
			checkTreasure();
			checkEnemies();
			checkBoulders();
			checkExit();
			
			if (checkTreasure() && checkEnemies() && checkBoulders() && checkExit()) {
				//player.setCanMove(false);
			}
		}		
	}
	
	public boolean checkTreasure() {
		for (Entity e: entities) {
			if (e instanceof Treasure)  {
				if (!((Treasure) e).isPicked()) {
					return false;
				}
			}				
		}
		
		
		
		if (goals.getGoal("treasure") != null) {
			for (GoalComponant gl: goals.getGoal("treasure")) {
				((GoalLeaf) gl).setGoalCompleted(true);
			}
		}
	
		
		
		System.out.println("Treasure objective complete");
		return true;
	}
	
	public boolean checkEnemies() {
		for (Entity e: entities) {
			if (e instanceof Enemy) {
				if (e.getVisible() == 1)
					return false;
			}			
		}
		
		
		
		if (goals.getGoal("enemies") != null) {
			for (GoalComponant gl: goals.getGoal("enemies")) {
				((GoalLeaf) gl).setGoalCompleted(true);
			}
		}
	
		
		System.out.println("Enemies objective complete");
		return true;
	}
	
	public boolean checkBoulders() {
		for (Entity e: entities) {
			if (e instanceof FloorSwitch) {
				if (!((FloorSwitch) e).isActivated()) {

					if (goals.getGoal("boulders") != null) {
						for (GoalComponant gl: goals.getGoal("boulders")) {
							((GoalLeaf) gl).setGoalCompleted(false);
						}
					}
					
					return false;
				
				}
			}
		}
		
		
			
			if (goals.getGoal("boulders") != null) {
				for (GoalComponant gl: goals.getGoal("boulders")) {
					((GoalLeaf) gl).setGoalCompleted(true);
				}
			}
		
		
		System.out.println("Boulder objective complete");
		return true;
	}
	
	public boolean checkExit() {
		
	
		
		if (goals.getGoal("exit") != null) {
			if (checkBoulders() && checkEnemies() && checkTreasure()) {
				for (Entity e: entities) {
					if (e instanceof Exit) {
						((Exit) e).canFinish();
						return false;
					}
				}
			} else {
				return false;
			}
		}
	
		
		
		System.out.println("Exit objective complete");
		return true;
	}

}