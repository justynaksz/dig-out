package com.digout.database.generator;

import java.io.IOException;

public class Generator {

    public static void main(String[] args) throws IOException {
        ScriptGenerator scriptGenerator = new ScriptGenerator();
        scriptGenerator.generateSqlScripts(10000);
    }
}