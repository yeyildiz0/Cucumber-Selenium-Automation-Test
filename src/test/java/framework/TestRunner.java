package framework;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinition")
// This is to run TestNG from TestRunner
//  Extend AbstractTestNGCucumberTests
//  Remove RunWith statement
public class TestRunner extends AbstractTestNGCucumberTests { }