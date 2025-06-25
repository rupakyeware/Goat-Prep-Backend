package com.rupakyeware.goatprep.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    private int companyId;
    private String companyName;
    private String companyLogoUrl;
}
