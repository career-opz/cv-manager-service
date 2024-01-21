package dev.careeropz.cvmanagerservice.model.userinfo.subclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfoModel {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;
    private CountryModel country;
}
