package dev.careeropz.cvmanagerservice.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum StatusEnum {
    APPLIED("APPLIED"),
    SHORTLISTED("SHORTLISTED"),
    ACTIVE("PROCESSING"),
    REJECTED("REJECTED");

    private final String status;
}
