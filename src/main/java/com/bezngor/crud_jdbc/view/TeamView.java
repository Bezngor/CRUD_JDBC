package com.bezngor.crud_jdbc.view;

import com.bezngor.crud_jdbc.controller.TeamController;
import com.bezngor.crud_jdbc.model.Developer;
import com.bezngor.crud_jdbc.model.TeamStatus;
import com.bezngor.crud_jdbc.repository.jdbc.JavaIOTeamRepositoryImpl;
import com.bezngor.crud_jdbc.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO: 09.06.2021  Make JOIN tables
//import static com.bezngor.crud_jdbc.controller.DeveloperController.devRepo;

public class TeamView {
    static TeamController teamController = new TeamController(new JavaIOTeamRepositoryImpl());

    public void teamViewStart() {
        System.out.printf(Constants.START_MESSAGE.getValue(), "Team", Constants.END);

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist) {
            System.out.println(Constants.INSERT_CODE_OPERATION.getValue());
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println(Constants.INSERT_NAME.getValue() + "Team:");
                    String name1 = scan.nextLine();
                    System.out.println("Введите статус Team:\n   1 - ACTIVE\n   2 - UNDER_REVIEW\n   3 - DELETED");
                    String statusId1 = scan.nextLine();
                    TeamStatus status1 = getStatusTeam(statusId1);
                    System.out.println("Для добавления Developer введите его id.\nПо окончании введите end");
                    List<Developer> devs1 = new ArrayList<>();
                    boolean isNext1 = true;
                    while (isNext1) {
                        String str1 = scan.nextLine();
                        if (!str1.equals("end")) {
//                            devs1.add(devRepo.getById(Integer.parseInt(str1)));
                            System.out.println("Введите id следующего Developer:");
                        } else isNext1 = false;
                    }

                    teamController.create(name1, status1, devs1);
                    break;
                case "2":
                    System.out.println(Constants.INSERT_ID_UPDATING.getValue() + "Team:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println("Введите имя Team:");
                    String name2 = scan.nextLine();
                    System.out.println("Введите статус Team:\n   1 - ACTIVE\n   2 - UNDER_REVIEW\n   3 - DELETED");
                    String statusId2 = scan.nextLine();
                    TeamStatus status2 = getStatusTeam(statusId2);

                    System.out.println("Для добавления Developer введите его id.\nПо окончании введите end");
                    List<Developer> devs2 = new ArrayList<>();
                    boolean isNext2 = true;
                    while (isNext2) {
                        String str2 = scan.nextLine();
                        if (!str2.equals("end")) {
//                            devs2.add(devRepo.getById(Integer.parseInt(str2)));
                            System.out.println("Введите id следующего Developer:");
                        } else isNext2 = false;
                    }
                    teamController.update(id2, name2, status2, devs2);
                    break;
                case "3":
                    System.out.println(Constants.INSERT_ID_CALLING.getValue() + "Team:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
//                    System.out.println(TeamController.teamRepo.getById(id3));
                    break;
                case "4":
                    teamController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println(Constants.INSERT_ID_DELETING.getValue() + "Team:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    teamController.deleteById(id5);
                    break;
                case "exit":
                    isExist = buf.equals("exit");
                    break;
                default:
                    System.out.println(Constants.WRONG_CODE.getValue());
                    break;
            }
        }
    }

    public TeamStatus getStatusTeam(String statusId) {
        TeamStatus status = null;
        switch (statusId) {
            case "1":
                status = TeamStatus.ACTIVE;
                break;
            case "2":
                status = TeamStatus.UNDER_REVIEW;
                break;
            case "3":
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
