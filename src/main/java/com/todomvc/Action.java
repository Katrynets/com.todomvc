package com.todomvc;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;

public class Action {

    TestData testData = new TestData();
    TodoMvcPage todoMvcPage = new TodoMvcPage();
    SelenideElement allItems = todoMvcPage.getAllItems();
    SelenideElement itemName = todoMvcPage.getItemName();
    SelenideElement clearButton = todoMvcPage.getClearButton();
    SelenideElement itemCheckbox = todoMvcPage.getItemCheckbox();
    SelenideElement activeTab = todoMvcPage.getActiveTab();


    SelenideElement createNewTodoItemPosData(int number) {
        String posData = testData.positiveData[number];
        return todoMvcPage.createTodoItem(posData);
    }

    SelenideElement removeAddedItems() {
        SelenideElement deleteIcon = todoMvcPage.getDeleteIcon();

        SelenideElement removeAddedItem = createNewTodoItemPosData(5);
        itemName.hover();
        deleteIcon.should(appear).click();

        return removeAddedItem;
    }

    SelenideElement createNewTodoItemNegativeData(int number) {
        String negData = testData.negativeData[number];
        SelenideElement createTodoItemNegData = todoMvcPage.createTodoItem(negData);
        return createTodoItemNegData;
    }

    SelenideElement clearAllItems(int number) {
        String posData = testData.positiveData[number];
        SelenideElement clearItems = todoMvcPage.createTodoItem(posData);
        allItems.click();
        clearButton.should(appear).click();
        return clearItems;
    }

    SelenideElement checkCheckboxAddedItem(int number) {
        String posData = testData.positiveData[number];
        SelenideElement enableCheckboxItem = todoMvcPage.createTodoItem(posData);
        itemCheckbox.click();
        return enableCheckboxItem;
    }

    SelenideElement createNewTodoItemPosDataWithoutSpaces() {
        String posData = testData.positiveData[2];

        SelenideElement createTodoItemPosDataWithoutSpaces = todoMvcPage.createTodoItem(posData);
        return createTodoItemPosDataWithoutSpaces;
    }

}
