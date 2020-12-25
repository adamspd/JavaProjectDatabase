package data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class AbstractBloc extends AbstractTeachingUnit implements Bloc {
    private String blocName;
    private String blocID;
    private int blocCredits = 0;
    private HashSet<Course> blocCourses;

    public AbstractBloc(String blocName, String blocID) {
        super(blocName, blocID);
        this.blocCourses = new HashSet<>();
        this.blocName = blocName;
        this.blocID = blocID;
    }

    @Override
    public void calculCredits() {
        int res = 0;
        if (this.getClass() == BlocSimple.class || this.getClass() == BlocComposite.class) {
            for (Course course : blocCourses) {
                res += course.getCredit();
            }
        } else {
            List<Course> listCourse = new ArrayList<>(blocCourses);
            if (!listCourse.isEmpty())
            {
                res = listCourse.get(0).getCredit();
            }
        }
        blocCredits = res;
    }

    public double getMoyenne(Student student) {
        double res = 0;
        for(Course course : blocCourses) {
            if (!(student.getNotesMap().get(course.name) == null)) {
                res += student.getNotesMap().get(course.name) * course.getCredit();
            }
        }
        return res / this.blocCredits;
    }

    @Override
    public int hashCode() {
        return blocID.hashCode();
    }

    @Override
    public void addCourses(Course course) {
        if (this.getClass() == BlocSimple.class && this.blocCourses.size() == 1) {
            blocCourses.add(course);
        } else {
            blocCourses.add(course);
        }
        calculCredits();
    }

    public String getBlocName() {
        return blocName;
    }

    public String getBlocID() {
        return blocID;
    }

    public int getBlocCredits() {
        return blocCredits;
    }

    public HashSet<Course> getBlocCourses() {
        return blocCourses;
    }

    @Override
    public String toString() {
        return "blocName='" + blocName + '\'' +
                ", blocID='" + blocID + '\'' +
                ", blocCredits=" + blocCredits +
                ", blocCourses=" + blocCourses + "\n";
    }
}
