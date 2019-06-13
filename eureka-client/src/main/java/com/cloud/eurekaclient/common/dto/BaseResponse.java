package com.cloud.eurekaclient.common.dto;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = -7672234412786558026L;
    /**
     * 服务返回消息
     */
    private String msg;

    /**
     * 是否执行成功，0表示成功，非0表示失败
     */
    private Integer code = new Integer(0);

    private T data = null;

    public BaseResponse(){
    }

    public BaseResponse(Integer code, String msg,T data ){
        this.setCode(code,msg);
        this.data = data ;
    }
    public void setCode(Integer code, String msg) {
        this.setCode(code);
        this.msg = msg;
    }



    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Integer code) {
        if (code == null) {
            code = -1;
        }
        this.code = code;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static BaseResponse<?> success(){
        return new BaseResponse<>(0,"success",null);
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    /**
     * 数据返回成功，返回前端对象
     * @param data
     * @param <E>
     * @return
     */
    public static <E> BaseResponse success(E data){
        return new BaseResponse<>(0,"success",data);
    }

    /**
     * 数据处理失败，返回前端对象
     * @Author wangke
     * @param errcode
     * @return
     */

    public static BaseResponse<?> fail (int errcode){
        return new BaseResponse<>(errcode,"failure",null);
    }

    /**
     * 数据处理失败，返回前端对象
     * @Author wangke
     * @param errCode
     * @param errMessage
     * @param <E>
     * @return
     */
    public static <E> BaseResponse fail(int errCode, String errMessage){
        if (errMessage == null || errMessage == "") {
            return fail(errCode);
        } else {
            BaseResponse<Object> result = new BaseResponse<Object>(errCode, errMessage,null);
            return result;
        }
    }
}
