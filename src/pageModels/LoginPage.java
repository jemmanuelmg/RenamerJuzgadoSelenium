package pageModels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	public WebDriver driver;
	public By usernameInput;
	public By nextButton;
	public By passwordInput;
	public By loginButton;
	public By yesButton;
	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
		this.usernameInput = By.cssSelector("input[type=\"email\"]");
		this.nextButton = By.cssSelector("input[type=\"submit\"]");
		this.passwordInput = By.cssSelector("input[type=\"password\"]");
		this.loginButton = By.cssSelector("input[type=\"submit\"]");
		this.yesButton = By.cssSelector("input[value=\"Yes\"]");
		
	}
	
	public void enterUsername(String username) {
		
		driver.findElement(usernameInput).sendKeys(username);
		
	}
	
	public void clickNext() {
		
		driver.findElement(nextButton).click();
		
	}
	
	public void enterPassword(String password) {
		
		driver.findElement(passwordInput).sendKeys(password);
		
	}
	
	public void clickOnLogin() {
		
		driver.findElement(loginButton).click();
		
	}
	
	public void clickOnYes() {
		
		driver.findElement(yesButton).click();
		
	}
	
}
