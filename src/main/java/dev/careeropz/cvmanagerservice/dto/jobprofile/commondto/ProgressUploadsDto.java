package dev.careeropz.cvmanagerservice.dto.jobprofile.commondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressUploadsDto {
    private FileDataDto cv;
    private FileDataDto coverLetter;
    private List<FileDataDto> other;
}
