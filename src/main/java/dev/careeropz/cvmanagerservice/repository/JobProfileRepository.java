package dev.careeropz.cvmanagerservice.repository;

import dev.careeropz.cvmanagerservice.model.jobprofilemodel.JobProfileModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JobProfileRepository extends MongoRepository<JobProfileModel, String> {
    Optional<List<JobProfileModel>> findAllByUserRef(String userid);
}
