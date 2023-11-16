package com.digout.database.generator;

import com.digout.database.generator.model.LocalizationGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Generator {

    public static void main(String[] args) throws IOException {
        ScriptGenerator scriptGenerator = new ScriptGenerator();
        scriptGenerator.generateSqlScripts(10000);
    }
}