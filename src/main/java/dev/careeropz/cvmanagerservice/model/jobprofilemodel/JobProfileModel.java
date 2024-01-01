package dev.careeropz.cvmanagerservice.model.jobprofilemodel;

import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import dev.careeropz.cvmanagerservice.model.constants.StatusEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "job-profile")
public class JobProfileModel {
    @Id
    private String id;

    @DocumentReference
    private UserInfoModel userid;

    private String companyName;
    private String country;
    private String industry;
    private String companyWebsite;
    private String companyCareersPage;
    private StatusEnum status;
    private Date createdOn;
    private Date updatedOn;
    private List<JobProfileProgressStep> progress;
}
