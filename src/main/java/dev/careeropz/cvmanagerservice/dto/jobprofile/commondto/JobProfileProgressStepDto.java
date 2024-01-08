package dev.careeropz.cvmanagerservice.dto.jobprofile.commondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobProfileProgressStepDto {
    private int id;
    private String title;
    private String description;
    private Date date;
    private ProgressUploadsDto uploads;
}
