package com.cloud.eurekaclient.common.contoller;

import com.cloud.eurekaclient.common.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseContrller<E extends BaseService<T>,T> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected  E service;

}
