package dev.careeropz.cvmanagerservice.dto.jobprofile.commondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileProgressStepDto {
    private Integer progressStepId;
    private String title;
    private String description;
    private Date date;
    private ProgressUploadsDto uploads;
}
