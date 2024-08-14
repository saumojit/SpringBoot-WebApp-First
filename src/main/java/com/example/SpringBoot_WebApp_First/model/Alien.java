package com.example.SpringBoot_WebApp_First.model;

public class Alien {
    private int aid ;
    private String aname ;

    public int aid() {
        return aid;
    }

    public Alien setAid(int aid) {
        this.aid = aid;
        return this;
    }

    public String aname() {
        return aname;
    }

    public Alien setAname(String aname) {
        this.aname = aname;
        return this;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                '}';
    }
}
