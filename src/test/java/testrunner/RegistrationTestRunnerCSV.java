package testrunner;

import config.RegistrationDataSet;
import config.Setup;
import config.UserModel;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.UserRegistrationPage;

import java.time.Duration;

public class RegistrationTestRunnerCSV extends Setup {

    @Test(dataProvider = "LoginCSVData", dataProviderClass = RegistrationDataSet.class)
    public void registerCSV(String firstName, String email, String password, String phoneNumber) throws InterruptedException {
        driver.findElement(By.partialLinkText("Register")).click();     // entering register page

        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setEmail(email);
        userModel.setPass(password);
        userModel.setPhn(phoneNumber);

        UserRegistrationPage user = new UserRegistrationPage(driver);
        user.userRegistration(userModel);

        Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));     // explicit wait
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String actualSuccessfulMsg = driver.findElement(By.className("Toastify__toast")).getText();
        String expectedSuccessfulMsg = "registered successfully!";
        Assert.assertTrue(actualSuccessfulMsg.contains(expectedSuccessfulMsg));
    }


}
