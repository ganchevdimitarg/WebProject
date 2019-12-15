package softuni.webproject.scheduleJobs;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import softuni.webproject.data.repositories.ScheduleRepository;

@Component
@EnableAsync
@AllArgsConstructor
public class ScheduleJob {
    private final ScheduleRepository scheduleRepository;

    @Async
    @Scheduled(cron = "0 0 18 ? * MON,TUE,WED,THU,FRI")
    protected void scheduleJob(){
        scheduleRepository.deleteAll();
    }
}
