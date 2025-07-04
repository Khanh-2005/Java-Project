import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CalendarCellRenderer extends DefaultTableCellRenderer {
    private int realDay, realMonth, realYear, currentMonth, currentYear;

    public CalendarCellRenderer() {}

    public CalendarCellRenderer(int realDay, int realMonth, int realYear, int currentMonth, int currentYear) {
        this.realDay = realDay;
        this.realMonth = realMonth;
        this.realYear = realYear;
        this.currentMonth = currentMonth;
        this.currentYear = currentYear;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
                                                   int row, int column) {
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        setForeground(Color.BLACK);

        if (column == 6) {
            setBackground(Color.PINK);
        } else {
            setBackground(Color.WHITE);
        }

        if (value != null) {
            int day = Integer.parseInt(value.toString());
            if (day == realDay && currentMonth == realMonth && currentYear == realYear) {
                setBackground(new Color(83, 230, 29));
            }
        }

        return this;
    }
}
