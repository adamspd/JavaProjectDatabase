package data;
import data.AbstractBloc;

import java.util.ArrayList;
import java.util.Map;

public class Program {
    private String programName;
    private String programID;
    private ArrayList<Bloc> blocs;

    public Program(String programName, String programID) {
        this.programName = programName;
        this.programID = programID;
        this.blocs = new ArrayList<>();
    }

    public void addBloc(Bloc bloc) {
        blocs.add(bloc);
    }

    public String getProgramName() {
        return programName;
    }

    public String getProgramID() {
        return programID;
    }

    public ArrayList<Bloc> getBlocs() {
        return blocs;
    }

    public ArrayList<Course> getCourse() {
        ArrayList<Course> listCourse = new ArrayList<>();
        for (Bloc bloc : blocs) {
            for(Course course : bloc.getBlocCourses()) {
                listCourse.add(course);
            }
        }
        return listCourse;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(programID + " " + programName );
        builder.append("\n");
        builder.append(blocs.toString());
        builder.append("\n");
        return builder.toString();
    }
}
