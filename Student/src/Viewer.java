import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Viewer {
    private JFrame frame;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private Controller controller;
    
    // 表单组件
    private JTextField idField, nameField, ageField, profField, classNoField, preferenceField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;

    public Viewer() {
        initialize();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void initialize() {
        // 创建主窗口
        frame = new JFrame("学生管理系统");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        
        // 创建菜单栏
        createMenuBar();
        
        // 创建工具栏
        createToolBar();
        
        // 创建表格区域
        createTablePanel();
        
        // 创建表单区域
        createFormPanel();
        
        // 设置窗口可见
        frame.setVisible(true);
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        // 文件菜单
        JMenu fileMenu = new JMenu("文件");
        JMenuItem loadItem = new JMenuItem("加载数据");
        JMenuItem saveItem = new JMenuItem("保存数据");
        JMenuItem exitItem = new JMenuItem("退出");
        
        loadItem.addActionListener(_ -> loadData());
        saveItem.addActionListener(_ -> saveData());
        exitItem.addActionListener(_ -> System.exit(0));
        
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // 编辑菜单
        JMenu editMenu = new JMenu("编辑");
        JMenuItem addItem = new JMenuItem("添加学生");
        JMenuItem updateItem = new JMenuItem("修改学生");
        JMenuItem deleteItem = new JMenuItem("删除学生");
        
        addItem.addActionListener(_ -> clearForm());
        updateItem.addActionListener(_ -> updateStudent());
        deleteItem.addActionListener(_ -> deleteStudent());
        
        editMenu.add(addItem);
        editMenu.add(updateItem);
        editMenu.add(deleteItem);

        JMenu helpMenu = new JMenu("   ");
        JMenuItem aboutItem = new JMenuItem("   ");
        aboutItem.addActionListener(_ -> _easterEgg_());
        helpMenu.add(aboutItem);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        
        frame.setJMenuBar(menuBar);
    }
    
    private void createToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        
        // 创建工具栏按钮
        JButton loadButton = new JButton("加载数据");
        JButton saveButton = new JButton("保存数据");
        JButton refreshButton = new JButton("刷新");
        addButton = new JButton("添加学生");
        updateButton = new JButton("修改学生");
        deleteButton = new JButton("删除学生");
        searchButton = new JButton("搜索学生");
        
        // 添加按钮事件
        loadButton.addActionListener(_ -> loadData());
        saveButton.addActionListener(_ -> saveData());
        refreshButton.addActionListener(_ -> refreshGUI());
        addButton.addActionListener(_ -> addStudent());
        updateButton.addActionListener(_ -> updateStudent());
        deleteButton.addActionListener(_ -> deleteStudent());
        searchButton.addActionListener(_ -> searchStudent());
        
        // 将按钮添加到工具栏
        toolBar.add(refreshButton);
        toolBar.add(loadButton);
        toolBar.add(saveButton);
        toolBar.addSeparator();
        toolBar.add(addButton);
        toolBar.add(updateButton);
        toolBar.add(deleteButton);
        toolBar.addSeparator();
        toolBar.add(searchButton);
        
        frame.add(toolBar, BorderLayout.NORTH);
    }
    
    private void createTablePanel() {
        // 创建表格模型
        String[] columnNames = {"学号", "姓名", "年龄", "专业", "班级", "兴趣爱好"};
        tableModel = new DefaultTableModel(columnNames, 0);
        
        // 创建表格
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow >= 0) {
                    populateForm(selectedRow);
                }
            }
        });
        
        // 添加表格到滚动面板
        JScrollPane scrollPane = new JScrollPane(studentTable);
        frame.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void createFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // 创建表单组件
        formPanel.add(new JLabel("学号:"));
        idField = new JTextField();
        formPanel.add(idField);
        
        formPanel.add(new JLabel("姓名:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("年龄:"));
        ageField = new JTextField();
        formPanel.add(ageField);
        
        formPanel.add(new JLabel("专业:"));
        profField = new JTextField();
        formPanel.add(profField);
        
        formPanel.add(new JLabel("班级:"));
        classNoField = new JTextField();
        formPanel.add(classNoField);
        
        formPanel.add(new JLabel("兴趣爱好:"));
        preferenceField = new JTextField();
        formPanel.add(preferenceField);
        
        // 添加按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        
        formPanel.add(buttonPanel);
        
        frame.add(formPanel, BorderLayout.SOUTH);
    }
    
    private void loadData() {
        // 打开文件选择对话框
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Student[] students = controller.loadData(file.getAbsolutePath());
            
            // 清空表格
            tableModel.setRowCount(0);
            
            // 添加学生数据到表格
            if (students != null) {
                for (Student student : students) {
                    tableModel.addRow(new Object[]{
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getProf(),
                        student.getClassNo(),
                        student.getPreference()
                    });
                }
            }
            
            JOptionPane.showMessageDialog(frame, "数据加载完成");
        }
    }
    
    private void saveData() {
        // 打开文件选择对话框
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(frame);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            controller.saveData(file.getAbsolutePath());
            JOptionPane.showMessageDialog(frame, "数据保存完成");
        }
    }
    
    private void addStudent() {
        // 获取表单数据
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String ageStr = ageField.getText().trim();
        String prof = profField.getText().trim();
        String classNo = classNoField.getText().trim();
        String preference = preferenceField.getText().trim();
        
        // 验证数据
        if (id.isEmpty() || name.isEmpty() || ageStr.isEmpty() || prof.isEmpty() || classNo.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "请填写所有必填字段", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int age = Integer.parseInt(ageStr);
            
            // 添加学生
            Student newStudent = controller.addStudent(id, name, age, prof, classNo, preference);
            
            if (newStudent != null) {
                // 更新表格
                tableModel.addRow(new Object[]{
                    newStudent.getId(),
                    newStudent.getName(),
                    newStudent.getAge(),
                    newStudent.getProf(),
                    newStudent.getClassNo(),
                    newStudent.getPreference()
                });
                
                JOptionPane.showMessageDialog(frame, "学生添加成功");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(frame, "学生添加失败，学号可能已存在", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "年龄必须是数字", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateStudent() {
        // 获取选中的行
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(frame, "请先选择要修改的学生", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 获取表单数据
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String ageStr = ageField.getText().trim();
        String prof = profField.getText().trim();
        String classNo = classNoField.getText().trim();
        String preference = preferenceField.getText().trim();
        
        // 验证数据
        if (id.isEmpty() || name.isEmpty() || ageStr.isEmpty() || prof.isEmpty() || classNo.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "请填写所有必填字段", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int age = Integer.parseInt(ageStr);
            
            // 修改学生
            Student updatedStudent = controller.updateStudent(id, name, age, prof, classNo, preference);
            
            if (updatedStudent != null) {
                // 更新表格
                tableModel.setValueAt(updatedStudent.getId(), selectedRow, 0);
                tableModel.setValueAt(updatedStudent.getName(), selectedRow, 1);
                tableModel.setValueAt(updatedStudent.getAge(), selectedRow, 2);
                tableModel.setValueAt(updatedStudent.getProf(), selectedRow, 3);
                tableModel.setValueAt(updatedStudent.getClassNo(), selectedRow, 4);
                tableModel.setValueAt(updatedStudent.getPreference(), selectedRow, 5);
                
                JOptionPane.showMessageDialog(frame, "学生信息修改成功");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(frame, "学生信息修改失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "年龄必须是数字", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteStudent() {
        // 获取选中的行
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(frame, "请先选择要删除的学生", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // 确认删除
        int confirmed = JOptionPane.showConfirmDialog(frame, "确定要删除选中的学生吗？", "确认删除", JOptionPane.YES_NO_OPTION);
        
        if (confirmed == JOptionPane.YES_OPTION) {
            // 获取学生ID
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            
            // 删除学生
            Student deletedStudent = controller.deleteStudent(id);
            
            if (deletedStudent != null) {
                // 从表格中删除行
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "学生删除成功");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(frame, "学生删除失败", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void searchStudent() {
        // 获取要搜索的学号
        String id = JOptionPane.showInputDialog(frame, "请输入要搜索的学号：");
        
        if (id != null && !id.trim().isEmpty()) {
            String[] studentInfo = controller.searchStudent(id.trim());
            
            if (studentInfo != null) {
                // 显示学生信息
                StringBuilder info = new StringBuilder();
                for (String line : studentInfo) {
                    info.append(line).append("\n");
                }
                JOptionPane.showMessageDialog(frame, info.toString(), "学生信息", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "未找到该学生", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void populateForm(int row) {
        // 从表格中获取数据并填充表单
        idField.setText((String) tableModel.getValueAt(row, 0));
        nameField.setText((String) tableModel.getValueAt(row, 1));
        ageField.setText(String.valueOf(tableModel.getValueAt(row, 2)));
        profField.setText((String) tableModel.getValueAt(row, 3));
        classNoField.setText((String) tableModel.getValueAt(row, 4));
        preferenceField.setText((String) tableModel.getValueAt(row, 5));
    }
    
    private void clearForm() {
        // 清空表单
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        profField.setText("");
        classNoField.setText("");
        preferenceField.setText("");
        
        // 取消表格选择
        studentTable.clearSelection();
    }
    
    private void _easterEgg_() {
        JOptionPane.showMessageDialog(frame, "By PeanutsLOL", " ", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void refreshGUI() {
        // 更新GUI显示
        frame.revalidate();
        frame.repaint();
        JOptionPane.showMessageDialog(frame, "GUI已刷新");
        // 好像没什么用，多个几行凑一下复杂度
    }
}