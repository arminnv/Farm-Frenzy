import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalTime;
import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MyClock extends Thread{
    private LocalTime auxiliaryTime;
    private Duration Time;
    private boolean isPaused;
    JLabel timeLabel;
    MyClock(){
        isPaused=false;
        auxiliaryTime=LocalTime.now();
        Time =Duration.ZERO;
        timeLabel=new JLabel();
        timeLabel.setBackground(Color.GREEN);
        timeLabel.setFont(new Font(Font.MONOSPACED,Font.PLAIN,20));
        timeLabel.setOpaque(true);
    }
    public void setTime(){
        if (!isPaused) {
            //System.out.println(Duration.between(auxiliaryTime, LocalTime.now()));
            Time = Time.plus(Duration.between(auxiliaryTime, LocalTime.now()));
        }
        auxiliaryTime=LocalTime.now();
    }
    public Duration getLocalTime() {
        //System.out.println(Time.truncatedTo(ChronoUnit.SECONDS));
        return Time.truncatedTo(ChronoUnit.SECONDS);
    }
    public void setTimeLabel(){
        Duration duration=this.getLocalTime();
        int hour=duration.toHoursPart();//less than a day bug
        int minutes=duration.toMinutesPart();
        int seconds=duration.toSecondsPart();
        timeLabel.setText(hour+" : "+minutes+" : "+seconds);
        timeLabel.setText(LocalTime.of(hour,minutes,seconds).toString());
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    @Override
    public void run() {
        setTime();
    }
    /*
            Runnable runnable = new Runnable() {
            public void run() {
                // task to run goes here
            }
        };
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.MILLISECONDS);
     */
}