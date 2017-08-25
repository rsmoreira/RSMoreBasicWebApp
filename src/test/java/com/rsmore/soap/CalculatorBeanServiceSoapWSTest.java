package com.rsmore.soap;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.junit.Test;

import com.rsmore.ejb.service.soap.CalculatorBeanSoapService;

public class CalculatorBeanServiceSoapWSTest {

	@Test
	public void soapAccessSumCalculatorTest() throws MalformedURLException {
		// Data to access the web service
	    URL wsdlDocumentLocation = new URL("http://127.0.0.1:4204/RSMoreEJB31App-0.0.1-SNAPSHOT/CalculatorBean?wsdl");
	    String namespaceURI = "http://rsmore.com/wsdl";
	    String servicePart = "CalculatorBeanService";
	    String portName = "CalculatorBeanPort";
	    QName serviceQN = new QName(namespaceURI, servicePart);
	    QName portQN = new QName(namespaceURI, portName);
	    
	    Service service = Service.create(wsdlDocumentLocation, serviceQN);
	    CalculatorBeanSoapService calculator = service.getPort(portQN, CalculatorBeanSoapService.class);
	    
	    int result = calculator.add(1, 1);
	    
	    assertEquals(2, result);
	}

}
