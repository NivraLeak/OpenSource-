package com.acme.ideogo.resource;

import com.acme.ideogo.model.AuditModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagResource extends AuditModel {
    private Long id;
    private String name;
}