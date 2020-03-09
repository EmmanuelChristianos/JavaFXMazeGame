package unsw.goal;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GoalLeaf extends GoalComponant {

	private String type;
	private BooleanProperty goalCompleted;
	
	public GoalLeaf(String type) {
		
		this.type = type;
		this.goalCompleted = new SimpleBooleanProperty(false);
	}
	
	
	@Override
	public boolean isGoalCompleted() {
		return this.goalCompleted.get();
	}
	
	
	/**
	 * @param bool
	 */
	public void setGoalCompleted(boolean bool) {
		this.goalCompleted.set(bool);
	}
	
	@Override
	public String toString() {
		return String.format(type + " is completed: " + goalCompleted.get());
	}
	
	/**
	 * @return
	 */
	public BooleanProperty booleanProperty() {
		return goalCompleted;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public ArrayList<GoalComponant> getGoal(String type) {
		if (this.getType().equals(type)) {
			ArrayList<GoalComponant> goals = new ArrayList<>();
			goals.add(this);
			return goals;
		}
		
		return null;
	}
}
