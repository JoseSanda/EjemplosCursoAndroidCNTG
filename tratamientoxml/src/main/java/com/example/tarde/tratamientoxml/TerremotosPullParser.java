package com.example.tarde.tratamientoxml;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tarde on 06/02/2015.
 */
public class TerremotosPullParser {

    public TerremotosPullParser() {
    }

    public static List<Terremoto> parse(InputStream is) throws XmlPullParserException, IOException, ParseException {
            LinkedList<Terremoto> terremotos = new LinkedList<>();
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(is,"UTF-8");
            int eventType = xmlPullParser.getEventType();
            Terremoto terremoto = null;
            while(eventType != XmlPullParser.END_DOCUMENT){
                String tag = xmlPullParser.getName();

                if(eventType == xmlPullParser.START_TAG){
                    if ("entry".equals(tag)){
                            terremoto = new Terremoto();
                    }else if(terremoto != null) {
                        if ("id".equals(tag)) {
                            terremoto.setId(xmlPullParser.nextText());
                        } else if ("title".equals(tag)) {
                            String titulo = xmlPullParser.nextText();
                            terremoto.setTitle(titulo);
                            Float magnitud = Float.valueOf(titulo.split(" ")[1]);
                            terremoto.setMagnitud(magnitud);
                        } else if ("updated".equals(tag)) {
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                            Date fecha = df.parse(xmlPullParser.nextText());
                            terremoto.setFecha(fecha);
                        } else if ("link".equals(tag)){
                            terremoto.setLink(xmlPullParser.getAttributeValue(null,"href"));
                        } else if ("georss:point".equals(tag)){
                            String latLong = xmlPullParser.nextText();
                            String[] tokens = latLong.split(" ");
                            terremoto.setLatitud(Float.valueOf(tokens[0]));
                            terremoto.setLongitud(Float.valueOf(tokens[1]));
                        }
                    }
                } else if(eventType == xmlPullParser.END_TAG){
                    if (tag.equals("entry")) {
                        terremotos.add(terremoto);
                        terremoto = null;
                    };
                }
                eventType = xmlPullParser.next();
            }

            return terremotos;
    }
}
