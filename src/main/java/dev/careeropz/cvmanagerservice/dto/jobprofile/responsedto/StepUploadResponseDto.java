package dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepUploadResponseDto {
    private String fileId;
    private String name;
}
