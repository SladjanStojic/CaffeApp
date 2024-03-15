package Model;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

 public class CustomRowRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

       
        if (!isSelected && !hasFocus) {
          
            int stanjeIndex = 2;
            int stanje = Integer.parseInt(table.getValueAt(row, stanjeIndex).toString());
            if (stanje <= 0) {
                cellComponent.setForeground(Color.red); 
                table.setValueAt(false, row, 4); 
            } else {
                cellComponent.setForeground(table.getForeground());
                table.setValueAt(true, row, 4);
            }
        }

        return cellComponent;
    }
}
