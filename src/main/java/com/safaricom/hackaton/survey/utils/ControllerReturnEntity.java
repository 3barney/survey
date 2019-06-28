package com.safaricom.hackaton.survey.utils;

import java.util.HashMap;

/**
 * Handles and formats response sent back from the API to the public (Structure used)
 * @param <T> Type of result object to send
 */
public class ControllerReturnEntity<T> {

    HashMap<String, Object> resultMap = new HashMap<>();

    public ControllerReturnEntity() {
        resultMap.put("code", ApiConstants.OK);
        resultMap.put("message", ApiConstants.MSG_OK);
    }

    public ControllerReturnEntity(String code, String message) {
        resultMap.put("code", code);
        resultMap.put("message", message);
    }

    public T getResult() { return  (T) resultMap.get("result"); }

    public void setResult(T mResult) { resultMap.put("result", mResult); }

    public String getCode() {
        return (String) resultMap.get("code");
    }

    public void setCode(String code) {
        resultMap.put("code", code);
    }

    public String getMessage() {
        return (String) resultMap.get("message");
    }

    public void setMessage(String message) {
        resultMap.put("message", message);
    }

    @Override
    public String toString() {
        return "ControllerReturnEntity{" +
                "resultMap=" + resultMap +
                '}';
    }
}
