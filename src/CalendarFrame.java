import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;

public class CalendarFrame extends JFrame {
    private CalendarPanel calendarPanel;
    private ClockLabel timeLabel;

    public CalendarFrame() {
        super("Calendar with Digital Clock");
        setSize(350, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        calendarPanel = new CalendarPanel();
        calendarPanel.setBounds(0, 0, 320, 335);
        add(calendarPanel);

        timeLabel = new ClockLabel("time");
        timeLabel.setBounds(0, 360, 360, 50);
        add(timeLabel);
    }
}
