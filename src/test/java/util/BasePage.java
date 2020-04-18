package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver = null;  // If run sequentially, static is good for memory as it creates one copy for each test
    // if run parallel, static must be removed to run driver for each test case.
    public Properties prop;

    public WebDriver initDriver() throws IOException {

        // This is the attach the data file in resources
        prop = new Properties();
        String path = System.getProperty("user.dir") + "/config.properties";
        System.out.println(path);
        FileInputStream fis = new FileInputStream(path);
        prop.load(fis);

        String browser = prop.getProperty("browser");
        //mvn test -Dbrowser=chrome
        //String browser = System.getProperty("browser");
        String url = prop.getProperty("url");
        if (browser.contains("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//test//java//util//chromedriver");
            //ChromeOptions chromeOptions = new ChromeOptions();
/*            if (prop.getProperty("headless").equals("yes")) {
                chromeOptions.addArguments("headless");

            }*/
            driver = new ChromeDriver();
        }

        else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src//test//java//util//geckodriver");
            driver = new FirefoxDriver();
        }

        driver.get(url);
        //driver.manage().window().fullscreen();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        return driver;

    }

    public void getScreenShot(String result) throws IOException {

        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("//users//yeyildiz//Downloads/Screenshot/"+result+"_ScreenShot.png"));
    }

//	public Properties initProperties() throws IOException {
//
//		Properties prop = new Properties();
//		String path = "//Users//yeyildiz//eclipse-workspace//01Project//src//main//java//config//data.properties";
//		FileInputStream fis = new FileInputStream(path);
//		prop.load(fis);
//
//		return prop;
//	}


}