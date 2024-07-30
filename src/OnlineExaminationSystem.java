import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OnlineExaminationSystem {

    public static void main(String[] args) {
        new LoginFrame();
    }
}

class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Login");
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Dummy authentication
        if (username.equals("user") && password.equals("pass")) {
            new MainFrame();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials");
        }
    }
}

class MainFrame extends JFrame implements ActionListener {
    private JButton updateProfileButton;
    private JButton startExamButton;
    private JButton logoutButton;

    public MainFrame() {
        setTitle("Online Examination System");
        setLayout(new GridLayout(3, 1));

        updateProfileButton = new JButton("Update Profile");
        updateProfileButton.addActionListener(this);
        add(updateProfileButton);

        startExamButton = new JButton("Start Exam");
        startExamButton.addActionListener(this);
        add(startExamButton);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        add(logoutButton);

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateProfileButton) {
            new UpdateProfileFrame();
        } else if (e.getSource() == startExamButton) {
            new ExamFrame();
        } else if (e.getSource() == logoutButton) {
            new LoginFrame();
            dispose();
        }
    }
}

class UpdateProfileFrame extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton updateButton;

    public UpdateProfileFrame() {
        setTitle("Update Profile");
        setLayout(new GridLayout(3, 2));

        add(new JLabel("New Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("New Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        add(updateButton);

        setSize(300, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update profile logic (not implemented)
        JOptionPane.showMessageDialog(this, "Profile updated");
        dispose();
    }
}

class ExamFrame extends JFrame implements ActionListener {
    private JRadioButton[] options;
    private JButton nextButton;
    private JButton submitButton;
    private JLabel timerLabel;
    private Timer timer;
    private int timeRemaining = 300; // 5 minutes
    private int currentQuestion = 0;
    private String[][] questions = {
            {"Question 1?", "A", "B", "C", "D"},
            {"Question 2?", "A", "B", "C", "D"}
    };

    public ExamFrame() {
        setTitle("Exam");
        setLayout(new GridLayout(6, 1));

        timerLabel = new JLabel("Time remaining: 05:00");
        add(timerLabel);

        ButtonGroup group = new ButtonGroup();
        options = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        startTimer();
        loadQuestion();
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
                int minutes = timeRemaining / 60;
                int seconds = timeRemaining % 60;
                timerLabel.setText(String.format("Time remaining: %02d:%02d", minutes, seconds));
                if (timeRemaining <= 0) {
                    timer.stop();
                    submitExam();
                }
            }
        });
        timer.start();
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            String[] question = questions[currentQuestion];
            setTitle(question[0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(question[i + 1]);
            }
        } else {
            submitExam();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            currentQuestion++;
            loadQuestion();
        } else if (e.getSource() == submitButton) {
            submitExam();
        }
    }

    private void submitExam() {
        JOptionPane.showMessageDialog(this, "Exam submitted");
        new MainFrame();
        dispose();
    }
}
