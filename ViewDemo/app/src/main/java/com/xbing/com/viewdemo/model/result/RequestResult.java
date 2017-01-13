package com.xbing.com.viewdemo.model.result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhaobing on 2016/9/23.
 */
public class RequestResult {

    @SerializedName("result_code")
    public  String resultCode;

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @SerializedName("result_msg")
    public  String resultMsg;

    public boolean isResultOk(){
        return  "0".equals(resultCode);
    }

}
