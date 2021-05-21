package com.weatherforecast.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.weatherforecast.WeatherForecastApplication;
import com.weatherforecast.util.Constants;

@SpringBootTest(classes = WeatherForecastApplication.class)
public class WeatherForecastSeleniumTest {

	private static WebDriver driver;
	@Autowired
	WeatherForecastApplication application;

	@BeforeAll
	public static void setUp() {
		System.out.println("Before start");
		ClassLoader classloader = new Object(){}.getClass().getClassLoader();
		URL url = classloader.getResource("chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", url.getFile());
		driver = new ChromeDriver();		
		driver.get("http://weatherforecast.demo.com:30007/weather.html");
	}

	@Test
	public void validCityName() throws InterruptedException {
		final WebElement textbox = driver.findElement(By.id("city"));
		textbox.click();
		textbox.clear();
		Thread.sleep(2000);
        textbox.sendKeys("london");
		Thread.sleep(2000);
		final WebElement button = driver.findElement(By.id("sbutton"));
		button.click();
		Thread.sleep(2000);
		final WebElement cities = driver.findElement(By.id("cities"));
        assertTrue(cities.getText().contains("london"));
	}
	
	@Test
	public void invalidCityName() throws InterruptedException {
		final WebElement textbox = driver.findElement(By.id("city"));
		textbox.click();
		textbox.clear();
		Thread.sleep(2000);
        textbox.sendKeys("Invalid City Name");
		Thread.sleep(2000);
		final WebElement button = driver.findElement(By.id("sbutton"));
		button.click();
		Thread.sleep(2000);
		final WebElement warning = driver.findElement(By.id("warning"));
		System.out.println("warning : " + warning.getText());
        assertTrue(warning.getText().equals(Constants.inValidCityWarningText));
	}

	@Test
	public void emptyCityName() throws InterruptedException {
		final WebElement textbox = driver.findElement(By.id("city"));
		textbox.click();
		textbox.clear();
		Thread.sleep(2000);
        textbox.sendKeys("");
		Thread.sleep(2000);
		final WebElement button = driver.findElement(By.id("sbutton"));
		button.click();
		Thread.sleep(2000);
		final WebElement warning = driver.findElement(By.id("warning"));
        assertTrue(warning.getText().equals(Constants.inValidCityWarningText));
	}
	@AfterAll
	public static void tearDown() {
		driver.quit();
		System.out.println("Exiting Selenium test");
	}
}