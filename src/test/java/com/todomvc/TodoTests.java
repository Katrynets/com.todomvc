package com.todomvc;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class TodoTests {

    Action action = new Action();
    TestData testData = new TestData();
    TodoMvcPage todoMvcPage = new TodoMvcPage();
    SelenideElement itemName = todoMvcPage.getItemName();
    SelenideElement itemCheckbox = todoMvcPage.getItemCheckbox();
    SelenideElement getItemCounter = todoMvcPage.getItemCount();
    SelenideElement counterText = todoMvcPage.getCounterText();

    @BeforeEach
    void openHomePage() {
        open(testData.todoMvcUrl);
        Selenide.clearBrowserLocalStorage();
    }

    @AfterEach
    void clearItems() {
        Selenide.clearBrowserLocalStorage();
    }

    @Test
    void addTodoItem() {
        action.createNewTodoItemPosData(0);
        itemName.shouldHave(empty);
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
        String posData = testData.positiveData[0];
        action.clearAllItems(0);

        itemName.shouldNotHave(Condition.text(posData));
    }

    @Test
    void checkItemCounter() {

        for (int i = 1; i < 6; i++) {
            String posData = testData.positiveData[0] + " " + i;
            todoMvcPage.createTodoItem(posData);
        }

        getItemCounter.shouldHave(text("5"));
    }

    @Test
    void checkItemCounterForCheckedItems() {
        todoMvcPage.createTodoItems(testData.positiveData);

        todoMvcPage.itemCheckNumers(2).click();
        todoMvcPage.itemCheckNumers(4).click();

        getItemCounter.shouldHave(Condition.text("3"));
    }

    @Test
    void checkCheckboxesForActiveTab() {

        for (int i = 1; i < 6; i++) {
            String posData = testData.positiveData[0] + " " + i;

            todoMvcPage.createTodoItem(posData);
        }

        todoMvcPage.itemCheckNumers(2).click();
        todoMvcPage.itemCheckNumers(4).click();
        todoMvcPage.getActiveTab().click();

        todoMvcPage.itemCheckNumers(1).shouldNotHave(attribute("checked"));
        todoMvcPage.itemCheckNumers(2).shouldNotHave(attribute("checked"));
        todoMvcPage.itemCheckNumers(3).shouldNotHave(attribute("checked"));
    }

    @Test
    void checkCheckboxesForCompletedTab() {
        SelenideElement complatedTab = todoMvcPage.getComplatedTab();

        for (int i = 1; i < 6; i++) {
            String posData = testData.positiveData[0] + " " + i;

            todoMvcPage.createTodoItem(posData);
        }

        todoMvcPage.itemCheckNumers(2).click();
        todoMvcPage.itemCheckNumers(4).click();

        complatedTab.click();

        todoMvcPage.itemCheckNumers(1).shouldHave(attribute("checked"));
        todoMvcPage.itemCheckNumers(2).shouldHave(attribute("checked"));
    }

    @Test
    void checkItemCounterForActiveTab() {

        for (int i = 1; i < 6; i++) {
            String posData = testData.positiveData[0] + " " + i;

            todoMvcPage.createTodoItem(posData);
        }

        todoMvcPage.itemCheckNumers(2).click();
        todoMvcPage.itemCheckNumers(4).click();

        todoMvcPage.getActiveTab().click();

        getItemCounter.shouldHave(Condition.text("3"));
        counterText.shouldHave(text("items active"));
    }

    @Test
    void checkItemCounterForCompletedTab() {
        SelenideElement complatedTab = todoMvcPage.getComplatedTab();

        for (int i = 1; i < 6; i++) {
            String posData = testData.positiveData[0] + " " + i;

            todoMvcPage.createTodoItem(posData);
        }

        todoMvcPage.itemCheckNumers(2).click();
        todoMvcPage.itemCheckNumers(4).click();

        complatedTab.click();
//        sleep(5000);
        getItemCounter.shouldHave(text("2"));
        counterText.shouldHave(text("items completed"));
    }

    @Test
    void TodoItemWithoutStartEndSpaces() {

        String posData = testData.positiveData[2];
        action.createNewTodoItemPosDataWithoutSpaces();

        itemName.shouldNotHave(exactValue(posData));
        itemName.shouldHave(exactText(String.valueOf(posData)));

        // Here the test must fail
//        itemName.shouldHave(exactValue(posData));
    }

    @Test
    void addTodoItemWithLongText() {
        String posData = testData.positiveData[3];
        action.createNewTodoItemPosData(3);

        itemName.shouldHave(exactText(String.valueOf(posData)));
    }

    @Test
    void addTodoItemWithSpace() {
        String negData = "";
        action.createNewTodoItemNegativeData(0);

        itemName.shouldNotHave(text(negData));
        itemName.shouldBe(hidden);
    }

}
