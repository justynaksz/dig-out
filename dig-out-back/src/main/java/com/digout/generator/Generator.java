package com.digout.generator;

import com.digout.generator.outputwriter.ScriptGenerator;

public class Generator {

    public static void main(String[] args) {
        ScriptGenerator scriptGenerator = new ScriptGenerator();
        scriptGenerator.generateSqlScripts(1000);
    }
}