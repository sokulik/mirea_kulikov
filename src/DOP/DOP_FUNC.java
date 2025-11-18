
package DOP;

import javax.swing.*;
import java.awt.*;

public class DOP_FUNC extends Component {
    public static void showMessageAndReturn(Component parent ,String message) {
        JOptionPane.showMessageDialog(parent, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
