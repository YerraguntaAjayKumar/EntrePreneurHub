package ui.components;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Reusable Table Component
 * Displays data in a formatted JTable with scroll support
 */
public class TableView extends JPanel {
    
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    
    public TableView(String[] columnNames) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        // Create table model with columns
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        
        // Create table
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(41, 128, 185));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(52, 152, 219));
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        
        // Add scroll pane
        scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(scrollPane, BorderLayout.CENTER);
    }
    
    /**
     * Add a row of data to the table
     * @param rowData Array of data for each column
     */
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }
    
    /**
     * Clear all rows from the table
     */
    public void clearTable() {
        tableModel.setRowCount(0);
    }
    
    /**
     * Get the selected row index
     * @return Selected row index or -1 if no selection
     */
    public int getSelectedRow() {
        return table.getSelectedRow();
    }
    
    /**
     * Get value from a specific cell
     * @param row Row index
     * @param column Column index
     * @return Cell value
     */
    public Object getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }
    
    /**
     * Get all data from a specific column
     * @param columnIndex Column index
     * @return Array of values in that column
     */
    public Object[] getColumnData(int columnIndex) {
        Object[] data = new Object[tableModel.getRowCount()];
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            data[i] = tableModel.getValueAt(i, columnIndex);
        }
        return data;
    }
    
    /**
     * Get row count
     * @return Number of rows in table
     */
    public int getRowCount() {
        return tableModel.getRowCount();
    }
    
    /**
     * Update a specific cell
     * @param row Row index
     * @param column Column index
     * @param value New value
     */
    public void setValueAt(Object value, int row, int column) {
        tableModel.setValueAt(value, row, column);
    }
    
    /**
     * Remove a specific row
     * @param row Row index
     */
    public void removeRow(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            tableModel.removeRow(row);
        }
    }
    
    /**
     * Enable/disable row selection
     * @param enabled True to enable, false to disable
     */
    public void setSelectionEnabled(boolean enabled) {
        table.setRowSelectionAllowed(enabled);
    }
    
    /**
     * Get the underlying JTable for custom styling
     * @return The JTable component
     */
    public JTable getTable() {
        return table;
    }
    
    /**
     * Set column width
     * @param columnIndex Column index
     * @param width Width in pixels
     */
    public void setColumnWidth(int columnIndex, int width) {
        table.getColumnModel().getColumn(columnIndex).setPreferredWidth(width);
    }
}
