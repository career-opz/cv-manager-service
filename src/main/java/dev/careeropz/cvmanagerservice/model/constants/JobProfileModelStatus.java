package dev.careeropz.cvmanagerservice.model.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum JobProfileModelStatus {
    Pending,
    Success,
    Processing,
    Rejected,
    Abundant
}
