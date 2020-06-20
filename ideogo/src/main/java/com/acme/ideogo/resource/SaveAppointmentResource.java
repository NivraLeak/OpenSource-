package com.acme.ideogo.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class SaveAppointmentResource {
    @NotNull
    @NotBlank
    public Date Date_time ;
}
