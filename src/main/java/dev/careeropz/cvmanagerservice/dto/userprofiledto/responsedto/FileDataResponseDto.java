package dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDataResponseDto {
    private String id;
    private String name;
    private Date dateUploaded;
}
