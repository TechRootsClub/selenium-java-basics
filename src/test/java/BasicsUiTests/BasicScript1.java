package BasicsUiTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasicScript1 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // implicit wait for 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Explicit wait for 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title']")));
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
        //ExplicitWait for Your Cart page to be loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' " +
                "and text()='Your Cart']")));
        String itemInCart = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        System.out.println("Item in cart: " + itemInCart);
        // Verify item added to cart
        if (itemInCart.equals("Sauce Labs Bolt T-Shirt")) {
            System.out.println("Item added to cart successfully");
        } else {
            System.out.println("Item not added to cart");
        }
        driver.findElement(By.id("checkout")).click();
        //ExplicitWait for Checkout: Your Information page to be loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' " +
                "and text()='Checkout: Your Information']")));
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("12345");
        driver.findElement(By.id("continue")).click();
        //ExplicitWait for Checkout: Overview page to be loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' " +
                "and text()='Checkout: Overview']")));
        driver.findElement(By.id("finish")).click();
        //ExplicitWait for Checkout: Complete page to be loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-test='title' " +
                "and text()='Checkout: Complete!']")));
        String orderCompleteMessage = driver.findElement(By.xpath("//h2[@data-test='complete-header']")).getText();
        // Verify order placed successfully
        if (orderCompleteMessage.equals("Thank you for your order!")) {
            System.out.println("Order placed successfully");
        } else {
            System.out.println("Order not placed");
        }
        driver.quit();
    }
}
