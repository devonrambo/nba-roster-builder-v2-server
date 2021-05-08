package com.example.nba.model;

import java.math.BigDecimal;

public class Player {

    private String name;
    private BigDecimal salary;
    private String position;
    private int playerId;
    private String nbaTeamCode;


    public Player(String name, String position, BigDecimal salary) {
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public Player(String name, BigDecimal salary){
        this.name = name;
        this.salary = salary;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getNbaTeamCode() {
        return nbaTeamCode;
    }

    public void setNbaTeamCode(String nbaTeamCode) {
        this.nbaTeamCode = nbaTeamCode;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }
}
