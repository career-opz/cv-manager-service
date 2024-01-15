package dev.careeropz.cvmanagerservice.service;

import dev.careeropz.cvmanagerservice.dto.userprofiledto.commondto.LinksDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.CareerInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.DefaultFilesRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.PersonalInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.requestdto.UserInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.CareerInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.DefaultFilesResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.PersonalInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.userprofiledto.responsedto.UserInfoResponseDto;
import dev.careeropz.cvmanagerservice.exception.IncorrectRequestDataException;
import dev.careeropz.cvmanagerservice.exception.ResourceNotFoundException;
import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import dev.careeropz.cvmanagerservice.model.jobprofilemodel.JobProfileModel;
import dev.careeropz.cvmanagerservice.model.subclasses.CareerInfo;
import dev.careeropz.cvmanagerservice.model.subclasses.DefaultFiles;
import dev.careeropz.cvmanagerservice.model.subclasses.Links;
import dev.careeropz.cvmanagerservice.model.subclasses.PersonalInfo;
import dev.careeropz.cvmanagerservice.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static dev.careeropz.cvmanagerservice.constant.ExceptionConstants.*;
import static dev.careeropz.cvmanagerservice.service.ServiceConstants.*;

@Service
@Slf4j
public class UserProfileService {
    private final UserInfoRepository userInfoRepository;
    private final ModelMapper modelMapper;

    public UserProfileService(UserInfoRepository userInfoRepository, ModelMapper modelMapper) {
        this.userInfoRepository = userInfoRepository;
        this.modelMapper = modelMapper;
        addUserProfileToResponseMapper();
    }

    public UserInfoResponseDto getUserProfile(String userId) {
        log.info("getUserProfile :: userid: {} :: ENTER", userId);
        return userInfoRepository.findById(userId)
                .map(userInfo -> {
                    log.info("getUserProfile :: userInfoRepository:findById :: userid: {} :: DONE", userId);
                    UserInfoResponseDto userInfoResponseDto = modelMapper.map(userInfo, UserInfoResponseDto.class);
                    log.info("getUserProfile :: userid: {} :: DONE", userId);
                    return userInfoResponseDto;
                })
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId)));
    }

    public UserInfoModel getUserInfoModel(String userId) {
        log.info("getUserInfoModel :: userid: {} :: ENTER", userId);
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId)));
    }

    public UserInfoResponseDto createUserProfile(UserInfoRequestDto userInfoRequestDto) {
        try {
            log.info("createUserProfile :: ENTER");
            UserInfoModel requestMappedModel = modelMapper.map(userInfoRequestDto, UserInfoModel.class);
            Optional<UserInfoModel> existingUser = userInfoRepository.findByPersonalInfoEmail(requestMappedModel.getPersonalInfo().getEmail());
            if (existingUser.isPresent()) {
                log.warn("createUserProfile :: email: {} :: already exists", requestMappedModel.getPersonalInfo().getEmail());
                throw new IncorrectRequestDataException(String.format("%s :%s", USER_ALREADY_EXISTS, requestMappedModel.getPersonalInfo().getEmail()));
            }
            UserInfoModel savedModel = userInfoRepository.save(requestMappedModel);
            log.info("createUserProfile :: email: {} :: DONE", requestMappedModel.getPersonalInfo().getEmail());
            return modelMapper.map(savedModel, UserInfoResponseDto.class);
        } catch (MappingException e) {
            log.error("createUserProfile :: mapping request to model :: MappingException occurred", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public DefaultFilesResponseDto getUserDefaultDocs(String userId) {
        log.info("getUserDefaultDocs :: userid: {} :: ENTER", userId);
        return userInfoRepository.findById(userId)
                .map(userInfo -> {
                    log.info("getUserDefaultDocs :: userInfoRepository:findById :: userid: {} :: DONE", userId);
                    DefaultFilesResponseDto defaultFilesResponseDto = modelMapper.map(userInfo.getDefaultFiles(), DefaultFilesResponseDto.class);
                    log.info("getUserDefaultDocs :: userid: {} :: DONE", userId);
                    return defaultFilesResponseDto;
                })
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId)));
    }

    public DefaultFilesResponseDto updateUserDefaultDocs(String userId, DefaultFilesRequestDto defaultFilesRequestDto) {
        try {
            log.info("updateUserDefaultDocs :: userid: {} :: ENTER", userId);
            Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
            if (existingUserOptional.isEmpty()) {
                log.warn("updateUserDefaultDocs :: userid: {} :: NOT FOUND", userId);
                throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
            }
            UserInfoModel existingUser = existingUserOptional.get();
            updateDefaultInfo(defaultFilesRequestDto, existingUser);

            UserInfoModel updatedModel = userInfoRepository.save(existingUser);
            log.info("updateUserDefaultDocs :: userid: {} :: DONE", userId);

            return modelMapper.map(updatedModel.getDefaultFiles(), DefaultFilesResponseDto.class);
        } catch (MappingException e) {
            log.error("updateUserDefaultDocs :: mapping request to model :: MappingException occurred", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public PersonalInfoResponseDto updatePersonalInfo(String userId, PersonalInfoRequestDto personalInfoRequestDto) {
        try {
            log.info("updateUserInfo :: userid: {} :: ENTER", userId);
            Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
            if (existingUserOptional.isEmpty()) {
                log.warn("updateUserInfo :: userid: {} :: NOT FOUND", userId);
                throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
            }
            UserInfoModel existingUser = existingUserOptional.get();
            updatePersonalInfo(personalInfoRequestDto, existingUser);

            UserInfoModel updatedModel = userInfoRepository.save(existingUser);
            log.info("updateUserInfo :: userid: {} :: DONE", userId);

            return modelMapper.map(updatedModel.getPersonalInfo(), PersonalInfoResponseDto.class);
        } catch (MappingException e) {
            log.error("updateUserInfo :: mapping request to model :: MappingException occurred", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public CareerInfoResponseDto updateCareerInfo(String userId, CareerInfoRequestDto careerInfoRequestDto) {
        try {
            log.info("updateCareerInfo :: userid: {} :: ENTER", userId);
            Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
            if (existingUserOptional.isEmpty()) {
                log.warn("updateCareerInfo :: userid: {} :: NOT FOUND", userId);
                throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
            }
            UserInfoModel existingUser = existingUserOptional.get();
            updateCareerInfo(careerInfoRequestDto, existingUser);

            UserInfoModel updatedModel = userInfoRepository.save(existingUser);
            log.info("updateCareerInfo :: userid: {} :: DONE", userId);

            return modelMapper.map(updatedModel.getCareerInfo(), CareerInfoResponseDto.class);
        } catch (MappingException e) {
            log.error("updateCareerInfo :: mapping request to model :: MappingException occurred", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public LinksDto updateSocialLinks(String userId, LinksDto linksDto) {
        log.info("updateSocialLinks :: userid: {} :: ENTER", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.warn("updateSocialLinks :: userid: {} :: NOT FOUND", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }
        UserInfoModel existingUser = existingUserOptional.get();
        Links updatesLinks = modelMapper.map(linksDto, Links.class);
        existingUser.setLinks(updatesLinks);

        UserInfoModel updatedModel = userInfoRepository.save(existingUser);
        log.info("updateSocialLinks :: userid: {} :: DONE", userId);

        return modelMapper.map(updatedModel.getLinks(), LinksDto.class);
    }

    public String deactivateUserProfile(String userId) {
        log.info("deactivateUserProfile :: userid: {} :: ENTER", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.warn("deactivateUserProfile :: userid: {} :: NOT FOUND", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }

        UserInfoModel existingUser = existingUserOptional.get();
        deactivateUser(existingUser);

        userInfoRepository.save(existingUser);
        log.info("deactivateUserProfile :: userid: {} :: DONE", userId);
        return String.format("%s :%s", USER_DEACTIVATED, userId);
    }

    public String activateUserProfile(String userId) {
        log.info("activateUserProfile :: userid: {} :: ENTER", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.warn("activateUserProfile :: userid: {} :: NOT FOUND", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }

        UserInfoModel existingUser = existingUserOptional.get();
        activateUser(existingUser);

        userInfoRepository.save(existingUser);
        log.info("activateUserProfile :: userid: {} :: DONE", userId);
        return String.format("%s :%s", USER_ACTIVATED, userId);
    }

    public UserInfoResponseDto updateUserProfile(String userId, UserInfoRequestDto userInfoRequestDto) {
        log.info("updateUserProfile :: userid: {} :: ENTER", userId);
        try {
            Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
            if (existingUserOptional.isEmpty()) {
                log.warn("updateUserProfile :: userid: {} :: NOT FOUND", userId);
                throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
            }
            // TODO: Handle the email, it should not be updated
            UserInfoModel existingUser = existingUserOptional.get();
            modelMapper.map(userInfoRequestDto, existingUser);

            UserInfoModel updatedModel = userInfoRepository.save(existingUser);
            log.info("updateUserProfile :: userid: {} :: DONE", userId);
            return modelMapper.map(updatedModel, UserInfoResponseDto.class);
        } catch (MappingException e) {
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    @Transactional
    public void addJobProfileToUserProfile(String userId, JobProfileModel jobProfile) {
        log.info("addJobProfileToUserProfile :: userid: {} :: ENTER", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.warn("addJobProfileToUserProfile :: userid: {} :: NOT FOUND", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }
        UserInfoModel existingUser = existingUserOptional.get();
        if(existingUser.getJobProfiles() == null){
            existingUser.setJobProfiles(new ArrayList<>());
        }
        existingUser.getJobProfiles().add(jobProfile);

        userInfoRepository.save(existingUser);
        log.info("addJobProfileToUserProfile :: userid: {} :: DONE", userId);
    }

    @Transactional
    public void removeJobProfileFromUserProfile(String userId, String jobProfileId) {
        log.info("removeJobProfileFromUserProfile :: userid: {} :: ENTER", userId);
        Optional<UserInfoModel> existingUserOptional = userInfoRepository.findById(userId);
        if (existingUserOptional.isEmpty()) {
            log.warn("removeJobProfileFromUserProfile :: userid: {} :: NOT FOUND", userId);
            throw new ResourceNotFoundException(String.format("%s :%s", USER_NOT_FOUND, userId));
        }
        UserInfoModel existingUser = existingUserOptional.get();
        existingUser.getJobProfiles().removeIf(jobProfileModel -> jobProfileModel.getId().equals(jobProfileId));

        userInfoRepository.save(existingUser);
        log.info("removeJobProfileFromUserProfile :: userid: {} :: DONE", userId);
    }

    private void updateDefaultInfo(DefaultFilesRequestDto defaultFilesRequestDto, UserInfoModel targetModel) {
        DefaultFiles defaultFiles = modelMapper.map(defaultFilesRequestDto, DefaultFiles.class);
        targetModel.setDefaultFiles(defaultFiles);
    }

    private void updatePersonalInfo(PersonalInfoRequestDto personalInfoRequestDto, UserInfoModel targetModel) {
        PersonalInfo personalInfo = modelMapper.map(personalInfoRequestDto, PersonalInfo.class);
        targetModel.setPersonalInfo(personalInfo);
    }

    private void updateCareerInfo(CareerInfoRequestDto careerInfoRequestDto, UserInfoModel targetModel) {
        CareerInfo careerInfo = modelMapper.map(careerInfoRequestDto, CareerInfo.class);
        targetModel.setCareerInfo(careerInfo);
    }

    private void deactivateUser(UserInfoModel targetModel) {
        targetModel.setAccountActive(false);
    }

    private void activateUser(UserInfoModel targetModel) {
        targetModel.setAccountActive(true);
    }

    private void addUserProfileToResponseMapper(){
        Condition<Collection<JobProfileModel>, Collection<String>> hasJobProfile = ctx -> ctx.getSource() != null && !ctx.getSource().isEmpty();
        Converter<Collection<JobProfileModel>, Collection<String>> jobProfileConverter = ctx -> ctx.getSource()
                .stream()
                .map(JobProfileModel::getId)
                .toList();
        modelMapper.typeMap(UserInfoModel.class, UserInfoResponseDto.class)
                .addMappings(mapper -> mapper.when(hasJobProfile).using(jobProfileConverter)
                        .map(UserInfoModel::getJobProfiles, UserInfoResponseDto::setJobProfiles));
    }
}
