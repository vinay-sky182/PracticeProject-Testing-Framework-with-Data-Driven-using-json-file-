package com.qa.vigupta.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
//	ExtentReports extent;
	
	public static ExtentReports getReportObject()
	{
        String path = System.getProperty("user.dir") + "\\reports\\index.html";
        // ExtentSparkReporter is a helper class which is helping to create some configuration i.e. (setReporteName, setDocumentTitle, set Path) that will finaly report to main 'ExtentReports'.
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Test Report");
        reporter.config().setReportName("Web Automation Result");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA", "viGupta");
        return extent;
	}

}
