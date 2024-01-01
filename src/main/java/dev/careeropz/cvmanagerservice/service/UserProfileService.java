package dev.careeropz.cvmanagerservice.service;

import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.CareerInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.DefaultFilesRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.PersonalInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.UserInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.DefaultFilesResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.UserInfoResponseDto;
import dev.careeropz.cvmanagerservice.exception.IncorrectRequestDataException;
import dev.careeropz.cvmanagerservice.exception.ResourceNotFoundException;
import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import dev.careeropz.cvmanagerservice.model.jobprofilemodel.JobProfileModel;
import dev.careeropz.cvmanagerservice.model.subclasses.CareerInfo;
import dev.careeropz.cvmanagerservice.model.subclasses.DefaultFiles;
import dev.careeropz.cvmanagerservice.model.subclasses.PersonalInfo;
import dev.careeropz.cvmanagerservice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dev.careeropz.cvmanagerservice.MessageConstents.ExceptionConstants.*;
import static dev.careeropz.cvmanagerservice.service.ServiceConstants.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserProfileService {
    private final UserInfoRepository userInfoRepository;
    private final ModelMapper modelMapper;

    public UserInfoResponseDto getUserProfile(String userId) {
        log.info("UserProfileService::getUserProfileInfo Fetching user profile for user id: {}", userId);
        return userInfoRepository.findById(userId)
                .map(userInfo -> {
                    log.info("UserProfileService::getUserProfileInfo User profile found for user id: {}", userId);
                    return modelMapper.map(userInfo, UserInfoResponseDto.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId)));
    }

    public UserInfoResponseDto createUserProfile(UserInfoRequestDto userInfoRequestDto) {
        try {
            log.info("UserProfileService::createUserProfile Creating user profile for a user");
            UserInfoModel requestMappedModel = modelMapper.map(userInfoRequestDto, UserInfoModel.class);
            UserInfoModel savedModel = userInfoRepository.save(requestMappedModel);
            log.info("UserProfileService::createUserProfile User profile created for a user");
            return modelMapper.map(savedModel, UserInfoResponseDto.class);
        } catch (MappingException e) {
            log.error("UserProfileService::createUserProfile Error occurred while mapping request to model", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public DefaultFilesResponseDto getUserDefaultDocs(String userId) {
        log.info("UserProfileService::getUserDefaultDocs Fetching default docs for user with ID: {}", userId);
        return userInfoRepository.findById(userId)
                .map(userInfo -> {
                    log.info("UserProfileService::getUserDefaultDocs Default docs found for user with ID: {}", userId);
                    return modelMapper.map(userInfo.getDefaultFiles(), DefaultFilesResponseDto.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId)));
    }

    public DefaultFilesResponseDto updateUserDefaultDocs(String userId, DefaultFilesRequestDto defaultFilesRequestDto) {
        try {
            log.info("UserProfileService::updateUserDefaultDocs Updating default docs for user with ID: {}", userId);
            Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
            if (existingUserOptional.isEmpty()) {
                log.error("UserProfileService::updateUserDefaultDocs User with ID {} not found", userId);
                throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
            }

            UserInfoModel existingUser = existingUserOptional.get();
            updateDefaultInfo(defaultFilesRequestDto, existingUser);

            UserInfoModel updatedModel = userInfoRepository.save(existingUser);
            log.info("UserProfileService::updateUserDefaultDocs Default docs updated for user with ID: {}", userId);

            return modelMapper.map(updatedModel.getDefaultFiles(), DefaultFilesResponseDto.class);
        } catch (MappingException e) {
            log.error("UserProfileService::updateUserDefaultDocs Error occurred while mapping request to model", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public UserInfoResponseDto addJobProfileToUserProfile(String userId, JobProfileModel jobProfile) {
        log.info("UserProfileService::addJobProfileToUserProfile Adding job profile to user profile for user with ID: {}", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.error("UserProfileService::addJobProfileToUserProfile User with ID {} not found", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }

        UserInfoModel existingUser = existingUserOptional.get();
        existingUser.getJobProfiles().add(jobProfile);

        UserInfoModel updatedModel = userInfoRepository.save(existingUser);
        log.info("UserProfileService::addJobProfileToUserProfile Job profile added to user profile for user with ID: {}", userId);

        return modelMapper.map(updatedModel, UserInfoResponseDto.class);
    }

    public String deactivateUserProfile(String userId) {
        log.info("UserProfileService::deactivateUserProfile Deactivating user profile for user with ID: {}", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.error("UserProfileService::deactivateUserProfile User with ID {} not found", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }

        UserInfoModel existingUser = existingUserOptional.get();
        deactivateUser(existingUser);

        userInfoRepository.save(existingUser);
        log.info("UserProfileService::deactivateUserProfile User profile deactivated for user with ID: {}", userId);

        return String.format("%s :%s", USER_DEACTIVATED, userId);
    }

    public String activateUserProfile(String userId) {
        log.info("UserProfileService::activateUserProfile Activating user profile for user with ID: {}", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.error("UserProfileService::activateUserProfile User with ID {} not found", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }

        UserInfoModel existingUser = existingUserOptional.get();
        activateUser(existingUser);

        userInfoRepository.save(existingUser);
        log.info("UserProfileService::activateUserProfile User profile activated for user with ID: {}", userId);

        return String.format("%s :%s", USER_ACTIVATED, userId);
    }

    public UserInfoResponseDto updateUserProfile(String userId, UserInfoRequestDto userInfoRequestDto) {
        try {
            log.info("UserProfileService::updateUserProfile Updating user profile for user with ID: {}", userId);
            Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
            if (existingUserOptional.isEmpty()) {
                log.error("UserProfileService::updateUserProfile User with ID {} not found", userId);
                throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
            }

            UserInfoModel existingUser = existingUserOptional.get();
            modelMapper.map(userInfoRequestDto, existingUser);

            UserInfoModel updatedModel = userInfoRepository.save(existingUser);
            log.info("UserProfileService::updateUserProfile User profile updated for user with ID: {}", userId);

            return modelMapper.map(updatedModel, UserInfoResponseDto.class);
        } catch (MappingException e) {
            log.error("UserProfileService::updateUserProfile Error occurred while mapping request to model", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    private UserInfoModel updateDefaultInfo(DefaultFilesRequestDto defaultFilesRequestDto, UserInfoModel targetModel) {
        DefaultFiles defaultFiles = modelMapper.map(defaultFilesRequestDto, DefaultFiles.class);
        targetModel.setDefaultFiles(defaultFiles);
        return targetModel;
    }

    private UserInfoModel updatePersonalInfo(PersonalInfoRequestDto personalInfoRequestDto, UserInfoModel targetModel) {
        PersonalInfo personalInfo = modelMapper.map(personalInfoRequestDto, PersonalInfo.class);
        targetModel.setPersonalInfo(personalInfo);
        return targetModel;
    }

    private UserInfoModel updateCareerInfo(CareerInfoRequestDto careerInfoRequestDto, UserInfoModel targetModel) {
        CareerInfo careerInfo = modelMapper.map(careerInfoRequestDto, CareerInfo.class);
        targetModel.setCareerInfo(careerInfo);
        return targetModel;
    }

    private UserInfoModel deactivateUser(UserInfoModel targetModel) {
        targetModel.setAccountActive(false);
        return targetModel;
    }

    private UserInfoModel activateUser(UserInfoModel targetModel) {
        targetModel.setAccountActive(true);
        return targetModel;
    }
}
