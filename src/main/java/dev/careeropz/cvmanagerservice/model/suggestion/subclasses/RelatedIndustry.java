package dev.careeropz.cvmanagerservice.model.suggestion.subclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelatedIndustry {
    private String industry;
    private List<String> jobTitles;
}
