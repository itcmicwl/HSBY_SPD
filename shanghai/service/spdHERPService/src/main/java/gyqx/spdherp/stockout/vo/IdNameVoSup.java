package gyqx.spdherp.stockout.vo;

import java.io.Serializable;

/**
 * Created by cjzyw on 2018/5/18.
 */
public class IdNameVoSup extends IdNameVo implements Serializable{
    private String address ;
    private String linkman ;
    private String contactWay ;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }
}
