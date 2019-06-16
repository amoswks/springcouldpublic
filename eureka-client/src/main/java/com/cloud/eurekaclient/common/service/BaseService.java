package com.cloud.eurekaclient.common.service;

import java.util.List;

public interface BaseService<T> {
    /**
     * 根据
     * @param entity
     * @return
     */
     T selectOne(T entity);

    /**
     *根据ID查询
     * @param id
     * @return
     */
    T selectById(Object id);

    /**
     *  查询列表
     * @param entity
     * @return
     */
    List<T> selectList(T entity);

    /**
     * 根据ID进行更新
     * @param entity
     * @return
     */
    int updateById(T entity);

    /**
     * 根据ID进行删除
     * @param id
     * @return
     */
    int delById(Object id);
}
