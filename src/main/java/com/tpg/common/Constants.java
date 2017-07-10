package com.tpg.common;

/**
 * Stores all constants used in this application.
 * 
 * @author jun.feng
 *
 */
public class Constants
{
	// IP address constants
	public static final String IP_ADDRESS_FILE = "/ip_address.txt";

	public static final String IP_DELIMITER = ".";
	public static final String IP_REGEX = "^\\d{1,3}(\\.\\d{1,3}){3}$";

	public static final int MINIMUM_IP_PORTION_NUMBER = 0;
	public static final int MAXIMUM_IP_PORTION_NUMBER = 255;

	// Stopwatch constants
	public static final int ONE_MILLION = 1000000;
	public static final int NUMBER_OF_INVOCATIONS = 2000;
	public static final int NUMBER_OF_DECIMAL_PLACES = 4;
}
