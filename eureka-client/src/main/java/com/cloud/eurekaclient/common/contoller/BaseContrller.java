package com.cloud.eurekaclient.common.contoller;

import com.cloud.eurekaclient.common.dto.BaseResponse;
import com.cloud.eurekaclient.common.dto.PageResponse;
import com.cloud.eurekaclient.common.service.BaseService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Slf4j
public abstract class BaseContrller<E extends BaseService<T>, T> {

    //protected Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected E service;

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResponse<List<T>> list(HttpServletRequest request, T selectOption,
                                      @RequestParam(value = "pageSize",defaultValue = "15", required = false) Integer pageSize
                                     , @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum) {

        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        //PageInfo
        List<T> result = service.selectList(selectOption);
        return PageResponse.successPage(result);
    }

}
