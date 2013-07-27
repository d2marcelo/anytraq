package com.brtracker.services.async;


public class AccountLookup  {

	
		private static AccountWSClient accountClient;

		public static AccountWSClient getAccountClient() {
			return accountClient;
		}

		public void setAccountClient(AccountWSClient accountClient) {
			this.accountClient = accountClient;
		}
		
		
}
