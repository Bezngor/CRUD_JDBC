package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Team;
import com.bezngor.crud_jdbc.model.TeamStatus;
import com.bezngor.crud_jdbc.repository.JavaIOTeamRepositoryImpl;
import com.bezngor.crud_jdbc.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class TeamControllerCliTest {
    private TeamRepository teamRepository;
    private TeamControllerCli teamControllerCli;
    private Team team;
    private List<Developer> devs;

    @BeforeEach
    void setUp() {
        teamRepository = mock(TeamRepository.class);
        teamControllerCli = new TeamControllerCli(teamRepository);
        devs = new ArrayList<>();
        team = new Team("Team", TeamStatus.ACTIVE, devs);
    }

    @Test
    void testConstructor() {
        JavaIOTeamRepositoryImpl teamRepositoryImpl = new JavaIOTeamRepositoryImpl();
        assertSame(teamRepositoryImpl, new TeamControllerCli(teamRepositoryImpl).getTeamRepo());
    }

    @Test
    void create() {
        when(teamRepository.save(any())).thenReturn(team);
        assertSame(team, teamControllerCli.create("Team", TeamStatus.ACTIVE, devs));
        verify(teamRepository).save(any());
        assertTrue(teamControllerCli.getAll().isEmpty());
    }

    @Test
    void update() {
        when(teamRepository.update(any())).thenReturn(team);
        assertSame(team, teamControllerCli.update(1, "Team", TeamStatus.ACTIVE, devs));
        verify(teamRepository).update(any());
        assertTrue(teamControllerCli.getAll().isEmpty());
    }

    @Test
    void getAll() {
        ArrayList<Team> teamList = new ArrayList<>();
        when(teamRepository.getAll()).thenReturn(teamList);
        List<Team> actualList = teamControllerCli.getAll();
        assertSame(teamList, actualList);
        assertTrue(actualList.isEmpty());
        verify(teamRepository).getAll();
    }

    @Test
    void getById() {
        when(teamRepository.getById(any())).thenReturn(team);
        assertSame(team, teamControllerCli.getById(1));
        verify(teamRepository).getById(any());
        assertTrue(teamControllerCli.getAll().isEmpty());
    }

    @Test
    void deleteById() {
        doNothing().when(teamRepository).deleteById(any());
        teamRepository.deleteById(1);
        verify(teamRepository).deleteById(any());
        assertTrue(teamControllerCli.getAll().isEmpty());
    }
}