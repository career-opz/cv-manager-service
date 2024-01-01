package dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultFilesResponseDto {
    private FileDataResponseDto cv;
    private FileDataResponseDto coverLetter;
    private FileDataResponseDto userPhoto;
}
