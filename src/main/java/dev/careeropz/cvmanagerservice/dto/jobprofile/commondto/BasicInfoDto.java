package dev.careeropz.cvmanagerservice.dto.jobprofile.commondto;

import dev.careeropz.cvmanagerservice.model.subclasses.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicInfoDto {
    private String companyName;
    private String industry;
    private Country country;
    private String position;
    private String companyWebsite;
    private JobProfileStatus status;
    private Date createdOn;
    private Date updatedOn;
}
