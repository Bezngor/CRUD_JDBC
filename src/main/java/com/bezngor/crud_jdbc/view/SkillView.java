package com.bezngor.crud_jdbc.view;

import com.bezngor.crud_jdbc.controller.SkillController;
import com.bezngor.crud_jdbc.repository.jdbc.JavaIOSkillRepositoryImpl;
import com.bezngor.crud_jdbc.utils.Constants;

import java.util.Scanner;

public class SkillView {
    static SkillController skillController = new SkillController(new JavaIOSkillRepositoryImpl());

    public void skillViewStart() {
        System.out.printf(Constants.START_MESSAGE.getValue(),"Skill", Constants.END);

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist) {
            System.out.println(Constants.INSERT_CODE_OPERATION.getValue());
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println(Constants.INSERT_NAME.getValue() + "Skill:");
                    skillController.create(scan.nextLine());
                    break;
                case "2":
                    System.out.println(Constants.INSERT_ID_UPDATING.getValue() + "Skill:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println(Constants.INSERT_NAME.getValue() + "Skill:");
                    String name = scan.nextLine();
                    skillController.update(id2, name);
                    break;
                case "3":
                    System.out.println(Constants.INSERT_ID_CALLING.getValue() + "Skill:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
                    System.out.println(skillController.getById(id3));
                    break;
                case "4":
                    skillController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println(Constants.INSERT_ID_DELETING.getValue() + "Skill:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    skillController.deleteById(id5);
                    break;
                case "exit":
                    isExist = buf.equals(Constants.EXIT.getValue());
                    break;
                default:
                    System.out.println(Constants.WRONG_CODE.getValue());
                    break;
            }
        }
    }
}
