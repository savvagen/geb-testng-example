package com.example.SystemTests

import com.example.TestBase
import com.example.listeners.TestListener
import com.example.models.pages.AccountPage.AccountPage
import com.example.models.users.User
import geb.Browser
import com.example.models.pages.LoginPage.LoginPage
import com.example.models.pages.GmailPage.GmailPage
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
    void messageTest1() {
        Browser.drive {
            to LoginPage
            loginAs(testUser)
            to(new GmailPage(forEmail: testUser.getEmail()))
            writeMessage().with(testUser.getEmail(), 'Test Message', "Hello Savva")
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
        login(testUser.getEmail(), testUser.getPassword())
        to(new GmailPage(forEmail: testUser.getEmail()))
        writeMessage().with(testUser.getEmail(), 'Test Message', "Hello Savva")
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
        AccountPage accountPage = to(new LoginPage()).loginAs(user)
        GmailPage gmailPage = to(new GmailPage(forEmail: testUser.getEmail()))
                .writeMessage()
                .with(user.getEmail(), 'Test Message', "Hello Savva")
        browser.waitFor{
            gmailPage.successMessage.displayed
            gmailPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert gmailPage.messages.get(0).subject.text() == "Test Message"
        assert gmailPage.messages[0].messageStart.text().contains("Hello Savva")
    }


    @Test
    void messageTest4(){
        User user = new User()
        loginPage.open().loginAs(user)
        gmailPage.open().writeMessage().with(user.getEmail(), 'Test Message', "Hello Savva")
        browser.waitFor{
            gmailPage.successMessage.displayed
            gmailPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert gmailPage.messages.get(0).subject.text() == "Test Message"
        assert gmailPage.messages[0].messageStart.text().contains("Hello Savva")
    }


    @Test
    void messageTestWithFileUpload(){
        User user = new User()
        loginPage.open()
                .loginAs(user)
        gmailPage.open()
                .writeMessage()
                .withAttachment(user.getEmail(),
                'Test Message',
                "Hello Savva",
                "src/main/groovy/com/example/data/HelloWorld.txt")
        browser.waitFor{
            gmailPage.successMessage.displayed
            gmailPage.successMessage.text() == "Письмо отправлено. Просмотреть сообщение"
        }
        browser.getDriver().navigate().refresh()
        assert gmailPage.messages.get(0).subject.text() == "Test Message"
        assert gmailPage.messages[0].messageStart.text().contains("Hello Savva")
    }


}
