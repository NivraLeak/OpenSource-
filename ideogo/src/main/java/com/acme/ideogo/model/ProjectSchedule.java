package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "projectschedules")
@Getter
@Setter
public class ProjectSchedule extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;

    @NotBlank
    @NotNull
    @Lob
    private String description;

    @OneToOne(mappedBy = "project_schedule", cascade = CascadeType.ALL)
    private Project project;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "mTask")
    //@JsonIgnore
    //private List<MTask> tasks;
/////
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    //@JsonIgnore
    //private List<Activity> activities;

}
