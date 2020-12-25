package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static data.StudentComparator.*;

public class Test {
    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader();
        xmlReader.initMap();
        List<Student> list = new ArrayList<Student>(xmlReader.getMapStudent().values());
        ExportCSV export = new ExportCSV();

        Comparator<Student> compar = new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getName().compareTo(student2.getName());
            }
        };
        StudentComparator studentcompa = new StudentComparator();
        Comparator<Student> comparator = studentcompa.sortCoursNote("SLUIN601", false);

        Collections.sort(list, comparator);
        export.toFichier(list, "SLINF3 180");
        System.out.println(list.get(0).moyenne());
        System.out.println(list.get(0));
        //System.out.println(list.get(0).getNotesMap().get("SLUIN502"));
        System.out.println(xmlReader.getMapProgram().get(0));
        System.out.println(list.get(0).getNotesMap().get("SLUIN601"));
        double nombre = -0.32;
        System.out.println((int) nombre);

    }
}
