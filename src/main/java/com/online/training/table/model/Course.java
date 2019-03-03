package com.online.training.table.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table(name = "course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="my_seq", sequenceName="course_course_id_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "my_seq")
    @JsonIgnore
    private Long id;

    @Column(name = "course_name")
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "course_description")
    @NotNull
    @Size(min = 1, max = 100)
    private String description;

    @Column(name = "course_createdon")
    @NotNull
    @JsonIgnore
    private Date createdOn;

    @Column(name =  "course_createdby")
    @NotNull
    @Size(min = 1, max = 100)
    private String createdBy;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
