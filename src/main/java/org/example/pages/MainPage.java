package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.example.dto.Input;
import org.example.dto.Output;
import org.example.elements.Selector;
import org.example.elements.Table;
import org.example.repository.InputRepository;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final SelenideElement lotName = $("input[name='filter[name]']");
    private final Selector lotPlan = new Selector($("button[data-id='s2_month']"));
    private final Table table = new Table($("table[id='search-result']"));

    public MainPage setLotName(String lotName) {
        this.lotName.setValue(lotName);
        return this;
    }

    public MainPage setLotPlan(String lotPlan) {
        this.lotPlan.select(lotPlan);
        return this;
    }

    public MainPage submitForm() {
        $(byText("Найти")).click();
        return this;
    }

    public void collectData(int num) {
        List<Output> outputs = new ArrayList<>();
        Input input = new Input();
        input.setLotName(lotName.getValue());
        input.setLotPlan(lotPlan.getText());

        for (int i = 0; i < num; i++) {
            System.out.println(i);
            Output output = new Output();
            output.setLotNum(table.getTdInRowByColumnName(i, "№ лота").getText());
            output.setAdName(table.getTdInRowByColumnName(i, "Наименование объявления").$("a").getText());
            output.setLotDesc(table.getTdInRowByColumnName(i, "Наименование и описание лота").$("a").getText());
            output.setCount(Double.parseDouble(table.getTdInRowByColumnName(i, "Кол-во").getText()));
            output.setCost(Double.parseDouble(table.getTdInRowByColumnName(i, "Сумма, тг.").getText().replaceAll("\\s+", "")));
            output.setPurchaseType(table.getTdInRowByColumnName(i, "Способ закупки").getText());
            output.setStatus(table.getTdInRowByColumnName(i, "Статус").getText());
            outputs.add(output);
        }
        InputRepository.saveInput(outputs, input);
    }

}
