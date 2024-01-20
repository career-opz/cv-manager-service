package dev.careeropz.cvmanagerservice.dto.jobprofile.commondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDataDto {
    private String fileId;
    private String name;
    private Date dateUploaded;
}
