package dev.careeropz.cvmanagerservice.model.subclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private String code;
    private String label;
    private String phone;
    Boolean suggested;
}
