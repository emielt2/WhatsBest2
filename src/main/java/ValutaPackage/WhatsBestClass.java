package ValutaPackage;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openga.selenium.
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

//---
//-------

public class WhatsBestClass {
	private static WebDriver driver;
	private static String baseUrl;
	private boolean acceptNextAlert = true;
	private static StringBuffer verificationErrors = new StringBuffer();

@Test
	public void testMain() throws Exception{
	System.out.println("testMain()");
	String argtest[] = new String[1];
	argtest[0] = "1";
	main(argtest);
	
}
	
	//@Before
	public static void beforesetUp() throws Exception {

		System.out.println("Check1a");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "F:\\SeleniumDownloadFolder\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Check1");
		//File file = new File("C:/EmielUserDATA/H-DISK/ALL_JAVA_SELENIUM/JAR_LIB/IEDriverServer_x64_2.45.0/IEDriverServer.exe");
		//System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		//WebDriver driver = new InternetExplorerDriver();
		//driver = new InternetExplorerDriver();
		System.out.println("Check2");
		baseUrl = "https://www.google.nl/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Check3");
		driver.get(baseUrl + "/?gws_rd=ssl");
	}

	public static ValutaResultObject main(String... args) throws Exception {

		//Integer aantalTesten = 12;
		//System.out.print("How many testcases? (max 12): ");
		//Scanner scanner1 = new Scanner( System.in );
		//String userChoiceString = scanner1.nextLine();
		//Integer aantalTesten = Integer.parseInt(userChoiceString);
		Integer aantalTesten = Integer.parseInt(args[0]);
		System.out.println("aantalTestenxxx = " + aantalTesten);
		Integer aantalTestwaarden =2;
		Integer startPositieRow =0+1;
		Integer startPositieColumn =0+1;
		Double resultsGoogle[] = new Double[aantalTesten];
		Double resultsSoap[] = new Double[aantalTesten];
		String arrayTestValues[][] = new String[aantalTesten][aantalTestwaarden];
		String TEMP;
		//ValutaResultObject vorArray[] = new ValutaResultObject[aantalTesten];
		ValutaResultObject vorArray = new ValutaResultObject();
		beforesetUp();
		if(args.length==3){
			arrayTestValues[0][0]=args[1];
			arrayTestValues[0][1]=args[2];
			System.out.println("ARGS WAS 3!");
		}
		//ReadFromExcel testvalues
		else{
			for(int i=0;i<aantalTesten;i++){
				arrayTestValues[i][0] = getvalueTEXT(i+startPositieRow, startPositieColumn);
				arrayTestValues[i][1] = getvalueTEXT(i+startPositieRow, startPositieColumn+1);
				//TEMP =  getvalueTEXT(i+startPositieRow, startPositieColumn);
				//array[12][2] = gettestvalues x12 from excel
				System.out.print(arrayTestValues[i][0]);
				System.out.println(arrayTestValues[i][1]);
			}

		}

		//GetResults Selenium google/Soap
		for(int i=0;i<aantalTesten;i++){
			resultsGoogle[i] = getGoogleResults(arrayTestValues[i][0], arrayTestValues[i][1]);
			System.out.println("resultsGoogle[i] = " + resultsGoogle[i]);
			resultsSoap[i] = getSoapResults(arrayTestValues[i][0], arrayTestValues[i][1]);
			System.out.println("resultsSoap[i] = " + resultsSoap[i]);
			//TODO vorArray[i].UID
			vorArray.UID = new SQLiteJDBC_Insert_RESULT_VALUES().getLastID();
			vorArray.fromVal = arrayTestValues[i][0];
			vorArray.toVal = arrayTestValues[i][1];
			vorArray.googleResult = resultsGoogle[i];
			vorArray.soapResult = resultsSoap[i];

		}

		tearDown();

		/*
		for(int i=0;i<aantalTesten;i++){
			//resultsSoap[i] = getGoogleResults(arrayTestValues[i][0], arrayTestValues[i][1]);
			resultsSoap[i] = getSoapResults(arrayTestValues[i][0], arrayTestValues[i][1]);
			//arrayTestValues[i][0] = getvalueTEXT(i+startPositieRow, startPositieColumn);
			//arrayTestValues[i][1] = getvalueTEXT(i+startPositieRow, startPositieColumn+1);
			//TEMP =  getvalueTEXT(i+startPositieRow, startPositieColumn);
			//array[12][2] = gettestvalues x12 from excel
			//System.out.print(arrayTestValues[i][0]);
			System.out.println("resultsSoap[i] = " + resultsSoap[i]);
		}
		 */
		databaseSaveValues(arrayTestValues,resultsGoogle,resultsSoap);
		storeTestValues(arrayTestValues,resultsGoogle,resultsSoap);

		System.out.println("WhatsBest.java ended succesfully!");
		maakRapport(arrayTestValues,resultsGoogle,resultsSoap);


		/*array[12] soapresults
		compare [x]vs[x]
		report txt

		array		{makerequestx1 getresponsex1}x12
		compare x12*/

		// TODO Auto-generated method stub
		System.out.println("vorArray check: " + vorArray + " fromvalue " + vorArray.fromVal);
return vorArray;
	}

	public static String getvalueTEXT(int row, int col) throws IOException{
		FileInputStream fileIn = new FileInputStream("Y:/TestFiles_YMAP/conv.xls");
		HSSFWorkbook workbook = new HSSFWorkbook(fileIn);
		HSSFSheet worksheet = workbook.getSheet("Sh1");	
		int getal = worksheet.getDefaultColumnWidth();
		String naam1 = worksheet.getSheetName();
		HSSFRow row1 = worksheet.getRow(row);
		HSSFCell cell1 = row1.getCell(col);
		//double double1 = cell1.getNumericCellValue();
		naam1 = cell1.getStringCellValue();
		int waarde1 = worksheet.getFirstRowNum();
		return naam1;
	}



	public static double getGoogleResults(String fromCur, String toCur) throws InterruptedException{


		System.out.println("Check4");
		//System.out.println(baseUrl);
		//driver.get(baseUrl /*+ "/"*/);
		baseUrl = "https://www.google.nl/";
		Thread.sleep(10);
		driver.get(baseUrl + "/?gws_rd=ssl");
		//driver.get("www.google.nl/");
		System.out.println("Check5");
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("");
		driver.findElement(By.id("lst-ib")).clear();
		driver.findElement(By.id("lst-ib")).sendKeys("convert " + fromCur + " to  " + toCur);
		//driver.findElement(By.id("lst-ib")).sendKeys("convert eur usd");
		driver.findElement(By.name("btnG")).click();
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (isElementPresent(By.cssSelector("div.vk_ans.vk_bk"))) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		String googleResultConv = driver.findElement(By.cssSelector("div.vk_ans.vk_bk")).getText();
		System.out.println("Google says = " + googleResultConv);







		double resultGoogleConvDOUBLE = Double.parseDouble(googleResultConv.substring(0,4));




		return resultGoogleConvDOUBLE;
	}

	public static double getSoapResults(String fromCur, String toCur) throws Exception{
		System.out.println("getSoapResults(String fromCur, String toCur)");
		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage1 = messageFactory.createMessage();
		String url = "http://www.webservicex.com/CurrencyConvertor.asmx";//http://www.webservicex.com/CurrencyConvertor.asmx?wsdl
		String curFrom = fromCur;
		String curTo = toCur;
		soapMessage1 = createSOAPRequestConv(curFrom,curTo);

		SOAPMessage soapResponse1 = soapConnection.call(soapMessage1, url);

		System.out.println("--------REQUEST BELOW------");
		soapMessage1.writeTo(System.out);
		System.out.println();
		System.out.println("--------REQUEST END--------");


		Document doc1 = toDocument(soapResponse1);
		NodeList nlXML1 = doc1.getChildNodes();
		Node nXML1 = nlXML1.item(0);
		Element element1 = (Element)nXML1;
		System.out.println("TESTING06a--> " + element1.getElementsByTagName("ConversionRateResult").item(0).getNodeName());
		System.out.println("TESTING06b--> " + element1.getElementsByTagName("ConversionRateResult").item(0).getTextContent());
		System.out.println("--------RESPONSE BELOW------");
		soapResponse1.writeTo(System.out);
		System.out.println();
		System.out.println("--------RESPONSE END--------");




		System.out.println("Check4");
		//System.out.println(baseUrl);
		//driver.get(baseUrl /*+ "/"*/);

		Thread.sleep(1000);

		//driver.get("www.google.nl/");
		System.out.println("Check5");



		String soapResultConv = element1.getElementsByTagName("ConversionRateResult").item(0).getTextContent();
		System.out.println("SOAP says = " + soapResultConv);

		double resultSoapConvDOUBLE = Double.parseDouble(soapResultConv.substring(0,2));
		//ETCHANGED WAS double resultSoapConvDOUBLE = Double.parseDouble(soapResultConv.substring(0,4));

		return resultSoapConvDOUBLE;
	}

	public static SOAPMessage createSOAPRequestConv(String fromCur, String toCur) throws Exception {
		MessageFactory messageFactorycreate = MessageFactory.newInstance();
		SOAPMessage requestxml = messageFactorycreate.createMessage();
		SOAPPart soapPart = requestxml.getSOAPPart();

		/*Dit gaat het worden:
		 * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="http://www.webserviceX.NET/">
   <soapenv:Header/>
   <soapenv:Body>
      <web:ConversionRate>
         <web:FromCurrency>EUR</web:FromCurrency>
         <web:ToCurrency>USD</web:ToCurrency>
      </web:ConversionRate>
   </soapenv:Body>
</soapenv:Envelope>
		 */

		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("web", "http://www.webserviceX.NET/");

		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("ConversionRate", "web");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("FromCurrency", "web");
		soapBodyElem1.addTextNode(fromCur);
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("ToCurrency", "web");
		soapBodyElem2.addTextNode(toCur);
		MimeHeaders headers = requestxml.getMimeHeaders();
		headers.addHeader("SOAPAction", "http://www.webserviceX.NET/"  + "ConversionRate");
		requestxml.saveChanges();
		return requestxml;
	}

	public static Document toDocument(SOAPMessage soapMsg) 
			throws TransformerConfigurationException, TransformerException, SOAPException, IOException {
		Source src = soapMsg.getSOAPPart().getContent();
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		DOMResult result = new DOMResult();
		transformer.transform(src, result);
		return (Document)result.getNode();
	}

	public static void maakRapport(String testValuesArray[][], Double resultsGoogleArray[],Double resultsSoapArray[]) throws IOException{
		System.out.println("RAPPORT START");
		System.out.println("RAPPORT length = " + resultsSoapArray.length);

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
		Calendar cal = Calendar.getInstance();
		String bestandsnaam = dateFormat.format(cal.getTime());
		System.out.println("RAPPORT bestandsnaam = " + bestandsnaam);
		File fileOut1 = new File("Y:/TestFiles_YMAP/OUT_CONV/out_" + bestandsnaam + ".txt");
		BufferedWriter outWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOut1),"utf-8"),1024);


		for(int i=0;i<resultsSoapArray.length;i++){
			outWriter.write("--------------------------------------------------");
			outWriter.newLine();
			outWriter.write("---Testcase = " + (i+1));
			outWriter.newLine();
			outWriter.write("---From = " + testValuesArray[i][0]);
			outWriter.newLine();
			outWriter.write("---To = " + testValuesArray[i][1]);
			outWriter.newLine();
			outWriter.write("---Google = " + resultsGoogleArray[i]);
			outWriter.newLine();
			outWriter.write("---Soap = " + resultsSoapArray[i]);
			outWriter.newLine();
		}
		outWriter.write("--------------------------------------------------");
		outWriter.flush();
		outWriter.close();
		System.out.println("RAPPORT END");

	}

	public static void databaseSaveValues(String testValuesArray[][],Double resultsGoogleArray[], Double resultsSoapArray[]){
		System.out.println("SQL SAVE START");
		for(int i=0;i<resultsSoapArray.length;i++){
			System.out.println("--------------------------------------------------");
			System.out.println("---Testcase = " + (i+1));
			System.out.println("---From = " + testValuesArray[i][0]);
			System.out.println("---To = " + testValuesArray[i][1]);
			System.out.println("---Google = " + resultsGoogleArray[i]);
			System.out.println("---Soap = " + resultsSoapArray[i]);





		}
		System.out.println("SQL SAVE END");
	}


	public static void storeTestValues(String testValuesArray[][],Double resultsGoogleArray[], Double resultsSoapArray[])
	{
		System.out.println("SQL SAVE START");

		Connection c = null;
		Statement stmt = null;
		String sql;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:y:/testfiles_ymap/test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			sql = "delete from RESULT_VALUES";
			stmt.executeUpdate(sql);
			System.out.println("Records from RESULT_VALUES deleted successfully");
			for(int i=0;i<resultsSoapArray.length;i++){
				System.out.println("--------------------------------------------------");
				System.out.println("---Testcase = " + (i+1));
				System.out.println("---From = " + testValuesArray[i][0]);
				System.out.println("---To = " + testValuesArray[i][1]);
				System.out.println("---Google = " + resultsGoogleArray[i]);
				System.out.println("---Soap = " + resultsSoapArray[i]);
				sql = "INSERT INTO RESULT_VALUES " +
						"VALUES"+
						"('"+ (i+1) +
						"','"+ testValuesArray[i][0] +
						"','"+ testValuesArray[i][1] +
						"','"+ resultsGoogleArray[i] +
						"','"+ resultsSoapArray[i] + "');";
				/*
				sql = "INSERT INTO RESULT_VALUES " +
			              "VALUES"+
			      		"(1,'AFA','EUR', '1.1','1.9');"
				 */
				System.out.println("---QUERY = " + sql);
				stmt.executeUpdate(sql);
			}


			//stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.out.println("FAILED");
			System.exit(0);
		}
		System.out.println("Records created successfully");
		System.out.println("SQL SAVE END");

	}




	private static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	//@After
	public static void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
