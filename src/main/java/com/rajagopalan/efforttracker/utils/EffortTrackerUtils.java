package com.rajagopalan.efforttracker.utils;

import java.time.LocalDate;

import com.rajagopalan.efforttracker.model.GOAL_STATUS;
import com.rajagopalan.efforttracker.model.Goal;

public class EffortTrackerUtils {

	public static GOAL_STATUS computeGoalStatus(Goal g) {
		if(g.getGoalStatus() == GOAL_STATUS.DELETED)
			return g.getGoalStatus();
		if(LocalDate.now().isBefore(g.getStartDate())) 
			return GOAL_STATUS.NOT_STARTED;
		if(g.getGoal()<=g.getProgress())
			return GOAL_STATUS.ACCOMPLISHED;
		if(LocalDate.now().isAfter(g.getEndDate()))
			return GOAL_STATUS.OVERDUE;
		else
			return GOAL_STATUS.ACTIVE;
	}
	
}
