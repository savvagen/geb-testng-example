import geb.Browser
import io.github.bonigarcia.wdm.ChromeDriverManager
import io.github.bonigarcia.wdm.FirefoxDriverManager
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.Dimension
import org.openqa.selenium.Platform
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver


//Use this for manual test running
driver = {
    ChromeDriverManager.getInstance().setup()
    def driverInstance = new ChromeDriver()
    driverInstance.manage().window().maximize()
    driverInstance
}


waiting{
    timeout = 10
    includeCauseInMessage = true
    baseNavigatorWaiting = 10
}

baseUrl = "http:/"

reportsDir = "build/geb-reports"
reportOnTestFailureOnly = true

/**
 * LOCAL DRIVER SETTINGS (use with example systemProperty("geb.env", "chrome"))
 */
environments {

    // run via “./gradlew chromeTest”
    chrome {
        driver = {
            //WebDriverManager.chromedriver().version("2.3").setup()
            ChromeDriverManager.getInstance().setup()

            ChromeOptions options = new ChromeOptions()
            Map<String, Object> prefs = new HashMap<String, Object>()
            prefs.put("credentials_enable_service", false)
            prefs.put("profile.password_manager_enabled", false)
            options.setExperimentalOption("prefs", prefs)
            options.addArguments("use-fake-device-for-media-stream")
            options.addArguments("use-fake-ui-for-media-stream")
            options.addArguments("--disable-notifications")
            options.addArguments("--lang=es")
            options.addArguments("--disable-confirmations")
            options.addArguments("disable-infobars")
            options.addArguments("window-size=1600,1200")

            def driverInstance = new ChromeDriver(options)
            driverInstance.manage().window().maximize()
            driverInstance
        }
    }

    // run via “./gradlew firefoxTest”
    firefox {
        driver = {
            FirefoxDriverManager.getInstance().setup()
            def driverInstance = new FirefoxDriver()
            driverInstance.manage().window().maximize()
            driverInstance
        }
    }

}

/**
 * REMOTE DRIVER SETTINGS (use with systemProperty("geb.env", "remote"))
 */
environments {

    remote {

        driver = {
            def remoteWebDriverServerUrl = new URL("http://localhost:4444/wd/hub")
            DesiredCapabilities dc = DesiredCapabilities.chrome()
            dc.setBrowserName("chrome")
            dc.setVersion("63.0")
            dc.setCapability("enableVNC", true)
            dc.setCapability("enableVideo", true)
            dc.setCapability("screenResolution", "1960x1280x24")
            dc.setCapability(CapabilityType.TAKES_SCREENSHOT, true)
            //More capabilities:
            // "videoName":"selenoid851e3f5f21e59a0eb5640b9c0c9a702c.mp4",
            // "videoScreenSize":"1960x1280",
            // "videoFrameRate":0,
            // "name":"",
            // "timeZone":""
            dc.setPlatform(Platform.LINUX)
            dc.setJavascriptEnabled(true)
            def driverInstance = new RemoteWebDriver(remoteWebDriverServerUrl, dc)
            driverInstance.manage().window().setSize(new Dimension(1920, 1080))
            driverInstance
        }
    }
}