package com.yuan.demomybatisplus.entityView.result;

import lombok.Data;
import java.io.Serializable;

/**
 * @author MuXue
 * @create 2022-07-13  16:29 PM
 */
@Data
public class ResultVO <T>  implements Serializable {
    private int code;       // 状态码
    private String msg;     // 返回的信息
    private T data;         // 返回的数据



    /**
     * 成功时候的调用（有数据）
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(T data){
        return new ResultVO<T>(data);
    }

    /**
     * 成功时候的调用（无数据）
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> success(){
        return new ResultVO<T>();
    }

    /**
     * 异常时候的调用（有msg参数）
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(String msg){
        return new ResultVO<T>(msg);
    }

    /**
     * 异常时候的调用（无msg参数）
     * @param <T>
     * @return
     */
    public static <T> ResultVO<T> error(){
        return new ResultVO<T>("error");
    }

    private ResultVO(T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    private ResultVO() {
        this.code = 200;
        this.msg = "success";
    }

    private ResultVO(String msg) {
        this.code = 400;
        this.msg = msg;
    }



}
