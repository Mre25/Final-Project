package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.nio.file.Paths;

public class ExtentReportManager {
    private static ExtentReports extent;
    
    public static ExtentReports getReportInstance() {
        if (extent == null) {
            // Absolute path with consistent naming
            String reportPath = Paths.get(System.getProperty("user.dir"), 
                "test-output", 
                "ParaBank_Extent_Report.html").toString();
            
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setDocumentTitle("ParaBank Automation Results");
            reporter.config().setReportName("Test Execution Report");
            reporter.config().setEncoding("utf-8");
            
            extent = new ExtentReports();
            extent.attachReporter(reporter);
            
            // Mandatory system info
            extent.setSystemInfo("Tester", System.getProperty("user.name"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));
        }
        return extent;
    }
}