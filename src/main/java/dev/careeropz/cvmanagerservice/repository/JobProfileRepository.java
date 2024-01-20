package dev.careeropz.cvmanagerservice.repository;

import dev.careeropz.cvmanagerservice.model.jobprofile.JobProfileModel;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JobProfileRepository extends MongoRepository<JobProfileModel, String> {

    @Query(value = "{ 'userRef' : ?0 }")
    Optional<Page<JobProfileModel>> findByUserRef(ObjectId userid, Pageable pageable);
}
