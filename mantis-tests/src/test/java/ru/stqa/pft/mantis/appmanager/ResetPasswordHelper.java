package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void finish(String resetLink, String realName, String newPassword) {
        wd.get(resetLink);
        type(By.name("realname"), realName);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.xpath("//span[contains(text(),'Update User')]"));
    }
}
