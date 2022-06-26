package com.company;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingWithFiles {

    public static void readFiles() {
        int count = 0;
        File dir = new File("logs/");
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                String line = "";
                try (BufferedReader inputStream = new BufferedReader(new FileReader(file))) {
                    List<String> first100Numbers = inputStream.lines().limit(100).collect(Collectors.toList());
                    first100Numbers.add(line);
                    count++;
                    first100Numbers.forEach(System.out::println);
                    saveToNewFiles(first100Numbers, count);
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    private static void saveToNewFiles(List<String> first100Numbers, int count) throws IOException {
        String fileWrite = "writeLogs";
        File storageDir = new File(fileWrite);
        if (!storageDir.exists()) {
            storageDir.mkdir();
        }
        File file = new File(fileWrite + "/Information-2016-05-31-000" + count + ".log");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < first100Numbers.size(); i++) {
            bw.write(first100Numbers.get(i));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
