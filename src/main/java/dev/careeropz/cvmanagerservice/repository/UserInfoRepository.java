package dev.careeropz.cvmanagerservice.repository;

import dev.careeropz.cvmanagerservice.model.UserInfoModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepository extends MongoRepository<UserInfoModel, String> {
}
