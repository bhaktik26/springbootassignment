package com.online.training.table.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "assignment")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Assignment {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="my_seq", sequenceName="assignment_id_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "my_seq")
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 100)
    private String name;


    @Column(name="assignment_info")
    @NotNull
    private String assignmentInfo;

    @Column(name="created_by")
    @NotNull
    @Size(min = 1, max = 100)
    private String createdBy;

    @Column(name = "course_name")
    @NotNull
    @Size(min = 1, max = 100)
    private String courseName;

    @Column(name="created_on")
    @NotNull
    private Date createdOn;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignmentInfo() {
        return assignmentInfo;
    }

    public void setAssignmentInfo(String assignmentInfo) {
        this.assignmentInfo = assignmentInfo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
