package com.anet.api;

import com.anet.api.util.BasicXmlDocument;
import com.anet.api.http.HttpUtil;
import com.brtracker.shared.utils.wsutils.ServiceResponse;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.*;

// import java.io.*;
public class ARBAPI{
	
	private BasicXmlDocument current_request = null;
	private BasicXmlDocument current_response = null;
	private String merchant_name = null;
	private String transaction_key = null;
	private ArrayList messages = null;
	private String result_code = null;
	private String result_subscription_id = null;

	private HttpUtil http_util = null;
	public ARBAPI(URL in_api_url, String in_merchant_name, String in_transaction_key){
		
		messages = new ArrayList();
		
		merchant_name = in_merchant_name;
		transaction_key = in_transaction_key;
		http_util = new HttpUtil(in_api_url);
	}
	public BasicXmlDocument getCurrentRequest(){
		return current_request;
	
	}
	
	public BasicXmlDocument getCurrentResponse(){
		return current_response;
	}
	
	public String getResultCode(){
		return result_code;
	}
	public String getResultSubscriptionId(){
		return result_subscription_id;
	}
	
	public void destroy(){
		http_util.cleanup();
	}
	private void addSubscriptionIdToRequest(BasicXmlDocument document, ARBSubscription subscription){
		if(subscription.getSubscriptionId() != null){
			Element subscr_id_el = document.createElement(ARBAPIRequests.ELEMENT_SUBSCRIPTION_ID);
			subscr_id_el.appendChild(document.getDocument().createTextNode(subscription.getSubscriptionId()));
			document.getDocumentElement().appendChild(subscr_id_el);
		}	
	}
	private void addSubscriptionToRequest(BasicXmlDocument document, ARBSubscription subscription){
	
		addSubscriptionIdToRequest(document, subscription);
		
		Element subscr_el = document.createElement(ARBAPIRequests.ELEMENT_SUBSCRIPTION);
		if(subscription.getName() != null){
			Element name_el = document.createElement(ARBAPIRequests.ELEMENT_NAME);
			name_el.appendChild(document.getDocument().createTextNode(subscription.getName()));
			subscr_el.appendChild(name_el);
		}
		
		addPaymentScheduleToSubscription(document, subscription, subscr_el);
		if(subscription.getAmount() != 0 || subscription.getTrialAmount() != 0){
			Element amount_el = document.createElement(ARBAPIRequests.ELEMENT_AMOUNT);
			amount_el.appendChild(document.getDocument().createTextNode(Double.toString(subscription.getAmount())));
			subscr_el.appendChild(amount_el);
			Element trial_el = document.createElement(ARBAPIRequests.ELEMENT_TRIAL_AMOUNT);
			trial_el.appendChild(document.getDocument().createTextNode(Double.toString(subscription.getTrialAmount())));
			subscr_el.appendChild(trial_el);
		}
		
		//addCustomerToSubscription(document, subscription, subscr_el);
		addPaymentToSubscription(document, subscription, subscr_el);
		addBillingInfoToSubscription(document, subscription, subscr_el);
		document.getDocumentElement().appendChild(subscr_el);
	}
	private void addBillingInfoToSubscription(BasicXmlDocument document, ARBSubscription subscription, Element subscr_el){
		if(subscription.getCustomer() == null || subscription.getCustomer().getBillTo() == null) return;
		ARBNameAndAddress bill_info = subscription.getCustomer().getBillTo();
		Element bill_el = document.createElement(ARBAPIRequests.ELEMENT_BILL_TO);
		
		Element fname_el = document.createElement(ARBAPIRequests.ELEMENT_FIRST_NAME);
		fname_el.appendChild(document.getDocument().createTextNode(bill_info.getFirstName()));
		bill_el.appendChild(fname_el);

		Element lname_el = document.createElement(ARBAPIRequests.ELEMENT_LAST_NAME);
		lname_el.appendChild(document.getDocument().createTextNode(bill_info.getLastName()));
		bill_el.appendChild(lname_el);
		
		Element address_el = document.createElement(ARBAPIRequests.ELEMENT_ADDRESS);
		address_el.appendChild(document.getDocument().createTextNode(bill_info.getAddress()));
		bill_el.appendChild(address_el);
		
		Element city_el = document.createElement(ARBAPIRequests.ELEMENT_CITY);
		city_el.appendChild(document.getDocument().createTextNode(bill_info.getCity()));
		bill_el.appendChild(city_el);
		
		Element state_el = document.createElement(ARBAPIRequests.ELEMENT_STATE);
		state_el.appendChild(document.getDocument().createTextNode(bill_info.getState()));
		bill_el.appendChild(state_el);
		
		Element zip_el = document.createElement(ARBAPIRequests.ELEMENT_ZIP);
		zip_el.appendChild(document.getDocument().createTextNode(bill_info.getZip()));
		bill_el.appendChild(zip_el);
		
		Element country_el = document.createElement(ARBAPIRequests.ELEMENT_COUNTRY);
		country_el.appendChild(document.getDocument().createTextNode(bill_info.getCountry()));
		bill_el.appendChild(country_el);
		subscr_el.appendChild(bill_el);
		
	}
	
	private void addCustomerToSubscription(BasicXmlDocument document, ARBSubscription subscription, Element subscr_el){
		ARBCustomer customer = subscription.getCustomer();
		if (customer ==  null) return;
		
		Element customer_el = document.createElement(ARBAPIRequests.ELEMENT_CUSTOMER);
		
		Element userId_el = document.createElement(ARBAPIRequests.ELEMENT_CUSTOMER_ID);
		userId_el.appendChild(document.getDocument().createTextNode(customer.getId()));
		customer_el.appendChild(userId_el);
		
		subscr_el.appendChild(customer_el);
		
	}
		
	private void addPaymentToSubscription(BasicXmlDocument document, ARBSubscription subscription, Element subscr_el){
		ARBPayment payment = subscription.getPayment();
		if(payment == null) return;
		
		Element payment_el = document.createElement(ARBAPIRequests.ELEMENT_PAYMENT);
		CreditCard credit_card= payment.getCreditCard();
		
		if(credit_card != null){
			Element cc_el = document.createElement(ARBAPIRequests.ELEMENT_CREDIT_CARD);
			
			Element cc_num_el = document.createElement(ARBAPIRequests.ELEMENT_CREDIT_CARD_NUMBER);
			cc_num_el.appendChild(document.getDocument().createTextNode(credit_card.getCardNumber()));
			cc_el.appendChild(cc_num_el);

			Element cc_exp_el = document.createElement(ARBAPIRequests.ELEMENT_CREDIT_CARD_EXPIRY);
			Date expirationDate = com.anet.api.util.DateUtil.getDateFromFormattedDate(credit_card.getExpirationDate(), CreditCard.EXPIRY_DATE_FORMAT);
			cc_exp_el.appendChild(document.getDocument().createTextNode(com.anet.api.util.DateUtil.getFormattedDate(expirationDate,CreditCard.EXPIRY_DATE_FORMAT)));
			cc_el.appendChild(cc_exp_el);
			
			payment_el.appendChild(cc_el);
		}
		
		subscr_el.appendChild(payment_el);
	}
		
	private void addPaymentScheduleToSubscription(BasicXmlDocument document, ARBSubscription subscription, Element subscr_el){
		ARBPaymentSchedule schedule = subscription.getSchedule();
		if(schedule == null) return;
		
		Element payment_el = document.createElement(ARBAPIRequests.ELEMENT_PAYMENT_SCHEDULE);
		
		// Add the interval
		//
		if(schedule.getInterval_length() > 0){
			Element interval_el = document.createElement(ARBAPIRequests.ELEMENT_INTERVAL);
			Element length_el = document.createElement(ARBAPIRequests.ELEMENT_LENGTH);
			Element unit_el = document.createElement(ARBAPIRequests.ELEMENT_UNIT);
			length_el.appendChild(document.getDocument().createTextNode(Integer.toString(schedule.getInterval_length())));
			interval_el.appendChild(length_el);
			interval_el.appendChild(unit_el);
			unit_el.appendChild(document.getDocument().createTextNode(schedule.getSubscriptionUnit()));
			
			payment_el.appendChild(interval_el);
		}
	
		Element start_date_el = document.createElement(ARBAPIRequests.ELEMENT_START_DATE);
		Date startDate = com.anet.api.util.DateUtil.getDateFromFormattedDate(schedule.getStartDate(), ARBPaymentSchedule.SCHEDULE_DATE_FORMAT);
		start_date_el.appendChild(document.getDocument().createTextNode(com.anet.api.util.DateUtil.getFormattedDate(startDate,ARBPaymentSchedule.SCHEDULE_DATE_FORMAT)));
		payment_el.appendChild(start_date_el);
		
		Element total_el = document.createElement(ARBAPIRequests.ELEMENT_TOTAL_OCCURRENCES);
		total_el.appendChild(document.getDocument().createTextNode(Integer.toString(schedule.getTotalOccurrences())));
		payment_el.appendChild(total_el);
		
		Element trial_el = document.createElement(ARBAPIRequests.ELEMENT_TRIAL_OCCURRENCES);
		trial_el.appendChild(document.getDocument().createTextNode(Integer.toString(schedule.getTrialOccurrences())));
		payment_el.appendChild(trial_el);
		
		subscr_el.appendChild(payment_el);
	}
	private void addAuthenticationToRequest(BasicXmlDocument document){
		Element auth_el = document.createElement(ARBAPIRequests.ELEMENT_MERCHANT_AUTHENTICATION);
		Element name_el = document.createElement(ARBAPIRequests.ELEMENT_NAME);
		name_el.appendChild(document.getDocument().createTextNode(merchant_name));
		Element trans_key = document.createElement(ARBAPIRequests.ELEMENT_TRANSACTION_KEY);
		trans_key.appendChild(document.getDocument().createTextNode(transaction_key));
		auth_el.appendChild(name_el);
		auth_el.appendChild(trans_key);
		document.getDocumentElement().appendChild(auth_el);
	
	}
	public String createSubscriptionRequest(ARBSubscription subscription){
		
		clearRequest();
		
		BasicXmlDocument document = new BasicXmlDocument();
		document.parseString("<" + ARBAPIRequests.REQUEST_CREATE_SUBSCRIPTION + " xmlns = \"" + ARBAPIRequests.XML_NAMESPACE + "\" />");
		
		addAuthenticationToRequest(document);
		addSubscriptionToRequest(document,subscription);
		current_request = document;
		return result_code;
	}
	public String updateSubscriptionRequest(ARBSubscription subscription){
		
		clearRequest();
		
		BasicXmlDocument document = new BasicXmlDocument();
		document.parseString("<" + ARBAPIRequests.REQUEST_UPDATE_SUBSCRIPTION + " xmlns = \"" + ARBAPIRequests.XML_NAMESPACE + "\" />");
		
		addAuthenticationToRequest(document);
		addSubscriptionToRequest(document,subscription);
		current_request = document;
		
		return result_code;
	}
	public String cancelSubscriptionRequest(ARBSubscription subscription){

		clearRequest();
		
		BasicXmlDocument document = new BasicXmlDocument();
		document.parseString("<" + ARBAPIRequests.REQUEST_CANCEL_SUBSCRIPTION + " xmlns = \"" + ARBAPIRequests.XML_NAMESPACE + "\" />");
		
		addAuthenticationToRequest(document);
		addSubscriptionIdToRequest(document,subscription);
		current_request = document;
		
		return result_code;
	}
	public String getSubscriptionStatusRequest(ARBSubscription subscription){

		clearRequest();
		
		BasicXmlDocument document = new BasicXmlDocument();
		document.parseString("<" + ARBAPIRequests.REQUEST_GET_SUBSCRIPTION_STATUS + " xmlns = \"" + ARBAPIRequests.XML_NAMESPACE + "\" />");
		
		addAuthenticationToRequest(document);
		addSubscriptionIdToRequest(document,subscription);
		current_request = document;
		
		return result_code;
	}
	public void clearRequest(){
		messages.clear();
		result_code = null;
		result_subscription_id = null;
		current_request = null;
		current_response = null;
	}
	public ServiceResponse sendRequest(){
		return sendRequest(current_request);
	}
	public ServiceResponse sendRequest(BasicXmlDocument request_document)  {
		ServiceResponse response = new ServiceResponse();
		result_code = null;
		messages.clear();
		
		String in_response = http_util.postUrl(request_document.dump());
		
		if(in_response == null) return null;
		
		int mark = in_response.indexOf("<?xml");
		if(mark == -1){
			response.setReturnValue(false);
			response.setMessage("Invalid response: "+in_response);
			return response;	
		}
		BasicXmlDocument xmlResponse = new BasicXmlDocument();
		
		xmlResponse.parseString(in_response.substring(mark,in_response.length()));
		// System.out.println("Response-XML::\n" + response.dump());
		if(xmlResponse.IsAccessible() == false){
			response.setReturnValue(false);
			response.setMessage("Invalid response: "+in_response);
			return response;
		}
		current_response = xmlResponse;
		importResponseMessages();
		response.setMessage("Request submitted successfuly. Response: "+this.getCurrentResponse());
		response.setReturnValue(true);
		return response;
	}
	
	private String getElementText(Element parent_el, String element_name){
		String out_val = null;
		NodeList match_list = parent_el.getElementsByTagName(element_name);
		if(match_list.getLength() == 0) return out_val;
		Element match_el = (Element)match_list.item(0);
		if(match_el.hasChildNodes()){
			out_val = match_el.getFirstChild().getNodeValue();
		}
		return out_val;
	}
	private void importResponseMessages(){
		NodeList messages_list = current_response.getDocument().getElementsByTagName(ARBAPIRequests.ELEMENT_MESSAGES);
		if(messages_list.getLength() == 0) return;
		Element messages_el =(Element)messages_list.item(0);
		
		result_code = getElementText(messages_el,ARBAPIRequests.ELEMENT_RESULT_CODE);
		result_subscription_id = getElementText(current_response.getDocumentElement(),ARBAPIRequests.ELEMENT_SUBSCRIPTION_ID);
		
		NodeList message_list = messages_el.getElementsByTagName(ARBAPIRequests.ELEMENT_MESSAGE);
		for(int i = 0; i < message_list.getLength(); i++){
			Element message_el = (Element)message_list.item(i);
			Message new_message = new Message();
			new_message.setCode(getElementText(message_el,ARBAPIRequests.ELEMENT_CODE));
			//new_message.setResultCode(getElementText(message_el,ARBAPIRequests.ELEMENT_RESULT_CODE));
			new_message.setText(getElementText(message_el,ARBAPIRequests.ELEMENT_TEXT));
			this.messages.add(new_message);
		}
	}
	public StringBuffer getMessages(){
		StringBuffer appender = new StringBuffer();
		appender.append("Result Code: " + (result_code != null ? result_code : "No result code"));
		if(result_subscription_id != null){
			appender.append("Result Subscription Id: " + result_subscription_id);
		}
		for(int i = 0; i < messages.size(); i++){
			Message message = (Message)messages.get(i);
			appender.append(message.getCode() + " - " + message.getText());
		}
		return appender;
	}
	
	
}
	
