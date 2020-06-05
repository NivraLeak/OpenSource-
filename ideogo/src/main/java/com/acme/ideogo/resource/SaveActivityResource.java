package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveActivityResource {
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    private String description;
}
