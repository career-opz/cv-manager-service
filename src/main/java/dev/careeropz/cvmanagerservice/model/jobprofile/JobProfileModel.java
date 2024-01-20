package dev.careeropz.cvmanagerservice.model.jobprofile;

import dev.careeropz.cvmanagerservice.model.userinfo.UserInfoModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Data
@Document(collection = "job-profile")
public class JobProfileModel {
    @Id
    private String id;

    @DocumentReference(lazy = true)
    private UserInfoModel userRef;

    private BasicInfo basicInfo;
    private List<JobProfileProgressStep> progress;
}
