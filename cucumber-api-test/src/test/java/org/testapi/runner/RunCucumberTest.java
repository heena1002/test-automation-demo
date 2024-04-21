package org.testapi.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber.html"},
        features = "src/test/resources/org/testapi/featurefiles",
        glue = {"org.testapi.stepdefinition"}
)
public class RunCucumberTest {
}
