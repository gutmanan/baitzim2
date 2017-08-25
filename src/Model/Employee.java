package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class Employee ~ represent a single Employee of the company
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Employee {
	// -------------------------------Class  Members------------------------------
	private int empNum;
	private String firstName;
	private String lastName;
	private Date birthdate;
	private Date startWorkingDate;
	private String Password;
	private Branch workBranch;
	private Address address;

	// -------------------------------Constructors------------------------------
	public Employee(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, Address address) {
		this.empNum = empNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.startWorkingDate = startWorkingDate;
		this.Password = password;
		this.address = address;
	}

	public Employee(int empNum) {
		this.empNum = empNum;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getEmployeeNumber() {
		return empNum;
	}

	public void setEmployeeNumber(int empNum) {
		this.empNum = empNum;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getStartWorkingDate() {
		return startWorkingDate;
	}

	public void setStartWorkingDate(Date startWorkingDate) {
		this.startWorkingDate = startWorkingDate;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Branch getWorkBranch() {
		return workBranch;
	}

	public void setWorkBranch(Branch workBranch) {
		this.workBranch = workBranch;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method calculate this employee seniority (in years). If he started
	 * to work in this year, than his seniority is 0 years.
	 * 
	 * @return employee seniority
	 */
	@SuppressWarnings("deprecation")
	public int getEmployeeSeniority() {
		return new Date().getYear() - this.startWorkingDate.getYear();
	}

	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empNum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empNum != other.empNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		DateFormat formatter = new SimpleDateFormat("d/M/yyyy");
		return "employeeNumber=" + empNum + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ formatter.format(birthdate) + ", startWorkingDate= " + formatter.format(startWorkingDate)
				+ ", password=" + Password;
	}
}
