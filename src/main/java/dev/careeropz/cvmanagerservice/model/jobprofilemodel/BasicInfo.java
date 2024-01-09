package dev.careeropz.cvmanagerservice.model.jobprofilemodel;

import dev.careeropz.cvmanagerservice.model.constants.JobProfileModelStatus;
import dev.careeropz.cvmanagerservice.model.subclasses.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicInfo {
    private String companyName;
    private Country country;
    private String industry;
    private String position;
    private String companyWebsite;
    private JobProfileModelStatus status;
    private LocalDate appliedDate;
}
