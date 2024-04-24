package com.zvv;

import com.zvv.template.TemplateProcessor;
import com.zvv.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("На вход необходимо 3 имени файла");
        }
        File textFile = Paths.get(args[0]).toFile();
        FileUtil.checkIfFileExists(textFile);

        File templateFile = Paths.get(args[1]).toFile();
        FileUtil.checkIfFileExists(templateFile);

        String resulFilePath = args[2];
        File outputFile = FileUtil.createFile(resulFilePath);

        TemplateProcessor templateProcessor = new TemplateProcessor(templateFile);

        templateProcessor.processFile(textFile);

        templateProcessor.printOutput(outputFile);
        System.out.println("Finished.");
    }


}
