package com.project.autotest.fun.config;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        plugin = {"pretty", "json:target/cucumber.json","com.project.autotest.fun.config.CustomReporter"},
        glue = "com.project.autotest.fun.tests",
        tags = "~@TODO",
        features = "src/test/java/com/project/autotest/fun/tests")
public class RunnerTest {
}
