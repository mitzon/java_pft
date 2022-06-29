package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void manageOverviewPage() {
        click(By.xpath("//span[contains(text(),'Manage')]"));
    }

    public void manageUsers() {
        click(By.linkText("Manage Users"));
    }

    public void specificUser(String username) {
        click(By.linkText(username));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
