package com.bezngor.crud_jdbc.view;

import com.bezngor.crud_jdbc.controller.DeveloperController;
import com.bezngor.crud_jdbc.model.Skill;
import com.bezngor.crud_jdbc.controller.SkillController;
import com.bezngor.crud_jdbc.repository.jdbc.JavaIODeveloperRepositoryImpl;
import com.bezngor.crud_jdbc.repository.jdbc.JavaIOSkillRepositoryImpl;
import com.bezngor.crud_jdbc.utils.Constants;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    static DeveloperController devController = new DeveloperController(new JavaIODeveloperRepositoryImpl());
    static SkillController skillController = new SkillController(new JavaIOSkillRepositoryImpl());

    public void devViewStart() throws SQLException {
        System.out.printf(Constants.START_MESSAGE.getValue(), "Developer", Constants.END);

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist){
            System.out.println(Constants.INSERT_CODE_OPERATION.getValue());
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println(Constants.INSERT_NAME.getValue() + "Developer:");
                    String firstName1 = scan.nextLine();
                    System.out.println(Constants.INSERT_LASTNAME.getValue() + "Developer:");
                    String lastName1 = scan.nextLine();

                    System.out.printf(Constants.FOR_ADDING.getValue(), "Skill", Constants.END.getValue());
                    List<Skill> skills1 = new ArrayList<>();
                    boolean isNext1 = true;
                    while (isNext1) {
                        String str1 = scan.nextLine();
                        if (!str1.equals("end")) {
                            skills1.add(skillController.getSkillRepo().getById(Integer.parseInt(str1)));
                            System.out.println(Constants.INSERT_ID_NEXT.getValue() + "Skill:");
                        } else isNext1 = false;
                    }

                    devController.create(firstName1, lastName1, skills1);
                    break;
                case "2":
                    System.out.println(Constants.INSERT_ID_UPDATING.getValue() + "Developer:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println(Constants.INSERT_NAME.getValue() + "Developer:");
                    String firstName2 = scan.nextLine();
                    System.out.println(Constants.INSERT_LASTNAME.getValue() + "Developer:");
                    String lastName2 = scan.nextLine();

                    System.out.printf(Constants.FOR_ADDING.getValue(), "Skill", Constants.END.getValue());
                    List<Skill> skills2 = new ArrayList<>();
                    boolean isNext2 = true;
                    while (isNext2) {
                        String str2 = scan.nextLine();
                        if (!str2.equals("end")) {
                            skills2.add(skillController.getSkillRepo().getById(Integer.parseInt(str2)));
                            System.out.println(Constants.INSERT_ID_NEXT.getValue() + "Skill:");
                        } else isNext2 = false;
                    }

                    devController.update(id2, firstName2, lastName2, skills2);
                    break;
                case "3":
                    System.out.println(Constants.INSERT_ID_CALLING.getValue() + "Developer:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
                    System.out.println(devController.getById(id3));
                    break;
                case "4":
                    devController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println(Constants.INSERT_ID_DELETING.getValue() + "Developer:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    devController.deleteById(id5);
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
}
