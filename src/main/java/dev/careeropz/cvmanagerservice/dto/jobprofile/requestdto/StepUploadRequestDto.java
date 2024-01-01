package dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StepUploadRequestDto {
    private String fileId;
    private String name;
}
