package com.rajagopalan.efforttracker.model;

import java.util.List;

public class GoalProfile {
	
	private String userName;
	private List<Goal> goals;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<Goal> getGoals() {
		return goals;
	}
	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}
	
	

}
