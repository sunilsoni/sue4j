package me.uuus.sue4j.pojo;

public class SUser {
    private Integer sId;

    private String sName;

    private String sTel;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public String getsTel() {
        return sTel;
    }

    public void setsTel(String sTel) {
        this.sTel = sTel == null ? null : sTel.trim();
    }

    @Override
    public String toString() {
        return "sName='" + sName + "', sTel=" + sTel;
    }
}