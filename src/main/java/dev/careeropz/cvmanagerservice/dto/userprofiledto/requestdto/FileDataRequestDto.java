package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDataRequestDto {
    private String id;
    private String name;
    private Date dateUploaded;
}
