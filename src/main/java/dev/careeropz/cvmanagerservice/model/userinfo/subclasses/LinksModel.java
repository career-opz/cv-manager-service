package dev.careeropz.cvmanagerservice.model.userinfo.subclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksModel {
    private String linkedIn;
    private String github;
    private String portfolio;
}
