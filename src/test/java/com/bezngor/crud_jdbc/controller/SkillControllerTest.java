package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.repository.JavaIOSkillRepositoryImpl;
import com.bezngor.crud_jdbc.repository.SkillRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkillControllerTest {
    private SkillRepository mockedRepository;
    private JavaIOSkillRepositoryImpl mockedIOSkill;
    private Skill skill;
    private String name;



    @BeforeEach
    void setUp() {
        mockedRepository = Mockito.mock(SkillRepository.class);
        mockedIOSkill = Mockito.mock(JavaIOSkillRepositoryImpl.class);
        name = "Java";
        skill = new Skill(name);
    }

    @Test
    void getSkillRepo() {
        assertNotNull(mockedRepository);
    }

    @Test
    void create() {
        Mockito.when(mockedRepository.save(new Skill(name))).thenReturn(skill);
    }

    @Test
    void update() {
        Mockito.when(mockedRepository.update(skill)).thenReturn(skill);
    }

    @Test
    void getAll() {
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        Mockito.when(mockedIOSkill.getAll()).thenReturn(skills);
        assertEquals(mockedRepository.getAll().size(), 1);
    }

    @Test
    void getById() {
        List<Skill> skills = new ArrayList<>();
        skills.add(skill);
        Mockito.when(mockedIOSkill.getById(0)).thenReturn(skill);
        assertEquals(mockedRepository.getById(0).getName(), "Java");
    }

    @Test
    void deleteById() {
    }
}