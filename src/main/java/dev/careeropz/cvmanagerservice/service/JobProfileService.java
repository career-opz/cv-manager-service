package dev.careeropz.cvmanagerservice.service;

import dev.careeropz.cvmanagerservice.dto.commondto.PageResponse;
import dev.careeropz.cvmanagerservice.dto.jobprofile.commondto.JobProfileProgressStepDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.BasicInfoRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.requestdto.JobProfileRequestDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.BasicInfoResponseDto;
import dev.careeropz.cvmanagerservice.dto.jobprofile.responsedto.JobProfileResponseDto;
import dev.careeropz.cvmanagerservice.dto.pagination.CommonPaginationRequest;
import dev.careeropz.cvmanagerservice.exception.DbOperationFailedException;
import dev.careeropz.cvmanagerservice.exception.IncorrectRequestDataException;
import dev.careeropz.cvmanagerservice.exception.ResourceNotFoundException;
import dev.careeropz.cvmanagerservice.model.userinfo.UserInfoModel;
import dev.careeropz.cvmanagerservice.model.jobprofile.BasicInfoModel;
import dev.careeropz.cvmanagerservice.model.jobprofile.JobProfileModel;
import dev.careeropz.cvmanagerservice.model.jobprofile.JobProfileProgressStepModel;
import dev.careeropz.cvmanagerservice.repository.JobProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
//        addPageToPageResponseMapper();
    }

    public PageResponse<JobProfileResponseDto> getAllJobProfiles(String userId, CommonPaginationRequest paginationRequest) {
        log.info("JobProfileService::getAllJobProfiles Fetching all job profiles for user id: {} ::ENTER", userId);
        Pageable pageable = PageRequest.of(paginationRequest.getPageNo(), paginationRequest.getPageSize());
        Page<JobProfileModel> response = jobProfileRepository.findByUserRef(new ObjectId(userId), pageable)
                .orElseThrow(() -> new DbOperationFailedException(String.format("%s :%s", DB_OPERATION_FAILED, userId)));
        return pageToResponsePage(response);
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
            jobProfileModel.setJobProfileId(jobProfileId);
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
            JobProfileProgressStepModel jobProfileProgressStep = modelMapper.map(jobProfileProgressStepDto, JobProfileProgressStepModel.class);
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
            BasicInfoModel basicInfoModel = modelMapper.map(basicInfoRequestDto, BasicInfoModel.class);
            jobProfileModel.setBasicInfo(basicInfoModel);
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
        Condition<Collection<JobProfileProgressStepModel>, String> hasProgress = context -> context.getSource() != null && !context.getSource().isEmpty();
        Converter<Collection<JobProfileProgressStepModel>, String> lastProgressTitleConverter = context -> ((JobProfileProgressStepModel) context.getSource().toArray()[context.getSource().size() - 1]).getTitle();
        Converter<Collection<JobProfileProgressStepModel>, Collection<JobProfileProgressStepDto>> progressStepConverter = context -> {
            AtomicInteger count = new AtomicInteger(1);
            return context.getSource().
                    stream()
                    .map(jobProfileProgressStep -> {
                        JobProfileProgressStepDto jobProfileProgressStepDto = modelMapper.map(jobProfileProgressStep, JobProfileProgressStepDto.class);
                        jobProfileProgressStepDto.setProgressStepId(count.getAndIncrement());
                        return jobProfileProgressStepDto;
                    })
                    .toList();
        };

        Converter<JobProfileModel, Boolean> isInHomeCountryConverter = (context -> context.getSource().getUserRef().getPersonalInfo().getCountry().getPhone().equals(context.getSource().getBasicInfo().getCountry().getPhone()));
        jobProfileModelToResponseMapper
                .addMappings(mapper -> mapper.map(src -> src.getUserRef().getUserId(), JobProfileResponseDto::setUserId))
                .addMappings(mapper -> mapper.when(hasProgress).using(lastProgressTitleConverter)
                        .map(JobProfileModel::getProgress, (dest, value) -> dest.getBasicInfo().setNote((String) value)))
                .addMappings(mapper -> mapper.using(isInHomeCountryConverter)
                        .map(src -> src, (dest, value) -> dest.getBasicInfo().setInHomeCountry((value != null) && (Boolean) value)))
                .addMappings(mapper -> mapper.when(hasProgress).using(progressStepConverter)
                        .map(JobProfileModel::getProgress, JobProfileResponseDto::setProgress));
    }

//    private void addPageToPageResponseMapper(){
//        TypeMap<Page, PageResponse> jobProfilePageToResponsePageMapper = this.modelMapper.createTypeMap(Page.class, PageResponse.class);
//        jobProfilePageToResponsePageMapper
//                .addMappings(mapper -> mapper.map(Page::getTotalPages, (dest, value) -> dest.setTotalPages((Integer) value)))
//                .addMappings(mapper -> mapper.map(Page::getTotalElements, (dest, value) -> dest.setTotalCount((Long) value)))
//                .addMappings(mapper -> mapper.map(Page::getNumber, (dest, value) -> dest.setPageNumber((Integer) value)))
//                .addMappings(mapper -> mapper.map(Page::getSize, (dest, value) -> dest.setPageSize((Integer) value)))
//                .addMappings(mapper -> mapper.map(Page::getContent, (dest, value) -> dest.setContent((List)value)))
//                .addMappings(mapper -> mapper.map(Page::hasPrevious, (dest, value) -> dest.setHasPreviousPage((Boolean) value)))
//                .addMappings(mapper -> mapper.map(Page::hasNext, (dest, value) -> dest.setHasNextPage((Boolean) value)));
//    }

    private PageResponse<JobProfileResponseDto> pageToResponsePage(Page<JobProfileModel> jobProfilePage){
        PageResponse<JobProfileResponseDto> pageResponse = new PageResponse<>();
        pageResponse.setTotalPages(jobProfilePage.getTotalPages());
        pageResponse.setTotalCount(jobProfilePage.getTotalElements());
        pageResponse.setPageNumber(jobProfilePage.getNumber() + 1);
        pageResponse.setPageSize(jobProfilePage.getSize());
        pageResponse.setContent(jobProfilePage.getContent().stream().map(jobProfileModel -> modelMapper.map(jobProfileModel, JobProfileResponseDto.class)).toList());
        pageResponse.setHasPreviousPage(jobProfilePage.hasPrevious());
        pageResponse.setHasNextPage(jobProfilePage.hasNext());
        pageResponse.setStartIndex(((long) pageResponse.getPageSize() * (pageResponse.getPageNumber() - 1) + 1));
        pageResponse.setEndIndex(pageResponse.getHasNextPage() ? pageResponse.getStartIndex() + pageResponse.getPageSize() - 1 : pageResponse.getTotalCount());
        return pageResponse;
    }
}
