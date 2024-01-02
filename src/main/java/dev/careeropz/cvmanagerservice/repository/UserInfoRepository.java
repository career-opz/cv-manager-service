package dev.careeropz.cvmanagerservice.repository;

import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserInfoRepository extends MongoRepository<UserInfoModel, String> {
    Optional<UserInfoModel> findByPersonalInfoEmail(String email);
}
