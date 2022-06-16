package com.rajagopalan.efforttracker.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rajagopalan.efforttracker.model.Effort;
import com.rajagopalan.efforttracker.model.GOAL_STATUS;
import com.rajagopalan.efforttracker.model.Goal;
import com.rajagopalan.efforttracker.model.GoalProfile;
import com.rajagopalan.efforttracker.utils.EffortTrackerUtils;

public class EffortTrackerManager {
	
	private GoalProfile goalProfile;
	ObjectMapper om = new ObjectMapper();
	private static EffortTrackerManager instance;
	private EffortTrackerManager() throws IOException {
		
		File effortTracker = Paths.get("EffortTracker.json").toFile();
		if(!effortTracker.exists()) {
			effortTracker = new File("EffortTracker.json");
			effortTracker.createNewFile();
			goalProfile = new GoalProfile();
			goalProfile.setGoals(new ArrayList<>());
			om.writeValue(effortTracker, goalProfile);
		}
		else {
			goalProfile = om.readValue(effortTracker, GoalProfile.class);
			goalProfile.getGoals().forEach(g->g.setGoalStatus(EffortTrackerUtils.computeGoalStatus(g)));
		}
			
	}
	
	public static EffortTrackerManager initialize() throws IOException {
		if(instance == null) {
			instance = new EffortTrackerManager();
		}
		return instance;
	}
	
	
	// Enquiry APIs
	public String getUserName() {
		return goalProfile.getUserName();
	}
	
	public boolean isUserProfileCreated() {
		return goalProfile != null && goalProfile.getUserName() != null;
	}
	
	public boolean isFirstTimeUser() {
		return goalProfile.getGoals() == null || goalProfile.getGoals().isEmpty();
	}
	
	public List<Goal> getGoals() {
		return goalProfile.getGoals();
	}
	
	public List<Goal> getGoals(GOAL_STATUS goalStatus) {
		return goalProfile.getGoals().stream().filter(g -> goalStatus.equals(g.getGoalStatus()))
				.collect(Collectors.toList());
	}
	
	
	//Update APIs
	
	public void updateOrCreateGoal(String name, LocalDate startDate, LocalDate endDate, double goal, String unit) {
		
		Goal thisGoal = goalProfile.getGoals().stream().filter(g -> g.getGoalName().equalsIgnoreCase(name)).findFirst()
				.orElse(new Goal());

		thisGoal.setEndDate(endDate);
		thisGoal.setStartDate(startDate);
		thisGoal.setGoal(goal);
		thisGoal.setUnit(unit);
		thisGoal.setGoalStatus(EffortTrackerUtils.computeGoalStatus(thisGoal));

	}
	
	public void updateEffort(String name, LocalDate date, double quantity) {
		Optional<Goal> goalObt = goalProfile.getGoals().stream().filter(g->g.getGoalName().equalsIgnoreCase(name)).findFirst();
		if(goalObt.isPresent()) {
			Goal thisGoal = goalObt.get();
			thisGoal.getEffortHistory().add(new Effort(quantity, date));
			thisGoal.setProgress(thisGoal.getProgress()+quantity);
			thisGoal.setGoalStatus(EffortTrackerUtils.computeGoalStatus(thisGoal));
		}
			
	}
	
	
	// Delete APIs
	public void deleteGoal(String name) {
		Optional<Goal> goalObt = goalProfile.getGoals().stream().filter(g->g.getGoalName().equalsIgnoreCase(name)).findFirst();
		if(goalObt.isPresent()) {
			goalObt.get().setGoalStatus(GOAL_STATUS.DELETED);
		}
	}
	
	public void clearAllGoals() {
		goalProfile.setGoals(new ArrayList<>());
	}
	

	
	public void commitChanges(Goal g)  {
		try {
			om.writeValue(Paths.get("EffortTracker.json").toFile(), g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
