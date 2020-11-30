package aspire_auto.configuration;

public class AspireConfiguration {
    /**
     * Get Environment Profile path for executing test suite.
     */
    public static String getEnvProfilePath() {
        return "./src/test/resources/env_config.properties";
    }

    /**
     * Get Browser type to run the test
     */
    private static final String BROWSER = System.getProperty("browser", "");

    /**
     * Get Browser type to run the test
     */
    public static String getBrowser() {
        return BROWSER;
    }

}
