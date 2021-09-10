package api.openweathermap.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterNG {
    static ExtentReports extent;

    public static ExtentReports getReportObject() {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ssZ").format(new Date());
        String path = System.getProperty("user.dir") + "/reports/Openweathermap_" + timestamp + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("API Automation result");
        reporter.config().setDocumentTitle("Openweathermap API");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA", System.getProperty("user.name"));
        return extent;
    }
}
