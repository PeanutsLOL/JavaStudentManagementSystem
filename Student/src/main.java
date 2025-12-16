import javax.swing.*;

public class main {
    public static void main(String[] args) {
        // 创建并显示GUI
        SwingUtilities.invokeLater(() -> {
            // 创建Student对象（模型）
            Student studentModel = new Student("", "", 0, "", "", "");

            // 创建Viewer对象（视图）
            Viewer viewer = new Viewer();

            // 创建Controller对象（控制器）
            Controller controller = new Controller(studentModel, viewer);

            // 将控制器设置到视图中
            viewer.setController(controller);
        });
    }
}
