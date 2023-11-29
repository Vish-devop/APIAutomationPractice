package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class FailRetry implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 1;   //always keep mex to 2: because this means you're executing every test case that will consume time.

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}
