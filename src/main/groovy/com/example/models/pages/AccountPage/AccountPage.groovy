package com.example.models.pages.AccountPage

import com.example.models.pages.LoginPage.LoginPage
import geb.Page
import io.qameta.allure.Step

class AccountPage extends Page{

    static atCheckWaiting = 10
    static at = {
        title == "Мой аккаунт"
        accountButton.displayed
    }

    static content = {
        accountButton(wait: 10) { $("a[aria-label*='(genchevskiy.test@gmail.com)']") }
        submitLogout(wait: 10){ $("a", text: "Выйти").click() }
    }

    @Step
    LoginPage logOut(){
        accountButton.click()
        submitLogout
        browser.at LoginPage
    }

}
