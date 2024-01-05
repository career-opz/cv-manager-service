package dev.careeropz.cvmanagerservice.service;

import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.JobProfileRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.JobProfileResponseDto;
import dev.careeropz.cvmanagerservice.exception.DbOperationFailedException;
import dev.careeropz.cvmanagerservice.exception.IncorrectRequestDataException;
import dev.careeropz.cvmanagerservice.exception.ResourceNotFoundException;
import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import dev.careeropz.cvmanagerservice.model.jobprofilemodel.JobProfileModel;
import dev.careeropz.cvmanagerservice.repository.JobProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static dev.careeropz.cvmanagerservice.MessageConstents.ExceptionConstants.*;

@Service
@Slf4j
public class JobProfileService {
    private final UserProfileService userProfileService;
    private final JobProfileRepository jobProfileRepository;
    private final ModelMapper modelMapper;

    public JobProfileService(UserProfileService userProfileService, JobProfileRepository jobProfileRepository, ModelMapper modelMapper) {
        this.userProfileService = userProfileService;
        this.jobProfileRepository = jobProfileRepository;
        this.modelMapper = modelMapper;

        // configuring custom mappers
        addJobProfileToResponseMapper();
    }

    public List<JobProfileResponseDto> getAllJobProfiles(String userId) {
        log.info("JobProfileService::getAllJobProfiles Fetching all job profiles for user id: {} ::ENTER", userId);
        return jobProfileRepository.findAllByUserRef(userId)
                .map(jobProfileModelList -> {
                    log.info("JobProfileService::getAllJobProfiles Fetching all job profiles for user id: {} ::DONE", userId);
                    return jobProfileModelList
                            .stream()
                            .map(jobProfileModel -> modelMapper.map(jobProfileModel, JobProfileResponseDto.class))
                            .collect(Collectors.toList());
                })
                .orElseThrow(() -> new DbOperationFailedException(String.format("%s :%s", DB_OPERATION_FAILED, userId)));
    }

    public JobProfileResponseDto getJobProfile(String jobProfileId) {
        log.info("JobProfileService::getJobProfile Fetching job profile for job profile id: {} ::ENTER", jobProfileId);
        return jobProfileRepository.findById(jobProfileId)
                .map(jobProfileModel -> {
                    log.info("JobProfileService::getJobProfile Fetching job profile for job profile id: {} ::DONE", jobProfileId);
                    return modelMapper.map(jobProfileModel, JobProfileResponseDto.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", JOB_PROFILE_NOT_FOUND, jobProfileId)));
    }

    @Transactional
    public JobProfileResponseDto createJobProfile(String userid, JobProfileRequestDto jobProfileRequestDto) {

        try{
            log.info("JobProfileService::createJobProfile Creating a new job profile ::ENTER");
            UserInfoModel userInfoModel = userProfileService.getUserInfoModel(userid);
            JobProfileModel jobProfileModel = modelMapper.map(jobProfileRequestDto, JobProfileModel.class);
            jobProfileModel.setUserRef(userInfoModel);

            JobProfileModel savedModel = jobProfileRepository.save(jobProfileModel);
            log.info("JobProfileService::createJobProfile Creating a new job profile ::DONE");
            userProfileService.addJobProfileToUserProfile(userid, savedModel);
            return modelMapper.map(savedModel, JobProfileResponseDto.class);
        } catch (MappingException e) {
            log.error("JobProfileService::createJobProfile Error occurred while mapping request to model", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public JobProfileResponseDto updateJobProfile(String jobProfileId, JobProfileRequestDto jobProfileRequestDto) {
        try {
            log.info("JobProfileService::updateJobProfile Updating job profile for job profile id: {} ::ENTER", jobProfileId);
            JobProfileModel jobProfileModel = modelMapper.map(jobProfileRequestDto, JobProfileModel.class);
            jobProfileModel.setId(jobProfileId);
            JobProfileModel savedModel = jobProfileRepository.save(jobProfileModel);
            log.info("JobProfileService::updateJobProfile Updating job profile for job profile id: {} ::DONE", jobProfileId);
            return modelMapper.map(savedModel, JobProfileResponseDto.class);
        } catch (MappingException e) {
            log.error("JobProfileService::updateJobProfile Error occurred while mapping request to model", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public void deleteJobProfile(String userid, String jobProfileId) {
        try{
            log.info("JobProfileService::deleteJobProfile Deleting job profile for job profile id: {} ::ENTER", jobProfileId);
            userProfileService.removeJobProfileFromUserProfile(userid, jobProfileId);
            jobProfileRepository.deleteById(jobProfileId);
            log.info("JobProfileService::deleteJobProfile Deleting job profile for job profile id: {} ::DONE", jobProfileId);
        } catch (Exception e) {
            log.error("JobProfileService::deleteJobProfile Error occurred while deleting job profile for job profile id: {}", jobProfileId, e);
            throw new DbOperationFailedException(String.format("%s :%s", DB_OPERATION_FAILED, jobProfileId));
        }
    }

    private void addJobProfileToResponseMapper() {
        TypeMap<JobProfileModel, JobProfileResponseDto> modelToResMapper = this.modelMapper.createTypeMap(JobProfileModel.class, JobProfileResponseDto.class);
        modelToResMapper.addMappings(
                mapper -> mapper.map(src -> src.getUserRef().getId(), JobProfileResponseDto::setUserRef)
        );
    }
}
