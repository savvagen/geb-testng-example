package com.example.models.pages.MainPage.modules

import geb.Module
import org.openqa.selenium.By


class Message extends Module{

    static content = {
        checkBox { $("div[role='checkbox']")}
        sender { $(By.xpath("//*/td[4]/div[2]/span"))}
        subject { $("span.bog b") }
        messageStart { $("span.y2")}
    }

}
