package com.digout.generator;

import com.digout.generator.outputwriter.ScriptGenerator;

import java.io.IOException;

public class Generator {

    public static void main(String[] args) throws IOException {
        ScriptGenerator scriptGenerator = new ScriptGenerator();
        scriptGenerator.generateSqlScripts(100);
    }
}