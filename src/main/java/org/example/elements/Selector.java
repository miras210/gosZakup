package org.example.elements;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ListSizeMismatch;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Selector {
    private final ElementsCollection options;
    private SelenideElement select;
    private String text;

    public Selector(SelenideElement select) {
        options = $$x("//button[data-id='s2_month']/following-sibling::div[role='combobox']//ul//span[class='text']");
        this.select = select;
    }

    /*public void selectByText(String text) {
        select.click();
        options.find(Condition.text(text)).click();
    }

    public void selectOptionByIndex(int index) {
        select.click();
        options.get(index).click();
    }*/
    public String getText() {
        return text;
    }

    public void select(String s) {
        select.click();
        $(byText(s)).click();
        text = s;
    }

    public void selectAny(Object option) {
        select.click();
        try {
            this.options.shouldBe(CollectionCondition.sizeGreaterThan(1));
        }
        catch (ListSizeMismatch l) {
            throw new NullPointerException("Справочник селектора пустой!");
        }
        if ((option instanceof String)) {
            $x("//button[@data-id='s2_month']/following-sibling::div[@role='combobox']//ul//span[contains(text(), '" + option + "')]").shouldBe(visible);
            $x("//button[@data-id='s2_month']/following-sibling::div[@role='combobox']//ul//span[contains(text(), '" + option + "')]").click();
        } else {
            options.get((Integer) option).click();
        }
    }

    public void selectAnyStreet(Object option) {
        select.click();

        if ((option instanceof String)) {
            $x("//ng-dropdown-panel//div//div[2]//div//p[contains(text(), '" + String.valueOf(option) + "')]").shouldBe(visible);
            $x("//ng-dropdown-panel//div//div[2]//div//p[contains(text(), '" + String.valueOf(option) + "')]").click();
        } else {
            options.get((Integer) option).click();
        }
    }

    //TODO Тут Нужен норм локатор на значение в слекторе
    public String getValueFromSelector() {
        SelenideElement spanWithValue = select.$("span:nth-child(2)");
        spanWithValue.waitUntil(exist, 10000);
        return spanWithValue.getText();
    }

    public SelenideElement getSelectInput() {
        return select;
    }
}
