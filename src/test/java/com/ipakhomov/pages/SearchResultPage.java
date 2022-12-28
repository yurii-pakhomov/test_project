package com.ipakhomov.pages;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class SearchResultPage {
    private final WebDriver driver;

    private By resultAddress = By.xpath("//span[@data-testid='address']");
    private By checkInDateValue = By.xpath("//button[@data-testid='date-display-field-start']");
    private By checkOutDateValue = By.xpath("//button[@data-testid='date-display-field-end']");

    public void allResultsHaveCorrectCityName(String cityName) {
        List<WebElement> addresses = driver.findElements(resultAddress);
        addresses.forEach(address -> {
            assertThat(address.getText()).contains(cityName);
        });
    }

    public void checkInAndCheckOutDatesAreCorrectOnSearchResultPage(LocalDate expectedCheckInDate, LocalDate expectedCheckOutDate) {
        String checkInDateValue = driver.findElement(this.checkInDateValue).getText().toLowerCase();
        String checkOutDateValue = driver.findElement(this.checkOutDateValue).getText().toLowerCase();
        assertThat(checkInDateValue).contains(String.valueOf(expectedCheckInDate.getYear()),
                joinMonthAndDayNumber(expectedCheckInDate.getMonth().name().toLowerCase(), expectedCheckInDate.getDayOfMonth()));
        assertThat(checkOutDateValue).contains(String.valueOf(expectedCheckOutDate.getYear()),
                joinMonthAndDayNumber(expectedCheckOutDate.getMonth().name().toLowerCase(), expectedCheckOutDate.getDayOfMonth()));
    }

    private String joinMonthAndDayNumber(String monthName, int dayOfMonth) {
        return String.format("%s %s", monthName, dayOfMonth);
    }
}
