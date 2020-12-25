package data;

import java.util.Comparator;

public class StudentComparator {

    StudentComparator() {}

    public Comparator<Student> sortName(boolean ascending) {
        return toAscending(Comparator.comparing(Student::getName), ascending);
    }

    public Comparator<Student> sortSurname(boolean ascending) {
        return toAscending(Comparator.comparing(Student::getSurname), ascending);
    }

    public Comparator<Student> sortProgram(boolean ascending) {
        return toAscending(Comparator.comparing(Student::getProgramName), ascending);
    }

    public Comparator<Student> sortIdentifier(boolean ascending) {
        return toAscending(Comparator.comparing(Student::getIdentifier), ascending);
    }

    public Comparator<Student> sortMoyenneThenName(boolean ascending) {
        return toAscending(Comparator.comparing(Student::moyenne).thenComparing(Student::getName), ascending);
    }

    public Comparator<Student> sortCoursNote(String coursID, boolean ascending) {
        Comparator studentComparator = new Comparator<Student>() {
            public int compare(Student student1, Student student2) {
                double noteS1 = student1.getNoteCours(coursID);
                double noteS2 = student2.getNoteCours(coursID);
                if (noteS1 == noteS2) {
                    return 0;
                } else if (noteS1 > noteS2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
        return toAscending(studentComparator, ascending);
    }

    public Comparator<Student> toAscending(Comparator<Student> comparator, boolean ascending) {
        return ascending ? comparator : comparator.reversed();
    }
}
