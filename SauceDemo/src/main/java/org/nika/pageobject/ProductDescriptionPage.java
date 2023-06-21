package org.nika.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDescriptionPage extends BasePage{
    public ProductDescriptionPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    By continueShoppingButtonLocator = By.id("continue-shopping");

    public void clickContinueShoppingButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueShoppingButtonLocator)).click();
    }
}
