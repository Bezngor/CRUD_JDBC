package com.bezngor.crud_jdbc.repository;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    static final String SQL_GET_ALL_DEVS = "select * from crud_jdbc.developers";
    static final String SQL_GET_SKILLS_OF_DEV = "select * from crud_jdbc.skills_of_developers";
    static final String SQL_GET_BY_ID = "select id, name from crud_jdbc.developers where id = ?";
    static final String SQL_SAVE_NAMES = "insert into crud_jdbc.developers(firstName, lastName) values(?, ?)";
    static final String SQL_SAVE_SKILLS = "insert into crud_jdbc.skills_of_developers(id_dev, id_skill) values(?, ?)";
    static final String SQL_UPDATE_NAMES = "update crud_jdbc.developers set firstName = ?, lastName = ? where id = ?";
    static final String SQL_DELETE_BY_ID = "delete from developers where id = ?";
    static DBWorker worker = new DBWorker();

    @Override
    public List<Developer> getAll() {
        List<Developer> devs = new ArrayList<>();
        List<Skill> skills = new ArrayList<>();
        Integer idDev;
        Integer idSkill;
        String nameSkill;
        String firstName;
        String lastName;

        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement();
            Statement statement = DBWorker.getConnection().createStatement())
        {
            ResultSet resultSetNames = statement.executeQuery(SQL_GET_ALL_DEVS);
            ResultSet resultSetSkills = statement.executeQuery(SQL_GET_SKILLS_OF_DEV);

            while (resultSetNames.next()) {
                idDev = resultSetNames.getInt("id");
                firstName = resultSetNames.getString("firstName");
                lastName = resultSetNames.getString("lastName");

                while (resultSetSkills.next()) {
                    idSkill = resultSetSkills.getInt("id");
                    nameSkill = resultSetNames.getString("name");
                    if (idSkill == idDev)
                }
                devs.add(new Developer())
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Developer getById(Integer id) {
        return null;
    }

    @Override
    public Developer save(Developer developer) {
        try (PreparedStatement preparedStatement1 = DBWorker.getConnection().prepareStatement(SQL_SAVE_NAMES);
             PreparedStatement preparedStatement2 = DBWorker.getConnection().prepareStatement(SQL_SAVE_SKILLS);
             Statement statement = DBWorker.getConnection()
                     .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY))
        {
            preparedStatement1.setString(1, developer.getFirstName());
            preparedStatement1.setString(2, developer.getLastName());
            preparedStatement1.executeUpdate();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            resultSet.last();
            int id = resultSet.getInt("id");

            for (Skill s : developer.getSkills()) {
                preparedStatement2.setInt(1, id);
                preparedStatement2.setInt(2, s.getId());
                preparedStatement2.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
    try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(SQL_UPDATE_NAMES))
    {

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return developer;
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement preparedStatement = DBWorker.getConnection().prepareStatement(SQL_DELETE_BY_ID))
        {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
