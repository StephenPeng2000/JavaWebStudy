package com.itheima.pojo;
/*
    统一响应结果类
 */
public class Result {
    //成员变量
    private Integer code;//响应码 0 失败  1成功
    private String msg;//响应消息
    private Object data;//保存后端给前端的数据的

    public Result() {
    }
    //满参构造器，一般是查询数据，因为查询才会给前端响应数据
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //2个参数构造器，一般是增删改数据使用，响应给前端，因为增删改不用将真实数据告知前端
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //get set方法

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //增删改 成功响应
    public static Result success(){
        return new Result(1,"success",null);
    }
    //查询 成功响应
    public static Result success(Object data){
        return new Result(1,"success",data);
    }
    //失败响应
    public static Result error(String msg){
        return new Result(0,msg,null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
