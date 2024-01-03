package dev.careeropz.cvmanagerservice.controller.userprofile;

import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.DefaultFilesRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.UserInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.DefaultFilesResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.UserInfoResponseDto;
import dev.careeropz.cvmanagerservice.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
