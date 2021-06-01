package com.bezngor.crud_jdbc.controller;

import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.repository.JavaIOSkillRepositoryImpl;
import com.bezngor.crud_jdbc.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkillControllerTest {
    private SkillRepository mockedRepository;
    private SkillController mockedController;
    private Skill skill;
    private String name;



    @BeforeEach
    void setUp() {
        mockedRepository = Mockito.mock(JavaIOSkillRepositoryImpl.class);
        mockedController = Mockito.mock(SkillController.class);
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
        assertSame(mockedController.create(name), skill);
    }

    @Test
    void update() {
        Mockito.when(mockedRepository.update(new Skill(0, name))).thenReturn(skill);
        assertSame(mockedController.update(0,name), skill);
    }

    @Test
    void getAll() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(name));
        Mockito.when(mockedRepository.getAll()).thenReturn(skills);
        assertSame(mockedController.getAll(), skills);
    }

    @Test
    void getById() {
        Mockito.when(mockedRepository.getById(0)).thenReturn(skill);
        assertSame(mockedController.getById(0), skill);
    }

    @Test
    void deleteById() {

    }
}