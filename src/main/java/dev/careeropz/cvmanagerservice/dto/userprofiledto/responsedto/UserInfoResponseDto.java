package dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto;

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
    String id;
    PersonalInfoResponseDto personalInfo;
    CareerInfoResponseDto careerInfo;
    DefaultFilesResponseDto defaultFiles;
    Map<String, String> links;
    Date accountCreatedOn;
    List<String> jobProfiles;
}
