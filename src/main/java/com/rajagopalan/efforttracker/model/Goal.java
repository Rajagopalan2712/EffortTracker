package com.rajagopalan.efforttracker.model;

import java.time.LocalDate;
import java.util.Set;

public class Goal {
	
	private String goalName;
	private LocalDate startDate;
	private LocalDate endDate;
	private double goal;
	private String unit;
	private double progress;
	private Set<Effort> effortHistory;
	public Set<Effort> getEffortHistory() {
		return effortHistory;
	}
	public void setEffortHistory(Set<Effort> effortHistory) {
		this.effortHistory = effortHistory;
	}
	private GOAL_STATUS goalStatus;
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public double getGoal() {
		return goal;
	}
	public void setGoal(double goal) {
		this.goal = goal;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getProgress() {
		return progress;
	}
	public void setProgress(double progress) {
		this.progress = progress;
	}
	public String getGoalName() {
		return goalName;
	}
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	public GOAL_STATUS getGoalStatus() {
		return goalStatus;
	}
	public void setGoalStatus(GOAL_STATUS goalStatus) {
		this.goalStatus = goalStatus;
	}
	
	

}
