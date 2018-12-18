package gyqx.spdherp.spdOutPtl.vo;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

/**
 * Created by cjzyw on 2018/6/26.
 */
public class MessageResponse implements Serializable{
    private int resultCode;
    private String resultContent;
    private String data;
    private String code;
    public MessageResponse() {
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultContent() {
        return resultContent;
    }

    public void setResultContent(String resultContent) {
        this.resultContent = resultContent;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String toString() {
        return "MessageResponse [resultCode=" + this.resultCode + ", resultContent=" + this.resultContent + "]";
    }
}
