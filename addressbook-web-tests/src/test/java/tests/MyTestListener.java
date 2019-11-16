package tests;

import appmanager.ApplicationManager;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;


public class MyTestListener implements ITestListener {


  @Override
  public void onTestFailure(ITestResult result) {
    ApplicationManager app = (ApplicationManager) result.getTestContext().getAttribute("app");
    saveScreenshot (app.takeScreenshot());
  }
  @Attachment(value = "Page screenshot", type = "image/png")
  public byte[] saveScreenshot(byte[] screenShot) {
    return screenShot;
  }


}
