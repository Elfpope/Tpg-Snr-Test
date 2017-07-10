package com.tpg.timer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import com.tpg.common.Constants;

/**
 * Stopwatch provides a comprehensive timming mechanism to calculate the duration of method execution.
 * 
 * @author jun.feng
 *
 */
public class StopWatch
{
	private boolean isRunning;
	private boolean isInitialStart;

	private long startTime;
	private long stopTime;
	private long duration;

	/**
	 * Default constructor
	 */
	public StopWatch()
	{
		initialize();
	}

	/**
	 * Start the stopwatch, which is required before each measurement.
	 */
	public void start()
	{
		if ( !isRunning && isInitialStart)
		{
			initialize();
			isRunning = true;
			isInitialStart = false;
			startTime = System.nanoTime();
		}
		else
		{
			throw new IllegalStateException("Stopwatch is already running. When start it, the stopwatch must NOT be running.");
		}
	}

	/**
	 * Resume the stopwatch which has been paused previously.
	 */
	public void resume()
	{
		if ( !isRunning && !isInitialStart)
		{
			initTime();
			isRunning = true;
			startTime = System.nanoTime();
		}
		else
		{
			throw new IllegalStateException("Stopwatch is already running. When start it, the stopwatch must NOT be running.");
		}
	}

	/**
	 * Pause or stop the stopwatch, either to calculate duration or to accumulate duration for later calculation.
	 */
	public void stop()
	{
		if (isRunning)
		{
			stopTime = System.nanoTime();
			duration += stopTime - startTime;
			isRunning = false;
			if (isInitialStart)
			{
				isInitialStart = false;
			}
		}
		else
		{
			throw new IllegalStateException("Stopwatch is NOT running. When stop it, the stopwatch must be running.");
		}
	}

	/**
	 * Initialize all properties.
	 */
	private void initialize()
	{
		duration = 0L;
		isInitialStart = true;
		isRunning = false;
		initTime();
	}

	/**
	 * Initialize only startTime and stopTime.
	 */
	private void initTime()
	{
		startTime = 0L;
		stopTime = 0L;
	}

	/**
	 * Get duration stored in the stopwatch.
	 * 
	 * @return duration in nanosecond
	 */
	public long getDuration()
	{
		if (isRunning)
		{
			throw new IllegalStateException("Stopwatch is still running. When calculating duration, the stopwatch must NOT be running.");
		}
		return duration;
	}

	/**
	 * Get the duration stored in the stopwatch.
	 * 
	 * @return duration in millisecond
	 */
	public double toMilliSecond()
	{
		BigDecimal result = new BigDecimal(0);
		if (getDuration() != 0)
		{
			BigDecimal durationBd = new BigDecimal(getDuration());
			BigDecimal dividorBd = new BigDecimal(Constants.ONE_MILLION);
			result = durationBd.divide(dividorBd, Constants.NUMBER_OF_DECIMAL_PLACES, BigDecimal.ROUND_HALF_UP);
		}
		return result.doubleValue();
	}

	/**
	 * Times execution time of the method of given clazz by averaging it in large number of time (over thousands).
	 * 
	 * <p>
	 * <b>Note: the timed method must be public accessable. </b>
	 * </p>
	 * 
	 * @param object
	 * @param methodName
	 * @param args
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public void timeMethod(Object object, String methodName, Object ... args) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException
	{
		Method[] methods = object.getClass().getDeclaredMethods();
		for (int i = 0; i < Constants.NUMBER_OF_INVOCATIONS; i++ )
		{
			invokeMethod(methodName, methods, object, i, args);
		}
	}

	/**
	 * Invoke the method with the object based on the given methondName.
	 * 
	 * @param methodName indicating which method to be invoked
	 * @param methods the collection of methods belong to the given object
	 * @param object it invokes the method
	 * @param i indicating what number of time it is currently executing
	 * @param args to be used in the invoked method
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void invokeMethod(String methodName, Method[] methods, Object object, int i, Object ... args) throws IllegalAccessException, InvocationTargetException
	{
		for (Method item : methods)
		{
			if (item.getName().toLowerCase().equals(methodName.toLowerCase()))
			{
				if (i == 0)
				{
					start();
				}
				else
				{
					resume();
				}
				item.invoke(object, args);
				stop();
			}
		}
	}
}
