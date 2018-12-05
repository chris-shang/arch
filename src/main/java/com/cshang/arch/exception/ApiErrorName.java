package com.cshang.arch.exception;

/**
 * Constants for error names in API responses.
 *
 * Values should never be modified as clients may base logic on it.
 */
public abstract class ApiErrorName {
    /*
     * Authorization
     */
    public static final String MissingAuthorization = "MissingAuthorization";
    public static final String InvalidAuthorization = "InvalidAuthorization";
    public static final String MalformedAuthorization = "MalformedAuthorization";

    /*
     * Token
     */
    public static final String IllegalToken = "IllegalToken";
    public static final String InvalidToken = "InvalidToken";
    public static final String MalformedToken = "MalformedToken";
    public static final String ExpiredToken = "ExpiredToken";

    /*
     * Version
     */
    public static final String MissingVersion = "MissingVersion";
    public static final String InvalidVersion = "InvalidVersion";
    public static final String UnsupportedVersion = "UnsupportedVersion";

    /*
     * Date
     */
    public static final String MissingDate = "MissingDate";
    public static final String InvalidDate = "InvalidDate";
    public static final String ExpiredDate = "ExpiredDate";

    /*
     * User
     */
    public static final String InvalidUser = "InvalidUser";
    public static final String InsufficientPrivilege = "InsufficientPrivilege";

    /*
     * Password
     */
    public static final String ExpiredPassword = "ExpiredPassword";
    public static final String InvalidPasswordPolicy = "InvalidPasswordPolicy";

    /*
     * Content
     */
    public static final String InvalidContentType = "InvalidContentType";
    public static final String MalformedBody = "MalformedBody";

    /*
     * Generic
     */
    public static final String InvalidInput = "InvalidInput";
}
