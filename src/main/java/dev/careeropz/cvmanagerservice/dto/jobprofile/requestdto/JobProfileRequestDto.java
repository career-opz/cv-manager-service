package dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto;

import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.BasicInfoDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.JobProfileProgressStepDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileRequestDto {
    private BasicInfoDto basicInfo;
    private List<JobProfileProgressStepDto> progress;
}
