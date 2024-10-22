package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("=== TEST 1: department findById =====");
		Department department = departmentDao.findById(3);
		System.out.println(department);

		System.out.println("=== TEST 2: department findAll =====");
		List <Department> list = departmentDao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}

		System.out.println("=== TEST 3: department insert =====");
		department = new Department(6, "Comedy");
		departmentDao.insert(department);
		System.out.println("Inserted! New id = " + department.getId());

		System.out.println("=== TEST 4: department update =====");
		department = departmentDao.findById(1);
		department.setName("Food");
		departmentDao.update(department);
		System.out.println("Updated completed!");

		System.out.println("=== TEST 5: department delete =====");
		System.out.println("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed!");

		sc.close();

	}

}
