package dev.careeropz.cvmanagerservice.model.suggestion;

import dev.careeropz.cvmanagerservice.model.suggestion.subclasses.RelatedIndustry;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "job-profile-suggestions")
public class JobProfileSuggestionModel {
    @Id
    private ObjectId id;
    private List<RelatedIndustry> relatedIndustries;
}
