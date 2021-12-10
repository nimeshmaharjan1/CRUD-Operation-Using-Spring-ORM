package com.spring.orm;

import org.hibernate.annotations.SourceType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.io.InputStreamReader;
import java.util.List;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		StudentDao studentDao = context.getBean("studentDao", StudentDao.class);
		// Student student = new Student(4, "Kiran Maharjan", "Lalitpur");
		// int r = studentDao.insert(student);
		// System.out.println("Done " + r);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean start = true;
		while (start) {
			System.out.println("Press 1 for entering a new student");
			System.out.println("Press 2 for displaying all the students");
			System.out.println("Press 3 for searching a student through their ID");
			System.out.println("Press 4 to delete a student");
			System.out.println("Press 5 to update the details of a student");
			System.out.println("Press 6 to exit.");
			try {
				int input = Integer.parseInt(br.readLine());

				switch (input) {
					case 1:
						System.out.println("Enter user ID: ");
						int userId = Integer.parseInt(br.readLine());
						System.out.println("Enter user name: ");
						String userName = br.readLine();
						System.out.println("Enter user city: ");
						String userCity = br.readLine();

						Student student1 = new Student();
						student1.setStudentId(userId);
						student1.setStudentName(userName);
						student1.setStudentCity(userCity);
						int r = studentDao.insert(student1);
						System.out.println(r + "Added");
						System.out.println("---------------------------------");
						break;

					case 2:
						List<Student> allStudents = studentDao.getAllStudents();
						for (Student st : allStudents) {

							System.out.println(st.getStudentId());
							System.out.println(st.getStudentName());
							System.out.println(st.getStudentCity());
							System.out.println("---------------------------------");
						}
						break;

					case 3:
						// System.out.println("")
						System.out.print("Enter user ID: ");
						int singleId = Integer.parseInt(br.readLine());
						Student student = studentDao.getStudent(singleId);
						System.out.println(student.getStudentId());
						System.out.println(student.getStudentName());
						System.out.println(student.getStudentCity());
						System.out.println("---------------------------------");

						break;

					case 4:
						System.out.print("Enter user ID: ");
						int deleteId = Integer.parseInt(br.readLine());
						studentDao.deleteStudent(deleteId);
						System.out.println("Student Deleted.");
						break;

					case 5:
						break;

					case 6:
						start = false;
						break;

				}
			} catch (Exception e) {
				System.out.println("Invalid Input");
				System.out.println(e.getMessage());
			}
		}
	}

}
