package com.board.api.service.utils;

import com.board.api.mapper.board.BoardMapper;
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
