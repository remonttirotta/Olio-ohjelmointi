package com.example.viikko9t2;

import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Finnkino {

    ArrayList<Theatre> theatreList = new ArrayList<Theatre>();
    ArrayList<String> theatreNameList = new ArrayList<String>();


    public Finnkino(){}

    public ArrayList<String> readXML(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Osoite tähän
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";

            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            System.out.println("Root element"+doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i<nList.getLength(); i++){
                Node node = nList.item(i);


                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    System.out.print("ID: ");
                    System.out.println(element.getElementsByTagName("ID").item(0).getTextContent());
                    System.out.print("Name: ");
                    System.out.println(element.getElementsByTagName("Name").item(0).getTextContent());

                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    Integer ID = Integer.parseInt(element.getElementsByTagName("ID").item(0).getTextContent());

                    Theatre theatre = new Theatre(name, ID);
                    theatreList.add(theatre);

                    theatreNameList.add(name);
                }

        }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("%%%%%%%%%%VALMIS%%%%%%%%%%%");
            return theatreNameList;
        }

    }
}
