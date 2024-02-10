package dev.careeropz.cvmanagerservice.model.jobprofile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileProgressStepModel {
    private Integer progressStepId;
    private String uniqueId;
    private String title;
    private String description;
    private Date date;
    private ProgressUploadsModel uploads;
}
