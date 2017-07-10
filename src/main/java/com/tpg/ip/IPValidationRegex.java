package com.tpg.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tpg.common.Constants;

public class IPValidationRegex
{
	public static void main(String[] args)
	{
		List<String> validIPs = new ArrayList<>();
		List<String> invalidIPs = new ArrayList<>();

		String fileName = Constants.IP_ADDRESS_FILE;
		InputStream is = IPValidationRegex.class.getResourceAsStream(fileName);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is)))
		{
			String currentLine;
			while ((currentLine = br.readLine()) != null)
			{
				if (isValidIpPattern(currentLine) && isValidIpRange(currentLine))
				{
					validIPs.add(currentLine);
				}
				else
				{
					invalidIPs.add(currentLine);
				}
			}
		}
		catch (IOException e)
		{
			System.out.println(String.format("Error is encountered during reading file [%s].", fileName));
		}

		System.out.println("Valid IPs: " + validIPs);
		System.out.println("Invalid IPs: " + invalidIPs);
	}

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

	private static boolean isValidIpPortion(int ipPortion)
	{
		return ipPortion >= Constants.MINIMUM_IP_PORTION_NUMBER && ipPortion <= Constants.MAXIMUM_IP_PORTION_NUMBER;
	}
}
