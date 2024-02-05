package dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto;

import dev.careeropz.commons.dto.FileDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultFilesDto {
    private FileDataDto cv;
    private FileDataDto coverLetter;
}
