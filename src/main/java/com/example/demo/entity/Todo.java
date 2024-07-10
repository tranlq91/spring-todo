package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Table(name = "todo")
public class Todo {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private String name;

    private boolean isChecked;

    public Todo(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public Todo() {
        this.name = "TODO";
        this.isChecked = false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
