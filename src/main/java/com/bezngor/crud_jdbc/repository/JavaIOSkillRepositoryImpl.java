package com.bezngor.crud_jdbc.repository;

import com.bezngor.crud_jdbc.model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    static final String SQL_GET_ALL = "select * from skills";
    static final String SQL_SAVE = "insert into skills(name) values(?)";
    static final String SQL_UPDATE = "update skills set name = ? where id = ?";
    static final String SQL_DELETE_BY_ID = "delete from skills where id = ?";
    static DBWorker worker = new DBWorker();

    @Override
    public List<Skill> getAll() {
        int id;
        String name;
        List<Skill> skills = new ArrayList<>();

        try (Statement statement = worker.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
        ) {

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                skills.add(new Skill(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    @Override
    public Skill getById(Integer id) {
        Skill result = null;

        List<Skill> skills = this.getAll();
        for (Skill s : skills) {
            if (s.getId() == id)
                result = s;
        }

        return result;
    }

    @Override
    public Skill save(Skill skill) {

        try (PreparedStatement preparedStatement = worker.getConnection().prepareStatement(SQL_SAVE);)
        {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {

        try (PreparedStatement preparedStatement = worker.getConnection().prepareStatement(SQL_UPDATE);)
        {
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skill;
    }

    @Override
    public void deleteById(Integer id) {

        try (PreparedStatement preparedStatement = worker.getConnection().prepareStatement(SQL_DELETE_BY_ID);)
        {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}