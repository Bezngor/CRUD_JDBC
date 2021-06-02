package com.bezngor.crud_jdbc.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.repository.JavaIOSkillRepositoryImpl;
import com.bezngor.crud_jdbc.repository.SkillRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkillControllerCliTest {
    private SkillRepository skillRepository;
    private SkillControllerCli skillControllerCli;
    private Skill skill;

    @BeforeEach
    void setUp() {
        skillRepository = mock(SkillRepository.class);
        skillControllerCli = new SkillControllerCli(skillRepository);
        skill = new Skill("Java");
    }

    @Test
    public void testConstructor() {
        JavaIOSkillRepositoryImpl skillRepositoryImpl = new JavaIOSkillRepositoryImpl();
        assertSame(skillRepositoryImpl, (new SkillControllerCli(skillRepositoryImpl)).getSkillRepo());
    }

    @Test
    public void testCreate() {
        when(skillRepository.save(any())).thenReturn(skill);
        assertSame(skill, skillControllerCli.create("Java"));
        verify(skillRepository).save(any());
        assertTrue(skillControllerCli.getAll().isEmpty());
    }

    @Test
    public void testUpdate() {
        when(skillRepository.update(any())).thenReturn(skill);
        assertSame(skill, skillControllerCli.update(1, "Java"));
        verify(skillRepository).update(any());
        assertTrue(skillControllerCli.getAll().isEmpty());
    }

    @Test
    public void testGetAll() {
        ArrayList<Skill> skillList = new ArrayList<>();
        when(skillRepository.getAll()).thenReturn(skillList);
        List<Skill> actualAll = skillControllerCli.getAll();
        assertSame(skillList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(skillRepository).getAll();
    }

    @Test
    public void testGetById() {
        when(skillRepository.getById(any())).thenReturn(skill);
        assertSame(skill, skillControllerCli.getById(1));
        verify(skillRepository).getById(any());
        assertTrue(skillControllerCli.getAll().isEmpty());
    }

    @Test
    public void testDeleteById() {
        doNothing().when(skillRepository).deleteById(any());
        skillControllerCli.deleteById(1);
        verify(skillRepository).deleteById(any());
        assertTrue(skillControllerCli.getAll().isEmpty());
    }
}

