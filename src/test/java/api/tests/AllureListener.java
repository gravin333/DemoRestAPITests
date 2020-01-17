package api.tests;

import lombok.extern.log4j.Log4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Log4j
public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("=== FAILURE: '" + result.getName() + "' TEST HAS FAILED ===", result.getThrowable());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("=== ATTENTION: '" + result.getName() + "' TEST HAS STARTED ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("=== SUCCESS: '" + result.getName() + "' TEST HAS PASSED ===");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("=== WARNING: '" + result.getName() + "' TEST HAS SKIPPED ===");
    }
}
