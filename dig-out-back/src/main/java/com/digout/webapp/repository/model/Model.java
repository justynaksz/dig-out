package com.digout.webapp.repository.model;

public abstract class Model {

    static String tableName;

    public static String getTableName() {
        return tableName;
    }
}
