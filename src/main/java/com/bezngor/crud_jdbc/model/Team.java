package com.bezngor.crud_jdbc.model;

import java.util.List;

public class Team {
    private Integer id;
    private String name;
    private List<Developer> devs;
    private TeamStatus status;

    public Team(Integer id, String name, List<Developer> devs) {
        this.id = id;
        this.name = name;
        this.devs = devs;
    }

    public Team(String name, List<Developer> devs) {
        this.name = name;
        this.devs = devs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDevs() {
        return devs;
    }

    public void setDevs(List<Developer> devs) {
        this.devs = devs;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", devs=" + devs +
                '}';
    }
}
