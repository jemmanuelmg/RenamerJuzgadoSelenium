package launcher;

import java.util.Timer;
import utility.FileRenamer;

public class SeleniumLauncher {

	public static String username;
	public static String password;
	public static String loginAddress;
	public static String contextPath;
	
	public static void start() throws InterruptedException {
		
		SeleniumLauncher.setDefaultParameters();
		String processNumberShort = javax.swing.JOptionPane.showInputDialog("Porfavor ingresa el radicado del proceso en el siguiente formato \n Ej: 2022-00251");
		FileRenamer fileRenamerObj = new FileRenamer(processNumberShort);
		//javax.swing.JOptionPane.showMessageDialog(null, "El proceso comenzará ahora. \n En unos minutos, se informará cuando haya terminado \n\n  También se informará si ha habido un problema. \n\n Recuerda autorizar la sesión desde la app de authenticator.", "En proceso", 1);
		
		try {
			fileRenamerObj.executeActions();
		} catch (Exception e) {
			e.printStackTrace();
			javax.swing.JOptionPane.showMessageDialog(null, "Ha ocurrido un problema y es posible \n que ningún archivo haya sido renombrado. \n\n", "Error", 0);
		}
		
		
	}
	
	public static void initializeParameters(String[] args) {
		
		//SeleniumLauncher.startReporter();
		if (SeleniumLauncher.validateParameters(args)) {
			SeleniumLauncher.username = args[0];
			SeleniumLauncher.password = args[1];
			SeleniumLauncher.loginAddress = args[2];
			SeleniumLauncher.contextPath = args[3];
		} else {
			SeleniumLauncher.setDefaultParameters();
		}
		SeleniumLauncher.setChromeDriverProperty();
		
	}

	public static boolean validateParameters(String[] args) {
		
		boolean validity;
		
		if (args != null && args.length > 0) {
			if (!args[0].equals("${user}") && !args[1].equals("${password}") && !args[2].equals("${address}")) {
				validity = true;
			} else {
				validity = false;
			}
		} else {
			validity = false;
		}
		
		return validity;
		
	}

	public static void setChromeDriverProperty() {	
		
		String path = SeleniumLauncher.contextPath;		
		String osName = System.getProperty("os.name").toLowerCase();
		
		if (osName.contains("windows")) {
			System.setProperty("webdriver.chrome.driver", path.replace("\\", "/") + "/libraries/chromedriver_win32/chromedriver.exe");
		} else if (osName.contains("mac")) {
			System.setProperty("webdriver.chrome.driver", path + "/libraries/chromedriver_mac64/chromedriver");
		} else if (osName.contains("linux")) {
			System.setProperty("webdriver.chrome.driver", path + "/libraries/chromedriver_linux64/chromedriver");
		}
		
	}
	
	public static void startReporter() {
		
		Timer timer = new Timer(true);
		timer.schedule(new SeleniumReporter(), 0, 2000);
		
	}
	
	public static void setDefaultParameters() {
		
		/*SeleniumLauncher.username = "juan_martinez2@epam.com";
		SeleniumLauncher.password = "BRunito55!!";
		SeleniumLauncher.loginAddress = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=4765445b-32c6-49b0-83e6-1d93765276ca&redirect_uri=https%3A%2F%2Fwww.office.com%2Flandingv2&response_type=code%20id_token&scope=openid%20profile%20https%3A%2F%2Fwww.office.com%2Fv2%2FOfficeHome.All&response_mode=form_post&nonce=638363770674346189.OTBjZjc2MTEtZWE5NS00YzM3LTkwMzgtMGY3ZWUwOTk2ZTVmMDM2YzdkMDUtMDQ5MC00OTEyLWEwMTYtZmJhOGU0ODMxZGM1&ui_locales=en-US&mkt=en-US&client-request-id=0a62092d-0813-4d36-b211-c2bcbc91e606&state=FvDavo_irQkiN34BLeFwiNni6cxUUSMainC0RFTXUTJt7FbJkuLbaY8TtR4z_mNBlcyIdF2mwHjHd30jsbWkH_YmuFgBHuyabM-ARiPc8kz1Hl9qBszsTwUWNIK6qA_QckTceqRoftY7t4wI-OARXKtUPf3ZZP4X92KXeOjxr2rlchWbATzeRw6-k7nIz5ecLrx6kaaXb0Co2u8yL2IFEF-LxXAcXT7uDT0BbiQCmYY9SUp1izjr0q9JdFuPfebG6HYXCmJ8IVPpVJGcLsJjyA_1hZVIUBCBgkNUpxgGfxk&x-client-SKU=ID_NET6_0&x-client-ver=6.30.1.0";
		*/
	
		SeleniumLauncher.username = "j14labmed@cendoj.ramajudicial.gov.co";
		SeleniumLauncher.password = "Medjuzlab2024%*";
		SeleniumLauncher.loginAddress = "https://login.microsoftonline.com/common/oauth2/v2.0/authorize?client_id=4765445b-32c6-49b0-83e6-1d93765276ca&redirect_uri=https%3A%2F%2Fwww.office.com%2Flandingv2&response_type=code%20id_token&scope=openid%20profile%20https%3A%2F%2Fwww.office.com%2Fv2%2FOfficeHome.All&response_mode=form_post&nonce=638357077971292754.NjMxN2E4YmMtM2Y1Zi00YTQyLThiZmEtZjM4Zjc2YWI3Njc0OTg4YWM0ZjMtNTFkMS00NzY1LWFlN2ItNDliZTdhN2ZkZGY4&ui_locales=en-US&mkt=en-US&client-request-id=41875c18-1bae-48d1-add5-90c7b0a11725&state=_8uYHBNfuOb-KN3np9CyPm_UcZW2aKuNE0Y82FWQo2Gi_B7FZe_GO08iy294YZzahFWD_GQtHk8Im2mXcov52shzsFVkGQvoxRgjUz81TLbIvoZKlvczFd1jyd-4LZZIicSfxX4eM1WVK39yu5wFISaVX6HIFtvyMj2EC4Hr92QwG8Y18Yum7xm3tZ0zY_7C-Qe3QUcCaFXWuPR_8PEmiCUdLehGCOop5x8LIw32lORPVw3Vg5Xe9x6G0Z49ZHFL7SY-wKQ3Q2HL72dk6_3StGK0Xx21IksmrNxXEqQ-6SA&x-client-SKU=ID_NET6_0&x-client-ver=6.30.1.0";
		
		String path = System.getProperty("user.dir");
		SeleniumLauncher.contextPath = path.substring(2, path.length());
		
	}
	
}
