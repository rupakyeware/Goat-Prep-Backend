package com.rupakyeware.goatprep.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="company")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Companies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String companyName;

    private String companyLogoUrl;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<CompanyProblems> companyProblems;
}
