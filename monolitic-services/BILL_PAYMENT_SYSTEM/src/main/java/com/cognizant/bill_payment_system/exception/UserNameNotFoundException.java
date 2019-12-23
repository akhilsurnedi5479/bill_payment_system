package com.cognizant.bill_payment_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Id already exists")

public class UserNameNotFoundException extends Exception{

}
