package com.acme.ideogo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "profiles")
@Getter
@Setter
public class Profile  extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String experience;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String degree;

    @NotNull
    @Lob
    private String description;

}
