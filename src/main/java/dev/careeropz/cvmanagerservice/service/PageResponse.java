package dev.careeropz.cvmanagerservice.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse <T> {
    private List<T> content;
    private Integer pageNumber;
    private Integer totalPages;
    private Long totalCount;
    private Boolean hasPreviousPage;
    private Boolean hasNextPage;
    private Integer pageSize;
    private Long startIndex;
    private Long endIndex;
}
