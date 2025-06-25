package com.rupakyeware.goatprep.dto.problem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDTO {
    private int problemId;
    private String problemName;
    private int problemDifficulty;
    private int problemLookups;
    private String problemUrl;
}
