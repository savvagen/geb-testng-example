package com.example.SystemTests

import com.example.TestBase
import com.example.models.pages.AccountPage.AccountPage
import geb.Browser
import io.qameta.allure.Flaky
import com.example.models.pages.LoginPage.LoginPage
import com.example.models.pages.GmailPage.GmailPage
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test

//@Listeners(TestListener.class)
class LoginTests extends TestBase {


    @AfterMethod
    void tearDownTest(){
        /*if (accountPage.accountButton.displayed){
            accountPage.logOut()
        }*/
        go "https://accounts.google.com/Logout"
        browser.getDriver().manage().deleteAllCookies()

    }


    /**
     * EXAMPLES
     */
    /*

    @Test
    void testExample2(){
        to LoginPage
        $("form").identifier = "genchevskiy.test@gmail.com" << Keys.ENTER
        waitFor { $("form").password().displayed }
        $("form").password = "s.g19021992" << Keys.ENTER

        waitFor { $("a[aria-label*='(genchevskiy.test@gmail.com)']").displayed }
        assert driver.currentUrl.contains('https://myaccount.google.com/')
    }

    @Test
    void testExample3(){
        Browser.drive {
            go '/accounts.google.com/signin/v2/identifier'
            $("input[type='email']").value("genchevskiy.test@gmail.com") << Keys.ENTER
            waitFor { $("input[type='password']").displayed }
            $("input[type='password']").value("s.g19021992")
            $("#passwordNext").click()
            waitFor { $("a[aria-label*='(genchevskiy.test@gmail.com)']").displayed }
            assert driver.currentUrl.contains('https://myaccount.google.com/')
        }

    }*/



    /**
     * TEST SAMPLES AND VARIANTS
     */

    @Test
    void  loginTest(){
        to LoginPage
        loginWith(testUser.getEmail(), testUser.getPassword())
        isAt AccountPage
        assert accountButton.displayed
    }

    @Test
    void loginTest1(){
        Browser.drive {
            to LoginPage
            login(testUser.getEmail(), testUser.getPassword())
            isAt GmailPage
            assert accountButton.isDisplayed()
        }
    }

    @Test
    void positiveLogin2(){
        to LoginPage
        login(testUser.getEmail(), testUser.getPassword())
        isAt AccountPage
        assert accountButton.displayed
    }

    @Test
    void loginTest3(){
        to LoginPage
        loginAs(testUser)
        isAt AccountPage
        assert accountButton.isDisplayed()
    }

    @Test
    void loginTest4(){
        LoginPage loginPage = browser.to LoginPage
        AccountPage accountPage = loginPage.loginAs(testUser)
        assert accountPage.accountButton.isDisplayed()

    }

    @Test
    void loginTest5(){
        AccountPage accountPage = loginPage.open().loginAs(testUser)
        assert accountPage.accountButton.isDisplayed()

    }

    @Flaky
    @Test
    void loginTest6(){
        loginPage.open().loginAs(testUser)
        assert accountPage.accountButton.isDisplayed()

    }


}
