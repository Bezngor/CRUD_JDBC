package com.bezngor.crud_jdbc.utils;

public enum Constants {
    START_MESSAGE("1 - Сохранить новый %1s;\n2 - Обновить %1s по индексу;\n" +
                        "3 - Вывести %1s по индексу;\n4 - Вывести все %1s;\n" +
                        "5 - Удалить %1s по индексу;\n%2s - Выход из модуля.\n"),
    INSERT_CODE_OPERATION("Введите код операции:"),
    INSERT_NAME("Введите название "),
    INSERT_ID_UPDATING("Введите id обновляемого "),
    INSERT_ID_CALLING("Введите id вызываемого "),
    INSERT_ID_DELETING("Введите id удаляемого "),
    INSERT_ID_NEXT("Введите id следующего "),
    WRONG_CODE("Вы ввели неверный код!"),
    EXIT("exit"),
    END("end"),
    INSERT_LASTNAME("Введите фамилию "),
    FOR_ADDING("Для добавления %s введите его id.\nПо окончании введите %s\n");

    private String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
