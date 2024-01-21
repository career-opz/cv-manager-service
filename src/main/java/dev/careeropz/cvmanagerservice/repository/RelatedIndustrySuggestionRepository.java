package dev.careeropz.cvmanagerservice.repository;

import dev.careeropz.cvmanagerservice.model.suggestion.RelatedIndustryModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RelatedIndustrySuggestionRepository extends MongoRepository<RelatedIndustryModel, String>{
    @Query(value = "{ $or: [ { 'isDefault' : ?0 }, { 'userId' : ?1 } ] }")
    List<RelatedIndustryModel> findByIsDefaultAndUserId(Boolean isDefault, ObjectId userId);

    @Query(value = "{ 'industryId' : ?0, 'userId' : ?1, 'isDefault' : { $ne : true } }", delete = true)
    RelatedIndustryModel deleteByIndustryIdAndUserId(ObjectId industryId, ObjectId userId);
}
