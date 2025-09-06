package com.broadridge.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v134.network.Network;
import org.openqa.selenium.devtools.v134.network.model.Request;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){}

    private static WebDriver driver;
    public static DevTools devTools;

    public static Request req ;

    //sets the driver object
    public static WebDriver getDriver(){
        if (driver == null){
            String browserType = ConfigurationReader.getProperty("browser");

            switch (browserType){

                case "chrome":
                    WebDriverManager.chromedriver().setup();

                    ChromeDriver chromeDriver = new ChromeDriver();
                    driver = chromeDriver;


// DevTools başlat
                    devTools = chromeDriver.getDevTools();
                    devTools.createSession();
                    devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

                    // Listener ekle
                    devTools.addListener(Network.requestWillBeSent(), request -> {
                        req = request.getRequest();
                        //https://www.broadridge.com/api/form-processor
                        //https://www-dev.broadridge.com/api/form-processor
                        if (req.getUrl().equals("https://www-dev.broadridge.com/api/form-processor")) {
                            System.out.println("URL: " + req.getUrl());
                            System.out.println("Method: " + req.getMethod());
                            //System.out.println("statuscode = " + req.getResponse().getStatus());
                            System.out.println("Payload: " + req.getPostData().orElse("No payload"));
                        }
                    });

                    driver.manage().window().maximize();
                    //driver.manage().window().setSize(new Dimension(1290,790));
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    //driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    /*EdgeOptions option = new EdgeOptions();
                    option.addArguments("--remote-allow-origins=*");
                    option.addArguments("--deny-permission-prompts");*/
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            }
        }

        return driver;
    }

    //used for closing browser
    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
