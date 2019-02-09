package com.kindgeek.test.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @Id
    @Column(name = "position_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String namePosition;

    public Position(String namePosition) {
        this.namePosition = namePosition;
    }
}
