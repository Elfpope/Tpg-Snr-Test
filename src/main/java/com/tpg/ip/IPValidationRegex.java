package com.tpg.ip;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tpg.common.Constants;

/**
 * Check if the numeric IP address is valid.
 * 
 * @author jun.feng
 *
 */
public class IPValidationRegex
{
	/**
	 * Check if the IP string matches the numeric IP pattern.
	 * 
	 * @param ipStr to check
	 * @return {@code true} if the IP string matches the numeric IP pattern; otherwise {@code false}
	 */
	public static boolean isValidIpPattern(String ipStr)
	{
		if (ipStr == null)
		{
			return false;
		}

		// Check if pattern matches
		Pattern pattern = Pattern.compile(Constants.IP_REGEX);
		Matcher matcher = pattern.matcher(ipStr);
		return matcher.matches();
	}

	/**
	 * Check all 4 numeric IP portions are within range 0~255.
	 * 
	 * @param ipStr the full numeric IP string representation
	 * @return {@code true} if all 4 numeric IP portions are within range 0~255; otherwise {@code false}
	 */
	public static boolean isValidIpRange(String ipStr)
	{
		if (ipStr == null)
		{
			return false;
		}

		StringTokenizer strTokenizer = new StringTokenizer(ipStr, Constants.IP_DELIMITER);
		while (strTokenizer.hasMoreElements())
		{
			int ipPortion = Integer.valueOf((String) strTokenizer.nextElement());
			if ( !isValidIpPortion(ipPortion))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Check if the individual IP portion is within the range 0~255.
	 * 
	 * @param ipPortion to be checked
	 * @return {@code true} if it is within the range 0~255; otherwise {@code false}
	 */
	private static boolean isValidIpPortion(int ipPortion)
	{
		return ipPortion >= Constants.MINIMUM_IP_PORTION_NUMBER && ipPortion <= Constants.MAXIMUM_IP_PORTION_NUMBER;
	}
}
