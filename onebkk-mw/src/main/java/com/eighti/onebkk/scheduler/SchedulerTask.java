package com.eighti.onebkk.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.eighti.onebkk.service.UserService;

@Component
public class SchedulerTask {

	public static final Logger LOG = LoggerFactory.getLogger(SchedulerTask.class);

	private static final long usersToFRDevicesFixedRate = 60000; // 1 minutes

	private final UserService userService;

	public SchedulerTask(UserService userService) {
		this.userService = userService;
	}


	@Scheduled(fixedRate = usersToFRDevicesFixedRate, initialDelay = 10000)
	public void syncUsersToFRDevicesTask() {
		LOG.info("\n===== START Perform Data Synchronizing =====");

		try {
			userService.syncUsersToFRDevices();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("syncUsersToFRDevicesTask() >>> ERROR: " + e.getMessage());
		}

		LOG.info("\n===== END Perform Data Synchronizing =====\n");

	}

}
