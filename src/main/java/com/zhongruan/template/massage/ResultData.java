package com.zhongruan.template.massage;

import com.alibaba.fastjson.JSONObject;

/**
 *
 */
public class ResultData {

    /**
     * 消息
     */
    private String msg;
    /**
     * code
     */
    private int code;
    /**
     * 数据
     */
    private Object body;

    /**
     * 成功
     * @return
     */
    public static ResultData success(){
        ResultData json = new ResultData();
        json.setCode(0);
        json.setMsg("success");
        return json;
    }

    /**
     * 成功，带body
     * @param body
     * @return
     */
    public static ResultData success(Object body){
        ResultData json = new ResultData();
        json.setCode(0);
        json.setMsg("success");
        json.setBody(body);
        return json;
    }
    /**
     * 失败
     * @return
     */
    public static ResultData error(String msg){
        ResultData json = new ResultData();
        json.setCode(1);
        json.setMsg(msg);
        return json;
    }

    /**
     * 失败，带body
     * @param body
     * @return
     */
    public static ResultData error(String msg, Object body){
        ResultData json = new ResultData();
        json.setCode(1);
        json.setMsg(msg);
        json.setBody(body);
        return json;
    }
    /**
     * 失败
     * @return
     */
    public static ResultData error(int code, String msg){
        ResultData json = new ResultData();
        json.setCode(code);
        json.setMsg(msg);
        return json;
    }

    /**
     * 失败，带body
     * @param body
     * @return
     */
    public static ResultData error(int code, String msg, Object body){
        ResultData json = new ResultData();
        json.setCode(code);
        json.setMsg(msg);
        json.setBody(body);
        return json;
    }
    public ResultData(){

    }

    public ResultData(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultData(int code, String msg, Object body){
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        if (body == null){
            this.body = new JSONObject();
            return;
        }
        this.body = body;
    }
}
