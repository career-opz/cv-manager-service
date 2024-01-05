package dev.careeropz.cvmanagerservice.model.jobprofilemodel;

import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import dev.careeropz.cvmanagerservice.model.constants.StatusEnum;
import dev.careeropz.cvmanagerservice.model.subclasses.Country;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "job-profile")
public class JobProfileModel {
    @Id
    private String id;

    @DocumentReference(lazy = true)
    private UserInfoModel userRef;

    private String companyName;
    private Country country;
    private String industry;
    private String companyWebsite;
    private String companyCareersPage;
    private StatusEnum status;
    private Date createdOn;
    private Date updatedOn;
    private List<JobProfileProgressStep> progress;
}
