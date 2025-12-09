public class Controller {
    private Student student;
    private Viewer viewer;

    public Controller(Student student, Viewer viewer){
        this.student = student;
        this.viewer = viewer;
    }

    public void setStudentId(String id){
        student.setId(id);
    }
}
