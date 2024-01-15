package dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto;

import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.LinksDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {
    private String id;
    private PersonalInfoResponseDto personalInfo;
    private CareerInfoResponseDto careerInfo;
    private DefaultFilesResponseDto defaultFiles;
    private FileDataResponseDto profilePicture;
    private LinksDto links;
    private Date accountCreatedOn;
    private Date lastLoginOn;
    private List<String> jobProfiles;
}
