package org.example;

import org.example.dto.Input;
import org.example.pages.MainPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        Base.MainTearUp();
        open("/lots");
        Input input = ParseInput.parseInput("C:\\Users\\Miras\\Desktop\\КТЖ\\input.txt"); // change the input path
        new MainPage()
                .setLotName(input.getLotName())
                .setLotPlan(input.getLotPlan())
                .submitForm()
                .collectData(50); // pass the number of needed rows
        Base.tearDown();
    }
}
