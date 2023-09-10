package com.example.warehouse.model;


import javax.persistence.*;


@Entity
public class Role  {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @Enumerated(value = EnumType.STRING)
    private RoleType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }
}
