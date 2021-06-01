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
    private SkillController controller;
    private Skill skill;
    private String name;



    @BeforeEach
    void setUp() {
        mockedRepository = Mockito.mock(JavaIOSkillRepositoryImpl.class);
        controller = new SkillController();
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
        assertEquals(controller.create(name).getName(), name);
    }

    @Test
    void update() {
        Mockito.when(mockedRepository.update(new Skill(0, name))).thenReturn(skill);
        assertSame(controller.update(0,name).getName(), name);
    }

    @Test
    void getAll() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(name));
        Mockito.when(mockedRepository.getAll()).thenReturn(skills);
        assertSame(controller.getAll(), skills);
    }

    @Test
    void getById() {
        Mockito.when(mockedRepository.getById(0)).thenReturn(skill);
        assertSame(controller.getById(0), skill);
    }

    @Test
    void deleteById() {

    }
}