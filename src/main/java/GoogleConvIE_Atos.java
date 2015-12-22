

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GoogleConvIE_Atos {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();

		System.out.println("Check1");
		//File file = new File("Y:/JAR_LIB/IEDriverServer_Win32_2.45.0/IEDriverServer.exe");
		//File file = new File("F:/SeleniumDownloadFolder/IEDriverServer_x64_2.45.0/IEDriverServer.exe");


		//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		//WebDriver driver = new InternetExplorerDriver();
		//driver = new InternetExplorerDriver();
		baseUrl = "https://www.google.nl/";
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

	}

	@Test
	public void testGoogleAndSoap() throws Exception {

		driver.get(baseUrl + "/?gws_rd=ssl");
		Thread.sleep(1000);
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("eur to usd");
		driver.findElement(By.name("btnG")).click();
		String resultGoogleConv = driver.findElement(By.cssSelector("div.vk_ans.vk_bk")).getText();
		System.out.println("resultGoogleConv = " + resultGoogleConv);
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		ET_SOAP_conv ET_SOAP_conv1 = new ET_SOAP_conv();
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapRequest1 = messageFactory.createMessage();
		SOAPPart soapPart1 = soapRequest1.getSOAPPart();
		String url = "http://www.webservicex.com/CurrencyConvertor.asmx";//http://www.webservicex.com/CurrencyConvertor.asmx?wsdl
		String curFrom = "EUR";
		String curTo = "USD";
		soapRequest1 = ET_SOAP_conv1.createSOAPRequest(curFrom,curTo);
		//System.out.println("soapRequest1 = " + soapRequest1);
		System.out.println();
		soapRequest1.writeTo(System.out);
		SOAPMessage soapResponse1 = soapConnection.call(soapRequest1, url);
		System.out.println();
		soapResponse1.writeTo(System.out);
		//System.out.println("soapResponse1 = " + soapResponse1);
		System.out.println("-----");
		System.out.println("-----");
		//System.out.println(soapResponse1.getSOAPBody());
		Document doc1 = toDocument(soapResponse1);
		NodeList nlXML1 = doc1.getChildNodes();
		Node nXML1 = nlXML1.item(0);
		Element element1 = (Element)nXML1;
		System.out.println("TESTING06a--> " + element1.getElementsByTagName("ConversionRateResult").item(0).getNodeName());
		System.out.println("TESTING06b--> " + element1.getElementsByTagName("ConversionRateResult").item(0).getTextContent());
		String resultSoapConv = element1.getElementsByTagName("ConversionRateResult").item(0).getTextContent();
		//SOAPMessage soapResponse1 = soapConnection.call(soapMessage1, url);
		System.out.println("-------------------------------");
		System.out.println("---RESULTS---------------------");
		System.out.println("-------------------------------");
		System.out.println("---resultGoogleConv geeft = " + resultGoogleConv);
		System.out.println("-----resultSoapConv geeft = " + resultSoapConv);
		System.out.println("-------------------------------");
		//int resultGoogleConvINT = Integer.parseInt(resultGoogleConv);
		String strOut;
		strOut = resultGoogleConv.substring(0,4);
		double resultGoogleConvDOUBLE = Double.parseDouble(strOut);
		System.out.println("resultGoogleConvDOUBLE = " + resultGoogleConvDOUBLE);
		strOut = resultSoapConv.substring(0,4);
		double resultSoapConvDOUBLE = Double.parseDouble(strOut);
		System.out.println("resultSoapConvDOUBLE = " + resultSoapConvDOUBLE);
		//if(resultGoogleConvDOUBLE.equals(resultSoapConvDOUBLE))System.out.println("EQUAL!");
		if(resultGoogleConvDOUBLE-resultSoapConvDOUBLE==0)System.out.println("EQUAL!");
		if(resultGoogleConvDOUBLE==resultSoapConvDOUBLE)System.out.println("EQUAL222!");
		System.out.println(resultSoapConvDOUBLE-resultSoapConvDOUBLE);

	}

	public Document toDocument(SOAPMessage soapMsg) 
			throws TransformerConfigurationException, TransformerException, SOAPException, IOException {
		Source src = soapMsg.getSOAPPart().getContent();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMResult result = new DOMResult();
		transformer.transform(src, result);
		return (Document)result.getNode();
	}

	public void main() {
		System.out.println("This is MAIN");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
