package com.bezngor.crud_jdbc.repository;

import com.bezngor.crud_jdbc.model.Skill;

import java.util.List;

public interface SkillRepository extends GenericRepository<Skill, Integer>{
    @Override
    List<Skill> getAll();

    @Override
    Skill getById(Integer integer);

    @Override
    Skill save(Skill skill);

    @Override
    Skill update(Skill skill);

    @Override
    void deleteById(Integer integer);
}
