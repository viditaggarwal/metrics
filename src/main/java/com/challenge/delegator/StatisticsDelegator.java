package com.challenge.delegator;

import java.util.Queue;

import com.challenge.comparator.MinAmountPriority;
import com.challenge.model.Statistics;
import com.challenge.model.Transaction;

public class StatisticsDelegator {
	public static final int TIME_LIMIT = 60000;

	public static void compute(Transaction transaction) {
		Statistics model = Statistics.getInstance();
		Queue<Transaction> queue = model.getTransactionQueue();
		computeValues(model, transaction.getAmount());
		queue.add(transaction);
		model.getMinAmountQueue().add(transaction);
		model.getMaxAmountQueue().add(transaction);
	}

	public static void deleteOldTransactions(Long timeNow) {
		Statistics model = Statistics.getInstance();
		Queue<Transaction> queue = model.getTransactionQueue();
		while(!queue.isEmpty() && timeNow - 
				queue.peek().getTimestamp() > TIME_LIMIT) {
			Transaction previous = queue.poll();
			updateValues(model, previous);
		}
	}

	private static void updateValues(Statistics model, Transaction previous) {
		if (model.getCount() == 0) {
			model.init();
		} else if(model.getCount() > 0) {
			model.setCount(model.getCount()-1);
			model.setSum(model.getSum() - previous.getAmount());
			model.getMinAmountQueue().remove(previous);
			model.getMaxAmountQueue().remove(previous);
			model.setMin(model.getMinAmountQueue().isEmpty() ? 
					0 : model.getMinAmountQueue().peek().getAmount());
			model.setMax(model.getMaxAmountQueue().isEmpty() ? 
					0 : model.getMaxAmountQueue().peek().getAmount());
			model.setAvg(model.getCount() > 0 ? model.getSum()/model.getCount() : 0);
		}
	}

	private static void computeValues(Statistics model, double amount) {
		model.setMax(model.getMax() == 0 ? amount: Math.max(model.getMax(), amount));
		model.setMin(model.getMin() == 0 ? amount: Math.min(model.getMin(), amount));
		model.setCount(model.getCount()+1);
		model.setSum(model.getSum() + amount);
		model.setAvg(model.getCount() > 0 ? model.getSum()/model.getCount() : 0);
	}
}
