package com.online.training.table.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "notes")
public class Notes {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="my_seq", sequenceName="notes_id_seq")
    @GeneratedValue(strategy=GenerationType.AUTO, generator = "my_seq")
    private Long id;

    @Column(name = "name")
    @NotNull
    
    @Size(min = 1, max = 100)
    private String name;

    @Column(name = "note_pdf")
    @NotNull
    @JsonIgnore
    private byte[] notePdf;

    @Column(name = "chapter_name")
    @NotNull
    
    private String chapterName;

    @Column(name =  "course_name")
    @NotNull
    
    @Size(min = 1, max = 100)
    private String courseName;

    @Column(name =  "professor_name")
    @NotNull
    
    @Size(min = 1, max = 100)
    private String professorName;

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

    public byte[] getNotePdf() {
        return notePdf;
    }

    public void setNotePdf(byte[] notePdf) {
        this.notePdf = notePdf;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
}
