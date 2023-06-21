package org.nika.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    By productsLinksLocator = By.xpath("//*[@class='inventory_item_label']/a");
    By addToCartLocator = By.xpath("//*[@class='inventory_details_desc_container']/button");
    By productNameLocator = By.xpath("(//*[@class='inventory_details_desc_container']/div)[1]");
    By shoppingCartBadgeLocator = By.xpath("//*[@class='shopping_cart_link']/span");
    By shoppingCartLocator = By.className("shopping_cart_link");
    By titleLocator = By.className("title");

    public void selectOptionByValue(String value) {
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productsLinksLocator));

        for (WebElement option : options) {
            if (option.getText().equals(value)) {
                option.click();
                break;
            }
        }
    }

    public void clickAddToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartLocator)).click();
    }

    public String getSelectedProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator)).getText().trim();
    }

    public String getAddToCartButtonColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartLocator)).getCssValue("color");
    }

    public boolean getShoppingCartBadgeIsDisplayed() {
        return driver.findElements(shoppingCartBadgeLocator).size() > 0;
    }

    public String getShoppingCartBadgeColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadgeLocator))
                .getCssValue("background-color").trim();
    }

    public void clickShoppingCart() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLocator)).click();
    }

    public String getHomePageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator)).getText().trim();
    }
}
