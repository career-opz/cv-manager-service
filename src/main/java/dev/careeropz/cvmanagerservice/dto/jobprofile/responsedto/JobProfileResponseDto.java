package dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto;

import dev.careeropz.commons.jobprofile.commondto.JobProfileProgressStepDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileResponseDto {
    private String jobProfileId;
    private String userId;

    private BasicInfoResponseDto basicInfo;
    private List<JobProfileProgressStepDto> progress;
}
