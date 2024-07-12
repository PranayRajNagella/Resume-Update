package com.updateResume;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ResumeUpdater {

    private WebDriver driver;

    @BeforeMethod()
    public void beforeMethod()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("incognito");
        driver=new ChromeDriver(options);
    }


    @Test()
    public void updateResume() throws InterruptedException {
        driver.get("https://www.naukri.com/");
        driver.manage().window().maximize();
        getWebElement(driver,"//div/a[text()='Login']").click();
        getWebElement(driver,"//label[contains(text(),'Email')]/following-sibling::input").sendKeys("nagelladhanush@gmail.com");
        getWebElement(driver,"//label[contains(text(),'Password')]/following-sibling::input").sendKeys("Dhanush$$14");
        getWebElement(driver,"//button[text()='Login']").click();
        Thread.sleep(10000);
       // checkInvisble(driver,"//div//div/div[@style='visibility: visible;']");
        getWebElement(driver,"//div[@class='view-profile-wrapper']/a").click();
        Thread.sleep(10000);
        getWebElement(driver,"//span[text()='Resume headline']//following-sibling::span").click();
        String charactersLeft=getWebElement(driver,"//div[@class='inputInfo']//span[@class='textCounter right']").getText();
        if(charactersLeft.contains("1")) {
            String text = getWebElement(driver, "//textarea[@id='resumeHeadlineTxt']").getText();
            System.out.println("the current resume headline text "+text);
            getWebElement(driver, "//textarea[@id='resumeHeadlineTxt']").clear();
            getWebElement(driver, "//textarea[@id='resumeHeadlineTxt']").sendKeys(text + ".");
            getWebElement(driver,"//button[text()='Save']").click();
        }
        else if(charactersLeft.contains("0"))
        {
            String text = getWebElement(driver, "//textarea[@id='resumeHeadlineTxt']").getText();
            System.out.println("the current resume headline text "+text);
            getWebElement(driver, "//textarea[@id='resumeHeadlineTxt']").clear();
            getWebElement(driver, "//textarea[@id='resumeHeadlineTxt']").sendKeys(text.substring(0,text.length()-2));
            getWebElement(driver,"//button[text()='Save']").click();
        }
        getWebElement(driver,"//div[@class='nI-gNb-drawer__icon']").click();
        getWebElement(driver,"//a[text()='Logout']").click();
        System.out.println("The profile Update sucessfully");
    }

    public static WebElement getWebElement(WebDriver driver, String locator)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(100));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));
        return driver.findElement(By.xpath(locator));

    }
    public void checkInvisble(WebDriver driver, String locator)
    {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(100));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(locator))));
    }


}
