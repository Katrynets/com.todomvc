package com.todomvc;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TodoTests {

    Action action = new Action();
    Data data = new Data();
    PageElements pageElements = new PageElements();
    SelenideElement itemName = pageElements.getItemName();
    SelenideElement itemCheckbox = pageElements.getItemCheckbox();
    SelenideElement getItemCounter = pageElements.getItemCount();
    SelenideElement counterText = pageElements.getCounterText();
    SelenideElement todoItem = pageElements.getTodoItem();

    @BeforeEach
    void openHomePage() {
        String site = data.getSite();
        open(site);
    }

    @AfterEach
    void clearItems() {
        action.clearAllItems(5);
    }


    @Test
    void addTodoItem() {
        action.createNewTodoItemPosData(0);
        String posData = new String();
        itemName.shouldHave(text(posData));
    }

    @Test
    void checkAddedItem() {
        action.checkCheckboxAddedItem(0);
        itemCheckbox.shouldHave(attribute("checked"));
    }

    @Test
    void uncheckAddedItem() {
        action.checkCheckboxAddedItem(0);
        itemCheckbox.click();
        itemCheckbox.shouldNotHave(attribute("checked"));
    }

    @Test
    void removeAddedItem() {
        String posData = new String();
        action.removeAddedItems();

        itemName.shouldNotHave(Condition.text(posData));
    }

    @Test
    void clearAllItems() {
        String posData = data.positiveData[0];
        action.clearAllItems(0);

        itemName.shouldNotHave(Condition.text(posData));
    }

    @Test
    void checkItemCounter() {

        for (int i = 1; i < 6; i++) {
            String posData = data.positiveData[0]+" "+i;
            this.todoItem.setValue(posData).pressEnter();
        }

        getItemCounter.shouldHave(text("5"));
    }

    @Test
    void checkItemCounterForCheckedItems() {

        for (int i = 1; i < 6; i++) {
            String posData = data.positiveData[0]+" "+i;
            this.todoItem.setValue(posData).pressEnter();
        }

        pageElements.itemCheckNumers(2).click();
        pageElements.itemCheckNumers(4).click();

        getItemCounter.shouldHave(Condition.text("3"));
    }

    @Test
    void checkCheckboxesForActiveTab() {

        for (int i = 1; i < 6; i++) {
            String posData = data.positiveData[0]+" "+i;

            this.todoItem.setValue(posData).pressEnter();
        }

        pageElements.itemCheckNumers(2).click();
        pageElements.itemCheckNumers(4).click();
        pageElements.getActiveTab().click();

        pageElements.itemCheckNumers(1).shouldNotHave(attribute("checked"));
        pageElements.itemCheckNumers(2).shouldNotHave(attribute("checked"));
        pageElements.itemCheckNumers(3).shouldNotHave(attribute("checked"));
    }

    @Test
    void checkCheckboxesForCompletedTab() {
        SelenideElement complatedTab = pageElements.getComplatedTab();

        for (int i = 1; i < 6; i++) {
            String posData = data.positiveData[0]+" "+i;

            this.todoItem.setValue(posData).pressEnter();
        }

        pageElements.itemCheckNumers(2).click();
        pageElements.itemCheckNumers(4).click();

        complatedTab.click();

        pageElements.itemCheckNumers(1).shouldHave(attribute("checked"));
        pageElements.itemCheckNumers(2).shouldHave(attribute("checked"));
    }

    @Test
    void checkItemCounterForActiveTab() {

        for (int i = 1; i < 6; i++) {
            String posData = data.positiveData[0]+" "+i;

            this.todoItem.setValue(posData).pressEnter();
        }

        pageElements.itemCheckNumers(2).click();
        pageElements.itemCheckNumers(4).click();

        pageElements.getActiveTab().click();

        getItemCounter.shouldHave(Condition.text("3"));
        counterText.shouldHave(text("items active"));
    }

    @Test
    void checkItemCounterForCompletedTab() {
        SelenideElement complatedTab = pageElements.getComplatedTab();

        for (int i = 1; i < 6; i++) {
            String posData = data.positiveData[0]+" "+i;

            this.todoItem.setValue(posData).pressEnter();
        }

        pageElements.itemCheckNumers(2).click();
        pageElements.itemCheckNumers(4).click();

        complatedTab.click();
//        sleep(5000);
        getItemCounter.shouldHave(text("2"));
        counterText.shouldHave(text("items completed"));
    }

    @Test
    void TodoItemWithoutStartEndSpaces() {

        String posData = data.positiveData[2];
        action.createNewTodoItemPosDataWithoutSpaces();

        itemName.shouldNotHave(exactValue(posData));
        itemName.shouldHave(exactText(String.valueOf(posData)));

        // Here the test must fail
//        itemName.shouldHave(exactValue(posData));
    }

    @Test
    void addTodoItemWithLongText() {
        String posData = data.positiveData[3];
        action.createNewTodoItemPosData(3);

        itemName.shouldHave(exactText(String.valueOf(posData)));
    }

    @Test
    void addTodoItemWithSpace() {
        String negData = new String();
        action.createNewTodoItemNegativeData(0);

        itemName.shouldNotHave(text(negData));
        itemName.shouldNotBe(visible);
    }

}
