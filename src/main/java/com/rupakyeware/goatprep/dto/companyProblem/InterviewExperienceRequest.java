package com.rupakyeware.goatprep.dto.companyProblem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewExperienceRequest {
    private int companyId;
    private List<Integer> problemIds;
}
