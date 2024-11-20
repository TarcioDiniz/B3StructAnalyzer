package com.B3.domain_layer.Domain.Enums;

public enum CsvColumnEnum {
    DATETIME("datetime"),
    TICKER("ticker"),
    OPEN("open"),
    CLOSE("close"),
    HIGH("high"),
    LOW("low"),
    VOLUME("volume");

    private final String columnName;

    // Construtor privado para cada valor do enum
    CsvColumnEnum(String columnName) {
        this.columnName = columnName;
    }

    // Método para obter o nome da coluna
    public String getColumnName() {
        return columnName;
    }

    // Método para obter um CsvColumnEnum a partir de um nome de coluna
    public static CsvColumnEnum fromColumnName(String columnName) {
        for (CsvColumnEnum column : CsvColumnEnum.values()) {
            if (column.getColumnName().equalsIgnoreCase(columnName)) {
                return column;
            }
        }
        throw new IllegalArgumentException("No enum constant for column name: " + columnName);
    }
}
