package com.challenge.model;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.challenge.comparator.MaxAmountPriority;
import com.challenge.comparator.MinAmountPriority;

public class Statistics {
	private double sum;
	private double min;
	private double max;
	private long count;
	private double avg;
	private transient Queue<Transaction> transactionQueue = new LinkedList<>();
	private transient Queue<Transaction> minAmountQueue = 
			new PriorityQueue<>(new MinAmountPriority());
	private transient Queue<Transaction> maxAmountQueue = 
			new PriorityQueue<>(new MaxAmountPriority());
	
	private static Statistics instance = null;
	
	public static Statistics getInstance(){
		if(instance == null){
	        synchronized (Statistics.class) {
	            if(instance == null){
	                instance = new Statistics();
	            }
	        }
	    }
	    return instance;
    }
	
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public Queue<Transaction> getTransactionQueue() {
		return transactionQueue;
	}

	public void setTransactionQueue(Queue<Transaction> transactionQueue) {
		this.transactionQueue = transactionQueue;
	}

	public Queue<Transaction> getMinAmountQueue() {
		return minAmountQueue;
	}

	public void setMinAmountQueue(Queue<Transaction> minAmountQueue) {
		this.minAmountQueue = minAmountQueue;
	}

	public Queue<Transaction> getMaxAmountQueue() {
		return maxAmountQueue;
	}

	public void setMaxAmountQueue(Queue<Transaction> maxAmountQueue) {
		this.maxAmountQueue = maxAmountQueue;
	}

	public void init() {
		sum = 0;
		max = 0;
		min = 0;
		avg = 0;
		count = 0;
		transactionQueue.clear();
		minAmountQueue.clear();
		maxAmountQueue.clear();
	}
}
