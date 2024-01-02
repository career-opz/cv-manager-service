package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoRequestDto {
    String firstName;
    String lastName;
    @NotNull
    String email;
    @Pattern(regexp = "\\d{10,15}") String mobile; // Assuming a pattern for a valid mobile number
    String address;
    String country;
}
