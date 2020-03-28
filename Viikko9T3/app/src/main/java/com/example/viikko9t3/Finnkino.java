package com.example.viikko9t3;

import android.view.View;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Finnkino {

    ArrayList<Theatre> theatreList = new ArrayList<Theatre>();
    ArrayList<String> theatreNameList = new ArrayList<String>();
    ArrayList<Movie> movieList = new ArrayList<Movie>();
    ArrayList<String> movieStringList = new ArrayList<String>();

    public Finnkino(){}

    public ArrayList<String> readTheatreXML(){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Osoite tähän
            String urlString = "https://www.finnkino.fi/xml/TheatreAreas/";

            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element"+doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for (int i = 0; i<nList.getLength(); i++){
                Node node = nList.item(i);


                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    /*
                    System.out.print("ID: ");
                    System.out.println(element.getElementsByTagName("ID").item(0).getTextContent());
                    System.out.print("Name: ");
                    System.out.println(element.getElementsByTagName("Name").item(0).getTextContent());
                    */
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
            System.out.println("%% Elokuvateatterit ladattu!%%");
            return theatreNameList;
        }

    }
    public ArrayList<String> readScheduleXML(String date, Integer index){
        //Jos teatteria vaihdettiin, tyhjennetään jo tehdyt listat
        if(movieList.size()!= 0){
            movieList.clear();
            movieStringList.clear();
            System.out.println("Tyhjennetään listat ja ladataan uudet tiedot");
        }
        try {

            //Haetaan valitun teatterin indeksiä vastaava teatteri-olio listasta theateList, jonka ID tallennetaan muuttujaan idString
            String idString = theatreList.get(index).ID.toString();
            String urlString = "https://www.finnkino.fi/xml/Schedule/?area=" + idString +"&dt="+date;

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(urlString);

            doc.getDocumentElement().normalize();
            //System.out.println("Root element: "+doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");

            for (int i = 0; i<nList.getLength(); i++){
                Node node = nList.item(i);

                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    //

                    String title = element.getElementsByTagName("Title").item(0).getTextContent();

                    //Päivämäärät ovat muotoa "2020-04-22T18:00:00"
                    SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    try{
                        //Haetaan xml:stä alkuaika ja tallenetaan se muodossa date
                        String dateStartSting = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                        Date dateStart = formatIn.parse(dateStartSting);

                        String dateEndString = element.getElementsByTagName("dttmShowEnd").item(0).getTextContent();
                        Date dateEnd = formatIn.parse(dateEndString);

                        Movie movie = new Movie(title, dateStart, dateEnd);
                        movieList.add(movie);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            //Tässä vaiheessa kaikki päivän elokuvat on lisätty listaan movieolioina. Palautetaan pääohljelmaan kuitenkin lista stringejä

            for(int i=0; i < movieList.size(); i++){

                String title = movieList.get(i).movieTitle;

                //Yksinkertaistetaan date vain tunnit sisältäväksi
                SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm");
                String startHour = formatHour.format(movieList.get(i).startDate);
                String endHour = formatHour.format(movieList.get(i).endDate);

                String movieInfo = title + ", " + startHour +"-"+endHour;
                System.out.println(movieInfo);
                movieStringList.add(movieInfo);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            System.out.println("%% Elokuvatiedot ladattu!%%");
            return movieStringList;
        }

    }




}
