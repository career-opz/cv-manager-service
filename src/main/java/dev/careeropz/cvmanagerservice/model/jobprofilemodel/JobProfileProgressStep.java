package dev.careeropz.cvmanagerservice.model.jobprofilemodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileProgressStep {
    private String title;
    private String description;
    private Date date;
    private List<StepUpload> uploads;
}
