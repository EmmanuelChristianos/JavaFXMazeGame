package unsw.tests;

import org.junit.jupiter.api.Test;

import unsw.goal.GoalComponant;
import unsw.goal.GoalLeaf;
import unsw.goal.LevelGoal;

public class LevelGoalTest {

	@Test
	void isGoalComplete() {
		
		LevelGoal newG = new LevelGoal("Goal");
		GoalLeaf gL = new GoalLeaf("G");
		gL.setGoalCompleted(false);
		newG.addGoal(gL);
		
		boolean found = false;
		
		for(GoalComponant g: newG.getGoals()) {
			
			if(!g.isGoalCompleted()) {
				
				found = true;
			}
		}
		
		assert(found == true);
		
	}
	
}
