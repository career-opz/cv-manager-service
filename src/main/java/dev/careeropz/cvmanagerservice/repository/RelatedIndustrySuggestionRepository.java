package dev.careeropz.cvmanagerservice.repository;

import dev.careeropz.cvmanagerservice.model.suggestion.RelatedIndustryModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RelatedIndustrySuggestionRepository extends MongoRepository<RelatedIndustryModel, String>{
    @Query(value = "{ $or: [ { 'isDefault' : ?0 }, { 'userRef' : ?1 } ] }")
    List<RelatedIndustryModel> findByIsDefaultAndUserRef(Boolean isDefault, ObjectId userRef);
}
