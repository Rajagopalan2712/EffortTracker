package com.rajagopalan.efforttracker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajagopalan.efforttracker.model.GOAL_STATUS;
import com.rajagopalan.efforttracker.model.Goal;
import com.rajagopalan.efforttracker.model.GoalProfile;

public class EffortTracker {
	
	public static void main(String[] args) throws IOException {
		
		GoalProfile goalProfile = null;
		ObjectMapper om = new ObjectMapper();
		File effortTracker = Paths.get("EffortTracker.json").toFile();
		if(!effortTracker.exists()) {
			effortTracker = new File("EffortTracker.json");
			effortTracker.createNewFile();
			goalProfile = new GoalProfile();
			goalProfile.setGoals(new ArrayList<>());
			om = new ObjectMapper();
			om.writeValue(effortTracker, goalProfile);
		}
		else {
			goalProfile = om.readValue(effortTracker, GoalProfile.class);
			goalProfile.getGoals().forEach(g->g.setGoalStatus(computeGoalStatus(g)));
		}
		
		System.out.println(goalProfile.getUserName());
		
	}
	
	private static GOAL_STATUS computeGoalStatus(Goal g) {
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
