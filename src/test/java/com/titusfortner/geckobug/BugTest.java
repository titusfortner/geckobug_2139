package com.titusfortner.geckobug;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

public class BugTest {
  WebDriver driver;
  String PASSING_VERSION = "119";
  String FAILING_VERSION = "120";

  @AfterEach
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  public void internationalization() {
    FirefoxOptions options = new FirefoxOptions();
    options.setBrowserVersion(FAILING_VERSION);
    FirefoxDriverService service =
        new GeckoDriverService.Builder()
            .withLogLevel(FirefoxDriverLogLevel.TRACE)
            .withLogOutput(System.out)
            .build();
    driver = new FirefoxDriver(service, options);

    driver.get("https://www.selenium.dev/selenium/web/cn-test.html");

    String input = "";
    input += new String(Character.toChars(0x20000));
    input += new String(Character.toChars(0x2070E));
    input += new String(Character.toChars(0x2000B));
    input += new String(Character.toChars(0x2A190));
    input += new String(Character.toChars(0x2A6B2));

    WebElement el = driver.findElement(By.name("i18n"));
    el.sendKeys(input);

    Assertions.assertEquals(input, el.getDomProperty("value"));
  }
}
