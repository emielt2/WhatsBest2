

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class googleConvert {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "https://www.google.nl/");
		selenium.start();
	}

	@Test
	public void testGoogletranslate() throws Exception {
		selenium.open("/?gws_rd=ssl");
		selenium.type("id=lst-ib", "convert EUR to USD");
		selenium.click("name=btnG");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isVisible("css=div.vk_ans.vk_bk")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		String results = selenium.getText("css=div.vk_ans.vk_bk");
		System.out.println("Result = " + results);
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
