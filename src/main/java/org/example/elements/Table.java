package org.example.elements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Table {
    private final SelenideElement table;
    private final SelenideElement tbody;
    private final ElementsCollection pagination;
    private Map<String, Integer> map;
    private final ElementsCollection headerColumns;

    public Table(SelenideElement container) {
        table = container;
        table.waitUntil(visible, 500);
        tbody = table.find(By.tagName("tbody"));
        pagination = container.$$("pagination ul li");
        headerColumns = table.$("thead").$$("th");
    }


    private int getColumnByHeader(String nameColumn) {

        map = new HashMap<>();
        for (int i = 0; i < headerColumns.size(); i++) {
            String header = headerColumns.get(i).getText();
            map.put(header, i);
        }
        //map.forEach((k, v) -> System.out.println("key: " + k + "\n" + "Value: " + v));

        return map.get(nameColumn);
    }

    public Table getTablePage(int numberOfPage) {
        int size = pagination.size() - 2;
        int pageNumber = numberOfPage + 2;
        if (numberOfPage >= size) {
            SelenideElement targetPage = pagination.get(pageNumber);
        } else {
            System.out.println("Столько пейджем там нет)");
        }
        return this;
    }

    @Step("Получить значение с {1} столбца в {0} строке")
    public String getValueInColumn(int row, String column) {
        SelenideElement rowTable = tbody.$$("tr").get(row);
        int indexTd = getColumnByHeader(column);
        return rowTable.$$("td").get(indexTd).getText();
    }

    @Step("Проверить что текст: \"{2}\" есть в {1} столбце {0}-й строке")
    public void checkValueInColumn(int row, String column, String expectedText) {
        assertEquals(getValueInColumn(row, column),expectedText);
    }
    //Тут все значения отсортированы от первого столбца в таблице
    public ArrayList<String> getAllValuesFromRow(int row) {
        ArrayList<String> list = new ArrayList<>();
        String column;
        for (int j = 0; j < headerColumns.size(); j++) {
            column = headerColumns.get(j).getText();
            list.add(getValueInColumn(row, column));
        }
        //list.forEach(k-> System.out.println(k.toLowerCase()));
        return list;
    }

    public SelenideElement getRowByValue(String value) {
        ElementsCollection rows;
        SelenideElement exactRow = null;
        if (value.contains(" ")) {
            String[] arr = value.split(" ");
            rows = tbody.$$("tr").filter(text(arr[0]));
            for (int i = 1; i < arr.length; i++) {
                rows = rows.filter(text(arr[i]));
                if (rows.size() == 1) {
                    exactRow = rows.first();
                }
            }
        } else {
            exactRow = tbody.$(byText(value)).closest("tr");
        }
        if (exactRow == null)
            throw new NullPointerException("Скорее всего знаечние не уникально проверь на интерфейсе");
        return exactRow;
    }

    public SelenideElement getTdInRowByColumnName(int row, String column) {
        SelenideElement rowTable = tbody.$$("tr").get(row);
        int indexTd = getColumnByHeader(column);
        return rowTable.$$("td").get(indexTd);
    }

    public SelenideElement getTdInRowByColumnIndex(int row, int col) {
        SelenideElement rowTable = tbody.$$("tr").get(row);
        return rowTable.$$("td").get(col);
    }

    public SelenideElement getRowByRowIndex(int row) {
        return tbody.$$("tr").get(row);
    }
}
