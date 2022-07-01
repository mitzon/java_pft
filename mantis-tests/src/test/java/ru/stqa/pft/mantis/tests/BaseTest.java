package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.BugifyIssue;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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


    public void skipIfNotFixed(int issueId) throws IOException, ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        Issue issue = app.soap().getIssue(BigInteger.valueOf(issueId));
        Set<String> resolutionsForClosedIssue = new HashSet<String>(Arrays.asList("fixed", "no change required", "won't fix"));
        if (resolutionsForClosedIssue.contains(issue.getResolution().getName())) {
            return false;
        }
        return true;
    }

//    private boolean isIssueOpen(int issueId) throws IOException {
//        BugifyIssue issue = app.rest().getIssueById(issueId);
//        if (issue.getState_name().equals("Closed")) {
//            return true;
//        }
//        return false;
//    }
}
