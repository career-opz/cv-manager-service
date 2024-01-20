package dev.careeropz.cvmanagerservice.model.userinfo.subclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
    private String fileId;
    private String name;
    private Date dateUploaded;
}