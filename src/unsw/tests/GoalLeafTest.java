package unsw.tests;

import org.junit.jupiter.api.Test;
import unsw.goal.GoalLeaf;


public class GoalLeafTest {

	@Test
	void isCompleted() {
		
		GoalLeaf goal = new GoalLeaf("goal");
		assert(goal.isGoalCompleted() == false);
	}
	
	@Test
	void setGoalCompleted() {
		
		GoalLeaf goal = new GoalLeaf("goal");
		goal.setGoalCompleted(true);
		assert(goal.isGoalCompleted() == true);
	}
	
}
