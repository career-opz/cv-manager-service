package dev.careeropz.cvmanagerservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Data
@Document(collection = "cvs")
public class CvModel {
    @Id
    private String id;

    @DocumentReference
    private UserInfoModel userinfo;

    private String fileServerId;
    private String fileName;
    private Date uploadedOn;
}
