import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReaderFromFile {
    public List<Applicant> ReadApplicantsFromXml(String filepath)
    {
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Applicant");
            List<Applicant> applicantList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++){
                applicantList.add(getApplicant(nodeList.item(i)));
            }

            return applicantList;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    private Applicant getApplicant(Node node)
    {
        Applicant applicant = new Applicant();
        List<Subject> subjects = new ArrayList<>();

        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element elem = (Element) node;
            applicant.setName(getValue("Name",elem));
            NodeList nl = elem.getElementsByTagName("Subject");
            for (int i = 0; i < nl.getLength(); i++) {
                Element element = (Element) nl.item(i);
                Subject s = new Subject();
                s.setName(getValue("Name", element));
                s.setScore(Integer.parseInt(getValue("Score",element)));
                subjects.add(s);
            }
        }
        applicant.setSubjects(subjects);

        return applicant;
    }
    private String getValue(String tag, Element element)
    {
        NodeList nl = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nl.item(0);
        return node.getNodeValue();
    }

    public List<Faculty> ReadFacultiesFromXml(String filepath)
    {
        File xmlFile = new File(filepath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Faculty");
            List<Faculty> facultyList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++){
                facultyList.add(getFaculty(nodeList.item(i)));
            }

            return facultyList;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    private Faculty getFaculty(Node node)
    {
        Faculty faculty = new Faculty();
        List<Subject> subjects = new ArrayList<>();

        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element elem = (Element) node;
            faculty.setName(getValue("Name",elem));
            faculty.setScore(Integer.parseInt(getValue("Score",elem)));
            NodeList nl = elem.getElementsByTagName("Subject");
            for (int i = 0; i < nl.getLength(); i++) {
                Element element = (Element) nl.item(i);
                Subject s = new Subject();
                s.setName(getValue("Name", element));
                s.setScore(Integer.parseInt(getValue("Score",element)));
                subjects.add(s);
            }
        }
        faculty.setNeedSubjects(subjects);

        return faculty;
    }

    public List<Applicant> ReadApplicantFromJson(String filepath)
    {
        Gson gson = new Gson();
        try (Reader r = new FileReader(filepath)) {
            return gson.fromJson(r, new TypeToken<List<Applicant>>() {}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Faculty> ReadFacultiesFromJson(String filepath)
    {
        Gson gson = new Gson();
        try (Reader r = new FileReader(filepath)) {
            return gson.fromJson(r, new TypeToken<List<Faculty>>() {}.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
