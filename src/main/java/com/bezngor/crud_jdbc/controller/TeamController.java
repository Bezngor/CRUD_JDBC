package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Team;
import com.bezngor.crud_jdbc.model.TeamStatus;
import com.bezngor.crud_jdbc.repository.JavaIOTeamRepositoryImpl;

import java.util.List;

public class TeamController {
    public static JavaIOTeamRepositoryImpl teamRepo = new JavaIOTeamRepositoryImpl();

    public static JavaIOTeamRepositoryImpl getTeamRepo() {
        return teamRepo;
    }

    public Team create(String name, TeamStatus status, List<Developer> devs) {
        return teamRepo.save(new Team(name, status, devs));
    }

    public Team update(Integer id, String name, TeamStatus status, List<Developer> devs) {
        return teamRepo.update(new Team(id, name, status, devs));
    }

    public List<Team> getAll() {
        return teamRepo.getAll();
    }

    public Team getById(Integer id) {
        return teamRepo.getById(id);
    }

    public void deleteById(Integer id) {
        teamRepo.deleteById(id);
    }
}
