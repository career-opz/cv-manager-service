package dev.careeropz.cvmanagerservice.controller.jobprofile;

import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.JobProfileProgressStepDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.BasicInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.JobProfileRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.BasicInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.JobProfileResponseDto;
import dev.careeropz.cvmanagerservice.dto.pagination.CommonPaginationRequest;
import dev.careeropz.cvmanagerservice.service.JobProfileService;
import dev.careeropz.cvmanagerservice.service.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cv/api/v1/job-profile/user/{userid}")
@Slf4j
public class JobProfilesController {
    private final JobProfileService jobProfileService;

    @GetMapping
    public ResponseEntity<PageResponse<JobProfileResponseDto>> getAllJobProfiles(@PathVariable("userid") String userId,
                                                                                 @RequestParam(value = "page-no", defaultValue = "1") int pageNo,
                                                                                 @RequestParam(value = "page-size", defaultValue = "10") int pageSize,
                                                                                 @RequestParam(value = "sort-by", defaultValue = "id") String sortBy,
                                                                                 @RequestParam(value = "sort-direction", defaultValue = "asc") String sortDirection) {
        log.info("JobProfilesController::getAllJobProfiles Fetching all job profiles for user id: {} ::ENTER", userId);
        CommonPaginationRequest paginationRequest = new CommonPaginationRequest(--pageNo, pageSize, sortBy, sortDirection);
        PageResponse<JobProfileResponseDto> jobProfiles = jobProfileService.getAllJobProfiles(userId, paginationRequest);
        log.info("JobProfilesController::getAllJobProfiles Fetching all job profiles for user id: {} ::DONE", userId);
        return ResponseEntity.ok(jobProfiles);
    }

    @PostMapping
    public ResponseEntity<JobProfileResponseDto> createJobProfile(@PathVariable("userid") String userId,
                                                                  @RequestBody @Valid JobProfileRequestDto jobProfileRequestDto) {
        log.info("JobProfilesController::createJobProfile Creating job profile for a user id: {} ::ENTER", userId);
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
        JobProfileResponseDto jobProfileResponseDto = jobProfileService.updateJobProfile(userId, jobProfileId, jobProfileRequestDto);
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

    @PutMapping("/{job-profile-id}/progress-step")
    public ResponseEntity<JobProfileResponseDto> updateJobProfileProgressStep(@PathVariable("userid") String userId,
                                                                              @PathVariable("job-profile-id") String jobProfileId,
                                                                              @RequestBody @Valid JobProfileProgressStepDto jobProfileProgressStepDto) {
        log.info("JobProfilesController::updateJobProfileProgressStep Updating job profile progress step for job profile id: {} ::ENTER", jobProfileId);
        JobProfileResponseDto jobProfileResponseDto = jobProfileService.updateJobProfileProgressStep(userId, jobProfileId, jobProfileProgressStepDto);
        log.info("JobProfilesController::updateJobProfileProgressStep Updating job profile progress step for job profile id: {} ::DONE", jobProfileId);
        return ResponseEntity.ok(jobProfileResponseDto);
    }

    @PutMapping("/{job-profile-id}/basic-info")
    public ResponseEntity<BasicInfoResponseDto> updateJobProfileBasicInfo(@PathVariable("userid") String userId,
                                                                           @PathVariable("job-profile-id") String jobProfileId,
                                                                           @RequestBody @Valid BasicInfoRequestDto basicInfoRequestDto) {
        log.info("JobProfilesController::updateJobProfileBasicInfo Updating job profile basic info for job profile id: {} ::ENTER", jobProfileId);
        BasicInfoResponseDto basicInfoResponseDto = jobProfileService.updateJobProfileBasicInfo(userId, jobProfileId, basicInfoRequestDto);
        log.info("JobProfilesController::updateJobProfileBasicInfo Updating job profile basic info for job profile id: {} ::DONE", jobProfileId);
        return ResponseEntity.ok(basicInfoResponseDto);
    }
}
