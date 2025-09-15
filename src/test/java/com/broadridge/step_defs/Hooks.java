package com.broadridge.step_defs;

import com.broadridge.utils.Driver;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

    @BeforeStep
    public void horizontalLineStart(){
        System.err.println("------------------- START STEP -------------------");

    }

    @After
    public void tearDown(Scenario scenario){

        if (scenario.isFailed()){
            byte[] screenshot = ( (TakesScreenshot) Driver.getDriver() ).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }

        //if scenario fails, it takes screenshot
        System.err.println("------------------- END BROWSER -------------------");

        //for closing the browser
        Driver.closeDriver();
    }
}
