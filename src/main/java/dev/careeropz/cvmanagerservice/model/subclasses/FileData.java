package dev.careeropz.cvmanagerservice.model.subclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileData {
    private String id;
    private String name;
    private Date dateUploaded;
}