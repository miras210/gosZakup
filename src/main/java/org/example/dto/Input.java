package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String lotName;

    private String lotPlan;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="input_id")
    private List<Output> outputs;
}
