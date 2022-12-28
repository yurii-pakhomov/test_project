package com.ipakhomov.pages;

import lombok.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

@RequiredArgsConstructor
public class MainPage {
    private final WebDriver driver;

    private By searchButton = By.className("sb-searchbox__button");
    private By searchInput = By.id("ss");
    private By nextButtonOnCalendar = By.className("bui-calendar__control--next");
    private By monthNameOnCalendar = By.className("bui-calendar__month");
    private By dateField = By.className("xp__dates");
    private String dayOfMonthXpath = "//td[@data-date='%s']";


    public SearchResultPage clickSearchButton() {
        driver.findElement(searchButton).click();
        return new SearchResultPage(driver);
    }

    public void setCityNameInSearch(String cityName) {
        driver.findElement(searchInput).sendKeys(cityName);
    }

    public void setCheckInAndCheckOut(LocalDate checkInDate, LocalDate checkOutDate) {
        if (!isElementDisplayed(nextButtonOnCalendar)) {
            driver.findElement(dateField).click();
        }
        while (!isNeededMonthDisplayed(checkInDate)) {
            driver.findElement(nextButtonOnCalendar).click();
        }
        driver.findElement(By.xpath(String.format(dayOfMonthXpath, checkInDate.toString()))).click();
        driver.findElement(By.xpath(String.format(dayOfMonthXpath, checkOutDate.toString()))).click();
    }

    private boolean isNeededMonthDisplayed(LocalDate date) {
        return driver.findElements(monthNameOnCalendar).stream()
                .anyMatch(month -> month.getText().toLowerCase().contains(date.getMonth().name().toLowerCase())
                        && month.getText().toLowerCase().contains(String.valueOf(date.getYear())));
    }

    private boolean isElementDisplayed(By by) {
        return driver.findElement(by).isDisplayed();
    }
}
