package org.example;

import org.example.dto.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParseInput {
    public static Input parseInput(String path) {
        Input input = new Input();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                input.setLotName(values[0]);
                input.setLotPlan(values[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
