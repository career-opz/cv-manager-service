package dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto;

import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.BasicInfoDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.JobProfileProgressStepDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileResponseDto {
    private String id;
    private String userRef;

    private BasicInfoDto basicInfo;
    private String note;
    private List<JobProfileProgressStepDto> progress;
}
