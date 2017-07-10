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

	@Override
	public int compare(Student o1, Student o2)
	{
		int result = 0;

		// diffGpa > 0 means o1's GPA is greater than o2's, by default set to 0 (equal)
		int diffGpa = getGpaDiff(o1, o2);

		// nameDiff > 0 means o1's firstname lexicographically precedes o2's
		int nameDiff = getNameDiff(o1, o2);

		// idDiff > 0 means o1's Id is lower than o2's
		int idDiff = getIdDiff(o1, o2);

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

	private int getIdDiff(Student o1, Student o2)
	{
		int idDiff = o1.getId() - o2.getId();
		return idDiff;
	}

	private int getNameDiff(Student o1, Student o2)
	{
		int nameDiff = o1.getName().compareTo(o2.getName());
		return nameDiff;
	}

	private int getGpaDiff(Student o1, Student o2)
	{
		int diffGpa = 0;
		double diffGpaDouble = o1.getGpa() - o2.getGpa();
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
