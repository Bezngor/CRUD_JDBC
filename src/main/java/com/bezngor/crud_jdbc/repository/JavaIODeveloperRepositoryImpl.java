package com.bezngor.crud_jdbc.repository;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    static final String SQL_GET_ALL = "select * from developers";
    static final String SQL_GET_BY_ID = "select id, name from developers where id = ?";
    static final String SQL_SAVE_NAMES = "insert into developers(firstName, lastName) values(?, ?)";
    static final String SQL_SAVE_SKILLS = "insert into skills_of_developers(id_dev, id_skill) values(?, ?)";
    static final String SQL_UPDATE = "update developers set name = ? where id = ?";
    static final String SQL_DELETE_BY_ID = "delete from developers where id = ?";
    static DBWorker worker = new DBWorker();

    @Override
    public List<Developer> getAll() {
        return null;
    }

    @Override
    public Developer getById(Integer integer) {
        return null;
    }

    @Override
    public Developer save(Developer developer) {
        try (PreparedStatement preparedStatement1 = DBWorker.getConnection().prepareStatement(SQL_SAVE_NAMES);
             PreparedStatement preparedStatement2 = DBWorker.getConnection().prepareStatement(SQL_SAVE_SKILLS);
             Statement statement = DBWorker.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet resultSet = statement.executeQuery(SQL_GET_ALL))
        {
            preparedStatement1.setString(1, developer.getFirstName());
            preparedStatement1.setString(2, developer.getLastName());
            preparedStatement1.executeUpdate();

            resultSet.last();
            int id = resultSet.getInt("id");

            for (Skill s : developer.getSkills()) {
                preparedStatement2.setInt(1, id);
                preparedStatement2.setInt(2, s.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer update(Developer developer) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
