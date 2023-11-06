import java.util.List;

public class Faculty {
    private String Name;
    private Integer Score;
    private List<Subject> Subjects;

    public String getName()
    {
        return this.Name;
    }
    public Integer getScore()
    {
        return this.Score;
    }
    public List<Subject> getSubjects()
    {
        return this.Subjects;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }
    public void setScore(Integer Score)
    {
        this.Score = Score;
    }
    public void setNeedSubjects(List<Subject> Subjects)
    {
        this.Subjects = Subjects;
    }
}
