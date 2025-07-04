public class MainApp {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            CalendarFrame calendarFrame = new CalendarFrame();
            calendarFrame.setVisible(true);
        });
    }
}
