package dev.careeropz.cvmanagerservice.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum JobProfileModelStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    PROCESSING("PROCESSING"),
    REJECTED("REJECTED"),
    ABUNDANT("ABUNDANT");

    private final String status;
}
