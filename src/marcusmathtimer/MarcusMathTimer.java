package marcusmathtimer;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author Shawn M. Crawford
 */


public class MarcusMathTimer {

    private int hour1;
    private int hour2;
    private int minute1;
    private int minute2;
    private int second1;
    private int second2;
    private Timer timer;
    private int interval;
    private final JLabel jLabelSeconds;
    private final JButton jButtonStart;
    private final JButton jButtonStop;
    private final JComboBox hour1JComboBox;
    private final JComboBox hour2JComboBox;
    private final JComboBox minute1JComboBox;
    private final JComboBox minute2JComboBox;
    private final JComboBox second1JComboBox;
    private final JComboBox second2JComboBox;

    public MarcusMathTimer(JComboBox hour1JComboBox, JComboBox hour2JComboBox, JComboBox minute1JComboBox, JComboBox minute2JComboBox, JComboBox second1JComboBox, JComboBox second2JComboBox, JLabel jLabelSeconds, JButton jButtonStart, JButton jButtonStop) {
        this.hour1JComboBox = hour1JComboBox;
        this.hour2JComboBox = hour2JComboBox;
        this.minute1JComboBox = minute1JComboBox;
        this.minute2JComboBox = minute2JComboBox;
        this.second1JComboBox = second1JComboBox;
        this.second2JComboBox = second2JComboBox;
        this.hour1 = hour1JComboBox.getSelectedIndex();
        this.hour2 = hour2JComboBox.getSelectedIndex();
        this.minute1 = minute1JComboBox.getSelectedIndex();
        this.minute2 = minute2JComboBox.getSelectedIndex();
        this.second1 = second1JComboBox.getSelectedIndex();
        this.second2 = second2JComboBox.getSelectedIndex();
        this.jLabelSeconds = jLabelSeconds;
        this.jButtonStart = jButtonStart;
        this.jButtonStop = jButtonStop;
    }

    public int getHour1() {
        return hour1;
    }

    public void setHour1(int hour1) {
        this.hour1 = hour1;
    }

    public int getHour2() {
        return hour2;
    }

    public void setHour2(int hour2) {
        this.hour2 = hour2;
    }

    public int getMinute1() {
        return minute1;
    }

    public void setMinute1(int minute1) {
        this.minute1 = minute1;
    }

    public int getMinute2() {
        return minute2;
    }

    public void setMinute2(int minute2) {
        this.minute2 = minute2;
    }

    public int getSecond1() {
        return second1;
    }

    public void setSecond1(int second1) {
        this.second1 = second1;
    }

    public int getSecond2() {
        return second2;
    }

    public void setSecond2(int second2) {
        this.second2 = second2;
    }
    
    public int getTimeInSeconds() {
        int hour1Seconds = (getHour1() * 60 * 60);
        int hour2Seconds = (getHour2() * 60 * 60 * 10);
        int minute1Seconds = (getMinute1() * 60);
        int minute2Seconds = (getMinute2() * 60 * 10);
        int second2Seconds = (getSecond2() * 10);
        int totalSeconds = hour1Seconds + hour2Seconds + minute1Seconds + minute2Seconds + getSecond1() + second2Seconds;

        return totalSeconds;
    }

    public void startTimer() {
        jButtonStart.setEnabled(false);
        jButtonStop.setEnabled(true);
        int delay = 1000;
        int period = 1000;
        timer = new Timer();
        interval = getTimeInSeconds();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int secondsLeft = getInterval();

                jLabelSeconds.setText(Integer.toString(secondsLeft));

                String hours = String.format("%02d", secondsLeft / 3600);
                String minutes = String.format("%02d", ((secondsLeft % 3600) / 60));
                String seconds = String.format("%02d", secondsLeft % 60);

                int midpoint = 1;

                String hours2 = hours.substring(0, midpoint);
                String hours1 = hours.substring(midpoint);
                String minutes2 = minutes.substring(0, midpoint);
                String minutes1 = minutes.substring(midpoint);
                String seconds2 = seconds.substring(0, midpoint);
                String seconds1 = seconds.substring(midpoint);
                
                hour1JComboBox.setSelectedIndex(Integer.parseInt(hours1));
                hour2JComboBox.setSelectedIndex(Integer.parseInt(hours2));
                minute1JComboBox.setSelectedIndex(Integer.parseInt(minutes1));
                minute2JComboBox.setSelectedIndex(Integer.parseInt(minutes2));
                second1JComboBox.setSelectedIndex(Integer.parseInt(seconds1));
                second2JComboBox.setSelectedIndex(Integer.parseInt(seconds2));
                
            }
        }, delay, period);
    }
    
    
    private int getInterval() {
        if (interval <= 1) {
            stopTimer();
            Sound.ALARM.play();
            return 0;
        }
        return --interval;
    }
    
    public void stopTimer() {
        jButtonStart.setEnabled(true);
        jButtonStop.setEnabled(false);
        timer.cancel();
    }
    
    public Timer getTimer() {
        return timer;
    }
}
