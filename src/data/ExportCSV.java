package data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExportCSV {
    public void toFichier(List<Student> listStudent, String programName) {
        try {
            FileWriter fw = new FileWriter("fic.csv");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            XMLReader xmlReader = new XMLReader();
            Program program = xmlReader.getMapProgram().get(programName);
            pw.printf("\"N° Etudiant\",\"Name\",\"Surname\",");
            pw.printf("\"%s - %s\",", program.getProgramID(), program.getProgramName());
            ArrayList<Course> listCourseProgram = program.getCourse();
               for(Course cours : listCourseProgram) {
                pw.printf("\"%s - %s\",", cours.getName(), cours.getID());
            }
            for(Bloc bloc : program.getBlocs()) {
                if (!(bloc.getClass() == BlocSimple.class)) {
                    pw.printf("\"%s - %s\",", bloc.getBlocName(), bloc.getBlocID());
                }
            }
            pw.println();
            for (int i = 0; i < listStudent.size(); i++) {
                String programStudent = listStudent.get(i).getProgram().getProgramName();
                if (programStudent.equals(programName)) {
                    pw.printf("%s", listStudent.get(i));
                    pw.printf("\"%.2f\",", listStudent.get(i).moyenne());
                    for (Course cours : listCourseProgram) {
                        if (!(listStudent.get(i).getNotesMap().get(cours.name) == null)) {
                            double note = listStudent.get(i).getNotesMap().get(cours.name);
                            pw.printf("\"%.2f\",", note);
                        } else {
                            pw.printf("\"\",");
                        }
                    }
                    for(Bloc bloc : program.getBlocs()) {
                        if(!(bloc.getClass() == BlocSimple.class)) {
                            pw.printf("\".2f\",", bloc.getMoyenne(listStudent.get(i))); }
                    }
                    pw.println();
                }
            }
            StringBuilder noteMaxSB = new StringBuilder();
            StringBuilder noteMinSB = new StringBuilder();
            StringBuilder noteMoyenneSB = new StringBuilder();
            StringBuilder ecartTypeSB = new StringBuilder();
            for (Course course : listCourseProgram) {
                noteMaxSB.append(String.format("\"%.2f\",", course.getNoteMax()));
                noteMinSB.append(String.format("\"%.2f\",", course.getNoteMin()));
                noteMoyenneSB.append(String.format("\"%.2f\",", course.getNoteMoyenne()));
                ecartTypeSB.append(String.format("\"%.2f\",", course.getEcartType()));
            }
            pw.printf(("\"Note Maximum\",\"\",\"\",\"\","));
            pw.println(noteMaxSB);
            pw.printf(("\"Note Minimum\",\"\",\"\",\"\","));
            pw.println(noteMinSB);
            pw.printf(("\"Note Moyenne\",\"\",\"\",\"\","));
            pw.println(noteMoyenneSB);
            pw.printf(("\"Ecart-Type\",\"\",\"\",\"\","));
            pw.println(ecartTypeSB);
            pw.close();
        } catch (IOException ex) {
            System.err.println("Erreur sur le fichier");
        }
    }
}