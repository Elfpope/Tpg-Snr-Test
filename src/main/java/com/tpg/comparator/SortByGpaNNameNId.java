package com.tpg.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.tpg.model.Student;

public class SortByGpaNNameNId implements Comparator<Student>
{

	public static void main(String[] args)
	{
		List<Student> students = new ArrayList<>();

		students.add(new Student(33, "Tina", 3.68));
		students.add(new Student(85, "Louis", 3.85));
		students.add(new Student(56, "Samil", 3.75));
		students.add(new Student(19, "Samar", 3.75));
		students.add(new Student(22, "Lorry", 3.76));
		students.add(new Student(19, "Samar", 3.75));

		System.out.println("\n----- Before sorted: -----");
		students.forEach(item -> System.out.println(item));

		students.sort(new SortByGpaNNameNId());

		System.out.println("\n----- After sorted: -----");
		students.forEach(item -> System.out.println(item));
	}

	/**
	 * Compares 2 students and consider student1 proceeds student2 with below rules:
	 * <ul>
	 * <li>firstly by descending GPA ;</li>
	 * <li>secondly by ascending name;</li>
	 * <li>thirdly by ascending Id</li>
	 * <ul>
	 * 
	 * @return
	 *         <ul>
	 *         <li>positive integer if student1 > student2 based on above rules;</li>
	 *         <li>0 if student1 equals to student2 based on above rules;</li>
	 *         <li>negative integer if student1 < student2 based on above rules</li>
	 *         <ul>
	 */
	@Override
	public int compare(Student student1, Student student2)
	{
		int result = 0;

		// diffGpa > 0 means student1's GPA is greater than student2's, by default set to 0 (equal)
		int diffGpa = getGpaDiff(student1, student2);

		// nameDiff > 0 means student1's firstname lexicographically precedes student2's
		int nameDiff = getNameDiff(student1, student2);

		// idDiff > 0 means student1's Id is lower than student2's
		int idDiff = getIdDiff(student1, student2);

		if (diffGpa != 0)
		{
			result = -diffGpa; // sort by descending GPA
		}
		else if (diffGpa == 0 && nameDiff != 0)
		{
			result = nameDiff;
		}
		else if (diffGpa == 0 && nameDiff == 0 && idDiff != 0)
		{
			result = idDiff;
		}
		else
		{
			System.out.println("\nError [Invalid data]: both students have the same ID.");
		}

		return result;
	}

	/**
	 * Calculates the difference of two students' Id.
	 * 
	 * @param student1
	 * @param student2
	 * @return the difference of two student's Id
	 * 
	 */
	private int getIdDiff(Student student1, Student student2)
	{
		int idDiff = student1.getId() - student2.getId();
		return idDiff;
	}

	/**
	 * Calculates the difference of two students' names.
	 * 
	 * @param student1
	 * @param student2
	 * @return
	 *         <ul>
	 *         <li>positive integer if the name of student1 lexicographically precedes the student2's;</li>
	 *         <li>0 if they are equal;</li>
	 *         <li>negative integer if the name of student1 lexicographically follows the student2's</li>
	 *         <ul>
	 */
	private int getNameDiff(Student student1, Student student2)
	{
		int nameDiff = student1.getName().compareTo(student2.getName());
		return nameDiff;
	}

	/**
	 * Calculates the difference of two students' GPAs.
	 * 
	 * @param student1
	 * @param student2
	 * @return
	 *         <ul>
	 *         <li>1 if GPA of student1 is greater than the student2's;</li>
	 *         <li>0 if they have the same GPA;</li>
	 *         <li>-1 if GPA of student1 is less than the student2's</li>
	 *         <ul>
	 */
	private int getGpaDiff(Student student1, Student student2)
	{
		int diffGpa = 0;
		double diffGpaDouble = student1.getGpa() - student2.getGpa();
		if (diffGpaDouble > 0)
		{
			diffGpa = 1;
		}
		else if (diffGpaDouble < 0)
		{
			diffGpa = -1;
		}
		return diffGpa;
	}
}
