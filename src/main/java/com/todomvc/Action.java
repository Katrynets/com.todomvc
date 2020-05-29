package com.todomvc;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;

public class Action {

    Data data = new Data();
    PageElements pageElements = new PageElements();
    SelenideElement todoItem = pageElements.getTodoItem();
    SelenideElement allItems = pageElements.getAllItems();
    SelenideElement itemName = pageElements.getItemName();
    SelenideElement clearButton = pageElements.getClearButton();
    SelenideElement itemCheckbox = pageElements.getItemCheckbox();
    SelenideElement activeTab = pageElements.getActiveTab();



    SelenideElement createNewTodoItemPosData(int number) {
        String posData = data.positiveData[number];
        SelenideElement createTodoItemPosData = todoItem.setValue(posData).pressEnter();
        return createTodoItemPosData;
    }

    SelenideElement removeAddedItems() {
        SelenideElement deleteIcon = pageElements.getDeleteIcon();
        String posData = new String();

        SelenideElement removeAddedItem = createNewTodoItemPosData(0);
        itemName.hover();
        deleteIcon.should(appear).click();

        return removeAddedItem;
    }

    SelenideElement createNewTodoItemNegativeData(int number) {
        String negData = data.negativeData[number];
        SelenideElement createTodoItemNegData = todoItem.setValue(negData).pressEnter();
        return createTodoItemNegData;
    }

    SelenideElement clearAllItems(int number) {
        String posData = data.positiveData[number];
        SelenideElement clearItems = todoItem.setValue(posData).pressEnter();
        allItems.click();
        clearButton.should(appear).click();
        return clearItems;
    }

    SelenideElement checkCheckboxAddedItem(int number) {
        String posData = data.positiveData[number];
        SelenideElement enableCheckboxItem = todoItem.setValue(posData).pressEnter();
        itemCheckbox.click();
        return enableCheckboxItem;
    }

    SelenideElement createNewTodoItemPosDataWithoutSpaces() {
        String posData = data.positiveData[2];

        SelenideElement createTodoItemPosDataWithoutSpaces = todoItem.setValue(posData).pressEnter();
        return createTodoItemPosDataWithoutSpaces;
    }

}