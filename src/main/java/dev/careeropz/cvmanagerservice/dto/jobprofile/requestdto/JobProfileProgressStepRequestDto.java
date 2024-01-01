package dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileProgressStepRequestDto {
    private String title;
    private String description;
    private Date date;
    private List<StepUploadRequestDto> uploads;
}