package com.perfect.community.api.service.utils;

import com.perfect.community.api.mapper.utils.UtilsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelocateService {

    private final UtilsMapper mapper;

    public long relocateBoardNumbers(String tableName) {
        return mapper.initializeAutoIncrement(tableName);
    }
}
