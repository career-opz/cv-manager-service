package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

import dev.careeropz.cvmanagerservice.dto.commondto.FileDataDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.DefaultFilesDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.LinksDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequestDto{
        @Valid private PersonalInfoRequestDto personalInfo;
        @Valid private CareerInfoRequestDto careerInfo;
        @Valid private DefaultFilesDto defaultFiles;
        @Valid private FileDataDto profilePicture;
        @Valid private LinksDto links;
        @Past private Date accountCreatedOn;
        @Past private Date lastLoginOn;
}
