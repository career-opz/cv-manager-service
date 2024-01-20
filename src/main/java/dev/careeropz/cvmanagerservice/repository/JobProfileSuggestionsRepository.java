package dev.careeropz.cvmanagerservice.repository;

import dev.careeropz.cvmanagerservice.model.suggestion.JobProfileSuggestionModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobProfileSuggestionsRepository extends MongoRepository<JobProfileSuggestionModel, String>{
}
