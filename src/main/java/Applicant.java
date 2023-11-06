import java.util.List;

public class Applicant {
    private String Name;
    private List<Subject> Subjects;

    public String getName()
    {
        return this.Name;
    }
    public List<Subject> getSubjects()
    {
        return this.Subjects;
    }
    public void setName (String Name)
    {
        this.Name = Name;
    }
    public void setSubjects (List<Subject> Subjects)
    {
        this.Subjects = Subjects;
    }


}
