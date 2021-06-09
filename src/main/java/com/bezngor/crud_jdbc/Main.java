package com.bezngor.crud_jdbc;

import com.bezngor.crud_jdbc.view.DeveloperView;
import com.bezngor.crud_jdbc.view.SkillView;
import com.bezngor.crud_jdbc.view.TeamView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        SkillView skillView = new SkillView();
        DeveloperView devView = new DeveloperView();
        TeamView teamView = new TeamView();

        System.out.println("Выберите тип операции:\n1 - Операции с Skill\n" +
                "2 - Операции с Developer\n3 - Операции с Team\nexit - Завершить работу.");

        Scanner scan = new Scanner(System.in);
        String str;
        boolean hasNext = true;

        while (hasNext) {
            str = scan.nextLine();
            switch (str) {
                case "1":
                    skillView.skillViewStart();
                    break;
                case "2":
                    devView.devViewStart();
                    break;
                case "3":
                    teamView.teamViewStart();
                    break;
                case "exit":
                    hasNext = false;
                    break;
                default:
                    System.out.println("Вы ввели некорректный индекс!\nПопробуйте ещё раз...");
                    break;
            }
        }
    }
}
