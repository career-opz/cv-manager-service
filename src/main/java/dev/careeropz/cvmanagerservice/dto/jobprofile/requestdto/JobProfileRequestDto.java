package dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto;

import dev.careeropz.cvmanagerservice.dto.jobprofile.JobProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileRequestDto {
    private String userId;
    private String companyName;
    private String industry;
    private String companyWebsite;
    private String companyCareersPage;
    private JobProfileStatus jobProfileStatus;
    private Date jobProfileCreatedOn;
    private Date jobProfileUpdatedOn;
    private List<JobProfileProgressStepRequestDto> progress;
}
