package com.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Base {

    protected WebDriver driver;
    protected Properties prop;

    protected static ExtentReports extent;
    protected ExtentTest test;

    // ---------------- REPORT SETUP ----------------
    @BeforeClass
    public void setUp() throws IOException {

        // Load config
        prop = new Properties();
        FileInputStream fis =
                new FileInputStream("src/main/java/com/utilities/config.properties");
        prop.load(fis);

        // Initialize Extent Report (ONLY ONCE)
        String timeStamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        String reportPath = System.getProperty("user.dir")
                + "/reports/ExtentReport_" + timeStamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("SauceDemo Automation Report");
        spark.config().setDocumentTitle("UI Automation Execution");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Tester", "Your Name");
        extent.setSystemInfo("Browser", prop.getProperty("browser"));
        extent.setSystemInfo("Environment", "QA");
    }

    // ---------------- DRIVER + TEST INIT ----------------
    @BeforeMethod
    public void initialize(Method method) {

        // Create test entry for EACH test
        test = extent.createTest(method.getName());

        String browser = prop.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver",
                    "src/main/java/com/drivers/chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--incognito");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_leak_detection_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        driver.get(prop.getProperty("baseUrl"));
        test.info("Navigated to application URL");
    }

    // ---------------- RESULT LOGGING ----------------
    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else {
            test.skip("Test Skipped");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    // ---------------- FLUSH REPORT ----------------
    @AfterClass
    public void closeReport() {
        if (extent != null) {
            extent.flush(); // THIS generates the report
        }
    }
}