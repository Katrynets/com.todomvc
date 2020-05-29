package com.todomvc;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PageElements {


    public SelenideElement getTodoItem() {
        SelenideElement todoItem = $("[class='new-todo']");
        return todoItem;
    }

    public SelenideElement getItemName() {
        SelenideElement itemName = $(".view label");
        return itemName;
    }

    public SelenideElement getDeleteIcon() {
        SelenideElement deleteIcon = $("[class='destroy']");
        return deleteIcon;
    }

    public SelenideElement getAllItems() {
        SelenideElement allItems = $("label[for='toggle-all']");
        return allItems;
    }

    public SelenideElement getClearButton() {
        SelenideElement clearButton = $("[class='clear-completed']");
        return clearButton;
    }

    public SelenideElement getItemCheckbox() {
        SelenideElement itemCheckbox = $(".toggle[type='checkbox']");
        return itemCheckbox;
    }


    public SelenideElement getItemCount() {
        SelenideElement getItemCounter = $(".todo-count strong");
        return getItemCounter;
    }

    public <number> SelenideElement itemCheckNumers(int number)  {
        SelenideElement itemCheckNumer = $("li:nth-child("+number+") .toggle[type='checkbox']");
        return itemCheckNumer;
    }


    public SelenideElement getAllTab() {
        SelenideElement allTab = $(".filters li:nth-child(1) a");
        return allTab;
    }


    public SelenideElement getActiveTab() {
        SelenideElement activeTab = $(".filters li:nth-child(2) a");
        return activeTab;
    }

    public SelenideElement getComplatedTab() {
        SelenideElement complatedTab = $(".filters li:nth-child(3) a");
        return complatedTab;
    }

    public SelenideElement getCounterText() {
        SelenideElement counterText = $(".footer span");
        return counterText;
    }


}


