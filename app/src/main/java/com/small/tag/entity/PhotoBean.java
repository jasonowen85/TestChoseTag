package com.small.tag.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：lyx on 2017/4/21 0021 11:22
 * 邮箱：liyouxun@eims.com.cn
 * 说明：
 */
public class PhotoBean implements Serializable {

    public  BidAuditSubject adjuest;
    public  String kidId;
    public List<BidSupervisor> bidSupervisor;


    public BidAuditSubject getAdjuest() {
        return adjuest;
    }

    public void setAdjuest(BidAuditSubject adjuest) {
        this.adjuest = adjuest;
    }

    public String getKidId() {
        return kidId;
    }

    public void setKidId(String kidId) {
        this.kidId = kidId;
    }

    public List<BidSupervisor> getBidSupervisor() {
        return bidSupervisor;
    }

    public void setBidSupervisor(List<BidSupervisor> bidSupervisor) {
        this.bidSupervisor = bidSupervisor;
    }


    public class BidAuditSubject{
        private String name;
        public  BidAuditSubject(String name){
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

    public class BidSupervisor{
        private String url;
        private String fortmat;

        public BidSupervisor(String fortmat, String url){
            this.url = url;
            this.fortmat = fortmat;
        }

        public String getFortmat() {
            return fortmat;
        }

        public void setFortmat(String fortmat) {
            this.fortmat = fortmat;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null) return false;
        if(getClass() != o.getClass()) return false;
        if(o instanceof PhotoBean){
            PhotoBean bean = (PhotoBean)o;
            if(bean.getAdjuest().getName().equals(this.getAdjuest().getName())){
                return true;
            }
        }
        return false;
    }


    @Override
    public int hashCode() {
        return 0;
    }
}
