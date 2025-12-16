import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private String name;
    private int age;
    private String prof;
    private String classNo;
    private String preference;
    
    // 静态集合，用于存储所有学生对象
    private static List<Student> studentList = new ArrayList<>();

    // 构造方法
    public Student(String id, String name, int age, String prof, String classNo, String preference){
        this.id = id;
        this.name = name;
        this.age = age;
        this.prof = prof;
        this.classNo = classNo;
        this.preference = preference;
    }

    // Getter和Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nAge: " + age + "\nProf: " + prof + "\nClass No: " + classNo + "\nPreference: " + preference;
    }

    public Student[] readStudentsFromFile(String fileName) {
        /* input: 要读取的文件名 */
        /* return 学生对象数组 */
        // 从文件中读取学生信息
        List<Student> students = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                // 文件中每行的格式为：id,name,age,prof,classNo,preference
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());
                    String prof = parts[3].trim();
                    String classNo = parts[4].trim();
                    String preference = parts[5].trim();
                    
                    students.add(new Student(id, name, age, prof, classNo, preference));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing age: " + e.getMessage());
        }
        
        // 更新静态集合
        studentList = students;
        
        return students.toArray(new Student[0]);
    }

    public void writeStudentsToFile(String fileName) {
        /* input: 要写入的文件名 */
        /* return: 无 */
        // 将学生信息写入文件
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : studentList) {
                // 格式：id,name,age,prof,classNo,preference
                String line = student.id + "," + student.name + "," + student.age + "," + 
                              student.prof + "," + student.classNo + "," + student.preference;
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public String[] searchStudentById(String id) {
        /* input: 要搜索的学生ID */
        /* return 学生信息字符串集合 */
        // 使用displayStudentInfo方法显示学生信息并切分为多个字符串
        String info = displayStudentInfo(id);
        if (info != null) {
            // 将学生信息按行分割为字符串数组
            return info.split("\\n");
        }
        return null;
    }

    public String displayStudentInfo(String id) {
        /* input: 要搜索的学生ID */
        /* return 学生信息字符串 */
        System.out.println("Searching for student with ID: " + id);
        
        for (Student student : studentList) {
            if (student.id.equals(id)) {
                System.out.println(student);
                return student.toString();
            }
        }
        
        System.out.println("Error: ID not found.");
        return null;
    }

    public Student addStudent(String id, String name, int age, String prof, String classNo, String preference) {
        /* input: 要添加的学生信息 */
        /* return 新添加的学生对象 */
        // 检查ID是否已存在
        for (Student student : studentList) {
            if (student.id.equals(id)) {
                System.out.println("Error: Student with ID " + id + " already exists.");
                return null;
            }
        }
        
        System.out.println("Adding student with ID: " + id);
        Student newStudent = new Student(id, name, age, prof, classNo, preference);
        System.out.println(newStudent);
        studentList.add(newStudent);
        return newStudent;
    }

    public Student modifyStudentInfo(String id, String name, int age, String prof, String classNo, String preference) {
        /* input: 要修改的学生ID和新的学生信息 */
        /* return 修改后的学生对象 */
        for (Student student : studentList) {
            if (student.id.equals(id)) {
                System.out.println("Modifying student with ID: " + id);
                student.name = name;
                student.age = age;
                student.prof = prof;
                student.classNo = classNo;
                student.preference = preference;
                return student;
            }
        }
        
        System.out.println("Error: ID not found.");
        return null;
    }

    public Student deleteStudent(String id) {
        /* input: 要删除的学生ID */
        /* return: 删除的学生对象 */
        for (Student student : studentList) {
            if (student.id.equals(id)) {
                System.out.println("Deleting student with ID: " + id);
                studentList.remove(student);
                return student;
            }
        }
        
        System.out.println("Error: ID not found.");
        return null;
    }
}