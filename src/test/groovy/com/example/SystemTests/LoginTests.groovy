package com.example.SystemTests

import com.example.TestBase
import com.example.listeners.TestListener
import com.example.models.pages.AccountPage.AccountPage
import com.example.models.pages.SearchPage.SearchPage
import geb.Browser
import geb.testng.GebTestTrait
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.qameta.allure.Flaky
import org.omg.IOP.ENCODING_CDR_ENCAPS
import org.openqa.selenium.Keys
import com.example.models.pages.LoginPage.LoginPage
import com.example.models.pages.MainPage.MainPage
import com.example.models.users.User
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.Listeners
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

    /*@Test
    void  testExample(){
        to LoginPage
        AccountPage accountPage = loginWith("genchevskiy.test@gmail.com", "s.g19021992")
        isAt AccountPage
        assert accountPage.accountButton.displayed
    }

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
    void loginTest1(){
        Browser.drive {
            to LoginPage
            login("genchevskiy.test@gmail.com", "s.g19021992")
            isAt MainPage
            assert accountButton.isDisplayed()
        }
    }


    @Test
    void positiveLogin2(){
        to LoginPage
        login("genchevskiy.test@gmail.com", "s.g19021992")
        isAt AccountPage
        assert accountButton.displayed
    }


    @Test
    void loginTest3(){
        User user = new User()
        to LoginPage
        loginAs(user)
        isAt AccountPage
        assert accountButton.isDisplayed()
    }


    @Test
    void loginTest4(){
        User user = new User()
        LoginPage loginPage = browser.to LoginPage
        AccountPage accountPage = loginPage.loginAs(user)
        assert accountPage.accountButton.isDisplayed()

    }

    @Test
    void loginTest5(){
        User user = new User()
        AccountPage accountPage = loginPage.open().loginAs(user)
        assert accountPage.accountButton.isDisplayed()

    }

    @Flaky
    @Test
    void loginTest6(){
        User user = new User()
        loginPage.open().loginAs(user)
        assert accountPage.accountButton.isDisplayed()

    }


}
