package com.ironhack.hellomonsterjpa.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "monsters")
public class Monster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "monster_level")
    private Integer level;

    private String type;

    private Boolean isLegendary;

    public Monster(String name, Integer level, String type, Boolean isLegendary) {
        this.name = name;
        this.level = level;
        this.type = type;
        this.isLegendary = isLegendary;
    }
}
