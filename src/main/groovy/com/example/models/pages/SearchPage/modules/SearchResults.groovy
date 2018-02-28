package com.example.models.pages.SearchPage.modules

import geb.Module

class SearchResults extends Module{

    static content = {
        //Make list of elements
        resultsContainer { $(".srg") }
        resultsList {resultsContainer.find(".g")}
        //Another implementation
        resultsCollection { $(".srg .g").findAll() }

        //Make list of modules
        results(wait: 8) { resultsList.moduleList(SearchResult) }
        //Another implementation
        //results(wait: 8) { $(".srg .g").moduleList(SearchResult) }
    }

    SearchResult get(int index){
       //resultsList.get(index-1).module(SearchResult)
        results[index-1].module(SearchResult)
    }


}



class SearchResult extends Module{

    static content = {
        title { $("h3 > a")}
    }


}