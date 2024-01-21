package dev.careeropz.cvmanagerservice.dto.suggestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatedIndustryDto {
    private String industryId;
    private String industry;
    private List<String> jobTitles;
}
