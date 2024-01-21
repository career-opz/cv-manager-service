package dev.careeropz.cvmanagerservice.controller.suggestion;

import dev.careeropz.cvmanagerservice.dto.suggestion.RelatedIndustryDto;
import dev.careeropz.cvmanagerservice.service.SuggestionsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cv/api/v1/job-profile/suggestions")
@Slf4j
@RequiredArgsConstructor
public class SuggestionController {

    private final SuggestionsService suggestionsService;

    @GetMapping("/{userid}")
    public ResponseEntity<List<RelatedIndustryDto>> getSuggestions(@PathVariable(value = "userid") String userId) {
        log.info("getSuggestions :: userid: {} :: ENTER", userId);
        List<RelatedIndustryDto> suggestions = suggestionsService.getRelatedIndustrySuggestions(userId);
        log.info("getSuggestions :: userid: {} :: DONE", userId);
        return ResponseEntity.ok(suggestions);
    }

    @PostMapping("/{userid}")
    public ResponseEntity<List<RelatedIndustryDto>> postSuggestions(@PathVariable(value = "userid") String userId,
                                                                    @RequestBody @Valid RelatedIndustryDto relatedIndustryDto) {
        log.info("postSuggestions :: userid: {} :: ENTER", userId);
        List<RelatedIndustryDto> suggestions = suggestionsService.addRelatedIndustrySuggestions(userId, relatedIndustryDto);
        log.info("postSuggestions :: userid: {} :: DONE", userId);
        return ResponseEntity.ok(suggestions);
    }

    @DeleteMapping("/{userid}/{suggestion-id}")
    public ResponseEntity<List<RelatedIndustryDto>> deleteSuggestions(@PathVariable(value = "userid") String userId,
                                                                      @PathVariable(value = "suggestion-id") String suggestionId) {
        log.info("deleteSuggestions :: userid: {} :: ENTER", userId);
        List<RelatedIndustryDto> suggestions = suggestionsService.deleteRelatedIndustrySuggestions(userId, suggestionId);
        log.info("deleteSuggestions :: userid: {} :: DONE", userId);
        return ResponseEntity.ok(suggestions);
    }
}
