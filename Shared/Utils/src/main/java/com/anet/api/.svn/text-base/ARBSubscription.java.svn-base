package com.anet.api;

public class ARBSubscription{
	private String subscription_id = null;
	private Long accountId; 
	private String name = null;
	private ARBPaymentSchedule schedule = null;
    //private boolean amount_specified = false;
    private double amount = 0.0;
    //private boolean trial_amount_specified = false;
    private double trial_amount = 0.0;
    private ARBPayment payment = null;
    //private boolean order_specified = false;
    private ARBCustomer customer;

    public ARBSubscription(){
		
	}
    
    public Long getAccountId() {
		return accountId;
	}


	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}


	public String getSubscriptionId() {
		return subscription_id;
	}
	public void setSubscriptionId(String subscription_id) {
		this.subscription_id = subscription_id;
	}
	public ARBCustomer getCustomer(){
    	return this.customer;
    }
    public void setCustomer(ARBCustomer customer){
    	this.customer = customer;
    }

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ARBPayment getPayment() {
		return payment;
	}

	public void setPayment(ARBPayment payment) {
		this.payment = payment;
	}

	public ARBPaymentSchedule getSchedule() {
		return schedule;
	}

	public void setSchedule(ARBPaymentSchedule schedule) {
		this.schedule = schedule;
	}

	public double getTrialAmount() {
		return trial_amount;
	}

	public void setTrialAmount(double trial_amount) {
		this.trial_amount = trial_amount;
	}
}