package unsw.entity;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.dungeon.Dungeon;
import unsw.player.FightEnemyInteract;
import unsw.player.IgnoreEnemyInteract;
import unsw.player.RunAwayEnemyInteract;

public class Sword extends Entity implements Pickup {
	
	private IntegerProperty hits = new SimpleIntegerProperty(3);
	private Dungeon dungeon;
	private Player player;
	private BooleanProperty bool = new SimpleBooleanProperty(false);
	
	public Sword(int x, int y, Dungeon dungeon) {
		super(x, y);
		super.setCanPickup(true);
		this.dungeon = dungeon;
		this.dungeon.addEntity(this);
		
	}
	
	public void hit(Player player) {

		this.player = player;
		
		hits.set(hits.get()-1);
		System.out.println("HIIIIITS: " + hits.get());
		if(hits.get() == 0) {
			
			player.getInventory().removeEntity(this);
			bool.set(false);
			
			List<Entity> items = player.getInventory().getInventory(); 
			 
			for(int i = 0; i<items.size(); i++) {
				
				if(items.get(i) instanceof InvincibilityPotion) {
					
					System.out.println("Thats a start");

					
					if(((InvincibilityPotion) items.get(i)).getTimer() > 0) {
						
						System.out.println("Shouldnt be here");
						player.changeInteract(player.getIgnoreEnemyInteract());
						
					} else {
						System.out.println("Should be here");
						player.changeInteract(player.getRunAwayEnemyInteract());
					}
				}
			}
			
			if(player.getInteractStatus() instanceof FightEnemyInteract) {
				player.changeInteract(player.getRunAwayEnemyInteract());
			}
		}
	}
	
	public int getHits() {
		return hits.get();
	}
	
	public void setHits(int hits) {
		this.hits.set(hits);
	}

	public void dissapear(Player player) {
		// TODO Auto-generated method stub
		this.player = player;
		super.dissapear(this.player);
		
		if(player.getInteractStatus() instanceof FightEnemyInteract) {
			
			List<Entity> items = this.player.getInventory().getInventory();
			
			for(int i = 0; i<items.size(); i++) {
				
				if(items.get(i) instanceof Sword) {
					
					((Sword) items.get(i)).setHits(((Sword) items.get(i)).getHits()+3);
				}
				
			}
		} else {
			
			player.changeInteract(player.getFightEnemyInteract());			
		}
		
	}

	@Override
	public boolean canPickup() {
		// TODO Auto-generated method stub
		return super.canPickup();
		
	}
	
	public IntegerProperty hitsProperty() {
		return hits;
	}
	
	public BooleanProperty booleanProperty() {
		return bool;
	}
	
	public void setBoolean(Boolean bool) {
		this.bool.set(bool);
	}
}
