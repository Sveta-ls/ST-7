package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Света\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
    
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            System.out.println("Task 1");
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebElement passwordContainer = webDriver.findElement(By.xpath("//div[@id='content']//b[contains(text(), '$') or contains(text(), '@') or contains(text(), '#')]"));
            String generatedPassword = passwordContainer.getText();

            System.out.println("Пароль: " + generatedPassword);
            System.out.println();

            Task2.run();
            Task3.run();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            webDriver.quit();
        }
    }
}