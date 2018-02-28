package com.example.models.pages.SearchPage

import geb.Page
import io.qameta.allure.Step
import com.example.models.pages.SearchPage.modules.SearchResults
import org.openqa.selenium.By
import org.openqa.selenium.Keys

class SearchPage extends Page{
    static atCheckWaiting = 10

    static at = {
        title == "Google"
        searchField.displayed
    }

    static url = "/google.com"


    static content = {
        searchField(wait: 8, required: true) { $(By.name("q"))}
        searchResults{ module(SearchResults)}
        //Create element with parameter
        pElement {pName -> $("input", name: pName)}

    }

    @Step
    SearchPage open(){
        browser.go url
        browser.at SearchPage
    }

    @Step
    SearchResults search(String searchQuery){
        searchField.value(searchQuery) << Keys.ENTER
        return module(SearchResults)
    }

}



