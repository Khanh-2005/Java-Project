import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class CalendarPanel extends JPanel {
    JLabel lblMonth, lblYear;
    JButton btnPrev, btnNext;
    JComboBox<String> cmbYear;
    JTable tblCalendar;
    DefaultTableModel mtblCalendar;
    JScrollPane stblCalendar;

    int realYear, realMonth, realDay;
    int currentYear, currentMonth;

    public CalendarPanel() {
        setLayout(null);
        setBorder(BorderFactory.createTitledBorder("Calendar"));

        lblMonth = new JLabel("January");
        lblYear = new JLabel("Change year:");
        cmbYear = new JComboBox<>();
        btnPrev = new JButton("<");
        btnNext = new JButton(">");
        mtblCalendar = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tblCalendar = new JTable(mtblCalendar);
        stblCalendar = new JScrollPane(tblCalendar);

        // Add headers
        String[] headers = { "Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri" };
        for (String header : headers) mtblCalendar.addColumn(header);
        mtblCalendar.setRowCount(6);

        tblCalendar.setRowHeight(38);
        tblCalendar.setDefaultRenderer(Object.class, new CalendarCellRenderer());
        tblCalendar.getTableHeader().setResizingAllowed(false);
        tblCalendar.getTableHeader().setReorderingAllowed(false);

        add(lblMonth); add(lblYear); add(cmbYear);
        add(btnPrev); add(btnNext); add(stblCalendar);

        // Set bounds
        lblMonth.setBounds(130, 25, 100, 25);
        lblYear.setBounds(10, 305, 80, 20);
        cmbYear.setBounds(230, 305, 80, 20);
        btnPrev.setBounds(10, 25, 50, 25);
        btnNext.setBounds(260, 25, 50, 25);
        stblCalendar.setBounds(10, 50, 300, 250);

        // Calendar data
        GregorianCalendar cal = new GregorianCalendar();
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        realMonth = cal.get(GregorianCalendar.MONTH);
        realYear = cal.get(GregorianCalendar.YEAR);
        currentMonth = realMonth;
        currentYear = realYear;

        for (int i = realYear - 100; i <= realYear + 100; i++) {
            cmbYear.addItem(String.valueOf(i));
        }

        // Add listeners
        btnPrev.addActionListener(e -> {
            if (currentMonth == 0) {
                currentMonth = 11;
                currentYear--;
            } else currentMonth--;
            refreshCalendar();
        });

        btnNext.addActionListener(e -> {
            if (currentMonth == 11) {
                currentMonth = 0;
                currentYear++;
            } else currentMonth++;
            refreshCalendar();
        });

        cmbYear.addActionListener(e -> {
            currentYear = Integer.parseInt((String) cmbYear.getSelectedItem());
            refreshCalendar();
        });

        refreshCalendar();
    }

    public void refreshCalendar() {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        btnPrev.setEnabled(!(currentMonth == 0 && currentYear <= realYear - 10));
        btnNext.setEnabled(!(currentMonth == 11 && currentYear >= realYear + 100));
        lblMonth.setText(months[currentMonth]);
        cmbYear.setSelectedItem(String.valueOf(currentYear));

        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                mtblCalendar.setValueAt(null, i, j);

        GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
        int nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        int som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        for (int i = 1; i <= nod; i++) {
            int row = (i + som - 2) / 7;
            int col = (i + som - 2) % 7;
            mtblCalendar.setValueAt(i, row, col);
        }

        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0),
                new CalendarCellRenderer(realDay, realMonth, realYear, currentMonth, currentYear));
    }
}
