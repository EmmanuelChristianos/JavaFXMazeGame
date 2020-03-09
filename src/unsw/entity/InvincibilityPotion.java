package unsw.entity;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import unsw.player.FightEnemyInteract;
import unsw.player.IgnoreEnemyInteract;
import unsw.player.RunAwayEnemyInteract;

public class InvincibilityPotion extends Entity implements Pickup {
	
	private IntegerProperty timer = new SimpleIntegerProperty(10);
	private Player player;
	private BooleanProperty bool = new SimpleBooleanProperty(false);
	
	public InvincibilityPotion(int x, int y) {
		super(x, y);
		super.setCanPickup(true);
	}
	
	public void tickTimer() {
		
		if(player.getPotionState() == this.player.getHasDrunkPotionState()) {
			
			if(getTimer() == 1) {
				
				setTimer(getTimer() - 1);
				
				List<Entity> items = player.getInventory().getInventory();
				
				for(int i = 0; i<items.size(); i++) {
					
					if(items.get(i) instanceof InvincibilityPotion) {
						
						player.getInventory().removeEntity(items.get(i));
					}
				}
				
				player.setState(player.getHasNotDrunkPotionState());
				
				if(player.getInteractStatus() instanceof IgnoreEnemyInteract) {
					
					player.changeInteract(player.getRunAwayEnemyInteract());
				}
				
				bool.setValue(false);
				
			} else {
				
				setTimer(getTimer() - 1);			
			}
		}
	}

	/**
	 * @return the timer
	 */
	public int getTimer() {
		return timer.get();
	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(int timer) {
		this.timer.set(timer);
	}
	
	public IntegerProperty timerProperty() {
		return timer;
	}
	
	
	public void dissapear(Player player) {
		this.player = player;
		super.dissapear(this.player);
		this.player.drinkPotion();
		System.out.println(getTimer());
	}
	
	public BooleanProperty booleanProperty() {
		return bool;
	}
	
	public void setBoolean(Boolean bool) {
		this.bool.set(bool);
	}

}
