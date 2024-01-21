package dev.careeropz.cvmanagerservice.model.jobprofile;

import dev.careeropz.cvmanagerservice.model.constants.JobProfileModelStatus;
import dev.careeropz.cvmanagerservice.model.userinfo.subclasses.CountryModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicInfoModel {
    private String companyName;
    private CountryModel country;
    private String industry;
    private String position;
    private String companyWebsite;
    private JobProfileModelStatus status;
    private LocalDate appliedDate;
}
