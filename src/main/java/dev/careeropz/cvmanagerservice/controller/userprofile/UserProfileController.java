package dev.careeropz.cvmanagerservice.controller.userprofile;

import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.CareerInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.DefaultFilesRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.PersonalInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.UserInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.CareerInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.DefaultFilesResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.PersonalInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.UserInfoResponseDto;
import dev.careeropz.cvmanagerservice.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<DefaultFilesResponseDto> getUserDefaultDocs(@PathVariable("userid") String userid){
        log.info("getUserDefaultDocs :: userid: {} :: ENTER", userid);
        DefaultFilesResponseDto defaultFilesResponseDto = userProfileService.getUserDefaultDocs(userid);
        log.info("getUserDefaultDocs :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(defaultFilesResponseDto);
    }

    @PutMapping("/{userid}/default-docs")
    public ResponseEntity<DefaultFilesResponseDto> updateUserDefaultDocs(@PathVariable("userid") String userid,
                                                                         @RequestBody @Valid DefaultFilesRequestDto defaultFilesRequestDto){
        log.info("updateUserDefaultDocs :: userid: {} :: ENTER", userid);
        DefaultFilesResponseDto defaultFilesResponseDto = userProfileService.updateUserDefaultDocs(userid, defaultFilesRequestDto);
        log.info("updateUserDefaultDocs :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(defaultFilesResponseDto);
    }

    @PutMapping("/{userid}/user-info")
    public ResponseEntity<PersonalInfoResponseDto> updatePersonalInfo(@PathVariable("userid") String userid,
                                                              @RequestBody @Valid PersonalInfoRequestDto personalInfoRequestDto){
        log.info("updateUserInfo :: userid: {} :: ENTER", userid);
        PersonalInfoResponseDto personalInfo = userProfileService.updatePersonalInfo(userid, personalInfoRequestDto);
        log.info("updateUserInfo :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(personalInfo);
    }

    @PutMapping("/{userid}/career-info")
    public ResponseEntity<CareerInfoResponseDto> updateCareerInfo(@PathVariable("userid") String userid,
                                                                  @RequestBody @Valid CareerInfoRequestDto careerInfoRequestDto){
        log.info("updateCareerInfo :: userid: {} :: ENTER", userid);
        CareerInfoResponseDto careerInfoResponseDto = userProfileService.updateCareerInfo(userid, careerInfoRequestDto);
        log.info("updateCareerInfo :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(careerInfoResponseDto);
    }

    @PutMapping("/{userid}/social-links")
    public ResponseEntity<Map<String, String>> updateSocialLinks(@PathVariable("userid") String userid,
                                                                 @RequestBody Map<String, String> socialLinks){
        log.info("updateSocialLinks :: userid: {} :: ENTER", userid);
        Map<String, String> updatedSocialLinks = userProfileService.updateSocialLinks(userid, socialLinks);
        log.info("updateSocialLinks :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(updatedSocialLinks);
    }

    @PutMapping("{userid}/deactivate")
    public ResponseEntity<String> deactivateUserProfile(@PathVariable("userid") String userid){
        log.info("deactivateUserProfile :: userid: {} :: ENTER", userid);
        String response = userProfileService.deactivateUserProfile(userid);
        log.info("deactivateUserProfile :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{userid}/activate")
    public ResponseEntity<String> activateUserProfile(@PathVariable("userid") String userid){
        log.info("activateUserProfile :: userid: {} :: ENTER", userid);
        String response = userProfileService.activateUserProfile(userid);
        log.info("activateUserProfile :: userid: {} :: DONE", userid);
        return ResponseEntity.ok(response);
    }
}
