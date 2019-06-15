package com.cloud.eurekaclient.common.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseContrller<E,T> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected  T service;


}
