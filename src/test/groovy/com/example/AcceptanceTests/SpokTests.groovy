package com.example.AcceptanceTests

import com.example.models.pages.AccountPage.AccountPage
import geb.spock.GebSpec
import com.example.models.pages.LoginPage.LoginPage
import com.example.models.pages.MainPage.MainPage
import com.example.models.pages.SearchPage.SearchPage
import com.example.models.users.User


class SpokTests extends GebSpec {

    def setupSpec() {
        println 'base setupSpec()'
    }

    def cleanupSpec() {
        println 'base cleanupSpec()'
    }

    def setup() {
        println 'base setup()'
    }

    def cleanup() {
        println 'base cleanup()'
        go "https://accounts.google.com/Logout"
        driver.manage().deleteAllCookies()
    }


    def "user can search"(){
        given:
        to SearchPage

        when:
        search("Selenium WebDriver")

        then:
        //We can write conditions without assert
        title == "Selenium WebDriver - Пошук Google"
        searchResults.results.size() <= 10
        searchResults.get(1).title.text() == "Selenium WebDriver"
        searchResults.resultsList.each {element -> element.displayed}
        searchResults.results.each {element -> element.title.text().contains("Selenium")}
    }



    def "user can authorize"(){
        given:
        User user = new User()
        to LoginPage

        when:
        loginAs(user)

        then:
        isAt AccountPage
        accountButton.displayed

    }



    def "user can send the message"(){
        given:
        User user = new User()
        to LoginPage
        loginAs(user)
        to MainPage

        when:
        writeMessage().with(user.getEmail(), 'Test Message', "Hello Savva")

        then:
        waitFor { successMessage.displayed }
        successMessage.text() == "Письмо отправлено. Просмотреть сообщение"

        then:
        getDriver().navigate().refresh()
        messages.get(0).subject.text() == "Test Message"
        messages[0].messageStart.text().contains("Hello Savva")

    }


}



//Spok Example project - https://github.com/spockframework/spock-example
