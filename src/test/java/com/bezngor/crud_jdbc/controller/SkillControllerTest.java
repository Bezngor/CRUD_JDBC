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
        name = "C++";
        skill = new Skill(1, name);
    }

    @Test
    void getSkillRepo() {
        assertNotNull(mockedRepository);
    }

    @Test
    void create() {
        Mockito.when(mockedRepository.save(skill)).thenReturn(skill);
        assertEquals(controller.create(name).getName(), name);
    }

    @Test
    void update() {
        Mockito.when(mockedRepository.update(skill)).thenReturn(skill);
        assertSame(controller.update(1,name).getName(), name);
    }

    @Test
    void getAll() {
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(1, name));
        Mockito.when(mockedRepository.getAll()).thenReturn(skills);
        assertEquals(controller.getAll(), skills);
    }

    @Test
    void getById() {
        Mockito.when(mockedRepository.getById(1)).thenReturn(skill);
        assertEquals(controller.getById(1).getName(), skill.getName());
    }

    @Test
    void deleteById() {

    }
}