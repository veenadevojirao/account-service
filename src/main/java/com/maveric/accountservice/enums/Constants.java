package com.maveric.accountservice.enums;

import java.time.LocalDateTime;

public class Constants {
    public Constants(String accountNotFoundCode)
    {

    }
    public static LocalDateTime getCurrentDateTime() {
        return (java.time.LocalDateTime.now());

    }
    public static final String ACCOUNT_NOT_FOUND_CODE="404";

    public static final String NOT_AUTHORIZED_USER = "You are not an authorized user";
    public static final String ACCOUNT_NOT_FOUND_MESSAGE="Account not found for Id-";
    public static final String BALANCE_NOT_FOUND_MESSAGE="Balance details not found for Id-";
    public static final String ACCOUNT_DELETED_SUCCESS="Account deleted successfully";
    public static final String METHOD_NOT_ALLOWED_CODE="405";
    public static final String METHOD_NOT_ALLOWED_MESSAGE="Method Not Allowed. Kindly check the Request URL and Request Type.";
    public static final String BAD_REQUEST_CODE="400";
    public static final String BAD_REQUEST_MESSAGE="Invalid inputs!";
    public static final String INCORRECT_URL_CODE="404";
    public static final String INCORRECT_URL_MESSAGE="The server can not find the requested resource.";
    public static final String NOT_FOUND="404";
    public static final String INVALID_INPUT_TYPE="Type should be - 'CURRENT' or 'SAVINGS'";

    public static final String HTTP_MESSAGE_NOT_READABLE_EXCEPTION_MESSAGE = "Kindly enter the valid type";

    public static final String SERVICE_UNAVAILABLE_CODE = "503";
    public static final String SERVICE_UNAVAILABLE_MESSAGE = "Services down! Kindly contact administrator.";

    public static final String INTERNAL_SERVER_ERROR_CODE = "500";

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Server could not resolve your request.";

    public static final String AUTH_HEADER_ERROR_CODE = "401";
    public static final String AUTH_HEADER_ERROR_MESSAGE = "Authorization header is invalid";
    public static final String CUSTOMER_ID_ERROR="customer id should be same in request body and url";




}



