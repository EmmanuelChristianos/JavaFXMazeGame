package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.entity.*;
import unsw.goal.*;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }               
        
        dungeon.setGoals(loadGoal(dungeon, json.getJSONObject("goal-condition")));
        dungeon.checkGoals();
        return dungeon;
    }
    
    private GoalComponant loadGoal(Dungeon dungeon, JSONObject json) {
    	String goalName = json.getString("goal");
    	GoalComponant goal = null;
    	
    	switch(goalName) {
    	case "exit":
    		GoalLeaf exitGoal = new GoalLeaf(goalName);
    		goal = exitGoal;
    		break;
    	case "enemies":
    		GoalLeaf enemiesGoal = new GoalLeaf(goalName);
    		goal = enemiesGoal;
    		break;
    	case "boulders":
    		GoalLeaf bouldersGoal = new GoalLeaf(goalName);
    		goal = bouldersGoal;
    		break;
    	case "treasure":
    		GoalLeaf treasureGoal = new GoalLeaf(goalName);
    		goal = treasureGoal;
    		break;
    	case "AND":
    		System.out.println("Were FFFF Here");
    		JSONArray subgoals = json.getJSONArray("subgoals");
    		LevelGoal andGoals = new LevelGoal("AND");
    		
    		for(int i = 0; i < subgoals.length(); i++) {
    			
    			GoalLeaf  leaf = new GoalLeaf((String) subgoals.getJSONObject(i).get("goal"));
    			System.out.println(subgoals.getJSONObject(i).get("goal"));
    			andGoals.addGoal(leaf);
    			
    			
    		}
    		goal = andGoals;
    		
//    		for (int i = 0; i < subgoals.length(); i++) {
//                andGoals.addGoal(loadGoal(dungeon, subgoals.getJSONObject(i)));
//            }
    		
    		break;
    	case "OR":
    		JSONArray subgoals1 = json.getJSONArray("subgoals");
    		LevelGoal orGoals = new LevelGoal("OR");
    		goal = orGoals;
    		
    		for (int i = 0; i < subgoals1.length(); i++) {
    			orGoals.addGoal(loadGoal(dungeon, subgoals1.getJSONObject(i)));
            }
    		    		
    		break;
    	}
    	
    	return goal;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "fuel":
            Fuel fuel = new Fuel(x, y);
            onLoad(fuel);
            entity = fuel;
            break;
        case "portalGun":
            Portalgun portalGun = new Portalgun(x, y);
            onLoad(portalGun);
            entity = portalGun;
            break;
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y, dungeon);
            onLoad(wall);
            entity = wall;
            break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon, x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	FloorSwitch floorSwitch = new FloorSwitch(x, y);
        	onLoad(floorSwitch);
        	entity = floorSwitch;
        	break;
        case "door":
        	int id = json.getInt("id");
        	Door door = new Door(x, y, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x, y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "invincibility":
        	InvincibilityPotion invinciblePot = new InvincibilityPotion(x, y);
        	onLoad(invinciblePot);
        	entity = invinciblePot;
        	break;
        case "key":
        	int id1 = json.getInt("id");
        	Key key = new Key(x, y, id1);
        	onLoad(key);
        	entity = key;
        	break;
        case "portal":
        	int id2 = json.getInt("id");
        	Portal portal = new Portal(dungeon, x, y, id2);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y, dungeon);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        	
        // TODO Handle other possible entities
        }       
 
        dungeon.addEntity(entity);
    }
    public abstract void onLoad(Fuel fuel);
    
    public abstract void onLoad(Portalgun portalGun);
    
    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(FloorSwitch floorSwitch);
    
    public abstract void onLoad(Door door);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(InvincibilityPotion invinciblePot);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Treasure Treasure);

    // TODO Create additional abstract methods for the other entities

}
