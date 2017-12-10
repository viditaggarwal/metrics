package com.challenge.comparator;

import java.util.Comparator;

import com.challenge.model.Transaction;

public class MaxAmountPriority implements Comparator<Transaction> {
	@Override
	public int compare(Transaction o1, Transaction o2) {
		return Double.compare(o2.getAmount(), o1.getAmount());
	}
}
