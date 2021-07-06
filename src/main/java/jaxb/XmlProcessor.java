package jaxb;


import jaxb.model.Training;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;


public class XmlProcessor {

    public Training fromXml(InputStream xml) throws JAXBException {
        Training training = new Training();

        JAXBContext jaxbContext = JAXBContext.newInstance(Training.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        training = (Training) jaxbUnmarshaller.unmarshal(xml);


        return training;
    }

    public String toXml(Training training) throws JAXBException {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(Training.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(training, stringWriter);

        return stringWriter.toString();
    }
}
