package com.example.SystemTests

import com.example.TestBase
import com.example.listeners.TestListener
import com.example.models.pages.AccountPage.AccountPage
import com.example.models.users.User
import geb.Browser
import com.example.models.pages.LoginPage.LoginPage
import com.example.models.pages.MainPage.MainPage
import org.testng.annotations.AfterMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

@Listeners(TestListener.class)
class MessageTests extends TestBase{



    @AfterMethod
    void tearDownTest(){
        go "https://accounts.google.com/Logout"
        browser.getDriver().manage().deleteAllCookies()
    }


    @Test
    void messageTest1(){
        Browser.drive {
            User user = new User()
            to LoginPage
            loginAs(user)
            to MainPage
            writeMessage().with(user.getEmail(), 'Test Message', "Hello Savva")
            waitFor {
                successMessage.displayed
                successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
            }
            getDriver().navigate().refresh()
            assert messages.get(0).subject.text() == "Test Message"
            assert messages[0].messageStart.text().contains("Hello Savva")
        }
    }




    @Test
    void messageTest2(){
        to LoginPage
        login("genchevskiy.test@gmail.com", "s.g19021992")
        to MainPage
        writeMessage().with("genchevskiy.test@gmail.com", 'Test Message', "Hello Savva")
        waitFor {
            successMessage.displayed
            successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        getDriver().navigate().refresh()
        assert messages.get(0).subject.text() == "Test Message"
        assert messages[0].messageStart.text().contains("Hello Savva")
    }



    @Test
    void messageTest3(){
        User user = new User()
        AccountPage accountPage = loginPage.open().loginAs(user)
        MainPage mainPage = mainPage.open().writeMessage().with(user.getEmail(), 'Test Message', "Hello Savva")
        browser.waitFor{
            mainPage.successMessage.displayed
            mainPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert mainPage.messages.get(0).subject.text() == "Test Message"
        assert mainPage.messages[0].messageStart.text().contains("Hello Savva")
    }


    @Test
    void messageTest4(){
        User user = new User()
        loginPage.open().loginAs(user)
        mainPage.open().writeMessage().with(user.getEmail(), 'Test Message', "Hello Savva")
        browser.waitFor{
            mainPage.successMessage.displayed
            mainPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert mainPage.messages.get(0).subject.text() == "Test Message"
        assert mainPage.messages[0].messageStart.text().contains("Hello Savva")
    }

    @Test
    void messageTestWithFileUpload(){
        User user = new User()
        loginPage.open()
                .loginAs(user)
        mainPage.open()
                .writeMessage()
                .withAttachment(user.getEmail(),
                'Test Message',
                "Hello Savva",
                "src/main/groovy/com/example/data/HelloWorld.txt")
        browser.waitFor{
            mainPage.successMessage.displayed
            mainPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert mainPage.messages.get(0).subject.text() == "Test Message"
        assert mainPage.messages[0].messageStart.text().contains("Hello Savva")
    }


}
