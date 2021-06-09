package com.bezngor.crud_jdbc.utils;

public enum Constants {
    START_MESSAGE("1 - Сохранить новый %1$s;\n2 - Обновить %1$s по индексу;\n" +
                        "3 - Вывести %1$s по индексу;\n4 - Вывести все %1$s;\n" +
                        "5 - Удалить %1$s по индексу;\n%2$s - Выход из модуля.\n"),
    INSERT_CODE_OPERATION("Введите код операции:"),
    INSERT_NAME("Введите название "),
    INSERT_ID_UPDATING("Введите id обновляемого "),
    INSERT_ID_CALLING("Введите id вызываемого "),
    INSERT_ID_DELETING("Введите id удаляемого "),
    INSERT_ID_NEXT("Введите id следующего "),
    INSERT_STATUS("Введите статус Team:\n   1 - %S\n   2 - %S\n   3 - %S\n"),
    INSERT_LASTNAME("Введите фамилию "),
    WRONG_CODE("Вы ввели неверный код!"),
    FOR_ADDING("Для добавления %s введите его id.\nПо окончании введите %s\n"),
    END("end"),
    EXIT("exit");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
