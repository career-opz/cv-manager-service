package dev.careeropz.cvmanagerservice.model;

import dev.careeropz.cvmanagerservice.model.jobprofilemodel.JobProfileModel;
import dev.careeropz.cvmanagerservice.model.subclasses.CareerInfo;
import dev.careeropz.cvmanagerservice.model.subclasses.DefaultFiles;
import dev.careeropz.cvmanagerservice.model.subclasses.FileData;
import dev.careeropz.cvmanagerservice.model.subclasses.PersonalInfo;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "user-info")
public class UserInfoModel {

    @Id
    private ObjectId id;

    private PersonalInfo personalInfo;
    private CareerInfo careerInfo;
    private DefaultFiles defaultFiles;
    private FileData profilePicture;
    private Map<String, String> links;
    private Date accountCreatedOn;
    private Date lastLoginOn;
    private Boolean accountActive;

    @DocumentReference(lazy = true)
    private List<JobProfileModel> jobProfiles;
}
