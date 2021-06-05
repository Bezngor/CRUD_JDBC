package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.repository.DeveloperRepository;
import com.bezngor.crud_jdbc.repository.JavaIODeveloperRepositoryImpl;
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

class DeveloperControllerCliTest {
    private DeveloperRepository developerRepository;
    private DeveloperControllerCli developerControllerCli;
    private Developer dev;
    private List<Skill> skills;

    @BeforeEach
    void setUp() {
        developerRepository = mock(DeveloperRepository.class);
        developerControllerCli = new DeveloperControllerCli(developerRepository);
        skills = new ArrayList<>();
        dev = new Developer("John", "Smith", skills);
    }

    @Test
    void testConstructor() {
        JavaIODeveloperRepositoryImpl developerRepositoryImpl = new JavaIODeveloperRepositoryImpl();
        assertSame(developerRepositoryImpl, new DeveloperControllerCli(developerRepositoryImpl).getDevRepo());
    }

    @Test
    void testCreate() {
        when(developerRepository.save(any())).thenReturn(dev);
        assertSame(dev, developerControllerCli.create("John", "Smith", skills));
        verify(developerRepository).save(any());
        assertTrue(developerControllerCli.getAll().isEmpty());
    }

    @Test
    void testUpdate() {
        when(developerRepository.update(any())).thenReturn(dev);
        assertSame(dev, developerControllerCli.update(1,"John", "Smith", skills));
        verify(developerRepository).update(any());
        assertTrue(developerControllerCli.getAll().isEmpty());
    }

    @Test
    void testGetAll() {
        ArrayList<Developer> devList = new ArrayList<>();
        when(developerRepository.getAll()).thenReturn(devList);
        List<Developer> actualList = developerControllerCli.getAll();
        assertSame(devList, actualList);
        assertTrue(actualList.isEmpty());
        verify(developerRepository).getAll();
    }

    @Test
    void testGetById() {
        when(developerRepository.getById(any())).thenReturn(dev);
        assertSame(dev, developerControllerCli.getById(1));
        verify(developerRepository).getById(any());
        assertTrue(developerControllerCli.getAll().isEmpty());
    }

    @Test
    void testDeleteById() {
        doNothing().when(developerRepository).deleteById(any());
        developerRepository.deleteById(1);
        verify(developerRepository).deleteById(any());
        assertTrue(developerControllerCli.getAll().isEmpty());
    }
}