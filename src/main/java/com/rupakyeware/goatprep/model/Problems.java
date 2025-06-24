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
    private int problemId;

    private String problemName;

    private int problemDifficulty;

    private int problemLookups;

    private String problemUrl;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<CompanyProblems> companyProblems;
}
