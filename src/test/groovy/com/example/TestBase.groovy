package com.example

import com.example.models.pages.AccountPage.AccountPage
import com.example.models.users.User
import geb.Browser
import com.example.models.pages.LoginPage.LoginPage
import com.example.models.pages.MainPage.GmailPage
import com.example.models.pages.SearchPage.SearchPage
import geb.testng.GebTestTrait
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

class TestBase implements GebTestTrait{

    SearchPage searchPage
    LoginPage loginPage
    GmailPage mainPage
    AccountPage accountPage
    Browser browser

    User testUser


    @BeforeClass
    void setUp(){
        testUser = new User()

        //Create browser instance - variant - 1
        //ChromeDriverManager.getInstance().setup()
        //browser = new Browser(driver: new ChromeDriver())
        //browser.driver.manage().window().maximize()


        //Create browser instance - variant - 2
        //browser = new Browser()
        //browser.driver = new ChromeDriver()


        browser = new Browser()
        searchPage = new SearchPage().init(browser)
        loginPage = new LoginPage().init(browser)
        mainPage = new GmailPage(forEmail: testUser.getEmail()).init(browser)
        accountPage = new AccountPage().init(browser)

        //Manual configuration
        //browser.config.baseUrl = "http://"
        //browser.config.baseNavigatorWaiting = 10

    }

    @AfterClass
    void tearDown(){
        //browser.quit()
    }


}
