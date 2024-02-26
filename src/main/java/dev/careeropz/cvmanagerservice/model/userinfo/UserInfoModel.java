package dev.careeropz.cvmanagerservice.model.userinfo;

import dev.careeropz.cvmanagerservice.model.jobprofile.JobProfileModel;
import dev.careeropz.cvmanagerservice.model.userinfo.subclasses.*;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "user-info")
public class UserInfoModel {

    @Id
    private String userId;

    private PersonalInfoModel personalInfo;
    private CareerInfoModel careerInfo;
    private DefaultFilesModel defaultFiles;
    private FileDataModel profilePicture;
    private LinksModel links;
    private Date accountCreatedOn;
    private Date lastLoginOn;
    private Boolean accountActive;

    @DocumentReference(lazy = true)
    private List<JobProfileModel> jobProfiles;
}
