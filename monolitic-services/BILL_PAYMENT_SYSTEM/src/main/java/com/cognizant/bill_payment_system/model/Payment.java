package com.cognizant.bill_payment_system.model;

public class Payment {
		int billId;
		
		String username;
		String vendorName;
		String billpaymentGateway;
		int monthsPaid;
		String nameOnCard;
		int cardNumber;
		String expirationMonth;
		int expirationYear;
		int postalCode;
		int billAmount;
		String email;
		String mobileNumber;

		
		
		public Payment(int id, String username, String vendorName, String billpaymentGateway, int monthsPaid,
				String nameOnCard, int cardNumber, String expirationMonth, int expirationYear, int postalCode,
				int billAmount, String email, String mobileNumber) {
			super();
			this.billId = id;
			this.username = username;
			this.vendorName = vendorName;
			this.billpaymentGateway = billpaymentGateway;
			this.monthsPaid = monthsPaid;
			this.nameOnCard = nameOnCard;
			this.cardNumber = cardNumber;
			this.expirationMonth = expirationMonth;
			this.expirationYear = expirationYear;
			this.postalCode = postalCode;
			this.billAmount = billAmount;
			this.email = email;
			this.mobileNumber = mobileNumber;
		}
		public Payment() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public int getBillId() {
			return billId;
		}
		public void setBillId(int billId) {
			this.billId = billId;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getVendorName() {
			return vendorName;
		}
		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}
		public String getBillpaymentGateway() {
			return billpaymentGateway;
		}
		public void setBillpaymentGateway(String billpaymentGateway) {
			this.billpaymentGateway = billpaymentGateway;
		}
		public int getMonthsPaid() {
			return monthsPaid;
		}
		public void setMonthsPaid(int monthsPaid) {
			this.monthsPaid = monthsPaid;
		}
		public String getNameOnCard() {
			return nameOnCard;
		}
		public void setNameOnCard(String nameOnCard) {
			this.nameOnCard = nameOnCard;
		}
		public int getCardNumber() {
			return cardNumber;
		}
		public void setCardNumber(int cardNumber) {
			this.cardNumber = cardNumber;
		}
		public String getExpirationMonth() {
			return expirationMonth;
		}
		public void setExpirationMonth(String expirationMonth) {
			this.expirationMonth = expirationMonth;
		}
		public int getExpirationYear() {
			return expirationYear;
		}
		public void setExpirationYear(int expirationYear) {
			this.expirationYear = expirationYear;
		}
		public int getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(int postalCode) {
			this.postalCode = postalCode;
		}
		public int getBillAmount() {
			return billAmount;
		}
		public void setBillAmount(int billAmount) {
			this.billAmount = billAmount;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		
}
