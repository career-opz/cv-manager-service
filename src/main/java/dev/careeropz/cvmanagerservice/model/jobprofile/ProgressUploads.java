package dev.careeropz.cvmanagerservice.model.jobprofile;

import dev.careeropz.cvmanagerservice.model.userinfo.subclasses.FileData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressUploads {
    private FileData cv;
    private FileData coverLetter;
    private List<FileData> other;
}
