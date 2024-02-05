package dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto;

import dev.careeropz.commons.dto.FileDataDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.DefaultFilesDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.LinksDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponseDto {
    private String userId;
    private PersonalInfoResponseDto personalInfo;
    private CareerInfoResponseDto careerInfo;
    private DefaultFilesDto defaultFiles;
    private FileDataDto profilePicture;
    private LinksDto links;
    private Date accountCreatedOn;
    private Date lastLoginOn;
    private List<String> jobProfiles;
}
