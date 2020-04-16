# ui-test-tool
A UI Test Project with Java, Maven, Selenium, TestNG 

1. Download the latest chromedriver from http://chromedriver.chromium.org/downloads and put it in src/main/resources


2. Edit file TC_UI_Test_001.java:

	a. define the OS (win/mac)

		String appOS = "win";
		
	b. Update the path of chrome web driver	
	
		if (appOS.equalsIgnoreCase("mac")) {
            System.setProperty("webdriver.chrome.driver", "/Users/vpetrou/apps/test-automation/ui-test-tool/src/main/resources/chromedriver_mac");
        } else {
            System.setProperty("webdriver.chrome.driver", "C:/Users/vpetrou/Desktop/test-automation/ui-test-tool/src/main/resources/chromedriver.exe");
        }
