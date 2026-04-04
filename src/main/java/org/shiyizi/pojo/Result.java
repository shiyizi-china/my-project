package org.shiyizi.pojo;

import lombok.Data;

@Data
public class Result {
    private String msg;
    private int code;
    private Object data;

    public static Result success(){
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }
    public  static  Result success(Object object){
        Result result = new Result();
        result.data=object;
        result.code = 1;
        result.msg = "success";
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
