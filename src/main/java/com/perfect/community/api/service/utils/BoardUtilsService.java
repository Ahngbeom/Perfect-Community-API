package com.perfect.community.api.service.utils;

import com.perfect.community.api.mapper.board.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUtilsService {

    private final BoardMapper mapper;

    public void removeAllBoards() {
        mapper.deleteAll();
    }
}
