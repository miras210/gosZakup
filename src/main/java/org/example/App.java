package org.example;

import org.example.dto.Input;
import org.example.dto.Search;
import org.example.pages.MainPage;
import org.example.repository.InputRepository;
import org.example.repository.SearchRepository;

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
        Search search = SearchRepository.getSearchById(151);
        new MainPage()
                .setLotName(search.getLotName())
                .setLotPlan(search.getLotPlan())
                .submitForm()
                .collectData(10); // pass the number of output rows needed to save
        Base.tearDown();
    }
}
