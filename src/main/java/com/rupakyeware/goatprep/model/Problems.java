package com.rupakyeware.goatprep.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="problem")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Problems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private int problemId;

    @Column(name = "problem_name")
    private String problemName;

    @Column(name = "problem_difficulty")
    private int problemDifficulty;

    @Column(name = "problem_lookups")
    private int problemLookups;

    @Column(name = "problem_url")
    private String problemUrl;
}
