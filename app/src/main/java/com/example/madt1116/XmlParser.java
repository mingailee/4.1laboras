package com.example.madt1116;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlParser {
    public static List<String> getRateFromECB(InputStream stream) throws IOException {
        String result = "";
        List<String> Ratelist;
        Ratelist = new ArrayList<String>();

        try {
            DocumentBuilderFactory xmlDocFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder xmlDocBuilder = xmlDocFactory.newDocumentBuilder();
            Document doc = xmlDocBuilder.parse(stream);

            NodeList rateNodes = doc.getElementsByTagName(Constants.CUBE_NODE);
            for (int i = 0; i < rateNodes.getLength(); ++i) {
                Element cube = (Element) rateNodes.item(i);
                if(cube.hasAttribute("currency")){
                    String currencyName = cube.getAttribute("currency");
                    String rate = cube.getAttribute("rate");
                    result = currencyName + " - " + rate;
                    Ratelist.add(result);
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();

        }
        return Ratelist;

    }
}
