package org.nika.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    By userNameLocator = By.id("user-name");
    By passwordLocator = By.id("password");
    By loginButtonLocator = By.id("login-button");
    By errorMessageLocator = By.tagName("h3");
    By errorMessageContainerLocator = By.xpath("(//form/div)[3]");

    public void setUserName(String userName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator)).sendKeys(userName);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator)).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator)).click();
    }

    public void logIn(String userName, String password) {
        //set username
        setUserName(userName);
        //set password
        setPassword(password);
        //click login button
        clickLoginButton();
    }

    public String getUserNameInputColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userNameLocator))
                .getCssValue("border-bottom-color");
    }

    public String getPasswordInputColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator))
                .getCssValue("border-bottom-color");
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText().trim();
    }

    public String getErrorMessageColor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageContainerLocator))
                .getCssValue("background-color");
    }
}
