package com.example.models.pages.GmailPage.modules

import geb.Module
import com.example.models.pages.GmailPage.GmailPage
import io.qameta.allure.Step
import org.openqa.selenium.By
import org.openqa.selenium.Keys

class MessageForm extends Module {

    static content = {
        messageForm { $("div[role='dialog']")}
        addressField { $(By.name("to"))}
        subjectField { $(By.name("subjectbox"))}
        messageField { messageForm.$("div[role='textbox']") }
        submitMessage { messageForm.$(By.xpath("//tbody/tr/td[1]/div/div[@role='button']")).click()}
        fileDataField { $("input", name: "Filedata") }
        attachmens { messageForm.find("input", name: "attach")}
    }

    @Step("Write message")
    GmailPage with(String to, String subject, String message){
        addressField << to
        subjectField << subject << Keys.TAB
        messageField << message
        submitMessage
        browser.at new GmailPage(forEmail: to)
    }

    GmailPage withAttachment(String to, String subject, String message, String filePath){
        def attachment = new File(filePath)
        addressField << to
        subjectField << subject << Keys.TAB
        messageField << message
        fileDataField = attachment.absolutePath
        waitFor {attachmens.first().parent().attr("aria-label").contains("Прикрепленный файл")}
        submitMessage
        browser.at(new GmailPage(forEmail: to))
    }

}
