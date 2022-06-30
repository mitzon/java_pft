package ru.stqa.pft.sandbox;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PointTests {

    @Test
    public void distanceTest(){
        Point p1 = new Point("p1", 6, 9);
        Point p2 = new Point("p2", 2, 5);
        double correctDistance = 5.656853249492381;

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(p1.distanceToAnotherPoint(p2), correctDistance, "First case failed because of: ");
        softAssert.assertEquals(p2.distanceToAnotherPoint(p1), correctDistance, "Second case failed because of: ");
        softAssert.assertAll();



    }
}
