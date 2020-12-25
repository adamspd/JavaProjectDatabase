package data;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private Map<String, Double> notesMap;
    private final String identifier;
    private final String name;
    private final String surname;
    private Program program;

    public Student(String identifier, String name, String surname, Program program) {
        this.identifier = identifier;
        this.name = name;
        this.surname = surname;
        this.program = program;
        this.notesMap = new HashMap<>();
    }

    public boolean equals(Object obj){
        boolean areEqual = false;
        if (obj instanceof Student) {
            Student student = (Student) obj;
            areEqual = identifier.equals(student.identifier);
        }
        return areEqual;
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    public double moyenne() {
        double res = 0;
        int div = 0;
        for (Bloc bloc : program.getBlocs()) {
            for (Course cours : bloc.getBlocCourses()) {
                if (!(notesMap.get(cours.name) == null)) {
                    res += notesMap.get(cours.name) * cours.getCredit();
                    div += cours.getCredit();
                }
            }
        }
        return res / div;
    }

    public double getNoteCours(String cours) {
        return notesMap.get(cours);
    }

    public String toString()
    {
        return String.format("\"%s\",\"%s\",\"%s\",", identifier, name, surname);
    }

    public Map<String, Double> getNotesMap() {
        return notesMap;
    }

    public Program getProgram() {
        return program;
    }

    public String getProgramName() {
        return program.getProgramName();
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public String getIdentifier() {
        return identifier;
    }


    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }


}
