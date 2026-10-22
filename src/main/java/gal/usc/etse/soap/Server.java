package gal.usc.etse.soap;

import gal.usc.etse.soap.calculator.Calculator;
import gal.usc.etse.soap.calculator.CalculatorImpl;
import gal.usc.etse.soap.textAnalyzer.TextAnalyzer;
import gal.usc.etse.soap.textAnalyzer.TextAnalyzerImpl;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Calculator c = new CalculatorImpl();
        String address = "http://localhost:8080/calculator";
        Endpoint.publish(address, c);
        TextAnalyzer t = new TextAnalyzerImpl();
        String address2 = "http://localhost:8080/textAnalyzer";
        Endpoint.publish(address2, t);
        System.out.println("Calculadora publicada en: " + address + "?wsdl");
        System.out.println("Analizador de textos publicado en:" + address2 + "?wsdl");
    }
}
