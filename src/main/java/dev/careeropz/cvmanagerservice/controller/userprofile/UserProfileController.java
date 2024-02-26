package dev.careeropz.cvmanagerservice.controller.userprofile;

import dev.careeropz.commons.userinfo.UserProfileCheckRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.LinksDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.CareerInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.PersonalInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.UserInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.CareerInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.DefaultFilesDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.PersonalInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.UserInfoResponseDto;
import dev.careeropz.cvmanagerservice.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cv/api/v1/userprofile")
@Slf4j
public class UserProfileController {
    private final UserProfileService userProfileService;

    @GetMapping("/{userid}")
    public ResponseEntity<UserInfoResponseDto> getUserProfileById(@PathVariable("userid") String userId) {
        log.info("getUserProfileById :: userid: {} :: ENTER", userId);
        UserInfoResponseDto userInfoResponseDto = userProfileService.getUserProfile(userId);
        log.info("getUserProfileById :: userid: {} :: DONE", userId);
        return ResponseEntity.ok(userInfoResponseDto);
    }

    @PostMapping
    public ResponseEntity<UserInfoResponseDto> createUserProfile(@RequestBody @Valid UserInfoRequestDto userInfoRequestDto) {
        log.info("createUserProfile :: ENTER");
        UserInfoResponseDto createdUserProfile = userProfileService.createUserProfile(userInfoRequestDto);
        log.info("createUserProfile :: DONE");
        return ResponseEntity.ok(createdUserProfile);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<UserInfoResponseDto> updateUserProfile(@PathVariable("userid") String userId,
                                                                 @RequestBody @Valid UserInfoRequestDto userInfoRequestDto) {
        log.info("updateUserProfile :: userid: {} :: ENTER", userId);
        UserInfoResponseDto updatedUserProfile = userProfileService.updateUserProfile(userId, userInfoRequestDto);
        log.info("updateUserProfile :: userid: {} :: DONE", userId);
        return ResponseEntity.ok(updatedUserProfile);
    }

    @GetMapping("/{userid}/default-docs")
    public ResponseEntity<DefaultFilesDto> getUserDefaultDocs(@PathVariable("userid") String userid) {
        log.info("getUserDefaultDocs :: userid: {} :: ENTER", userid);
        DefaultFilesDto defaultFilesDto = userProfileService.getUserDefaultDocs(userid);
        log.info("getUserDefaultDocs :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(defaultFilesDto);
    }

    @PutMapping("/{userid}/default-docs")
    public ResponseEntity<DefaultFilesDto> updateUserDefaultDocs(@PathVariable("userid") String userid,
                                                                 @RequestBody @Valid dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.DefaultFilesDto defaultFilesDto) {
        log.info("updateUserDefaultDocs :: userid: {} :: ENTER", userid);
        DefaultFilesDto defaultFilesResponseDto = userProfileService.updateUserDefaultDocs(userid, defaultFilesDto);
        log.info("updateUserDefaultDocs :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(defaultFilesResponseDto);
    }

    @PutMapping("/{userid}/personal-info")
    public ResponseEntity<PersonalInfoResponseDto> updatePersonalInfo(@PathVariable("userid") String userid,
                                                                      @RequestBody @Valid PersonalInfoRequestDto personalInfoRequestDto) {
        log.info("updateUserInfo :: userid: {} :: ENTER", userid);
        PersonalInfoResponseDto personalInfo = userProfileService.updatePersonalInfo(userid, personalInfoRequestDto);
        log.info("updateUserInfo :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(personalInfo);
    }

    @PutMapping("/{userid}/career-info")
    public ResponseEntity<CareerInfoResponseDto> updateCareerInfo(@PathVariable("userid") String userid,
                                                                  @RequestBody @Valid CareerInfoRequestDto careerInfoRequestDto) {
        log.info("updateCareerInfo :: userid: {} :: ENTER", userid);
        CareerInfoResponseDto careerInfoResponseDto = userProfileService.updateCareerInfo(userid, careerInfoRequestDto);
        log.info("updateCareerInfo :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(careerInfoResponseDto);
    }

    @PutMapping("/{userid}/social-links")
    public ResponseEntity<LinksDto> updateSocialLinks(@PathVariable("userid") String userid,
                                                      @RequestBody LinksDto linksDto) {
        log.info("updateSocialLinks :: userid: {} :: ENTER", userid);
        LinksDto updatedSocialLinks = userProfileService.updateSocialLinks(userid, linksDto);
        log.info("updateSocialLinks :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(updatedSocialLinks);
    }

    @PutMapping("{userid}/deactivate")
    public ResponseEntity<String> deactivateUserProfile(@PathVariable("userid") String userid) {
        log.info("deactivateUserProfile :: userid: {} :: ENTER", userid);
        String response = userProfileService.deactivateUserProfile(userid);
        log.info("deactivateUserProfile :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userid}/activate")
    public ResponseEntity<String> activateUserProfile(@PathVariable("userid") String userid) {
        log.info("activateUserProfile :: userid: {} :: ENTER", userid);
        String response = userProfileService.activateUserProfile(userid);
        log.info("activateUserProfile :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userid}/check")
    public ResponseEntity<Boolean> checkUserProfileAvailability(@PathVariable("userid") String userid, @RequestBody UserProfileCheckRequestDto userProfileCheckRequestDto) {
        log.info("checkUserProfileAvailability :: userid: {} :: ENTER", userid);
        Boolean response = userProfileService.checkUserProfileAvailability(userid);
        if(Boolean.TRUE.equals(response)) {
            return ResponseEntity.ok(true);
        }
        else{
            PersonalInfoRequestDto personalInfoRequestDto = PersonalInfoRequestDto.builder()
                    .firstName(userProfileCheckRequestDto.getFirstName())
                    .lastName(userProfileCheckRequestDto.getLastName())
                    .email(userProfileCheckRequestDto.getEmail())
                    .build();

            UserInfoRequestDto userInfoRequestDto = UserInfoRequestDto.builder()
                    .userId(userid)
                    .personalInfo(personalInfoRequestDto)
                    .build();

            UserInfoResponseDto userInfoResponseDto = userProfileService.createUserProfile(userInfoRequestDto);
            log.info("checkUserProfileAvailability :: userid: {} :: DONE", userid);
            if(userInfoResponseDto != null) {
                return ResponseEntity.created(URI.create("/cv/api/v1/userprofile/" + userid)).body(true);
            }
            return ResponseEntity.badRequest().body(false);
        }

    }
}
