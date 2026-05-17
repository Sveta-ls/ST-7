package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;

public class Task3 {

    public static void run() {

        System.setProperty(
        "webdriver.chrome.driver",
        "C:\\Users\\Света\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
);

        WebDriver webDriver = new ChromeDriver();

        try {

            String url =
                    "https://api.open-meteo.com/v1/forecast" +
                    "?latitude=56" +
                    "&longitude=44" +
                    "&hourly=temperature_2m,rain" +
                    "&timezone=Europe%2FMoscow" +
                    "&forecast_days=1" +
                    "&wind_speed_unit=ms";

            webDriver.get(url);

            WebElement elem =
                    webDriver.findElement(By.tagName("pre"));

            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();

            JSONObject obj =
                    (JSONObject) parser.parse(jsonStr);

            JSONObject hourly =
                    (JSONObject) obj.get("hourly");

            JSONArray time =
                    (JSONArray) hourly.get("time");

            JSONArray temperature =
                    (JSONArray) hourly.get("temperature_2m");

            JSONArray rain =
                    (JSONArray) hourly.get("rain");

            // Создание папки result
            File dir = new File("result");

            if (!dir.exists()) {
                dir.mkdir();
            }

            FileWriter writer =
                    new FileWriter("result/forecast.txt");

            String header =
                    String.format("%-5s %-22s %-15s %-15s\n",
                            "№",
                            "Дата/время",
                            "Температура",
                            "Осадки");

            System.out.println();
            System.out.println(header);

            writer.write(header);

            for (int i = 0; i < time.size(); i++) {

                String row =
                        String.format("%-5d %-22s %-15s %-15s\n",
                                i + 1,
                                time.get(i),
                                temperature.get(i),
                                rain.get(i));

                System.out.print(row);

                writer.write(row);
            }

            writer.close();

            System.out.println();
            System.out.println("Файл forecast.txt создан.");

        } catch (Exception e) {

            System.out.println("Error");
            System.out.println(e.toString());

        } finally {

            webDriver.quit();

        }
    }
}