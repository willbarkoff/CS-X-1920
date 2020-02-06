package com.williambarkoff.csx.util;

import javax.swing.*;
import java.awt.*;

public class JOptionPaneExtended {
    public static Object showListInputDialog(Component parentComponent, Object message, String title, Object[] selectionValues) {
        Object defaultOptionPaneUI = UIManager.get("OptionPaneUI");
        UIManager.put("OptionPaneUI", "MyBasicOptionPaneUI");
        Object object = JOptionPane.showInputDialog(parentComponent, message, title, JOptionPane.PLAIN_MESSAGE, null,
                selectionValues, selectionValues[0]);
        UIManager.put("OptionPaneUI", defaultOptionPaneUI);
        return object;
    }
}