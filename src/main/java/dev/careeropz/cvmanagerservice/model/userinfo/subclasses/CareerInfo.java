package dev.careeropz.cvmanagerservice.model.userinfo.subclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CareerInfo {
    private String currentEmployer;
    private String currentJobTitle;
    private String industry;
}
