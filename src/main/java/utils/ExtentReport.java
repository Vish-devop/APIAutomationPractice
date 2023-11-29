//package utils;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//
//import java.io.File;
//
//public class ExtentReport {
//
//
//
//    public static ExtentReports extentreport = null;
//
//    public static ExtentTest extentlog;
//
//
//
//    public static void initialize(String path) {
//
//        if (extentreport == null) {
//
//            extentreport = new ExtentReports(path, true);
//
//            extentreport.addSystemInfo("Host Name", System.getProperty("user.name"));
//
//            extentreport.addSystemInfo("Environment", Helper.propertyReader(Helper.commonFilePath, "executionEnv"));
//
//            extentreport.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
//
//        }
//
//    }
//
//}