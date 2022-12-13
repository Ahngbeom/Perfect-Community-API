package com.board.api.service.board;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.board.api.dto.PostDTO;
import com.board.api.dto.PostSearchDTO;
import com.board.api.mapper.PostSearchMapper;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostSearchServiceImpl implements PostSearchService {
    private static final Logger log = LogManager.getLogger(PostSearchServiceImpl.class);
    private final PostSearchMapper postSearchMapper;

    @Override
    public List<PostDTO> searchPostByKeyword(List<PostSearchDTO> searchConditions) {

        for (PostSearchDTO postSearchDTO : searchConditions) {
            if (postSearchDTO.getTargets().size() > 1 && postSearchDTO.getLogic_operator() == null) {
                throw new RuntimeException("Many targets but no logical operators");
            }
            if (postSearchDTO.getLogic_operator() != null && postSearchDTO.getTargets().size() <= 1) {
                throw new RuntimeException("A logical operator is set, but there are not enough operands.");
            }
            if (postSearchDTO.getLogic_operator() != null)
                postSearchDTO.setLogic_operator(postSearchDTO.getLogic_operator().toUpperCase());
            postSearchDTO.getTargets().replaceAll(String::toUpperCase);
        }
        return postSearchMapper.searchPostByKeyword(searchConditions);
    }

    @Override
    public List<PostDTO> searchPostByRegex(List<String> searchConditionsRegex) {
        return postSearchMapper.searchPostByRegex(searchConditionsRegex);
    }
}
