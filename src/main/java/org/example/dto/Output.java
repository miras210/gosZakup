package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String lotNum;

    private String adName;

    private String lotDesc;

    private int count;

    private double cost;

    private String purchaseType;

    private String status;

    @ManyToOne
    private Input input;

}
