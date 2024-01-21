package dev.careeropz.cvmanagerservice.service;

import dev.careeropz.cvmanagerservice.dto.suggestion.RelatedIndustryDto;
import dev.careeropz.cvmanagerservice.model.suggestion.RelatedIndustryModel;
import dev.careeropz.cvmanagerservice.repository.RelatedIndustrySuggestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SuggestionsService {

    private final RelatedIndustrySuggestionRepository relatedIndustrySuggestionRepository;
    private final ModelMapper modelMapper;

    public List<RelatedIndustryDto> getRelatedIndustrySuggestions(String userId) {
        log.info("getSuggestions :: userid: {} :: ENTER", userId);
        List<RelatedIndustryModel> relatedIndustries = relatedIndustrySuggestionRepository.findByIsDefaultAndUserId(true, new ObjectId(userId));
        log.info("getSuggestions :: userid: {} :: DONE", userId);
        return relatedIndustries
                .stream()
                .map(relatedIndustryModel -> modelMapper.map(relatedIndustryModel, RelatedIndustryDto.class))
                .toList();
    }

    public List<RelatedIndustryDto> addRelatedIndustrySuggestions(String userId, RelatedIndustryDto relatedIndustryDto) {
        log.info("addSuggestions :: userid: {} :: ENTER", userId);
        RelatedIndustryModel relatedIndustryModel = modelMapper.map(relatedIndustryDto, RelatedIndustryModel.class);
        relatedIndustryModel.setUserId(new ObjectId(userId));
        relatedIndustryModel.setIsDefault(false);
        relatedIndustrySuggestionRepository.save(relatedIndustryModel);
        List<RelatedIndustryDto> response = getRelatedIndustrySuggestions(userId);
        log.info("addSuggestions :: userid: {} :: DONE", userId);
        return response;
    }

    public List<RelatedIndustryDto> deleteRelatedIndustrySuggestions(String userId, String suggestionId) {
        log.info("deleteSuggestions :: userid: {} :: ENTER", userId);
        relatedIndustrySuggestionRepository.deleteByIndustryIdAndUserId(new ObjectId(suggestionId), new ObjectId(userId));
        List<RelatedIndustryDto> response = getRelatedIndustrySuggestions(userId);
        log.info("deleteSuggestions :: userid: {} :: DONE", userId);
        return response;
    }
}
