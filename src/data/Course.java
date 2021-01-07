package data;

import java.util.List;
import java.util.Objects;

public class Course extends AbstractTeachingUnit {

    public Course(String courseName, String courseID, int credit) {
        super(courseName, courseID, credit);
    }


    public double getMoyenne(Student student) {
        double note = -1;
        if(!hasStudent(student)) {return -2;}
        if(hasStudent(student) && student.getNotesMap().get(this.getID()) >= 0) {
            note = student.getNotesMap().get(this.getID());
        }
        return note;
    }

    public double getNoteMoyenne(List<Student> studentList) {
        int nbNotes = 0;
        double sommeNotes = 0;
        for(Student student : studentList) {
            if(hasStudent(student) && student.getNotesMap().get(this.getID()) >= 0) {
                double noteStudent = student.getNotesMap().get(this.getID());
                sommeNotes += noteStudent;
                nbNotes += 1;
            }
        }
        return (sommeNotes / nbNotes);
    }

    public boolean hasStudent(Student student) {
        return !(student.getNotesMap().get(this.getID()) == null);
    }

    public double getNoteMax(List<Student> studentList) {
        double max = 0;
        for(Student student : studentList) {
            if(hasStudent(student) && student.getNotesMap().get(this.getID()) > max) {
                max = student.getNotesMap().get(this.getID());
            }
        }
        return max;
    }

    public double getNoteMin(List<Student> studentList) {
        double max = 20;
        for(Student student : studentList) {
            if(hasStudent(student) && student.getNotesMap().get(this.getID()) < max && student.getNotesMap().get(this.getID()) >= 0) {
                max = student.getNotesMap().get(this.getID());
            }
        }
        return max;
    }

    public double getEcartType(List<Student> studentList) {
        double moyenne = this.getNoteMoyenne(studentList);
        double nombreNote = 0;
        double acc = 0;
        for (Student student : studentList)
        {
            if(hasStudent(student) && student.getNotesMap().get(this.getID()) >= 0) {
                double note = student.getNotesMap().get(this.getID());
                nombreNote += 1;
                double squrDiffToMean = Math.pow(note - moyenne, 2);
                acc += squrDiffToMean;
            }
        }
        double meanOfDiffs = acc / nombreNote;
        return Math.sqrt(meanOfDiffs);
    }
    @Override
    public boolean equals(Object obj) {
        boolean areEqual = false;
        if (obj instanceof Course course){
            areEqual = Objects.equals(course.getID(),course.getName());
        }
        return areEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getID(),this.getName());
    }

    public String toString()
    {
        return String.format(" Cours ID : %s, courseName: %s, credits : %d" ,
                this.getID(), this.getName(), this.getCredit());
    }

}