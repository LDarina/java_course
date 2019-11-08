package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.16.8.199");
    assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>CA</State></GeoIP>");

  }


  @Test
  public void testInvalidIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.16.8.zzz");
    assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>CA</State></GeoIP>");

  }
}
