package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Team;
import com.bezngor.crud_jdbc.model.TeamStatus;
import com.bezngor.crud_jdbc.repository.DeveloperRepository;
import com.bezngor.crud_jdbc.repository.JavaIOTeamRepositoryImpl;
import com.bezngor.crud_jdbc.repository.TeamRepository;

import java.util.List;

public class TeamControllerCli {
    private final TeamRepository teamRepo;

    public TeamControllerCli(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    public TeamRepository getTeamRepo() {
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
