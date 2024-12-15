package com.itheima.game;

import com.itheima.domain.User;
import com.itheima.util.CodeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
//登录界面
public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> allUsers = new ArrayList<>();

    static {
        allUsers.add(new User("zhangsan", "123"));
        allUsers.add(new User("lisi", "1234"));
    }

    JButton login = new JButton();
    JButton register = new JButton();
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();
    JLabel rightCode = new JLabel();

    public LoginJFrame() {
        initJFrame();
        initView();
        this.setVisible(true);
    }

    public void initView() {
        addLabel("用户名", 140, 55);
        username.setBounds(223, 46, 200, 30);
        this.getContentPane().add(username);

        addLabel("密码", 197, 95);
        password.setBounds(263, 87, 160, 30);
        this.getContentPane().add(password);

        addLabel("验证码", 215, 142);
        code.setBounds(291, 133, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        rightCode.setForeground(Color.RED);
        rightCode.setFont(new Font(null, Font.BOLD, 15));
        rightCode.setText(codeStr);
        rightCode.addMouseListener(this);
        rightCode.setBounds(400, 133, 100, 30);
        this.getContentPane().add(rightCode);

        setupButton(login, "image\\login\\登录按钮.png", 123, 310);
        setupButton(register, "image\\login\\注册按钮.png", 256, 310);

        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 633, 423);
        this.getContentPane().add(background);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setFont(new Font(null, Font.BOLD, 16));
        label.setBounds(x, y, 55, 22);
        this.getContentPane().add(label);
    }

    private void setupButton(JButton button, String iconPath, int x, int y) {
        button.setBounds(x, y, 128, 47);
        button.setIcon(new ImageIcon(iconPath));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.addMouseListener(this);
        this.getContentPane().add(button);
    }

    public void initJFrame() {
        this.setSize(633, 423);
        this.setTitle("斗地主游戏 V1.0登录");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == login) {
            handleLogin();
        } else if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
        } else if (e.getSource() == rightCode) {
            resetCode();
        }
    }

    private void handleLogin() {
        String usernameInput = username.getText().trim();
        String passwordInput = new String(password.getPassword()).trim();
        String codeInput = code.getText().trim();

        if (usernameInput.isEmpty()) {
            showJDialog("用户名不能为空");
            return;
        }
        if (passwordInput.isEmpty()) {
            showJDialog("密码不能为空");
            return;
        }
        if (codeInput.isEmpty()) {
            showJDialog("验证码不能为空");
            return;
        }
        if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
            showJDialog("验证码输入错误");
            return;
        }

        User userInfo = new User(usernameInput, passwordInput);
        if (allUsers.contains(userInfo)) {
            this.setVisible(false);
            new GameJFrame();
        } else {
            showJDialog("用户名或密码错误");
        }
    }

    private void resetCode() {
        String code = CodeUtil.getCode();
        rightCode.setText(code);
    }

    public void showJDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        JLabel warning = new JLabel(content);
        warning.setHorizontalAlignment(SwingConstants.CENTER);
        jDialog.getContentPane().add(warning);
        jDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
