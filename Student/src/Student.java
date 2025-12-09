public class Student {
    private String id;
    private String name;
    private int age;
    private String prof;
    private String classNo;
    private String preference;

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

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getProf() {
        return prof;
    }

    public String getClassNo() {
        return classNo;
    }

    public String getPreference() {
        return preference;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nAge: " + age + "\nProf: " + prof + "\nClass No: " + classNo + "\nPreference: " + preference;
    }

    public String searchStudentById(String id){
        /* input: 要搜索的学生ID */
        /* return 学生信息字符串集合 */
        // TODO: 使用displayStudentInfo方法显示学生信息并切分为多个字符串
        try{
            if(!this.id.equals(id)){
                throw new Exception("ID not found.");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        return null;
    }

    public String displayStudentInfo(String id){
        /* input: 要搜索的学生ID */
        /* return 学生信息字符串 */
        System.out.println("Searching for student with ID: " + id);
        try{
            if(!this.id.equals(id)){
                throw new Exception("ID not found.");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        System.out.print(this.toString());
        return this.toString();
    }

    public Student addStudent(String id, String name, int age, String prof, String classNo, String preference){
        /* input: 要添加的学生信息 */
        /* return 新添加的学生对象 */
        // TODO: 检查ID是否已存在 --Done
        try{
            if(!this.id.equals(id)){
                throw new Exception("ID not found.");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        System.out.println("Adding student with ID: " + id);
        return new Student(id, name, age, prof, classNo, preference);
    }

    public Student modifyStudentInfo(String id, String name, int age, String prof, String classNo, String preference){
        /* input: 要修改的学生ID和新的学生信息 */
        /* return 修改后的学生对象 */
        // TODO: 检查ID是否存在 --Done
        try{
            if(!this.id.equals(id)){
                throw new Exception("ID not found.");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        System.out.println("Modifying student with ID: " + id);
        this.name = name;
        this.age = age;
        this.prof = prof;
        this.classNo = classNo;
        this.preference = preference;
        return this;
    }

    public Student deleteStudent(String id){
        /* input: 要删除的学生ID */
        /* return: 删除的学生对象 */
        // TODO: 检查ID是否存在 --Done
        try{
            if(!this.id.equals(id)){
                throw new Exception("ID not found.");
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
        System.out.println("Deleting student with ID: " + id);
        this.id = null;
        this.name = null;
        this.age = 0;
        this.prof = null;
        this.classNo = null;
        this.preference = null;
        return this;
    }

    public Student[] readStudentsFromFile(String fileName){
        /* input: 要读取的文件名 */
        /* return 学生对象数组 */
        // TODO: 从文件中读取学生信息
        return null;
    }

    public void writeStudentsToFile(String fileName){
        /* input: 要写入的文件名 */
        /* return: 无 */
        // TODO: 将学生信息写入文件

    }
}
