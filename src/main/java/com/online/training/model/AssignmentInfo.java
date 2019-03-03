package com.online.training.model;

import java.util.List;

public class AssignmentInfo {
    private String assignmentName;
    private String course;
    private List<ActualAssignment> assignment;

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<ActualAssignment> getAssignment() {
        return assignment;
    }

    public void setAssignment(List<ActualAssignment> assignment) {
        this.assignment = assignment;
    }
}
