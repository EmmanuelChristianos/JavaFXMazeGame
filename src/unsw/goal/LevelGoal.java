package unsw.goal;

import java.util.ArrayList;

public class LevelGoal extends GoalComponant{

	private ArrayList<GoalComponant> goals;
	private String logicalOperator;
	private String type = "comp";
	
	/*
	 * LevelGoal constructor
	 */
	public LevelGoal(String log) {
		this.logicalOperator = log;
		this.goals = new ArrayList<>();

	}
	
	@Override
	public boolean isGoalCompleted() {
		
		boolean found = false;
		
		for(GoalComponant g: this.goals) {
			
			if(!g.isGoalCompleted()) {
				
				found = true;
			}
		}
		return !found;
	}

	
	/**
	 * Adds goal to current goal list
	 * @param g
	 */
	public void addGoal(GoalComponant g) {
		this.goals.add(g);
	}
	

	@Override
	public void listSubGoals() {
		for(GoalComponant g: this.goals) {
			System.out.println(g);
		}
	}
	
	/**
	 * Returns goals
	 * @return goals
	 */
	public ArrayList<GoalComponant> getGoals(){
		return this.goals;
	}
	
	@Override
	public ArrayList<GoalComponant> getGoal(String type) {
		ArrayList<GoalComponant> goals = new ArrayList<>();
		
		for (GoalComponant goal: this.goals) {
			
			if (goal.getType().equals(type)) {
				goals.add(goal);
			}
		}
		
		return goals;
	}

	/**
	 * @return the logical operator
	 */
	public String getLogicalOperator() {
		return logicalOperator;
	}

	@Override
	public String getType() {
		return type;
	}
	
}
