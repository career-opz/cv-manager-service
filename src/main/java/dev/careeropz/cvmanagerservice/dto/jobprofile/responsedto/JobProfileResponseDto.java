package dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto;

import dev.careeropz.cvmanagerservice.dto.jobprofile.JobProfileStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileResponseDto {
    private String id;
    private String userId;
    private String companyName;
    private String country;
    private String industry;
    private String companyWebsite;
    private String companyCareersPage;
    private JobProfileStatus status;
    private Date createdOn;
    private Date updatedOn;
    private List<JobProfileProgressStepResponseDto> progress;
}
