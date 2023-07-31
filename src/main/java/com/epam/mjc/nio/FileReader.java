package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> data = new HashMap<>();
        try (RandomAccessFile aFile = new RandomAccessFile(file.getAbsolutePath(), "r");
             FileChannel inChannel = aFile.getChannel()){
            Path path = Paths.get(file.getAbsolutePath());
            List<String> text = Files.readAllLines(path);

            for (String s : text) {
                String[] line = s.split(": ");
                data.put(line[0], line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String name = data.get("Name");
        Integer age = Integer.parseInt(data.get("Age"));
        String email = data.get("Email");
        Long phone = Long.parseLong(data.get("Phone"));

        return new Profile(name, age, email, phone);
    }
}
