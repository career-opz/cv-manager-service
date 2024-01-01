package dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoResponseDto {
    String firstName;
    String lastName;
    String email;
    @Pattern(regexp = "\\d{10,15}") String mobile; // Assuming a pattern for a valid mobile number
    String address;
    String country;
}
