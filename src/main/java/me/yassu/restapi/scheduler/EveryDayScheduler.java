package me.yassu.restapi.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EveryDayScheduler {

	@Scheduled(cron = "0 0 0 * * *")
	public void runEveryDay() {
		// insert a record in db for all habits for every user every day
	}
}
