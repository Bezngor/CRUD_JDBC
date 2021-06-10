package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.repository.DeveloperRepository;
import com.bezngor.crud_jdbc.repository.jdbc.JavaIODeveloperRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class DeveloperControllerTest {
    private DeveloperRepository developerRepository;
    private DeveloperController developerController;

    @BeforeEach
    void setUp() {
        developerRepository = mock(DeveloperRepository.class);
        developerController = new DeveloperController(developerRepository);
    }

    @Test
    void testCreateTrue() {
        List<Skill> skills = new ArrayList<>();
        Developer dev = new Developer("John", "Smith", skills);
        when(developerRepository.save(any())).thenReturn(dev);
        assertSame(dev, developerController.create("John", "Smith", skills));
        verify(developerRepository).save(any());
        assertTrue(developerController.getAll().isEmpty());
    }

    @Test
    void testCreateFalse() {
        List<Skill> skills = new ArrayList<>();
        Developer dev = new Developer("John", "Smith", skills);
        when(developerRepository.save(any())).thenReturn(dev);
        assertFalse(developerController.create("John", "Smith", skills) == null);
    }

    @Test
    void testUpdateTrue() {
        List<Skill> skills = new ArrayList<>();
        Developer dev = new Developer("John", "Smith", skills);
        when(developerRepository.update(any())).thenReturn(dev);
        assertSame(dev, developerController.update(1,"John", "Smith", skills));
        verify(developerRepository).update(any());
        assertTrue(developerController.getAll().isEmpty());
    }

    @Test
    void testUpdateFalse() {
        List<Skill> skills = new ArrayList<>();
        Developer dev = new Developer("John", "Smith", skills);
        when(developerRepository.update(any())).thenReturn(dev);
        assertFalse(developerController.update(1, "John", "Smith", skills) == null);
    }

    @Test
    void testGetAllTrue() {
        ArrayList<Developer> devList = new ArrayList<>();
        when(developerRepository.getAll()).thenReturn(devList);
        List<Developer> actualList = developerController.getAll();
        assertSame(devList, actualList);
        assertTrue(actualList.isEmpty());
        verify(developerRepository).getAll();
    }

    @Test
    void testGetAllFalse() {
        ArrayList<Developer> devList = new ArrayList<>();
        when(developerRepository.getAll()).thenReturn(devList);
        assertFalse(developerController.getAll() == null);
    }

    @Test
    void testGetByIdTrue() {
        List<Skill> skills = new ArrayList<>();
        Developer dev = new Developer("John", "Smith", skills);
        when(developerRepository.getById(any())).thenReturn(dev);
        assertSame(dev, developerController.getById(1));
        verify(developerRepository).getById(any());
        assertTrue(developerController.getAll().isEmpty());
    }

    @Test
    void testGetByIdFalse() {
        List<Skill> skills = new ArrayList<>();
        Developer dev = new Developer("John", "Smith", skills);
        when(developerRepository.getById(any())).thenReturn(dev);
        assertFalse(developerController.getById(1) == null);
    }

    @Test
    void testDeleteById() {
        doNothing().when(developerRepository).deleteById(any());
        developerRepository.deleteById(1);
        verify(developerRepository).deleteById(any());
        assertTrue(developerController.getAll().isEmpty());
    }
}