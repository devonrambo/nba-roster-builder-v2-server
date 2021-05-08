package com.example.nba.model;

import java.math.BigDecimal;

public class CreatedTeam implements Teamable {

    private String location;
    private String nickname;
    private BigDecimal teamSalary = new BigDecimal(0);
    private int id;
    private BigDecimal softCap = new BigDecimal(109);
    private BigDecimal taxLine = new BigDecimal(132.6);
    private BigDecimal luxuryTax = new BigDecimal(0);


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setTeamSalary(BigDecimal teamSalary) {
        this.teamSalary = teamSalary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public void setSoftCap(BigDecimal softCap) {
        this.softCap = softCap;
    }

    public BigDecimal getTaxLine() {
        return taxLine;
    }

    public void setTaxLine(BigDecimal taxLine) {
        this.taxLine = taxLine;
    }

    public BigDecimal getLuxuryTax() {
        return luxuryTax;
    }

    public void setLuxuryTax(BigDecimal luxuryTax) {
        this.luxuryTax = luxuryTax;
    }

    @Override
    public BigDecimal getTeamSalary() {
        return this.teamSalary;
    }

    @Override
    public BigDecimal getTeamLuxuryTax() {
        return this.luxuryTax;
    }



}
