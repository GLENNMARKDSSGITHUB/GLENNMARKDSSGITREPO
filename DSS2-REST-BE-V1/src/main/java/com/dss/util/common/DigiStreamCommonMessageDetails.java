package com.dss.util.common;

import java.util.List;
import java.util.Map;

public class DigiStreamCommonMessageDetails {

    private String header;
    private String content;
    private boolean success;
    private Map<String, Object> returnParams;
    private List<?> objList;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getReturnParams() {
        return returnParams;
    }

    public void setReturnParams(Map<String, Object> returnParams) {
        this.returnParams = returnParams;
    }

    public List<?> getObjList() {
        return objList;
    }

    public void setObjList(List<?> objList) {
        this.objList = objList;
    }
}
