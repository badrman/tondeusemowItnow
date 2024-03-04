package com.mowitnow.tondeuse.cucumber;

import com.mowitnow.tondeuse.TondeuseApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.NoArgsConstructor;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;
import org.springframework.boot.test.context.SpringBootTest;

import static io.cucumber.core.options.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@CucumberContextConfiguration
@SpringBootTest(classes = {
        TondeuseApplication.class,
        TondeuseIntegrationTest.class},
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ConfigurationParameters({@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/reports/cucumber.html,json:target/reports/cucumber.json,junit:target/reports/cucumber.xml"),
        @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value ="src/test/resources/features")})
@NoArgsConstructor
public class TondeuseIntegrationTest {
}
