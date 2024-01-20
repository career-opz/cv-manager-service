package dev.careeropz.cvmanagerservice.model.jobprofile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileProgressStep {
    private String title;
    private String description;
    private Date date;
    private ProgressUploads uploads;
}
