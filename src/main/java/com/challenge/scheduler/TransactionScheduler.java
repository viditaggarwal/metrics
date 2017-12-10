package com.challenge.scheduler;

import java.util.Date;
import java.util.TimerTask;

import com.challenge.delegator.StatisticsDelegator;

public class TransactionScheduler extends TimerTask {
	Date current;
	
	@Override
	public void run() {
		current = new Date();
		StatisticsDelegator.deleteOldTransactions(current.getTime());
	}
}
