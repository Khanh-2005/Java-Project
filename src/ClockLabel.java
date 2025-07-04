import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClockLabel extends JLabel implements ActionListener {
    private final SimpleDateFormat sdf;

    public ClockLabel(String type) {
        setForeground(Color.CYAN);

        if ("time".equals(type)) {
            sdf = new SimpleDateFormat("hh:mm:ss a");
            setFont(new Font("sans-serif", Font.PLAIN, 40));
            setHorizontalAlignment(SwingConstants.CENTER);
        } else if ("date".equals(type)) {
            sdf = new SimpleDateFormat("MMMM dd yyyy");
            setFont(new Font("sans-serif", Font.PLAIN, 12));
            setHorizontalAlignment(SwingConstants.LEFT);
        } else {
            sdf = new SimpleDateFormat();
        }

        Timer t = new Timer(1000, this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setText(sdf.format(new Date()));
    }
}
