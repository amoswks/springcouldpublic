package com.cloud.eurekaclient.user.service;

import com.cloud.eurekaclient.common.service.BaseService;
import com.cloud.eurekaclient.user.model.City;


public interface CityService  {
    public City findOne(Long id);
}
