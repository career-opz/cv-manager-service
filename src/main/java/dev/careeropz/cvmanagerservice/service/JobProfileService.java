package dev.careeropz.cvmanagerservice.service;

import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.JobProfileProgressStepDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.BasicInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.JobProfileRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.BasicInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.JobProfileResponseDto;
import dev.careeropz.cvmanagerservice.dto.pagination.CommonPaginationRequest;
import dev.careeropz.cvmanagerservice.exception.DbOperationFailedException;
import dev.careeropz.cvmanagerservice.exception.IncorrectRequestDataException;
import dev.careeropz.cvmanagerservice.exception.ResourceNotFoundException;
import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import dev.careeropz.cvmanagerservice.model.jobprofilemodel.BasicInfo;
import dev.careeropz.cvmanagerservice.model.jobprofilemodel.JobProfileModel;
import dev.careeropz.cvmanagerservice.model.jobprofilemodel.JobProfileProgressStep;
import dev.careeropz.cvmanagerservice.repository.JobProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static dev.careeropz.cvmanagerservice.constant.ExceptionConstants.*;

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

    public List<JobProfileResponseDto> getAllJobProfiles(String userId, CommonPaginationRequest paginationRequest) {
        log.info("JobProfileService::getAllJobProfiles Fetching all job profiles for user id: {} ::ENTER", userId);
        Pageable pageable = PageRequest.of(paginationRequest.getPageNo(), paginationRequest.getPageSize());
        return jobProfileRepository.findByUserRef(new ObjectId(userId), pageable)
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

        try {
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

    public JobProfileResponseDto updateJobProfile(String userid, String jobProfileId, JobProfileRequestDto jobProfileRequestDto) {
        try {
            log.info("JobProfileService::updateJobProfile Updating job profile for job profile id: {} ::ENTER", jobProfileId);
            UserInfoModel userInfoModel = userProfileService.getUserInfoModel(userid);
            JobProfileModel jobProfileModel = modelMapper.map(jobProfileRequestDto, JobProfileModel.class);
            jobProfileModel.setUserRef(userInfoModel);
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
        try {
            log.info("JobProfileService::deleteJobProfile Deleting job profile for job profile id: {} ::ENTER", jobProfileId);
            userProfileService.removeJobProfileFromUserProfile(userid, jobProfileId);
            jobProfileRepository.deleteById(jobProfileId);
            log.info("JobProfileService::deleteJobProfile Deleting job profile for job profile id: {} ::DONE", jobProfileId);
        } catch (Exception e) {
            log.error("JobProfileService::deleteJobProfile Error occurred while deleting job profile for job profile id: {}", jobProfileId, e);
            throw new DbOperationFailedException(String.format("%s :%s", DB_OPERATION_FAILED, jobProfileId));
        }
    }

    public JobProfileResponseDto updateJobProfileProgressStep(String userid, String jobProfileId, JobProfileProgressStepDto jobProfileProgressStepDto) {
        try {
            log.info("JobProfileService::updateJobProfileProgressStep Updating job profile progress step for job profile id: {} ::ENTER", jobProfileId);
            JobProfileModel jobProfileModel = jobProfileRepository.findById(jobProfileId)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", JOB_PROFILE_NOT_FOUND, jobProfileId)));
            JobProfileProgressStep jobProfileProgressStep = modelMapper.map(jobProfileProgressStepDto, JobProfileProgressStep.class);
            jobProfileModel.getProgress().add(jobProfileProgressStep);
            JobProfileModel savedModel = jobProfileRepository.save(jobProfileModel);
            log.info("JobProfileService::updateJobProfileProgressStep Updating job profile progress step for job profile id: {} ::DONE", jobProfileId);
            return modelMapper.map(savedModel, JobProfileResponseDto.class);
        } catch (MappingException e) {
            log.error("JobProfileService::updateJobProfileProgressStep Error occurred while mapping request to model", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    public BasicInfoResponseDto updateJobProfileBasicInfo(String userid, String jobProfileId, BasicInfoRequestDto basicInfoRequestDto) {
        try {
            log.info("JobProfileService::updateJobProfileBasicInfo Updating job profile basic info for job profile id: {} ::ENTER", jobProfileId);
            JobProfileModel jobProfileModel = jobProfileRepository.findById(jobProfileId)
                    .orElseThrow(() -> new ResourceNotFoundException(String.format("%s :%s", JOB_PROFILE_NOT_FOUND, jobProfileId)));
            BasicInfo basicInfo = modelMapper.map(basicInfoRequestDto, BasicInfo.class);
            jobProfileModel.setBasicInfo(basicInfo);
            JobProfileModel savedModel = jobProfileRepository.save(jobProfileModel);
            log.info("JobProfileService::updateJobProfileBasicInfo Updating job profile basic info for job profile id: {} ::DONE", jobProfileId);
            return modelMapper.map(savedModel.getBasicInfo(), BasicInfoResponseDto.class);
        } catch (MappingException e) {
            log.error("JobProfileService::updateJobProfileBasicInfo Error occurred while mapping request to model", e);
            throw new IncorrectRequestDataException(INCORRECT_REQUEST_DATA);
        }
    }

    private void addJobProfileToResponseMapper() {
        TypeMap<JobProfileModel, JobProfileResponseDto> jobProfileModelToResponseMapper = this.modelMapper.createTypeMap(JobProfileModel.class, JobProfileResponseDto.class);
        Condition<Collection<JobProfileProgressStep>, String> hasProgress = context -> context.getSource() != null && !context.getSource().isEmpty();
        Converter<Collection<JobProfileProgressStep>, String> lastProgressTitleConverter = context -> ((JobProfileProgressStep) context.getSource().toArray()[context.getSource().size() - 1]).getTitle();
        Converter<Collection<JobProfileProgressStep>, Collection<JobProfileProgressStepDto>> progressStepConverter = context -> {
            AtomicInteger count = new AtomicInteger(1);
            return context.getSource().
                    stream()
                    .map(jobProfileProgressStep -> {
                        JobProfileProgressStepDto jobProfileProgressStepDto = modelMapper.map(jobProfileProgressStep, JobProfileProgressStepDto.class);
                        jobProfileProgressStepDto.setId(count.getAndIncrement());
                        return jobProfileProgressStepDto;
                    })
                    .toList();
        };

        Converter<JobProfileModel, Boolean> isInHomeCountryConverter = (context -> context.getSource().getUserRef().getPersonalInfo().getCountry().getPhone().equals(context.getSource().getBasicInfo().getCountry().getPhone()));
        jobProfileModelToResponseMapper
                .addMappings(mapper -> mapper.map(src -> src.getUserRef().getId(), JobProfileResponseDto::setUserRef))
                .addMappings(mapper -> mapper.when(hasProgress).using(lastProgressTitleConverter)
                        .map(JobProfileModel::getProgress, (dest, value) -> dest.getBasicInfo().setNote((String) value)))
                .addMappings(mapper -> mapper.using(isInHomeCountryConverter)
                        .map(src -> src, (dest, value) -> dest.getBasicInfo().setInHomeCountry((value != null) && (Boolean) value)))
                .addMappings(mapper -> mapper.when(hasProgress).using(progressStepConverter)
                        .map(JobProfileModel::getProgress, JobProfileResponseDto::setProgress));
    }
}
