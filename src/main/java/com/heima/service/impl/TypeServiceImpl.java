package com.heima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.mapper.TypeMapper;
import com.heima.pojo.Type;
import com.heima.service.TypeService;
import com.heima.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @author asura
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
        implements TypeService {

    private final TypeMapper typeMapper;

    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }

    @Override
    public Result<?> getIndex() {
        return Result.ok(typeMapper.selectTypeList());
    }
}




