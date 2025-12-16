public class Controller {
    private Student student;
    private Viewer viewer;

    public Controller(Student student, Viewer viewer){
        this.student = student;
        this.viewer = viewer;
    }

    // 加载数据
    public Student[] loadData(String filePath) {
        return student.readStudentsFromFile(filePath);
    }

    // 保存数据
    public void saveData(String filePath) {
        student.writeStudentsToFile(filePath);
    }

    // 添加学生
    public Student addStudent(String id, String name, int age, String prof, String classNo, String preference) {
        return student.addStudent(id, name, age, prof, classNo, preference);
    }

    // 修改学生
    public Student updateStudent(String id, String name, int age, String prof, String classNo, String preference) {
        return student.modifyStudentInfo(id, name, age, prof, classNo, preference);
    }

    // 删除学生
    public Student deleteStudent(String id) {
        return student.deleteStudent(id);
    }

    // 搜索学生
    public String[] searchStudent(String id) {
        return student.searchStudentById(id);
    }

    // 显示学生信息
    public String displayStudent(String id) {
        return student.displayStudentInfo(id);
    }

}