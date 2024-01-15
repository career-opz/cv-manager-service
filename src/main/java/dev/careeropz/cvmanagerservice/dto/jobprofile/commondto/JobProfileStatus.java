package dev.careeropz.cvmanagerservice.dto.jobprofile.commondto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum JobProfileStatus {
    PENDING,
    SUCCESS,
    PROCESSING,
    REJECTED,
    ABUNDANT
}
