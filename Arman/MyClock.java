import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class MyClock extends Thread{
    private LocalTime auxiliaryTime;
    private Duration Time;
    private boolean isPaused;
    MyClock(){
        isPaused=false;
        auxiliaryTime=LocalTime.now();
        Time =Duration.ZERO;
    }
    public void setTime(){
        if (!isPaused)
            Time = Time.plus(Duration.between(auxiliaryTime,LocalTime.now()));
        auxiliaryTime=LocalTime.now();
    }
    public Duration getLocalTime() {
        System.out.println(Time.truncatedTo(ChronoUnit.SECONDS));
        return Time.truncatedTo(ChronoUnit.SECONDS);
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