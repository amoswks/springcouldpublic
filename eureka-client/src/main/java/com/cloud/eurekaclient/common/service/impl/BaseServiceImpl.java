package com.cloud.eurekaclient.common.service.impl;

import com.cloud.eurekaclient.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public abstract class BaseServiceImpl<M extends Mapper<T>,T> implements BaseService<T> {
    @Autowired
    public M mapper;

    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    @Override
    public int updateById(T entity) {
         return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int delById(Object id) {
        return mapper.deleteByPrimaryKey(id);
    }
}
