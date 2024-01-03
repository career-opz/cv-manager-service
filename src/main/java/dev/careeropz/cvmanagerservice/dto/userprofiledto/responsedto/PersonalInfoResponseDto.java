package dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto;

import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.CountryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;
    private CountryDto country;
}
