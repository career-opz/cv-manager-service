package dev.careeropz.cvmanagerservice.model.suggestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "related-industry")
public class RelatedIndustryModel {
    @Id
    private ObjectId industryId;
    private String industry;
    private List<String> jobTitles;
    private Boolean isDefault;
    private ObjectId userId;
}
