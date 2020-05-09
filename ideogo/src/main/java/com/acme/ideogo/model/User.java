package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends  AuditModel {

     @Id
     @NotBlank
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     private Long Id;

    @NotBlank
    @NotNull
    @Size(max = 20)
    @Column(unique = true)
    private String Username;

    @NotBlank
    @NotNull
    private String sexo;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String Password;

   // @ManyToMany(fetch = FetchType.LAZY,
     //           cascade = {CascadeType.PERSIST, CascadeType.MERGE},
       //         mappedBy = "Users")
    //@JsonIgnore
    //private List<Project> projects;


}
