import javax.swing.*;

public class Viewer {

    public Viewer(){
        // TODO: 初始化Viewer对象
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        JButton addButton = new JButton("Add Student");
        addButton.setBounds(50, 50, 100, 30);
        frame.add(addButton);
    }

    public void refreshGUI(){
        // TODO: 更新GUI显示
    }

    public void displayStudentInfoInTerminal(Student student){
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Age: " + student.getAge());
        System.out.println("Prof: " + student.getProf());
        System.out.println("Class No: " + student.getClassNo());
        System.out.println("Preference: " + student.getPreference());
    }
}
