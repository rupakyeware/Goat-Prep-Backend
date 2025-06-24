package com.rupakyeware.goatprep.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "problems_solved")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemsSolved {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int problemsSolvedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="problem_id", nullable = false)
    private Problems problem;
}
