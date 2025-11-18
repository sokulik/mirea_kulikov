package Practices.Practice4;

import Practices.Practice4.Classes.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Task4 extends JFrame {
    private JFrame mainMenuFrame;
    private JTextArea resultArea;

    public Task4(JFrame mainMenuFrame) {
        super("Practice 4.4: Компьютерная техника");
        this.mainMenuFrame = mainMenuFrame;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
        demonstrateComputer();
    }

    private void initComponents() {
        resultArea = new JTextArea(15, 50);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(248, 249, 250));
    }

    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель с кнопками
        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.NORTH);

        // Центральная панель с результатами
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Спецификации компьютера"));
        add(scrollPane, BorderLayout.CENTER);

        // Панель снизу с кнопкой назад
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(240, 240, 240));

        JButton demoButton = new JButton("💻 Демонстрация компьютера");
        demoButton.setBackground(new Color(70, 130, 180));
        demoButton.setForeground(Color.WHITE);
        demoButton.addActionListener(e -> demonstrateComputer());

        JButton customButton = new JButton("⚙️ Создать свой компьютер");
        customButton.setBackground(new Color(40, 167, 69));
        customButton.setForeground(Color.WHITE);
        customButton.addActionListener(e -> createCustomComputer());

        controlPanel.add(demoButton);
        controlPanel.add(customButton);

        return controlPanel;
    }

    private void demonstrateComputer() {
        StringBuilder output = new StringBuilder();
        output.append("=== Демонстрация компьютерной техники ===\n\n");

        // Создаем компьютер как в оригинальном Task4
        Processor processor = new Processor(ProcessorBrand.INTEL, 3.5, 8);
        Memory memory = new Memory(MemoryBrand.SAMSUNG, 16, "DDR4");
        Monitor monitor = new Monitor(MonitorBrand.DELL, 27.0, "1920x1080");
        Computer computer = new Computer(ComputerBrand.LENOVO, processor, memory, monitor);

        output.append("🖥️ Создан компьютер со следующими характеристиками:\n\n");
        computer.displaySpecifications();

        output.append("\n\n=== Дополнительные примеры ===\n\n");

        // Дополнительный пример 1
        Processor processor2 = new Processor(ProcessorBrand.AMD, 4.2, 12);
        Memory memory2 = new Memory(MemoryBrand.CORSAIR, 32, "DDR5");
        Monitor monitor2 = new Monitor(MonitorBrand.LG, 32.0, "2560x1440");
        Computer computer2 = new Computer(ComputerBrand.ASUS, processor2, memory2, monitor2);

        output.append("💪 Мощный игровой компьютер:\n");
        computer2.displaySpecifications();

        output.append("\n");

        // Дополнительный пример 2
        Processor processor3 = new Processor(ProcessorBrand.APPLE_SILICON, 3.2, 10);
        Memory memory3 = new Memory(MemoryBrand.CRUCIAL, 8, "LPDDR4");
        Monitor monitor3 = new Monitor(MonitorBrand.SAMSUNG, 24.0, "1920x1080");
        Computer computer3 = new Computer(ComputerBrand.APPLE, processor3, memory3, monitor3);

        output.append("🍎 Компьютер Apple:\n");
        computer3.displaySpecifications();

        resultArea.setText(output.toString());
    }

    private void createCustomComputer() {
        // Создаем диалог для настройки компьютера
        JDialog customDialog = new JDialog(this, "Создание компьютера", true);
        customDialog.setLayout(new GridLayout(6, 2, 10, 10));
        customDialog.setSize(400, 300);
        customDialog.setLocationRelativeTo(this);

        // Компоненты для выбора характеристик
        JComboBox<ComputerBrand> computerBrandCombo = new JComboBox<>(ComputerBrand.values());
        JComboBox<ProcessorBrand> processorBrandCombo = new JComboBox<>(ProcessorBrand.values());
        JTextField processorSpeedField = new JTextField("3.5");
        JTextField processorCoresField = new JTextField("8");
        JComboBox<MemoryBrand> memoryBrandCombo = new JComboBox<>(MemoryBrand.values());
        JTextField memorySizeField = new JTextField("16");
        JTextField memoryTypeField = new JTextField("DDR4");
        JComboBox<MonitorBrand> monitorBrandCombo = new JComboBox<>(MonitorBrand.values());
        JTextField monitorSizeField = new JTextField("27.0");
        JTextField monitorResolutionField = new JTextField("1920x1080");

        customDialog.add(new JLabel("Бренд компьютера:"));
        customDialog.add(computerBrandCombo);
        customDialog.add(new JLabel("Бренд процессора:"));
        customDialog.add(processorBrandCombo);
        customDialog.add(new JLabel("Частота процессора (GHz):"));
        customDialog.add(processorSpeedField);
        customDialog.add(new JLabel("Количество ядер:"));
        customDialog.add(processorCoresField);
        customDialog.add(new JLabel("Бренд памяти:"));
        customDialog.add(memoryBrandCombo);
        customDialog.add(new JLabel("Размер памяти (GB):"));
        customDialog.add(memorySizeField);
        customDialog.add(new JLabel("Тип памяти:"));
        customDialog.add(memoryTypeField);
        customDialog.add(new JLabel("Бренд монитора:"));
        customDialog.add(monitorBrandCombo);
        customDialog.add(new JLabel("Размер монитора (дюймы):"));
        customDialog.add(monitorSizeField);
        customDialog.add(new JLabel("Разрешение:"));
        customDialog.add(monitorResolutionField);

        JButton createButton = new JButton("Создать");
        createButton.setBackground(new Color(70, 130, 180));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> {
            try {
                ComputerBrand computerBrand = (ComputerBrand) computerBrandCombo.getSelectedItem();
                ProcessorBrand processorBrand = (ProcessorBrand) processorBrandCombo.getSelectedItem();
                double processorSpeed = Double.parseDouble(processorSpeedField.getText());
                int processorCores = Integer.parseInt(processorCoresField.getText());
                MemoryBrand memoryBrand = (MemoryBrand) memoryBrandCombo.getSelectedItem();
                int memorySize = Integer.parseInt(memorySizeField.getText());
                String memoryType = memoryTypeField.getText();
                MonitorBrand monitorBrand = (MonitorBrand) monitorBrandCombo.getSelectedItem();
                double monitorSize = Double.parseDouble(monitorSizeField.getText());
                String monitorResolution = monitorResolutionField.getText();

                Processor processor = new Processor(processorBrand, processorSpeed, processorCores);
                Memory memory = new Memory(memoryBrand, memorySize, memoryType);
                Monitor monitor = new Monitor(monitorBrand, monitorSize, monitorResolution);
                Computer computer = new Computer(computerBrand, processor, memory, monitor);

                StringBuilder output = new StringBuilder();
                output.append("=== Ваш собственный компьютер ===\n\n");
                computer.displaySpecifications();

                resultArea.setText(output.toString());
                customDialog.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(customDialog,
                        "Пожалуйста, введите корректные числовые значения",
                        "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        customDialog.add(new JLabel(""));
        customDialog.add(buttonPanel);

        customDialog.setVisible(true);
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 240, 240));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = createBackButton();
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(new Color(240, 240, 240));
        leftPanel.add(backButton);

        JLabel infoLabel = new JLabel("Перечисления: ComputerBrand, ProcessorBrand, MemoryBrand, MonitorBrand");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        infoLabel.setForeground(Color.DARK_GRAY);

        bottomPanel.add(leftPanel, BorderLayout.WEST);
        bottomPanel.add(infoLabel, BorderLayout.EAST);

        return bottomPanel;
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("← Назад к меню");
        backButton.setFont(new Font("Arial", Font.BOLD, 12));
        backButton.setBackground(new Color(108, 117, 125));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 90, 100), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(130, 140, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButton.setBackground(new Color(108, 117, 125));
            }
        });

        backButton.addActionListener(e -> returnToMainMenu());
        return backButton;
    }

    private void addListeners() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnToMainMenu();
            }
        });
    }

    private void returnToMainMenu() {
        dispose();
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(true);
            mainMenuFrame.toFront();
        }
    }

    public static void task4(JFrame mainMenuFrame) {
        if (mainMenuFrame != null) {
            mainMenuFrame.setVisible(false);
        }

        SwingUtilities.invokeLater(() -> new Task4(mainMenuFrame).setVisible(true));
    }
}