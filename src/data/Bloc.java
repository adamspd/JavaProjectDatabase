package data;

import java.util.ArrayList;
import java.util.HashSet;

public interface Bloc {
    public void addCourses(Course course);
    public void calculCredits();
    public String getBlocName();
    public String getBlocID();
    public int getBlocCredits();
    public HashSet<Course> getBlocCourses();
    public double getMoyenne(Student student);
}
