package com.frisk.ui.customer;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * User: frisk
 * Date: 2018/8/2
 * Time: 11:03
 */
public class Hidden {
    public static void hiddenCell(JTable table, int column) {
        TableColumn tc = table.getTableHeader().getColumnModel().getColumn(
                column);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setWidth(0);
        tc.setMinWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column)
                .setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(column)
                .setMinWidth(0);
    }
}
