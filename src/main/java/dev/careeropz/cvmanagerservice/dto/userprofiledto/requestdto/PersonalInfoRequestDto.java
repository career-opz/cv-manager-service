package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

import dev.careeropz.cvmanagerservice.dto.commondto.CountryDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoRequestDto {
    private String firstName;
    private String lastName;

    @NotNull
    @NotBlank
    private String email;

    @Pattern(regexp = "\\d{10,15}", message = "Mobile number must be between 10 and 15 digits")
    private String mobile;

    private String address;
    private CountryDto country;
}
