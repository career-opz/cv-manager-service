package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.LinksDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequestDto{
        @Valid private PersonalInfoRequestDto personalInfo;
        @Valid private CareerInfoRequestDto careerInfo;
        @Valid private DefaultFilesRequestDto defaultFiles;
        @Valid private FileDataRequestDto profilePicture;
        @Valid private LinksDto links;
        @Past private Date accountCreatedOn;
        @Past private Date lastLoginOn;
}
