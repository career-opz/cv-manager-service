package dev.careeropz.cvmanagerservice.model.subclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DefaultFiles {
    private FileData cv;
    private FileData coverLetter;
    private FileData userPhoto;
}
