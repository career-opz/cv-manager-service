package dev.careeropz.cvmanagerservice.model.jobprofile;

import dev.careeropz.cvmanagerservice.model.userinfo.subclasses.FileDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgressUploadsModel {
    private FileDataModel cv;
    private FileDataModel coverLetter;
    private List<FileDataModel> other;
}
