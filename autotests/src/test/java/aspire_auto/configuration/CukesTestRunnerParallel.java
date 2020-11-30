package aspire_auto.configuration;

import cucumber.api.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import aspire_auto.utils.Props;

/*----TAGS LIST----
 * @aspire_auto
 * */


@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "aspire_auto.stepdefs",
        tags = "@aspire_auto",
        plugin = {
                "pretty",
                "json:target/json-cucumber-reports/cukejson.json",
                "testng:target/testng-cucumber-reports/cuketestng.xml",
                "rerun:target/failed_scenarios.txt"
        },
        dryRun = false
)
public class CukesTestRunnerParallel extends AbstractTestNGCucumberParallelTests {

    @BeforeClass
    public static void before() {
        // Load Profile Path for Test/Stage/Production Environment.
        Props.loadRunConfigProps(AspireConfiguration.getEnvProfilePath());

        // Setup Before run any Cucumber Test
    }

    @AfterClass
    public static void after() {
        //Clean Up After run all Cucumber Tests
    }
}