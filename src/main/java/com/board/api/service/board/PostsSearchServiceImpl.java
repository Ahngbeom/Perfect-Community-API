package com.board.api.service.board;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.board.api.DTO.PostsDTO;
import com.board.api.DTO.PostsSearchDTO;
import com.board.api.mapper.PostsSearchMapper;
import com.board.api.utils.DateUtility;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsSearchServiceImpl implements PostsSearchService {
    private static final Logger log = LogManager.getLogger(PostsSearchServiceImpl.class);
    private final PostsSearchMapper postsSearchMapper;

    private final DateUtility dateUtility;

    @Override
    public List<PostsDTO> searchBoardByKeyword(PostsSearchDTO searchVO) {
        List<PostsDTO> searchResult = postsSearchMapper.selectBoardByKeyword(searchVO);
        searchResult.forEach(board -> board.setDateToToday(dateUtility.dateToTodayCalculator(board.getRegDate(), board.getUpdateDate())));
        return searchResult;
    }
}
