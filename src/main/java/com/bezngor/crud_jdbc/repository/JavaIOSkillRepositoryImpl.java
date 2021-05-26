package com.bezngor.crud_jdbc.repository;

import com.bezngor.crud_jdbc.model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaIOSkillRepositoryImpl implements SkillRepository {
    DBWorker worker;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Integer id = null;
    String name = null;

    public JavaIOSkillRepositoryImpl() {
        worker = new DBWorker();
    }

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();
        String query = "select * from skills";

        try {
            statement = worker.getConnection().createStatement();
            resultSet = statement.executeQuery(query);

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
        String query = "select id, name from skills where id = ?";

        try {
            preparedStatement = worker.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                result = new Skill(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Skill save(Skill skill) {
        String query = "insert into skills(name) values(?)";
        try {
            preparedStatement = worker.getConnection().prepareStatement(query);
            preparedStatement.setString(1, skill.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
