package utility;

import java.time.Duration;

import javax.swing.JOptionPane;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import launcher.SeleniumLauncher;
import pageModels.LoginPage;
import pageModels.OnedrivePage;

public class FileRenamer {
	
	public WebDriver driver;
	public LoginPage loginPage;
	public WebDriverWait wait;
	public WebDriverWait waitShort;
	public String processNumber;
	public String processNumberShort;
	
	public FileRenamer(String processNumberShort) {
		
		this.initializeDriver();
		this.loginPage = new LoginPage(driver);
		this.wait = new WebDriverWait(driver, Duration.of(120, java.time.temporal.ChronoUnit.SECONDS));
		this.waitShort = new WebDriverWait(driver, Duration.of(30, java.time.temporal.ChronoUnit.SECONDS));
		this.processNumberShort = processNumberShort;
		this.processNumber = "05001 31 05 014 " + processNumberShort + " 00";
		
	}
	
	public void initializeDriver() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		driver = new ChromeDriver();
		
	}

	public void executeActions() {
		
		try {
			driver.manage().window().setSize(getDimensionForLogin());
			login();
		} catch (Exception e) {
			e.printStackTrace();
			String newPassword = javax.swing.JOptionPane.showInputDialog("Parece que la contraseña para acceder a OneDrive ha cambiado. \n\n Por favor ingresa aquí la nueva contraseña para realizar un nuevo intento");
			SeleniumLauncher.password = newPassword;
			login();
		}
		
		driver.manage().window().minimize();
		renameFiles();
		
	}
	
	public void login() {

		driver.get(SeleniumLauncher.loginAddress);
		waitShort.until(ExpectedConditions.visibilityOfElementLocated(loginPage.usernameInput));
		
		loginPage.enterUsername(SeleniumLauncher.username);
		loginPage.clickNext();
		waitShort.until(ExpectedConditions.visibilityOfElementLocated(loginPage.passwordInput));
		
		loginPage.enterPassword(SeleniumLauncher.password);
		loginPage.clickOnLogin();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginPage.yesButton));
		loginPage.clickOnYes();
		
		wait.until(ExpectedConditions.titleContains("Microsoft"));
		driver.get("https://etbcsj-my.sharepoint.com/personal/j14labmed_cendoj_ramajudicial_gov_co/_layouts/15/onedrive.aspx?view=1");
		//driver.get("https://epam-my.sharepoint.com/personal/juan_martinez2_epam_com/_layouts/15/onedrive.aspx?view=1");
			
	}
	
	public void renameFiles() {
		
		OnedrivePage oneDrivePage = new OnedrivePage(driver, processNumber, processNumberShort);
		
		try {
			waitShort.until(ExpectedConditions.visibilityOfElementLocated(oneDrivePage.searchIconShortScreens));
			oneDrivePage.clickOnMinimizedSearchIcon();
			waitShort.until(ExpectedConditions.visibilityOfElementLocated(oneDrivePage.searchInput));
		} catch (Exception e) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(oneDrivePage.searchInput));
		}
		
		oneDrivePage.searchProcessNumber();
		
		waitShort.until(ExpectedConditions.visibilityOfElementLocated(oneDrivePage.folderToEnter));
		oneDrivePage.enterProcessFolder();
		oneDrivePage.sleepFor(5000);
				
		waitShort.until(ExpectedConditions.visibilityOfElementLocated(oneDrivePage.filesIdentifier));
		oneDrivePage.startRenamingProcess();
		
	}
	
	public Dimension getDimensionForLogin() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int width = 789;
		long height = (long) js.executeScript("return window.innerHeight");
		return new Dimension(width, (int) height);
		
	}
	
}
