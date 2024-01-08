package dev.careeropz.cvmanagerservice.dto.jobprofile.commondto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum JobProfileStatus {
    PENDING("PENDING"),
    SUCCESS("SUCCESS"),
    PROCESSING("PROCESSING"),
    REJECTED("REJECTED"),
    ABUNDANT("ABUNDANT");

    private final String status;
}
