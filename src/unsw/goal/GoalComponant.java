package unsw.goal;

import java.util.ArrayList;

public abstract class GoalComponant {
	
	/**
	 * @return the completion of the goal
	 */
	public boolean isGoalCompleted() {
		return false;
	}
	/**
	 * @return
	 */
	public boolean addGoal() {
		return false;
	}
	/**
	 * Prints all goals
	 */
	public void listSubGoals() {
	}
	
	/**
	 * @return the name of the goal
	 */
	public String getType() {
		return "";
	}
	
	/**
	 * Returns a list of goals that match the search criteria
	 * @param type - the goal in search
	 * @return list of goals matching search
	 */
	public ArrayList<GoalComponant> getGoal(String type) {
		return null;
	}

}
