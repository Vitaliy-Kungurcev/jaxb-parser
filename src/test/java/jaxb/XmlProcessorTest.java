package jaxb;

import jaxb.model.ModuleTraining;
import jaxb.model.Training;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class XmlProcessorTest {


    @Test
    public void jaxbReaderTest() throws  JAXBException {

        InputStream xml=this.getClass().getClassLoader().getResourceAsStream("testJaxb.xml");
        XmlProcessor xmlProcessor = new XmlProcessor();
        assertEquals(xmlProcessor.fromXml(xml), trainingCreat());
    }

    @Test(expected = JAXBException.class)
    public void jaxbReaderExceptionCheckingTest() throws JAXBException, IOException, URISyntaxException {
        URL url=this.getClass().getClassLoader().getResource("testJaxb.xml");
        Path path = Paths.get(URI.create(url.toString()));
        String stringXml = new String(Files.readAllBytes(path));
        stringXml = stringXml + "!";
        InputStream stream = new ByteArrayInputStream(stringXml.getBytes(StandardCharsets.UTF_8));
        XmlProcessor xmlProcessor = new XmlProcessor();
        xmlProcessor.fromXml(stream);
    }

    @Test
    public void jaxbWriterTest() throws JAXBException {
        XmlProcessor xmlProcessor = new XmlProcessor();
        assertEquals(xmlProcessor.toXml(trainingCreat()), testText);
    }


    private static Training trainingCreat() {
        Training training = new Training();
        List<ModuleTraining> moduleTrainingList = new ArrayList<>();
        ModuleTraining moduleTraining1 = new ModuleTraining(1, "Collections", "description of the collections module", LocalDate.of(2021, 06, 15));
        ModuleTraining moduleTraining2 = new ModuleTraining(2, "Streams", "description of the streams module", LocalDate.of(2021, 06, 20));
        ModuleTraining moduleTraining3 = new ModuleTraining(3, "Tests", "description of the tests module", LocalDate.of(2021, 06, 25));

        moduleTrainingList.add(moduleTraining1);
        moduleTrainingList.add(moduleTraining2);
        moduleTrainingList.add(moduleTraining3);


        training.setName("epam");
        training.setModuleTrainingList(moduleTrainingList);
        return training;
    }

    static final String testText = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
            "<training>\n" +
            "    <name>epam</name>\n" +
            "    <modules>\n" +
            "        <module>\n" +
            "            <id>1</id>\n" +
            "            <title>Collections</title>\n" +
            "            <description>description of the collections module</description>\n" +
            "            <publishDate>2021-06-15</publishDate>\n" +
            "        </module>\n" +
            "        <module>\n" +
            "            <id>2</id>\n" +
            "            <title>Streams</title>\n" +
            "            <description>description of the streams module</description>\n" +
            "            <publishDate>2021-06-20</publishDate>\n" +
            "        </module>\n" +
            "        <module>\n" +
            "            <id>3</id>\n" +
            "            <title>Tests</title>\n" +
            "            <description>description of the tests module</description>\n" +
            "            <publishDate>2021-06-25</publishDate>\n" +
            "        </module>\n" +
            "    </modules>\n" +
            "</training>\n";


}