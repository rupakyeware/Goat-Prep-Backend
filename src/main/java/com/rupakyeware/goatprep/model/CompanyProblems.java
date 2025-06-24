package com.rupakyeware.goatprep.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="company_problems")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyProblems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyProblemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "problem_id", nullable = false)
    private Problems problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Companies company;

    private int frequency;
}
