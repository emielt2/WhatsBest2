import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.junit.Test;


public class ET_SOAP_conv {
	@Test
	public void testSOAPstart() throws Exception{
		System.out.println("START");	
		
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
		
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage1 = messageFactory.createMessage();
        SOAPPart soapPart1 = soapMessage1.getSOAPPart();
        
        String url = "http://www.webservicex.com/CurrencyConvertor.asmx";//http://www.webservicex.com/CurrencyConvertor.asmx?wsdl
        
        String curFrom = "EUR";
        String curTo = "USD";
        soapMessage1 = createSOAPRequest(curFrom,curTo);
        System.out.println("soapMessage1 = " + soapMessage1);
        
        SOAPMessage soapResponse1 = soapConnection.call(soapMessage1, url);
        System.out.println();

        System.out.println("soapResponse1 = " + soapResponse1.getSOAPBody());
        //String serverURI = "http://ws.cdyne.com/";
        System.out.println("--------RESPONSE BELOW------");
        soapResponse1.writeTo(System.out);
        System.out.println("--------RESPONSE END--------");
        
	}
	static SOAPMessage createSOAPRequest(String fromCur, String toCur) throws Exception {
		MessageFactory messageFactorycreate = MessageFactory.newInstance();
		SOAPMessage requestxml = messageFactorycreate.createMessage();
		SOAPPart soapPart = requestxml.getSOAPPart();
		
		/*Dit moet het worden:
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
        //headers.addHeader("SOAPAction", "abc.com"  + "VerifyEmail");
        headers.addHeader("SOAPAction", "http://www.webserviceX.NET/"  + "ConversionRate");
        
        requestxml.saveChanges();
		
		System.out.println("requestxml inhoud = " + requestxml.getSOAPBody());
		requestxml.writeTo(System.out);
		return requestxml;
	}
}
