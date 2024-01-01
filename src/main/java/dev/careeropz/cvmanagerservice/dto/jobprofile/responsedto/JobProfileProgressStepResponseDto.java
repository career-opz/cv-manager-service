package dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileProgressStepResponseDto {
    private String title;
    private String description;
    private Date date;
    private List<StepUploadResponseDto> uploads;
}
