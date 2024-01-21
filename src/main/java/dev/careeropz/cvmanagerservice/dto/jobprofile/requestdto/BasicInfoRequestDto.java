package dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto;

import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.JobProfileStatus;
import dev.careeropz.cvmanagerservice.model.userinfo.subclasses.CountryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicInfoRequestDto {
    private String companyName;
    private String industry;
    private CountryModel country;
    private String position;
    private String companyWebsite;
    private JobProfileStatus status;
    private LocalDate appliedDate;
}
