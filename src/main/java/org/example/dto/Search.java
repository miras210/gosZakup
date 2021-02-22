package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Search {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String lotName;

    private String lotPlan;
}
