package com.bezngor.crud_jdbc.view;

import com.bezngor.crud_jdbc.controller.SkillController;
import com.bezngor.crud_jdbc.utils.Constants;

import java.util.Scanner;

public class SkillView {
    static SkillController skillController = new SkillController();

    public void skillViewStart() {
        System.out.println("1 - Сохранить новый Skill;\n2 - Обновить Skill по индексу;\n" +
                "3 - Вывести Skill по индексу;\n4 - Вывести все Skill;\n" +
                "5 - Удалить Skill по индексу;\nexit - Выход из модуля.");

        boolean isExist = false;
        String buf;
        Scanner scan = new Scanner(System.in);

        while (!isExist) {
            System.out.println(Constants.INSERT_CODE_OPERATION);
            buf = scan.nextLine();
            switch (buf) {
                case "1":
                    System.out.println("Введите название Skill:");
                    skillController.create(scan.nextLine());
                    break;
                case "2":
                    System.out.println("Введите id обновляемого Skill:");
                    Integer id2 = Integer.parseInt(scan.nextLine());
                    System.out.println("Введите название Skill:");
                    String name = scan.nextLine();
                    skillController.update(id2, name);
                    break;
                case "3":
                    System.out.println("Введите id вызываемого Skill:");
                    Integer id3 = Integer.parseInt(scan.nextLine());
                    System.out.println(skillController.getById(id3));
                    break;
                case "4":
                    skillController.getAll().forEach(System.out::println);
                    break;
                case "5":
                    System.out.println("Введите id удаляемого Skill:");
                    Integer id5 = Integer.parseInt(scan.nextLine());
                    skillController.deleteById(id5);
                    break;
                case "exit":
                    isExist = buf.equals("exit");
                    break;
                default:
                    System.out.println("Вы ввели неверный код!");
                    break;
            }
        }
    }
}
