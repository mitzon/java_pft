package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @Test
    public void testRegistration() {
        app.registration().start("user", "2@2");

    }
}
