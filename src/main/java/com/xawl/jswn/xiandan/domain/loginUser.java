package com.xawl.jswn.xiandan.domain;

public class loginUser {
    private String bname;
    private String bpsd;
    private String brealname;
    private String bidcard;
    private Integer status;
    private Integer breal;
    private String bpic;
    private String bscore;
    private Integer bcredit;
    private String bqianming;
    private String lasttime;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBpsd() {
        return bpsd;
    }

    public void setBpsd(String bpsd) {
        this.bpsd = bpsd;
    }

    public String getBrealname() {
        return brealname;
    }

    public void setBrealname(String brealname) {
        this.brealname = brealname;
    }

    public String getBidcard() {
        return bidcard;
    }

    public void setBidcard(String bidcard) {
        this.bidcard = bidcard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBreal() {
        return breal;
    }

    public void setBreal(Integer breal) {
        this.breal = breal;
    }

    public String getBpic() {
        return bpic;
    }

    public void setBpic(String bpic) {
        this.bpic = bpic;
    }

    public String getBscore() {
        return bscore;
    }

    public void setBscore(String bscore) {
        this.bscore = bscore;
    }

    public Integer getBcredit() {
        return bcredit;
    }

    public void setBcredit(Integer bcredit) {
        this.bcredit = bcredit;
    }

    public String getBqianming() {
        return bqianming;
    }

    public void setBqianming(String bqianming) {
        this.bqianming = bqianming;
    }

    public String getLasttime() {
        return lasttime;
    }

    public void setLasttime(String lasttime) {
        this.lasttime = lasttime;
    }

    @Override
    public String toString() {
        return "loginUser{" +
                "bname='" + bname + '\'' +
                ", bpsd='" + bpsd + '\'' +
                ", brealname='" + brealname + '\'' +
                ", bidcard='" + bidcard + '\'' +
                ", status=" + status +
                ", breal=" + breal +
                ", bpic='" + bpic + '\'' +
                ", bscore='" + bscore + '\'' +
                ", bcredit=" + bcredit +
                ", bqianming='" + bqianming + '\'' +
                ", lasttime='" + lasttime + '\'' +
                '}';
    }
}
