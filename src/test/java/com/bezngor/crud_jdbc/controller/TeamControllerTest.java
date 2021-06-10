package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Team;
import com.bezngor.crud_jdbc.model.TeamStatus;
import com.bezngor.crud_jdbc.repository.jdbc.JavaIOTeamRepositoryImpl;
import com.bezngor.crud_jdbc.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class TeamControllerTest {
    private TeamRepository teamRepository;
    private TeamController teamController;

    @BeforeEach
    void setUp() {
        teamRepository = mock(TeamRepository.class);
        teamController = new TeamController(teamRepository);
    }

    @Test
    void createTrue() {
        List<Developer> devs = new ArrayList<>();
        Team team = new Team("Team", TeamStatus.ACTIVE, devs);
        when(teamRepository.save(any())).thenReturn(team);
        assertSame(team, teamController.create("Team", TeamStatus.ACTIVE, devs));
        verify(teamRepository).save(any());
        assertTrue(teamController.getAll().isEmpty());
    }

    @Test
    void createFalse() {
        List<Developer> devs = new ArrayList<>();
        Team team = new Team("Team", TeamStatus.ACTIVE, devs);
        when(teamRepository.save(any())).thenReturn(team);
        assertFalse(teamController.create("Team", TeamStatus.ACTIVE, devs) == null);
    }

    @Test
    void updateTrue() {
        List<Developer> devs = new ArrayList<>();
        Team team = new Team("Team", TeamStatus.ACTIVE, devs);
        when(teamRepository.update(any())).thenReturn(team);
        assertSame(team, teamController.update(1, "Team", TeamStatus.ACTIVE, devs));
        verify(teamRepository).update(any());
        assertTrue(teamController.getAll().isEmpty());
    }

    @Test
    void updateFalse() {
        List<Developer> devs = new ArrayList<>();
        Team team = new Team("Team", TeamStatus.ACTIVE, devs);
        when(teamRepository.update(any())).thenReturn(team);
        assertFalse(teamController.update(1, "Team", TeamStatus.ACTIVE, devs) == null);
    }

    @Test
    void getAllTrue() {
        ArrayList<Team> teamList = new ArrayList<>();
        when(teamRepository.getAll()).thenReturn(teamList);
        List<Team> actualList = teamController.getAll();
        assertSame(teamList, actualList);
        assertTrue(actualList.isEmpty());
        verify(teamRepository).getAll();
    }

    @Test
    void getAllFalse() {
        ArrayList<Team> teamList = new ArrayList<>();
        when(teamRepository.getAll()).thenReturn(teamList);
        assertFalse(teamController.getAll() == null) ;
    }

    @Test
    void getByIdTrue() {
        List<Developer> devs = new ArrayList<>();
        Team team = new Team("Team", TeamStatus.ACTIVE, devs);
        when(teamRepository.getById(any())).thenReturn(team);
        assertSame(team, teamController.getById(1));
        verify(teamRepository).getById(any());
        assertTrue(teamController.getAll().isEmpty());
    }

    @Test
    void getByIdFalse() {
        List<Developer> devs = new ArrayList<>();
        Team team = new Team("Team", TeamStatus.ACTIVE, devs);
        when(teamRepository.getById(any())).thenReturn(team);
        assertFalse(teamController.getById(1) == null);
    }

    @Test
    void deleteById() {
        doNothing().when(teamRepository).deleteById(any());
        teamRepository.deleteById(1);
        verify(teamRepository).deleteById(any());
        assertTrue(teamController.getAll().isEmpty());
    }
}