package com.intrasoft.stsc;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vpetrou on 2/3/2018.
 */
public class Main {

    public static void main(String[] args) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();

        System.out.println("TestNG:"+args[0]);
        suites.add("test_suites/"+args[0]+".xml");
        String timeStamp = getTimestamp();
        new File("reports/" + args[0] + "_" + timeStamp).mkdirs();
        testng.setOutputDirectory("reports/" + args[0] + "_" + timeStamp);
        testng.setTestSuites(suites);
        testng.addListener(tla);
        testng.run();
    }

    public static String getTimestamp() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        return timeStamp;
    }
}