package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;

public class BaseTest {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        File current = new File(".");
        System.out.println(current.getAbsolutePath());
        app.ftp().upload(new File("src/test/java/ru/stqa/pft/mantis/resources/config_defaults_inc.php"), "config_defaults_inc.php", "config_inc.php.back");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
        app.ftp().restore("config_inc.php.back", "config_defaults_inc.php");
    }
}
