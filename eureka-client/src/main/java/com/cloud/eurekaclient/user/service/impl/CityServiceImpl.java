package com.cloud.eurekaclient.user.service.impl;

import com.cloud.eurekaclient.common.service.impl.BaseServiceImpl;
import com.cloud.eurekaclient.user.dao.CityMapper;
import com.cloud.eurekaclient.user.model.City;
import com.cloud.eurekaclient.user.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    public CityMapper cityMapper;
    @Override
    public City findOne(Long id) {
        City city = cityMapper.selectByPrimaryKey(id);
        return city;
    }
}
