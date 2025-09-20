package pe.gob.interbank.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        features = "src/test/resources/features",
        stepNotifications = true,
        glue = {"pe.gob.interbank.step"},
        tags = "@CrearDataSimple"
)
public class Runner {


}
