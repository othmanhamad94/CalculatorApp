import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculatorApp extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public AdvancedCalculatorApp() {
        setTitle("آلة حاسبة متقدمة");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // إنشاء CardLayout لإدارة الواجهات
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // إضافة واجهات الآلة الحاسبة
        cardPanel.add(createWelcomePanel(), "Welcome");
        cardPanel.add(createSimpleCalculatorPanel(), "SimpleCalculator");
        cardPanel.add(createProfessionalCalculatorPanel(), "ProfessionalCalculator");

        // عرض الواجهة الأولى (الترحيب)
        cardLayout.show(cardPanel, "Welcome");

        add(cardPanel);
    }

    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("مرحبًا! اختر نوع الآلة الحاسبة:", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton simpleCalculatorButton = new JButton("آلة حاسبة بسيطة");
        JButton professionalCalculatorButton = new JButton("آلة حاسبة احترافية");

        simpleCalculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "SimpleCalculator");
            }
        });

        professionalCalculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "ProfessionalCalculator");
            }
        });

        buttonPanel.add(simpleCalculatorButton);
        buttonPanel.add(professionalCalculatorButton);
        welcomePanel.add(buttonPanel, BorderLayout.SOUTH);

        return welcomePanel;
    }

    private JPanel createSimpleCalculatorPanel() {
        JPanel simpleCalculatorPanel = new JPanel();
        simpleCalculatorPanel.setLayout(new GridLayout(5, 2));

        JTextField num1Field = new JTextField();
        JTextField num2Field = new JTextField();
        JTextField resultField = new JTextField();
        resultField.setEditable(false);

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton clearButton = new JButton("مسح");
        JButton backButton = new JButton("العودة");

        simpleCalculatorPanel.add(new JLabel("الرقم الأول:"));
        simpleCalculatorPanel.add(num1Field);
        simpleCalculatorPanel.add(new JLabel("الرقم الثاني:"));
        simpleCalculatorPanel.add(num2Field);
        simpleCalculatorPanel.add(new JLabel("النتيجة:"));
        simpleCalculatorPanel.add(resultField);
        simpleCalculatorPanel.add(addButton);
        simpleCalculatorPanel.add(subtractButton);
        simpleCalculatorPanel.add(multiplyButton);
        simpleCalculatorPanel.add(divideButton);
        simpleCalculatorPanel.add(clearButton);
        simpleCalculatorPanel.add(backButton);

        // إضافة معالجات الأحداث للأزرار
        ActionListener operationListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(num1Field.getText());
                    double num2 = Double.parseDouble(num2Field.getText());
                    double result = 0;

                    if (e.getSource() == addButton) {
                        result = num1 + num2;
                    } else if (e.getSource() == subtractButton) {
                        result = num1 - num2;
                    } else if (e.getSource() == multiplyButton) {
                        result = num1 * num2;
                    } else if (e.getSource() == divideButton) {
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            resultField.setText("خطأ: القسمة على صفر");
                            return;
                        }
                    }

                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    resultField.setText("خطأ: إدخال غير صحيح");
                }
            }
        };

        addButton.addActionListener(operationListener);
        subtractButton.addActionListener(operationListener);
        multiplyButton.addActionListener(operationListener);
        divideButton.addActionListener(operationListener);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num1Field.setText("");
                num2Field.setText("");
                resultField.setText("");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Welcome");
            }
        });

        return simpleCalculatorPanel;
    }

    private JPanel createProfessionalCalculatorPanel() {
        JPanel professionalCalculatorPanel = new JPanel();
        professionalCalculatorPanel.setLayout(new GridLayout(6, 2));

        JTextField numField = new JTextField();
        JTextField resultField = new JTextField();
        resultField.setEditable(false);

        JButton sqrtButton = new JButton("√");
        JButton squareButton = new JButton("x²");
        JButton sinButton = new JButton("sin");
        JButton cosButton = new JButton("cos");
        JButton tanButton = new JButton("tan");
        JButton clearButton = new JButton("مسح");
        JButton backButton = new JButton("العودة");

        professionalCalculatorPanel.add(new JLabel("الرقم:"));
        professionalCalculatorPanel.add(numField);
        professionalCalculatorPanel.add(new JLabel("النتيجة:"));
        professionalCalculatorPanel.add(resultField);
        professionalCalculatorPanel.add(sqrtButton);
        professionalCalculatorPanel.add(squareButton);
        professionalCalculatorPanel.add(sinButton);
        professionalCalculatorPanel.add(cosButton);
        professionalCalculatorPanel.add(tanButton);
        professionalCalculatorPanel.add(clearButton);
        professionalCalculatorPanel.add(backButton);

        // إضافة معالجات الأحداث للأزرار
        ActionListener operationListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num = Double.parseDouble(numField.getText());
                    double result = 0;

                    if (e.getSource() == sqrtButton) {
                        if (num >= 0) {
                            result = Math.sqrt(num);
                        } else {
                            resultField.setText("خطأ: جذر عدد سالب");
                            return;
                        }
                    } else if (e.getSource() == squareButton) {
                        result = num * num;
                    } else if (e.getSource() == sinButton) {
                        result = Math.sin(Math.toRadians(num));
                    } else if (e.getSource() == cosButton) {
                        result = Math.cos(Math.toRadians(num));
                    } else if (e.getSource() == tanButton) {
                        result = Math.tan(Math.toRadians(num));
                    }

                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    resultField.setText("خطأ: إدخال غير صحيح");
                }
            }
        };

        sqrtButton.addActionListener(operationListener);
        squareButton.addActionListener(operationListener);
        sinButton.addActionListener(operationListener);
        cosButton.addActionListener(operationListener);
        tanButton.addActionListener(operationListener);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numField.setText("");
                resultField.setText("");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Welcome");
            }
        });

        return professionalCalculatorPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdvancedCalculatorApp().setVisible(true);
            }
        });
    }
}