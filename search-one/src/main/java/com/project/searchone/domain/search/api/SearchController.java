package com.project.searchone.domain.search.api;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.searchone.domain.search.dao.SearchDao;
import com.project.searchone.domain.search.dto.SearchResultResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchDao searchDao;
    

    @GetMapping
    public ResponseEntity<?> findByKeyword(@RequestParam String keyword,
                                      @RequestParam Integer page,
                                      @RequestParam String startDate, // 0001-01-01 ~
                                      @RequestParam String endDate // ~ 9999-12-31
                                      ) throws InterruptedException, ExecutionException{
                                        
        SearchResultResponseDto result = searchDao.getBoardByKeyword(keyword, startDate, endDate, page);

        if(result.getBoardResponseDtos().isEmpty())
             return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        else      
            return new ResponseEntity<>(result, HttpStatus.OK);

    }
    
}
