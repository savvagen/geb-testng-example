package com.example.models.pages.MainPage

import geb.Page
import com.example.models.pages.LoginPage.LoginPage
import com.example.models.pages.MainPage.modules.MessageForm
import com.example.models.pages.MainPage.modules.Message
import io.qameta.allure.Step
import org.openqa.selenium.By
import sun.applet.Main

class MainPage extends Page{

    static url = "/mail.google.com/mail/u/0/#inbox"
    static atCheckWaiting = 10
    static at = {
        title.contains(" - genchevskiy.test@gmail.com - Gmail")
        accountButton.displayed
        newMessageButton.displayed
    }

    static content = {
        accountButton(wait: 10) { $("a", title: contains('(genchevskiy.test@gmail.com)')) }
        submitLogout(wait: 5){ $("a", text: "Выйти").click() }
        newMessageButton(wait: 5) { $(By.xpath('//div[contains(text(), "НАПИСАТЬ")]')) }
        messageForm(wait: 8){ module(MessageForm) }
        successMessage {$(By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]"))}

        //Create list of messages modules
        messagesContainer { $(By.xpath('//div[@role="main"]/div[6]/div/div[1]/div[2]/div/table/tbody')) }
        messagesCollection { messagesContainer.find("tr").findAll()}
        messages { messagesContainer.find('tr').moduleList(Message)}
    }

    @Step("Open Main page")
    MainPage open(){
        browser.go url
        browser.at MainPage
    }

    @Step("Logout")
    LoginPage logOut(){
        accountButton.click()
        submitLogout
        browser.at LoginPage
    }

    @Step("Press \"New Message Button\"")
    MessageForm writeMessage(){
        newMessageButton.click()
        waitFor {messageForm.subjectField.displayed}
        module(MessageForm)
    }



}
