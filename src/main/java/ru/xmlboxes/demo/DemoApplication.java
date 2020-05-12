package ru.xmlboxes.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.xmlboxes.demo.service.db.DatabaseXmlInitializer;
import ru.xmlboxes.demo.service.serializer.XmlSerializer;

@SpringBootApplication
public class DemoApplication implements ApplicationRunner {

//    mvn spring-boot:run -Dspring-boot.run.arguments=--path=box.xml

    @Autowired
    private DatabaseXmlInitializer databaseXmlInitializer;
    @Autowired
    private XmlSerializer xmlSerializer;



    @Value("${path}")
    private String xmlPath;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (xmlPath != null) {
            databaseXmlInitializer.InitializeDatabase(xmlPath);
        } else {
            databaseXmlInitializer.InitializeDatabase("testBox.xml");
        }
        xmlSerializer.serializeXML();
    }

}
