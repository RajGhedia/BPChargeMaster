package cucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "src/main/resources",
        glue = {"Steps.stepDefinition"},
        plugin = { "pretty", "json:target/cucumber-reports/cucumber.json" },

       // tags = { "~@ignore", "~@high", "~@mid", "~@low", "~@wip","~@Test","@Pod"}
        tags = { "@Pod"}
        )


public class TestRunner {


}
