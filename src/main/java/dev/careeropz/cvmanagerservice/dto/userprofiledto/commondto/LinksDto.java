package dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksDto {
    private String linkedIn;
    private String github;
    private String portfolio;
}
