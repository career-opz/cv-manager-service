package dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerInfoRequestDto {
    String currentEmployer;
    String currentJobTitle;
    String industry;
}
