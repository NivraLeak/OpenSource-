package com.acme.ideogo.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveApplicationResource {
    @NotNull
    @NotBlank
    private int orderRequest;

    @NotNull
    @NotBlank
    public String state;
}
