package com.bezngor.crud_jdbc.repository;

import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.model.Team;
import com.bezngor.crud_jdbc.model.TeamStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaIOTeamRepositoryImpl implements TeamRepository {
    static final String SQL_GET_ALL_DEVS = "select * from crud_jdbc.developers";
    static final String SQL_GET_ALL_TEAMS = "select * from crud_jdbc.teams";
    static final String SQL_GET_DEVS_OF_TEAMS = "select * from crud_jdbc.developers_of_teams";
    static final String SQL_SAVE_TEAMS = "insert into crud_jdbc.teams(name, status) values(?, ?)";
    static final String SQL_SAVE_DEVS = "insert into crud_jdbc.developers_of_teams(id_team, id_dev) values(?, ?)";
    static final String SQL_UPDATE_NAMES = "update crud_jdbc.developers set firstName = ?, lastName = ? where id = ?";
    static final String SQL_SKILLS_DELETE = "delete from crud_jdbc.skills_of_developers where id_dev = ?";
    static final String SQL_DELETE_BY_ID = "delete from crud_jdbc.developers where id = ?";
    static DBWorker worker = new DBWorker();
    JavaIODeveloperRepositoryImpl developerRepository = new JavaIODeveloperRepositoryImpl();

    @Override
    public List<Team> getAll() {
        List<Team> teams = new ArrayList<>();
        int idTeam;
        int idDev;
        int idStatus;
        int id_team;
        int id_dev;
        String nameTeam;
        TeamStatus status;

        try (Statement statement1 = DBWorker.getConnection().createStatement();
             Statement statement2 = DBWorker.getConnection()
                     .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY))

        {   ResultSet resultSetTeams = statement1.executeQuery(SQL_GET_ALL_TEAMS);
            ResultSet resultSetDevsOfTeams = statement2.executeQuery(SQL_GET_DEVS_OF_TEAMS);

            while (resultSetTeams.next()) {
                idTeam = resultSetTeams.getInt("id");
                nameTeam = resultSetTeams.getString("name");
                idStatus = resultSetTeams.getInt("status");
                status = getStatusTeam(idStatus);

                List<Developer> devs = new ArrayList<>();
                while (resultSetDevsOfTeams.next()) {
                    id_team = resultSetDevsOfTeams.getInt("id_team");

                    if (id_team == idTeam) {
                        id_dev = resultSetDevsOfTeams.getInt("id_dev");
                        devs.add(developerRepository.getById(id_dev));
                    }
                }
                resultSetDevsOfTeams.first();
                teams.add(new Team(idTeam, nameTeam, status, devs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public Team getById(Integer id) {
        Team result = null;

        List<Team> teams = this.getAll();
        for (Team t : teams) {
            if (t.getId() == id) {
                result = t;
            }
        }
        return result;
    }

    @Override
    public Team save(Team team) {
        try (PreparedStatement preparedStatement1 = DBWorker.getConnection().prepareStatement(SQL_SAVE_TEAMS);
            PreparedStatement preparedStatement2 = DBWorker.getConnection().prepareStatement(SQL_SAVE_DEVS);
             Statement statement = DBWorker.getConnection()
                     .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY))
        {
            int statusId = setStatusTeam(team.getStatus());
            preparedStatement1.setString(1, team.getName());
            preparedStatement1.setInt(2, statusId);
            preparedStatement1.executeUpdate();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TEAMS);
            resultSet.last();
            int id = resultSet.getInt("id");

            for (Developer d : team.getDevs()) {
                preparedStatement2.setInt(1, id);
                preparedStatement2.setInt(2, d.getId());
                preparedStatement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }

    @Override
    public Team update(Team team) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    public TeamStatus getStatusTeam(int statusId) {
        TeamStatus status = null;
        switch (statusId) {
            case 1:
                status = TeamStatus.ACTIVE;
                break;
            case 2:
                status = TeamStatus.UNDER_REVIEW;
                break;
            case 3:
                status = TeamStatus.DELETED;
                break;
            default:
                System.out.println("Вы ввели неверный код!");
                break;
        }
        return status;
    }

    public int setStatusTeam(TeamStatus statusName) {
        int index = 0;
        switch (statusName) {
            case ACTIVE:
                index = 1;
                break;
            case UNDER_REVIEW:
                index = 2;
                break;
            case DELETED:
                index = 3;
                break;
            default:
                System.out.println("Вы ввели неверный код!");
                break;
        }
        return index;
    }
}
