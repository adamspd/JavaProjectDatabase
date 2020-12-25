package data;

import java.util.*;

public class Course extends AbstractTeachingUnit {
    private final Map<String, Double> students;

    public Course(String courseID, String courseName, int credit) {
        super(courseID, courseName, credit);
        this.students = new HashMap<>();
    }

    public String getStudent() {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<String, Double> entry : students.entrySet()) {
            String studentID = entry.getKey();
            double note = entry.getValue();
            builder.append(studentID).append(" a une note de ").append(note);
            builder.append("\n");
        }
        return builder.toString();
    }

    public void addStudent(String id, double note) {
        if (!students.containsKey(id)) {
            students.put(id, note);
        }
    }

    public double getNoteMoyenne() {
        int nbNotes = 0;
        double sommeNotes = 0;
        for(Map.Entry<String, Double> entry : students.entrySet()) {
            if(entry.getValue() >= 0) {
                double noteStudent = entry.getValue();
                sommeNotes += noteStudent;
                nbNotes += 1;
            }
        }
        return (sommeNotes / nbNotes);
    }

    public double getNoteMax() {
        double max = 0;
        for (Double note : students.values()) {
            if (note > max) {
                max = note;
            }
        }
        return max;
    }

    public double getNoteMin() {
        double min = 20;
        for (Double note : students.values()) {
            if (note < min && note >= 0) {
                min = note;
            }
        }
        return min;
    }

    public double getEcartType() {
        double moyenne = this.getNoteMoyenne();
        List<Double> listNote = new ArrayList<Double>(students.values());
        double acc = 0;
        for (Double note : listNote)
        {
            if(note >= 0) {
                double squrDiffToMean = Math.pow(note - moyenne, 2);
                acc += squrDiffToMean;
            }
        }
        double meanOfDiffs = acc / listNote.size();
        return Math.sqrt(meanOfDiffs);
    }


    public String toString()
    {
        return String.format(" Cours ID : %s, courseName: %s, credits : %d" ,
                this.getID(), this.getName(), this.getCredit());
    }
}
