package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultFilesRequestDto {
    private FileDataRequestDto cv;
    private FileDataRequestDto coverLetter;
    private FileDataRequestDto userPhoto;
}
