package tests;

import org.nika.pageobject.HomePage;
import org.nika.pageobject.LoginPage;
import org.nika.pageobject.ProductDescriptionPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTests extends BaseTest {
    @Test(priority = 1, invocationCount = 1, enabled = true, testName = "ვწერთ მხოლოდ იუზერნეიმს")
    public void setOnlyUserName() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setUserName("abc");
        loginPage.clickLoginButton();
        //verify that after set only userName both userName and password input must be red and
        // red error h3 element must be displayed
        Assert.assertEquals(loginPage.getUserNameInputColor(), "rgba(226, 35, 26, 1)");
        Assert.assertEquals(loginPage.getPasswordInputColor(), "rgba(226, 35, 26, 1)");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
        Assert.assertEquals(loginPage.getErrorMessageColor(), "rgba(226, 35, 26, 1)");
    }

    @Test(priority = 2, invocationCount = 1, enabled = true, testName = "ვწერთ მხოლოდ პასწორდს")
    public void setOnlyPassword() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setPassword("123");
        loginPage.clickLoginButton();
        //verify that after set only userName both userName and password input must be red and
        // red error h3 element must be displayed
        Assert.assertEquals(loginPage.getUserNameInputColor(), "rgba(226, 35, 26, 1)");
        Assert.assertEquals(loginPage.getPasswordInputColor(), "rgba(226, 35, 26, 1)");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
        Assert.assertEquals(loginPage.getErrorMessageColor(), "rgba(226, 35, 26, 1)");
    }

    @Test(priority = 3, invocationCount = 1, enabled = true, testName = "ვწერთ არასწორ იუზერნეიმს ან პასწორდს")
    public void setIncorrectPasswordOrUserName() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.setPassword("standard_user");
        loginPage.setUserName("123");
        loginPage.clickLoginButton();
        //verify that after set only userName both userName and password input must be red and
        // red error h3 element must be displayed
        Assert.assertEquals(loginPage.getUserNameInputColor(), "rgba(226, 35, 26, 1)");
        Assert.assertEquals(loginPage.getPasswordInputColor(), "rgba(226, 35, 26, 1)");
        Assert.assertEquals(loginPage.getErrorMessage()
                , "Epic sadface: Username and password do not match any user in this service");
        Assert.assertEquals(loginPage.getErrorMessageColor(), "rgba(226, 35, 26, 1)");
    }

    @Test(priority = 4, invocationCount = 1, enabled = true, testName = "ვამატებთ პროდუქტს კალათაში და ვაგრძელებთ შოპინგს")
    public void addProductToCartAndContinueShopping() {
        //verify loginPage url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");

        new LoginPage(driver, wait).logIn("standard_user", "secret_sauce");

        //verify homePage url
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        //select product
        String productName = "Sauce Labs Bike Light";
        HomePage homePage = new HomePage(driver, wait);
        homePage.selectOptionByValue(productName);
        //verify selected product
        Assert.assertEquals(homePage.getSelectedProductName(), productName);
        homePage.clickAddToCart();
        //verify addToCart button color after click
        Assert.assertEquals(homePage.getAddToCartButtonColor(), "rgba(226, 35, 26, 1)");
        //verify shopping cart badge and color after clicking add to cart button
        Assert.assertTrue(homePage.getShoppingCartBadgeIsDisplayed(), "shopping cart badge is displayed");
        Assert.assertEquals(homePage.getShoppingCartBadgeColor(), "rgba(226, 35, 26, 1)");
        homePage.clickShoppingCart();

        ProductDescriptionPage productDescriptionPage = new ProductDescriptionPage(driver, wait);
        productDescriptionPage.clickContinueShoppingButton();
        //verify that after clicking continue shopping button should redirect to home page
        Assert.assertEquals(homePage.getHomePageTitle(), "Products");
    }
}
