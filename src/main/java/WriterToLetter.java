import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriterToLetter {
    public void Write(List<Applicant> applicants, List<Faculty> faculties, String filepath)
    {
        for (Applicant applicant : applicants)
        {
            StringBuilder textOfLetter = new StringBuilder("Уважаемый, " + applicant.getName() + ",\n");
            boolean passed = true;

            for (Faculty faculty : faculties)
            {
                passed = true;
                int score = 0;
                for (Subject subjectF : faculty.getSubjects())
                {
                    for (Subject subjectA : applicant.getSubjects())
                    {
                        if (subjectA.getName().equals(subjectF.getName()))
                        {
                            if (subjectA.getScore() < subjectF.getScore()) passed = false;
                            else score += subjectA.getScore();
                            break;
                        }
                    }
                }

                if (score >= faculty.getScore() && passed) {
                    textOfLetter.append("поздравляем, ваши результаты ЕГЭ позволяют вам поступить на ")
                            .append(faculty.getName())
                            .append("!\n");
                }
                else passed = false;
            }

            if (!passed) textOfLetter.append("к сожалению, вы не смогли пройти ни на один из факультетов. \nПопробуйте поступить в следующем году.");

            // Сохранение текста письма в файл
            try {
                String txtFileName = applicant.getName().replaceAll(" ", "_") + ".txt";
                String pdfFileName = applicant.getName().replaceAll(" ", "_") + ".pdf";
                BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + txtFileName));
                PDDocument document = new PDDocument();
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                String[] lines = textOfLetter.toString().split("\n"); // Разделить текст на строки по символу новой строки
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDType0Font font = PDType0Font.load(document, new File("src\\main\\resources\\ofont.ru_Hero.ttf"));
                contentStream.setFont(font, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 600);
                for (String line : lines) {
                    contentStream.showText(line);
                    contentStream.newLineAtOffset(0, -12); // Переход на следующую строку
                }
                contentStream.endText();
                contentStream.close();

                document.save(filepath +pdfFileName); // Сохранение в PDF-файл
                document.close();
                writer.write(textOfLetter.toString());
                writer.close();
                System.out.println("Письмо для " + applicant.getName() + " сохранено в файл " + txtFileName);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        }



    }
}
