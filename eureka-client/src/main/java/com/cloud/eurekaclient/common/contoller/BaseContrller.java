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

    /**
     * 通用列表查询
     * @param request
     * @param selectOption
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public BaseResponse<List<T>> list(HttpServletRequest request, T selectOption,
                                      @RequestParam(value = "pageSize",defaultValue = "15", required = false) Integer pageSize
                                     , @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum) {

        if (pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<T> result = service.selectList(selectOption);
        return PageResponse.successPage(result);
    }


    /**
     *  PUT请求：如果两个请求相同，后一个请求会把第一个请求覆盖掉。（所以PUT用来改资源）
     *  PUT请求来进行更新操作
     *  进行更新操作
     * @param request
     * @param entity
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public BaseResponse<?> modify(HttpServletRequest request,@RequestBody T entity,@PathVariable(value = "id") String id){
        service.updateById(entity);
        return  BaseResponse.success();
    }

    /**
     * 根据id进行查询
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public BaseResponse<T> cat(HttpServletRequest request,@PathVariable(value = "id") String id){
        T result = service.selectById(id);
        return BaseResponse.success(result);
    }

    /**
     * 根据id进行删除操作
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public BaseResponse<?> del(HttpServletRequest request,@PathVariable String id){
        service.delById(id);
        return BaseResponse.success();
    }
}
