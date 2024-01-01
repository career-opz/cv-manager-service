package dev.careeropz.cvmanagerservice.controller.userprofile;

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
        log.info("UserProfileController::getUserProfileById Fetching user profile for user id: {}", userId);
        UserInfoResponseDto userInfoResponseDto = userProfileService.getUserProfile(userId);
        log.info("UserProfileController::getUserProfileById User profile found for user id: {}", userId);
        return ResponseEntity.ok(userInfoResponseDto);
    }

    @PostMapping
    public ResponseEntity<UserInfoResponseDto> createUserProfile(@RequestBody @Valid UserInfoRequestDto userInfoRequestDto) {
        log.info("UserProfileController::createUserProfile Creating user profile for a user");
        UserInfoResponseDto createdUserProfile = userProfileService.createUserProfile(userInfoRequestDto);
        log.info("UserProfileController::createUserProfile User profile created for a user");
        return ResponseEntity.ok(createdUserProfile);
    }

    @GetMapping("/default-docs/{userid}")
    public ResponseEntity<DefaultFilesResponseDto> getUserDefaultDocs(@PathVariable("userid") String userid){
        log.info("UserProfileController::getUserDefaultDocs Fetching default docs for user with ID: {}", userid);
        DefaultFilesResponseDto defaultFilesResponseDto = userProfileService.getUserDefaultDocs(userid);
        log.info("UserProfileController::getUserDefaultDocs Default docs found for user with ID: {}", userid);
        return ResponseEntity.ok(defaultFilesResponseDto);
    }

    @PutMapping("/default-docs/{userid}")
    public ResponseEntity<DefaultFilesResponseDto> updateUserDefaultDocs(@PathVariable("userid") String userid){
        log.info("UserProfileController::updateUserDefaultDocs Updating default docs for user with ID: {}", userid);
        DefaultFilesResponseDto defaultFilesResponseDto = userProfileService.getUserDefaultDocs(userid);
        log.info("UserProfileController::updateUserDefaultDocs Default docs updated for user with ID: {}", userid);
        return ResponseEntity.ok(defaultFilesResponseDto);
    }

    @PutMapping("/deactivate/{userid}")
    public ResponseEntity<String> deactivateUserProfile(@PathVariable("userid") String userid){
        log.info("UserProfileController::deactivateUserProfile Deactivating user profile for user with ID: {}", userid);
        String response = userProfileService.deactivateUserProfile(userid);
        log.info("UserProfileController::deactivateUserProfile User profile deactivated for user with ID: {}", userid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/activate/{userid}")
    public ResponseEntity<String> activateUserProfile(@PathVariable("userid") String userid){
        log.info("UserProfileController::activateUserProfile Activating user profile for user with ID: {}", userid);
        String response = userProfileService.activateUserProfile(userid);
        log.info("UserProfileController::activateUserProfile User profile activated for user with ID: {}", userid);
        return ResponseEntity.ok(response);
    }
}
