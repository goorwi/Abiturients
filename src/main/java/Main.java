import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String PATH_IN = "src\\main\\resources\\in\\";
    public static final String PATH_OUT = "src\\main\\resources\\out\\";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Выберите тип файла для считывания:\n1).xml\n2).json");
        String method = sc.nextLine();
        switch (method)
        {
            case "1":
            {
                ReaderFromFile r = new ReaderFromFile();
                List<Applicant> applicants = r.ReadApplicantsFromXml(PATH_IN + "Applicants.xml");
                List<Faculty> faculties = r.ReadFacultiesFromXml(PATH_IN + "Faculties.xml");
                WriterToLetter writer = new WriterToLetter();
                writer.Write(applicants,faculties,PATH_OUT);
                break;
            }
            case "2":
            {
                ReaderFromFile r = new ReaderFromFile();
                List<Applicant> applicants = r.ReadApplicantFromJson(PATH_IN + "Applicants.json");
                List<Faculty> faculties = r.ReadFacultiesFromJson(PATH_IN + "Faculties.json");
                WriterToLetter writer = new WriterToLetter();
                writer.Write(applicants,faculties,PATH_OUT);
                break;
            }
            default:
        }
    }
}
