package com.perfect.community.api.service.utils;

import com.perfect.community.api.mapper.utils.UtilsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelocateTablePrimaryKeyService {

    private final UtilsMapper mapper;

    public void RelocateTablePK(String tableName) {
        mapper.initializeAutoIncrement(tableName);
    }
}
