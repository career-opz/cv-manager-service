package dev.careeropz.cvmanagerservice.controller.jobprofiles;

import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.JobProfileRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.JobProfileResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.UserInfoResponseDto;
import dev.careeropz.cvmanagerservice.service.JobProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cv/api/v1/job-profile/{userid}")
@Slf4j
public class JobProfilesController {
    private final JobProfileService jobProfileService;

    @GetMapping
    public ResponseEntity<List<JobProfileResponseDto>> getAllJobProfiles(@PathVariable("userid") String userId) {
        log.info("JobProfilesController::getAllJobProfiles Fetching all job profiles for user id: {} ::ENTER", userId);
        List<JobProfileResponseDto> jobProfiles = jobProfileService.getAllJobProfiles(userId);
        log.info("JobProfilesController::getAllJobProfiles Fetching all job profiles for user id: {} ::DONE", userId);
        return ResponseEntity.ok(jobProfiles);
    }

    @PostMapping
    public ResponseEntity<JobProfileResponseDto> createJobProfile(@PathVariable("userid") String userId,
                                                                  @RequestBody @Valid JobProfileRequestDto jobProfileRequestDto) {
        log.info("JobProfilesController::createJobProfile Creating job profile for a user id: {} ::ENTER", userId);
        jobProfileRequestDto.setUserId(userId);
        JobProfileResponseDto jobProfileResponseDto = jobProfileService.createJobProfile(userId, jobProfileRequestDto);
        log.info("JobProfilesController::createJobProfile Creating job profile for a user id: {} ::DONE", userId);
        return ResponseEntity.ok(jobProfileResponseDto);
    }

    @GetMapping("/{job-profile-id}")
    public ResponseEntity<JobProfileResponseDto> getJobProfile(@PathVariable("userid") String userId,
                                                               @PathVariable("job-profile-id") String jobProfileId) {
        log.info("JobProfilesController::getJobProfile Fetching job profile for job profile id: {} ::ENTER", jobProfileId);
        JobProfileResponseDto jobProfileResponseDto = jobProfileService.getJobProfile(jobProfileId);
        log.info("JobProfilesController::getJobProfile Fetching job profile for job profile id: {} ::DONE", jobProfileId);
        return ResponseEntity.ok(jobProfileResponseDto);
    }

    @PutMapping("/{job-profile-id}")
    public ResponseEntity<JobProfileResponseDto> updateJobProfileById(@PathVariable("userid") String userId,
                                                                      @PathVariable("job-profile-id") String jobProfileId,
                                                                      @RequestBody @Valid JobProfileRequestDto jobProfileRequestDto) {
        log.info("JobProfilesController::updateJobProfileById Updating job profile for job profile id: {} ::ENTER", jobProfileId);
        JobProfileResponseDto jobProfileResponseDto = jobProfileService.updateJobProfile(jobProfileId, jobProfileRequestDto);
        log.info("JobProfilesController::updateJobProfileById Updating job profile for job profile id: {} ::DONE", jobProfileId);
        return ResponseEntity.ok(jobProfileResponseDto);
    }

    @DeleteMapping("/{job-profile-id}")
    public ResponseEntity<String> deleteJobProfileById(@PathVariable("userid") String userId,
                                                                    @PathVariable("job-profile-id") String jobProfileId) {
        log.info("JobProfilesController::deleteJobProfileById Deleting job profile for job profile id: {} ::ENTER", jobProfileId);
        jobProfileService.deleteJobProfile(userId, jobProfileId);
        return ResponseEntity.ok("Job profile deleted successfully");
    }
}
