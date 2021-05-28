package com.bezngor.crud_jdbc.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SkillControllerTest {
    @Test
    public void testGetSkillRepo() {
        assertTrue(
                (new SkillController()).getSkillRepo() instanceof com.bezngor.crud_jdbc.repository.JavaIOSkillRepositoryImpl);
    }
}

