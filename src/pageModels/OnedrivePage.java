package pageModels;

import java.time.Duration;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OnedrivePage {

	public WebDriver driver;
	public WebDriverWait wait;
	public By searchInput;
	public By folderToEnter;
	public By filesIdentifier;
	public By renameButton;
	public By renameTextBox;
	public By searchIconShortScreens;
	public By notificationCloseButton;
	public Actions actions;
	public String processNumber;
	public String processNumberShort;
	public List<Integer> allCustomIds;
	
	public OnedrivePage(WebDriver driver, String processNumber, String processNumberShort) {
		
		this.driver = driver;
		this.processNumber = processNumber;
		this.processNumberShort = processNumberShort;
		this.searchInput = By.cssSelector("form input[role=\"combobox\"]");
		this.folderToEnter = By.cssSelector("button[title=\"" + processNumber.replace("-", " ") + "\"]");
		this.filesIdentifier = By.cssSelector("button.ms-Link[data-automationid=\"FieldRenderer-name\"][data-selection-invoke=\"true\"][role=\"link\"][type=\"button\"]");
		this.renameButton = By.cssSelector("button[name=\"Cambiar nombre\"]");
		this.renameTextBox = By.cssSelector("input[type=\"text\"][placeholder=\"Escriba el nuevo nombre\"]");
		this.searchIconShortScreens = By.xpath("//*[@id=\"SearchButton\"]");
		this.notificationCloseButton = By.cssSelector("div[role=\"alert\"][data-automationid=\"msFluentToast\"] button");
		this.actions = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.of(30, java.time.temporal.ChronoUnit.SECONDS));
		//this.allCustomIds = new List<Integer>();
		
	}
	
	public void searchProcessNumber() {
		
		driver.findElement(searchInput).sendKeys(processNumber.replace("-", " "));
		driver.findElement(searchInput).sendKeys(Keys.ENTER);
		
	}
	
	public void enterProcessFolder() {
		
		actions.moveToElement(driver.findElement(folderToEnter)).perform();
		driver.findElement(folderToEnter).sendKeys(Keys.ENTER);
		
	}
	
	public void clickOnMinimizedSearchIcon() {
		
		driver.findElement(searchIconShortScreens).click();
		
	}
	
	public void startRenamingProcess() {
		
		boolean complete = false;
		int timeToSleep = 2000;
		int iteration = 0;
		
		while(!complete) {
			
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(filesIdentifier));
			
			List<WebElement> filesFoundUntilNow = driver.findElements(filesIdentifier);
						
			renameFiles(filesFoundUntilNow);
			
			sleepFor(timeToSleep);
			
			if (iteration == 5) {
				complete = true;
			}
			
			iteration += 1;			
			
		}
		
		exitOnSuccess();
		
	}
		
	/*public void setCustomIdentifierToElement(WebElement file, int attNumber) {
		
		String ariaDescribedBy = file.getAttribute("aria-describedby");
		String customId = file.getAttribute("juanmartinezid");
		if (ariaDescribedBy != null && customId == null) {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String command = "document.querySelector('[aria-describedby=" + ariaDescribedBy  + "]').setAttribute('juanmartinezid', '" + attNumber + "')";
			js.executeScript(command);
			allCustomIds.add(attNumber);
			
		}
		
	}*/
		
	public String getNewName(String currentName) {
		
		String newName = "";
		
		newName = currentName.replace(processNumberShort, "");
		
		newName = newName.replace(".pdf", "");
		newName = newName.replace(".jpg", "");
		newName = newName.replace(".png", "");
		newName = newName.replace(".docx", "");
		newName = newName.replace(".doc", "");
		newName = newName.replace(".xlsx", "");
		newName = newName.replace(".xls", "");
		newName = newName.replace(".mp4", "");
		
		newName = newName.replace(".PDF", "");
		newName = newName.replace(".JPG", "");
		newName = newName.replace(".PNG", "");
		newName = newName.replace(".DOCX", "");
		newName = newName.replace(".DOC", "");
		newName = newName.replace(".XLSX", "");
		newName = newName.replace(".XLS", "");
		newName = newName.replace(".MP4", "");
		
		newName = newName.replace(".", "");
		newName = newName.replace(" ", "");
		
		return newName;
		
	}
		
	public void renameFiles(List<WebElement> filesFound) {
		
		for (int i = 0; i < filesFound.size(); i++) {
			
			WebElement file = driver.findElements(filesIdentifier).get(i);
			
			String currentName = file.getText();
			System.out.println("Intentando renombrar: ---> " + currentName);
			System.out.println(" ----------------------- ");
			System.out.println(" ");
			System.out.println(" ");
			
			if (currentName.contains(processNumberShort) || currentName.contains(" ") || currentName.matches(".*\\s.*")) {
				
				actions.moveToElement(file).perform();
				actions.contextClick(file).perform();
				
				try {
					wait.until(ExpectedConditions.visibilityOfElementLocated(renameButton));
					driver.findElement(renameButton).click();
				} catch (Exception e) {
					actions.moveToElement(file).perform();
					
					file.sendKeys(Keys.ESCAPE);
					actions.contextClick(file).perform();
					
					wait.until(ExpectedConditions.visibilityOfElementLocated(renameButton));
					driver.findElement(renameButton).click();
				}
				
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(renameTextBox));
				String newName = getNewName(currentName);
				
				driver.findElement(renameTextBox).sendKeys(Keys.CONTROL + "a");
				driver.findElement(renameTextBox).sendKeys(Keys.DELETE);
				driver.findElement(renameTextBox).sendKeys(newName);
				driver.findElement(renameTextBox).sendKeys(Keys.ENTER);
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(notificationCloseButton));
				actions.moveToElement(driver.findElement(notificationCloseButton)).perform();
				
				try {
					driver.findElement(notificationCloseButton).click();
				} catch (Exception e) {
					actions.moveToElement(file).perform();
					sleepFor(5000);
				}
				
				sleepFor(2000);
				
			}
		}
				
	}
	
	public void exitOnSuccess() {
		
		javax.swing.JOptionPane.showMessageDialog(null, "Los archivos se han renombrado correctamente \n\n si deseas renombrar otro proceso da click en 'Comenzar' nuevamente", "Completado", 1);
		
	}
	
	public void sleepFor(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
