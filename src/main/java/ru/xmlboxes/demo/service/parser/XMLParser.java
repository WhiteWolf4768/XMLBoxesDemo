package ru.xmlboxes.demo.service.parser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class XMLParser {
    public Storage parseXML(String xmlPath) throws IOException {
        File initialFile = new File(xmlPath);
        InputStream is = new FileInputStream(initialFile);
        XmlMapper xmlMapper = new XmlMapper();
        String xml = inputStreamToString(is);
        return xmlMapper.readValue(xml, Storage.class);
    }

    private String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
