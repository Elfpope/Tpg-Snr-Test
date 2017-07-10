package com.tpg.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.tpg.comparator.SortByGpaNNameNId;
import com.tpg.ip.IPValidationRegex;
import com.tpg.model.Student;
import com.tpg.timer.StopWatch;

/**
 * Drive the application for demostration.
 * 
 * @author jun.feng
 *
 */
public class AppDriver
{
	public static void main(String[] args)
	{
		demoIpValidationRegex();

		demoSortStudents();

		demoStopWatch();
	}

	/**
	 * Question 1 (IP Address) demo
	 */
	private static void demoIpValidationRegex()
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
				if (IPValidationRegex.isValidIpPattern(currentLine) && IPValidationRegex.isValidIpRange(currentLine))
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

		System.out.println("Question 1 (IP Address) demo");
		System.out.println("Valid IPs: " + validIPs);
		System.out.println("Invalid IPs: " + invalidIPs);
	}

	/**
	 * Question 2 (Sorting Students) demo
	 */
	private static void demoSortStudents()
	{
		List<Student> students = new ArrayList<>();

		students.add(new Student(33, "Tina", 3.68));
		students.add(new Student(85, "Louis", 3.85));
		students.add(new Student(56, "Samil", 3.75));
		students.add(new Student(19, "Samar", 3.75));
		students.add(new Student(22, "Lorry", 3.76));
		students.add(new Student(10, "Samar", 3.75));

		System.out.println("\nQuestion 2 (Sorting Students) demo");
		System.out.println("----- Before sorted: -----");
		students.forEach(item -> System.out.println(item));

		students.sort(new SortByGpaNNameNId());

		System.out.println("\n----- After sorted: -----");
		students.forEach(item -> System.out.println(item));
	}

	/**
	 * Question 3 (Stopwatch) demo
	 */
	private static void demoStopWatch()
	{
		Student student = new Student(10000, "dummy student", 4.85);
		String methodName = "setId";
		Object[] arguments = { 123 };

		StopWatch stopWatch = new StopWatch();
		try
		{
			stopWatch.timeMethod(student, methodName, arguments);
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e)
		{
			System.out.println(String.format("Error is encountered when measuring method [%s] of object [%s].", methodName, student));
		}

		System.out.println("\nQuestion 3 (Stopwatch) demo");
		System.out.println(String.format("Execution time for method [%s] of object [%s]: %.4f in millisecond", methodName, student, stopWatch.toMilliSecond()));
	}
}
