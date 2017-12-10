package com.challenge.scheduler;

import java.util.Timer;

public class BatchApplication {
	private static final int BATCH_TIME_LIMIT = 1000;
	
	static {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Timer timer = new Timer();
				TransactionScheduler scheduler = new TransactionScheduler();
				timer.scheduleAtFixedRate(scheduler, 0, BATCH_TIME_LIMIT);
			}
		}).start();
	}
}
