package com.ipakhomov.tests;

import com.ipakhomov.pages.MainPage;
import com.ipakhomov.pages.SearchResultPage;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class SearchTest extends BaseTest {

    @Test
    public void searchTest() {
        String cityName = "New York";
        LocalDate checkInDate = LocalDate.parse("2023-06-01");
        LocalDate checkOutDate = LocalDate.parse("2023-06-30");
        MainPage mainPage = new MainPage(driver);
        driver.get(configFile.getApplicationUrl());
        mainPage.setCityNameInSearch(cityName);
        mainPage.setCheckInAndCheckOut(checkInDate, checkOutDate);
        SearchResultPage searchResultPage = mainPage.clickSearchButton();
        searchResultPage.allResultsHaveCorrectCityName(cityName);
        searchResultPage.checkInAndCheckOutDatesAreCorrectOnSearchResultPage(checkInDate, checkOutDate);
    }
}
