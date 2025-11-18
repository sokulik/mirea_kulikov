package DOP;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Iterator;

public class DOP_FUNC extends Component {
    public static void showMessageAndReturn(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Информация", JOptionPane.INFORMATION_MESSAGE);
    }
}


