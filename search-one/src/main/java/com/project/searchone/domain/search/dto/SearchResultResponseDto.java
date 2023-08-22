package com.project.searchone.domain.search.dto;

import java.util.List;

import com.google.auto.value.AutoValue.Builder;
import com.project.searchone.domain.board.dto.BoardResponseDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class SearchResultResponseDto {

    private final Integer total;
    private final List<BoardResponseDto> boardResponseDtos;
    
}
