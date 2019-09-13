package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void TestDistance() {
    Point p1 = new Point(12, 66);
    Point p2 = new Point(10,60);

    Assert.assertEquals(p1.distance(p2), 6.324555320336759);
  }
  @Test
  public void TestDistanceNeg() {
    Point p1 = new Point(10, 60);
    Point p2 = new Point(12, 66);

    Assert.assertEquals(p1.distance(p2), 6.324555320336759);
  }
}

