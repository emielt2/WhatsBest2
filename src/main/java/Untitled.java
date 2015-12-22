

import com.thoughtworks.selenium.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class Untitled {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.voedingscentrum.nl/");
		selenium.start();
	}

	@Test
	public void testUntitled() throws Exception {
		selenium.open("/nl/mijn-gewicht/heb-ik-een-gezond-gewicht/bmi-meter.aspx");
		selenium.click("id=geslachtMan");
		selenium.type("name=leeftijd", "50");
		selenium.type("name=lengte", "175");
		selenium.type("name=gewicht", "50");
		selenium.click("css=div.berekenOk");
		Thread.sleep(1000);
		String BMI = selenium.getText("id=i11");
		System.out.println("Resultaat is: " + BMI);
		String Conclusie = selenium.getText("id=i13");
		System.out.println("Conclusie is: " + Conclusie);
	}

	@After
	public void tearDown() throws Exception {
		//selenium.stop();
	}
}
