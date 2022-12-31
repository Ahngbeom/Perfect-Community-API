package com.perfect.community.api.service.post;

import com.perfect.community.api.dto.post.PostDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.perfect.community.api.dto.post.PostExtractionDTO;
import com.perfect.community.api.mapper.post.PostSearchMapper;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostSearchServiceImpl implements PostSearchService {
    private static final Logger log = LogManager.getLogger(PostSearchServiceImpl.class);
    private final PostSearchMapper postSearchMapper;

    @Override
    public List<PostDTO> searchPostByKeyword(List<PostExtractionDTO> searchConditions) {

//        for (PostExtractionDTO postExtractionDTO : searchConditions) {
//            if (postExtractionDTO.getTargets().size() > 1 && postExtractionDTO.getLogic_operator() == null) {
//                throw new RuntimeException("Many targets but no logical operators");
//            }
//            if (postExtractionDTO.getLogic_operator() != null && postExtractionDTO.getTargets().size() <= 1) {
//                throw new RuntimeException("A logical operator is set, but there are not enough operands.");
//            }
//            if (postExtractionDTO.getLogic_operator() != null)
//                postExtractionDTO.setLogic_operator(postExtractionDTO.getLogic_operator().toUpperCase());
//            postExtractionDTO.getTargets().replaceAll(String::toUpperCase);
//        }
//        return postSearchMapper.searchPostByKeyword(searchConditions);
        return null;
    }

    @Override
    public List<PostDTO> searchPostByRegex(List<String> searchConditionsRegex) {
        return postSearchMapper.searchPostByRegex(searchConditionsRegex);
    }
}
