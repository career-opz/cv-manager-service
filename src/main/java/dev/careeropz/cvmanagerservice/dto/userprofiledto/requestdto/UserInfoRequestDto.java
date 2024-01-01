package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

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
        @Valid PersonalInfoRequestDto personalInfo;
        @Valid CareerInfoRequestDto careerInfo;
        @Valid DefaultFilesRequestDto defaultFiles;
        Map<String, String> links;
        @Past Date accountCreatedOn;
}
