package com.bezngor.crud_jdbc.repository.jdbc;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.utils.JdbcUtils;
import com.bezngor.crud_jdbc.repository.DeveloperRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    static final String SQL_GET_ALL_DEVS = "select * from crud_jdbc.developers";
    static final String SQL_GET_SKILLS_OF_DEV = "select * from crud_jdbc.skills_devs";
    static final String SQL_SAVE_NAMES = "insert into crud_jdbc.developers(firstName, lastName) values(?, ?)";
    static final String SQL_SAVE_SKILLS = "insert into crud_jdbc.skills_devs(id_dev, id_skill) values(?, ?)";
    static final String SQL_UPDATE_NAMES = "update crud_jdbc.developers set firstName = ?, lastName = ? where id = ?";
    static final String SQL_SKILLS_DELETE = "delete from crud_jdbc.skills_devs where id_dev = ?";
    static final String SQL_DELETE_BY_ID = "delete from crud_jdbc.developers where id = ?";
    public JavaIOSkillRepositoryImpl skillRepository = new JavaIOSkillRepositoryImpl();

    @Override
    public List<Developer> getAll() {
        List<Developer> devs = new ArrayList<>();
        int idDev;
        int id_dev;
        int id_skill;
        String firstName;
        String lastName;

        try (Statement statement1 = JdbcUtils.getConnection().createStatement();
             Statement statement2 = JdbcUtils.getConnection()
                .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY))

        {   ResultSet resultSetNames = statement1.executeQuery(SQL_GET_ALL_DEVS);
            ResultSet resultSetSkillsOfDev = statement2.executeQuery(SQL_GET_SKILLS_OF_DEV);

            while (resultSetNames.next()) {
                idDev = resultSetNames.getInt("id");
                firstName = resultSetNames.getString("firstName");
                lastName = resultSetNames.getString("lastName");
                List<Skill> skills = new ArrayList<>();

                while (resultSetSkillsOfDev.next()) {
                    id_dev = resultSetSkillsOfDev.getInt("id_dev");
                    if (id_dev == idDev) {
                        id_skill = resultSetSkillsOfDev.getInt("id_skill");

                        skills.add(skillRepository.getById(id_skill));
                    }
                }
                resultSetSkillsOfDev.first();

                devs.add(new Developer(idDev, firstName, lastName, skills));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devs;
    }

    @Override
    public Developer getById(Integer id) {
        Developer result = null;

        List<Developer> devs = this.getAll();
        for (Developer d : devs) {
            if (d.getId() == id) {
                result = d;
            }
        }
        return result;
    }

    @Override
    public Developer save(Developer developer) {
        try (PreparedStatement preparedStatement1 = JdbcUtils.getConnection().prepareStatement(SQL_SAVE_NAMES);
             PreparedStatement preparedStatement2 = JdbcUtils.getConnection().prepareStatement(SQL_SAVE_SKILLS);
             Statement statement = JdbcUtils.getConnection()
                     .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY))
        {
            preparedStatement1.setString(1, developer.getFirstName());
            preparedStatement1.setString(2, developer.getLastName());
            preparedStatement1.executeUpdate();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_DEVS);
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
    public Developer update(Developer dev) {
        Developer updDev = null;
    try (PreparedStatement preparedStatement1 = JdbcUtils.getConnection().prepareStatement(SQL_UPDATE_NAMES);
         PreparedStatement preparedStatement2 = JdbcUtils.getConnection().prepareStatement(SQL_SKILLS_DELETE);
         PreparedStatement preparedStatement3 = JdbcUtils.getConnection().prepareStatement(SQL_SAVE_SKILLS))
    {
        List<Developer> devs = this.getAll();
        updDev = devs.stream().filter(s -> s.getId() == dev.getId()).findFirst().orElse(null);
        updDev.setFirstName(dev.getFirstName());
        updDev.setLastName(dev.getLastName());
        updDev.setSkills(dev.getSkills());

        preparedStatement1.setString(1, updDev.getFirstName());
        preparedStatement1.setString(2, updDev.getLastName());
        preparedStatement1.setInt(3, updDev.getId());
        preparedStatement1.executeUpdate();

        preparedStatement2.setInt(1, updDev.getId());
        preparedStatement2.executeUpdate();

        for (Skill s : updDev.getSkills()) {
            preparedStatement3.setInt(1, updDev.getId());
            preparedStatement3.setInt(2, s.getId());
            preparedStatement3.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return updDev;
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement preparedStatement1 = JdbcUtils.getConnection().prepareStatement(SQL_SKILLS_DELETE);
             PreparedStatement preparedStatement2 = JdbcUtils.getConnection().prepareStatement(SQL_DELETE_BY_ID))
        {
            preparedStatement1.setInt(1, id);
            preparedStatement1.executeUpdate();

            preparedStatement2.setInt(1, id);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
