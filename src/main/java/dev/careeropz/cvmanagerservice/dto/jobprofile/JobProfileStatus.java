package dev.careeropz.cvmanagerservice.dto.jobprofile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum JobProfileStatus {
    APPLIED("APPLIED"),
    SHORTLISTED("SHORTLISTED"),
    ACTIVE("PROCESSING"),
    REJECTED("REJECTED");

    private final String status;
}
