package com.brtracker.shared.utils;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import com.brtracker.shared.utils.activemq.QueueName;
import com.brtracker.shared.utils.json.JSONMapper;
import com.brtracker.shared.utils.json.JSONMapperException;
import com.brtracker.shared.utils.wsutils.ClientCall;
import com.brtracker.shared.utils.wsutils.ServiceException;

public class SystemResourcesUtil  implements InitializingBean  {

	private static final String ACCOUNT = "ACCOUNT";
	private static final String METADATA = "METADATA";
	private static final String MIDLINK = "MIDLINK";
	private static final String NOTIFICATION = "NOTIFICATION";
	private static final String TRACKING = "TRACKING";
	private static final String NETTY = "NETTY";
	private static final String ASYNC = "ASYNC";
	private static final String APPAPI = "APPAPI";
	private static final String DATABASE = "DATABASE";
	private static final String ACTIVE_MQ = "ACTIVE_MQ";
	private static final String MEMCACHED = "MEMCACHED";
	private static final String UI_ALERT = "UI_ALERT";
	
	
	private static Map<String, Map<String,String>> resources = null;
	private String url;
	
	public SystemResourcesUtil () {};
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static Map<String, Map<String,String>> getResources() {
		return resources;
	}

	public void setResources(Map<String, Map<String,String>> resources) {
		SystemResourcesUtil.resources = resources;
	}
	
	@SuppressWarnings("unchecked")
	public void loadResources () {
		try {
			Map<String, Map<String,String>> resourceMap = (Map<String, Map<String,String>>) JSONMapper.toObject(ClientCall.get(url), Map.class);
			setResources(resourceMap);
		} catch (JSONMapperException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	
	private abstract static class WebService  {
		
		public String name;
		
		public  String getComponentName(){
			return name;
		}
			
		protected void setComponentName(String name){
			this.name = name;
		}
		public String getUrl (){
			return getResources().get(name).get("url");
		}
		
		public String getPort () {
			return 	getResources().get(name).get("port");
		}
		
		public Database getDatabase () {
			return new Database(name); 
		}
	}
	
	public static class Midlink  extends WebService {
		
		public Midlink(String name){
			super.setComponentName(name);
		}
		public static Midlink get(){
			return new Midlink(MIDLINK);
		}
		
	}
	
	public static class Notification  extends WebService {
		
		public Notification(String name){
			super.setComponentName(name);
		}
		public static Notification get(){
			return new Notification(NOTIFICATION);
		}
		
	}
	
public static class UIAlert  extends WebService {
		
		public UIAlert(String name){
			super.setComponentName(name);
		}
		public static UIAlert get(){
			return new UIAlert(UI_ALERT);
		}
		
	}
	
	public static class Tracking  extends WebService {
		
		public Tracking(String name){
			super.setComponentName(name);
		}
		public static Tracking get(){
			return new Tracking(TRACKING);
		}
	}
	
	public static class Account  extends WebService {
		static String component;
		public Account(String name){
			super.setComponentName(name);
			Account.component = name;
		}
		public static Account get(){
			return new Account(ACCOUNT);
		}
		public String getARBUrl(){
			return getResources().get(component).get("arb_url");
		}
		public String getARBMerchantId(){
			return getResources().get(component).get("arb_merchant_id");
		}
		public String getARBTokenId(){
			return getResources().get(component).get("arb_token_id");
		}
	}
	
	public static class AppAPI  extends WebService {
		public AppAPI(String name){
			super.setComponentName(name);
		}
		public static AppAPI get(){
			return new AppAPI(APPAPI);
		}
	}
	
	public static class Metadata  extends WebService {
		public Metadata(String name){
			super.setComponentName(name);
		}
		public static Metadata get(){
			return new Metadata(METADATA);
		}
	}
	public static class Netty  extends WebService {
		
		public Netty(String name){
			super.setComponentName(name);
		}
		public static Netty get(){
			return new Netty(NETTY);
		}
	}
	
	public static class Async  extends WebService {
		public Async(String name){
			super.setComponentName(name);
		}
		public static Async get(){
			return new Async(ASYNC);
		}
	}
	
	public static class Database   {
		
		private String component;
		public Database(String name){
			this.component = name;
		}
		public static Database get(){
			return new Database(DATABASE);
		}
			
		public String getURL () {
			return getResources().get(component).get("db_url");
		}
		
		public String getDriver () {
			return getResources().get(component).get("db_driver");
		}
		
		public String getUsername () {
			return getResources().get(component).get("db_username");
		}
		
		public String getPassword () {
			return getResources().get(component).get("db_password");
		}
		
	}
	
	public static class ActiveMQ  extends WebService {
		
		public ActiveMQ(String name){
			super.setComponentName(name);
		}
		public static ActiveMQ get(){
			return new ActiveMQ(ACTIVE_MQ);
		}
		
		public String getQueueName (QueueName queue) {
			return getResources().get(DATABASE).get(queue.name());
		}
	}
	
	public static class Memcached  extends WebService {
		
		public Memcached(String name){
			super.setComponentName(name);
		}
		public static Memcached get(){
			return new Memcached(MEMCACHED);
		}
		
		public int getExpirationTime (String entity){
			int def = 60; // default one minute
			String value = getResources().get(MEMCACHED).get("expiration_"+entity);
			if (value==null)return def; 
			try {
				return Integer.parseInt(value);
			}catch (NumberFormatException e){
				return def;
			}
		}
		
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		loadResources();
	}

	
	
	
}
