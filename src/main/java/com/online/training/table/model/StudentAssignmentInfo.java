package com.online.training.table.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student_assignment_info")
public class StudentAssignmentInfo {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="my_seq", sequenceName="student_assignment_info_id_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "my_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    @Column(name = "course_name")
    @NotNull
    private String courseName;

    @Column(name = "assignment_name")
    @NotNull
    private String assignmentName;

    @Column(name = "assignment_questions")
    @NotNull
    private String assignmentQuestions;

    @Column(name = "assignment_answers")
    private String assignmentAnswers;

    @Column(name = "assignment_state")
    @NotNull
    private String assignmentState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getAssignmentQuestions() {
        return assignmentQuestions;
    }

    public void setAssignmentQuestions(String assignmentQuestions) {
        this.assignmentQuestions = assignmentQuestions;
    }

    public String getAssignmentAnswers() {
        return assignmentAnswers;
    }

    public void setAssignmentAnswers(String assignmentAnswers) {
        this.assignmentAnswers = assignmentAnswers;
    }

    public String getAssignmentState() {
        return assignmentState;
    }

    public void setAssignmentState(String assignmentState) {
        this.assignmentState = assignmentState;
    }
}
