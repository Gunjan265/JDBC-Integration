public class Main {

    public static void main(String[] args) {

        StudentDAO dao = new StudentDAO();

        dao.createTable();

        Student s1 = new Student(
                "Rahul",
                22,
                "rahul@gmail.com"
        );

        dao.insertStudent(s1);

        dao.getAllStudents();

        dao.updateStudent(
                1,
                "rahul_updated@gmail.com"
        );

        dao.getAllStudents();

        dao.deleteStudent(1);

        dao.getAllStudents();
    }
}