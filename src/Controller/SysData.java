package Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Address;
import Model.Branch;
import Model.Customer;
import Model.Instructor;
import Model.Receptionist;
import Model.Room;
import Model.RoomRun;
import Model.Subscription;
import utils.Constants;
import utils.E_Cities;
import utils.E_Levels;
import utils.E_Periods;
import utils.E_Rooms;

/**
 * This SysData object ~ represents the class system
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class SysData {
	// -------------------------------Class Members------------------------------
	private ArrayList<Instructor> instructors;
	private ArrayList<Receptionist> receptionists;
	private ArrayList<Branch> branches;
	private ArrayList<Customer> customers;
	private ArrayList<RoomRun> roomRuns;

	// -------------------------------Constructors------------------------------
	public SysData() {
		instructors = new ArrayList<Instructor>();
		receptionists = new ArrayList<Receptionist>();
		branches = new ArrayList<Branch>();
		customers = new ArrayList<Customer>();
		roomRuns = new ArrayList<RoomRun>();
	}

	// -----------------------------------------Getters--------------------------------------
	public ArrayList<Branch> getBranches() {
		return branches;
	}

	public ArrayList<Instructor> getInstructors() {
		return instructors;
	}

	public ArrayList<Receptionist> getReceptionists() {
		return receptionists;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public ArrayList<RoomRun> getRoomRuns() {
		return roomRuns;
	}

	// -------------------------------Add && Remove
	// Methods------------------------------
	/**
	 * This method adds a new branch to our company IFF the branch doesn't
	 * already exist and the details are valid.
	 * 
	 * @param branchNumber
	 * @param branchName
	 * @param city
	 * @param street
	 * @param houseNumber
	 * @param phoneNumber
	 * @return true if the branch was added successfully, false otherwise
	 */
	public boolean addBranch(int branchNumber, String branchName, E_Cities city, String country, String street,
			int houseNumber, String[] phoneNumber) {
		if (branchName != null && branchNumber > 0 && city != null && street != null && houseNumber > 0
				&& phoneNumber != null && country != null) {
			Branch branchToAdd = new Branch(branchNumber);
			if (!branches.contains(branchToAdd)) {
				Address branchAddress = new Address(country, city, street, houseNumber, phoneNumber);
				branchToAdd = new Branch(branchNumber, branchName, branchAddress);
				return branches.add(branchToAdd);
			}
		}
		return false;
	} // ~ END OF addBranch

	/**
	 * Creates and adds a new instructor into the relevant data-structure
	 * 
	 * @param instructor
	 * @return true IF the instructor was added successfully, false otherwise
	 */
	public boolean addInstructor(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, int level, Address address, E_Rooms[] types) {
		if (empNum > 0 && !firstName.equals("") && !lastName.equals("") && birthdate != null && startWorkingDate != null
				&& !password.equals("") && level > 0 && address != null && types != null) {
			Instructor instructor = new Instructor(empNum, firstName, lastName, birthdate, startWorkingDate, password,
					level, address, types);
			if (instructor != null && !instructors.contains(instructor)) {
				return instructors.add(instructor);
			}
		}
		return false;
	} // ~ END OF addInstructor

	/**
	 * Creates and adds a new receptionist into the relevant data-structure
	 *
	 * @param empNum
	 * @param firstName
	 * @param lastName
	 * @param birthdate
	 * @param startWorkingDate
	 * @param password
	 * @param address
	 * @return true IF the receptionist was added successfully, false otherwise
	 */
	public boolean addReceptionist(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, Address address) {
		if (empNum > 0 && !firstName.equals("") && !lastName.equals("") && birthdate != null && startWorkingDate != null
				&& !password.equals("") && address != null) {
			Receptionist receptionist = new Receptionist(empNum, firstName, lastName, birthdate, startWorkingDate,
					password, address);
			if (receptionist != null && !receptionists.contains(receptionist)) {
				return receptionists.add(receptionist);
			}
		}
		return false;
	} // ~ END OF addReceptionist

	/**
	 * Creates and adds a new customer into the relevant data-structure. ID
	 * number length needs to be as it's represented in Constants class and
	 * contains only digits.
	 * 
	 * @see Constants #ID_NUMBER_SIZE
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param birthDate
	 * @param password
	 * @param email
	 * @param customerAddress
	 * @return true if the customer was added successfully, false otherwise
	 */
	public boolean addCustomer(String id, String firstName, String lastName, Date birthDate, String password,
			E_Levels level, URL email, Address customerAddress) {
		// Check validity first
		if (id != null && firstName != null && lastName != null && birthDate != null && password != null
				&& level != null && email != null && customerAddress != null) {
			if (id.length() == Constants.ID_NUMBER_SIZE) {
				for (int i = 0; i < id.length(); i++)
					if (!Character.isDigit(id.charAt(i))) {
						return false;
					}
				// Creating a new customer with his full constructor
				Customer customer = new Customer(id, firstName, lastName, birthDate, password, level, email,
						customerAddress);
				if (!customers.contains(customer)) {
					return customers.add(customer); // Add this customer;
				}
			}
		}
		return false;
	}// ~ END OF addCustomer

	/**
	 * Creates a new subscription, and add it to the relevant customer and
	 * receptionist.
	 * 
	 * @param subNumber
	 * @param custId
	 * @param period
	 * @param startDate
	 * @return true if the subscription was added to the customer, false
	 *         otherwise
	 */
	public boolean addSubToCustomer(int subNumber, String custId, int receptNumber, E_Periods period, Date startDate) {
		// Check validity first
		if (subNumber > 0 && receptNumber > 0 && custId.length() == Constants.ID_NUMBER_SIZE) {
			Customer customer = new Customer(custId);
			Receptionist receptionist = new Receptionist(receptNumber);
			if (customers.contains(customer) && receptionists.contains(receptionist)) {
				// Get the customer
				for (Customer c : customers) {
					if (c != null && customer.equals(c))
						customer = customers.get(customers.indexOf(c));
				}
				// Get the receptionist
				for (Receptionist r : receptionists) {
					if (r != null && receptionist.equals(r))
						receptionist = receptionists.get(receptionists.indexOf(r));
				}
				Subscription subscription = new Subscription(subNumber, customer, receptionist, period, startDate);
				if (customer.addSubscription(subscription)) {
					if (receptionist.addSubscription(subscription)) {
						return true;
					}
					customer.removeSubscription(subscription);
				}
			}
		}
		return false;
	} // ~ END OF addSubToCustomer

	/**
	 * This method connects an instructor to a branch IF the branch and the
	 * instructor exists.
	 * 
	 * @param instructorNumber
	 * @param branchNumber
	 * @return true if the connection was added successfully, false otherwise
	 */
	public boolean connectInstructorToBranch(int instructorNumber, int branchNumber) {
		// Check validity first
		if (instructorNumber > 0 && branchNumber > 0) {
			Instructor instructor = new Instructor(instructorNumber);
			Branch branch = new Branch(branchNumber);
			if (instructors.contains(instructor) && branches.contains(branch)) {
				// Get the branch
				for (Branch br : branches) {
					if (br != null && branch.equals(br))
						branch = branches.get(branches.indexOf(br));
				}
				// Get the instructor
				for (Instructor i : instructors) {
					if (i != null && instructor.equals(i))
						instructor = instructors.get(instructors.indexOf(i));
				}
				if (branch.addInstructor(instructor)) {
					instructor.setWorkBranch(branch);
					return true;
				}
			}
		}
		return false;
	}// ~ END OF connectInstructorToBranch

	/**
	 * This method connects a Receptionist to a branch IF the branch and the
	 * Receptionist exists.
	 * 
	 * @param receptionistNumber
	 * @param branchNumber
	 * @return true if the connection was added successfully, false otherwise
	 */
	public boolean connectReceptionistToBranch(int receptionistNumber, int branchNumber) {
		// Check validity first
		if (receptionistNumber > 0 && branchNumber > 0) {
			Receptionist receptionist = new Receptionist(receptionistNumber);
			Branch branch = new Branch(branchNumber);
			if (receptionists.contains(receptionist) && branches.contains(branch)) {
				// Get the branch
				for (Branch br : branches) {
					if (br != null && branch.equals(br))
						branch = branches.get(branches.indexOf(br));
				}
				// Get the receptionist
				for (Receptionist r : receptionists) {
					if (r != null && receptionist.equals(r))
						receptionist = receptionists.get(receptionists.indexOf(r));
				}
				if (branch.addReceptionist(receptionist)) {
					receptionist.setWorkBranch(branch);
					return true;
				}
			}
		}
		return false;
	}// ~ END OF connectReceptionistToBranch

	/**
	 * this method adds a room to a branch IF the branch already exist
	 * 
	 * @param roomNum
	 * @param maxNumOfTrainers
	 * @param roomType
	 * @param branchNum
	 * @return true if the room was added to the branch, false otherwise
	 */
	public boolean addRoomToBranch(int roomNum, String name, int maxNumOfParticipants, int minNumOfParticipants,
			int timeLinit, E_Levels level, E_Rooms roomType, int branchNum) {
		// Check validity first
		if (roomNum > 0 && maxNumOfParticipants > 0 && minNumOfParticipants > 0 && timeLinit > 0 && branchNum > 0) {
			Branch branch = new Branch(branchNum);
			if (branches.contains(branch)) {
				// Get the branch
				for (Branch br : branches) {
					if (br != null && branch.equals(br))
						branch = branches.get(branches.indexOf(br));
				}
				Room room = new Room(roomNum, name, maxNumOfParticipants, minNumOfParticipants, timeLinit, level,
						roomType, branch);
				return branch.addRoom(room);
			}
		}
		return false;
	}// ~ END OF addRoomToBranch

	/**
	 * This method add a new roomRun to SysData Hint- think of all the things
	 * that are related to a roomRun or should store the roomRun, and don't
	 * forget to rollBack if needed
	 * 
	 * @param roomRunNum
	 *            \ * @param dateTime
	 * @param level
	 * @param instructorNum
	 * @param branchNum
	 * @param roomNum
	 * @return true if a roomRun was added, false otherwise
	 */
	public boolean addRoomRun(int roomRunNum, Date dateTime, int duration, int instructorNum, int branchNum,
			int roomNum) {
		// Check validity first
		if (roomRunNum > 0 && dateTime != null && duration > 0 && instructorNum > 0 && branchNum > 0 && roomNum > 0) {
			RoomRun roomRun = new RoomRun(roomRunNum);
			Branch branch = new Branch(branchNum);
			Instructor instructor = new Instructor(instructorNum);
			Room room = new Room(roomNum);
			if (branches.contains(branch) && instructors.contains(instructor) && !roomRuns.contains(roomRun)) {
				// Get the branch
				for (Branch br : branches) {
					if (br != null && branch.equals(br))
						branch = branches.get(branches.indexOf(br));
				}
				// Get the instructor
				for (Instructor i : instructors) {
					if (i != null && instructor.equals(i))
						instructor = instructors.get(instructors.indexOf(i));
				}
				// Get the room
				boolean flag = false;
				if (branch.getRooms().containsKey(room.getRoomNum())) {
					room = branch.getRooms().get(room.getRoomNum());
					flag = true;
				}
				if (flag) {
					roomRun = new RoomRun(roomRunNum, dateTime, duration, instructor, room);
					if (instructor.addRoomRun(roomRun)) {
						if (room.addRoomRun(roomRun)) {
							return roomRuns.add(roomRun);
						}
						instructor.deleteRoomRun(roomRun);
					}
				}
			}
		}
		return false;
	}

	/**
	 * This method adds a customer to a specific roomRun if his subscription
	 * fits, he has no other roomRuns at the time, and there is a free space in
	 * the class Hint: if needed, don't forget to rollback
	 * 
	 * @param custNum
	 * @param roomRunNum
	 * @return true if the customer is registered to the roomRun, false
	 *         otherwise
	 */
	public boolean addCustomerToRoomRun(String custNum, int roomRunNum) {
		// Check validity first
		if (custNum.length() == Constants.ID_NUMBER_SIZE && roomRunNum > 0) {
			Customer customer = new Customer(custNum);
			RoomRun roomRun = new RoomRun(roomRunNum);
			if (customers.contains(customer) && roomRuns.contains(roomRun)) {
				// Get the customer
				for (Customer c : customers) {
					if (c != null && customer.equals(c))
						customer = customers.get(customers.indexOf(c));
				}
				// Get the roomRun
				for (RoomRun l : roomRuns) {
					if (l != null && roomRun.equals(l))
						roomRun = roomRuns.get(roomRuns.indexOf(l));
				}
				if (customer.addRoomRun(roomRun)) {
					if (roomRun.addParticipant(customer)) {
						return true;
					}
					customer.deleteRoomRun(roomRun);
				}
			}
		}
		return false;
	}

	/**
	 * This method change the address of existing customer IFF the customer
	 * already exist and the Address details are valid.
	 * 
	 * @param id
	 * @param country
	 * @param city
	 * @param street
	 * @param houseNumber
	 * @param phonenumbers
	 * @return true if succeeded, false otherwise
	 */
	public boolean changeCustomerAddress(String id, String country, E_Cities city, String street, int houseNumber,
			String[] phonenumbers) {
		// Check validity first
		if (id.length() == Constants.ID_NUMBER_SIZE && country != null && city != null && street != null
				&& houseNumber > 0 && phonenumbers != null) {
			Customer customer = new Customer(id);
			if (customers.contains(customer)) {
				for (Customer c : customers) {
					if (c != null && customer.equals(c))
						customer = customers.get(customers.indexOf(c));
				}
				customer.setCustomerAddress(new Address(country, city, street, houseNumber, phonenumbers));
				return true;
			}
		}
		return false;
	} // ~ END OF changeCustomerAddress

	/**
	 * This method cancels a subscription from the system using the subNumber
	 * (Primary Key). The subscription will be canceled IFF all related objects
	 * will delete from SysData
	 * 
	 * @param subNumber
	 * @return true if subscription was canceled, false otherwise
	 */
	public boolean removeSubscription(int subNumber) {
		// Check validity first
		if (subNumber > 0) {
			Subscription subscription = new Subscription(subNumber);
			for (Customer c : customers) {
				if (c != null) {
					for (int i = 0; i < c.getSubs().length; i++) {
						if (c.getSubs()[i] != null) {
							if (c.getSubs()[i] == subscription) {
								subscription = c.getSubs()[i];
							}
						}
					}
				}
				if (subscription == null) {
					return false;
				}
				if (c.removeSubscription(subscription)) {
					return true;
				}
			}
		}
		return false;
	}// ~ END OF removeSubscription
	// -------------------------------Queries------------------------------
	// ===================================================
	// HW_1_Queries
	// ===================================================

	/**
	 * This method returns all roomRuns of the most active customer. Most active
	 * customer is the customer with the most PARTICIPATED roomRuns A roomRuns
	 * will be counted as participated if its date has past already
	 * 
	 * @return participatedRoomRuns if found, empty list otherwise 
	 */
	public List<RoomRun> getAllParticipatedRoomRunsOfMostActiveCustomer() {
		int numberOfRoomRuns = 0;
		Customer mostActiveCustomer = null;
		for (Customer customer : customers) {
			if (customer.getParticipatedRoomRuns().size() > numberOfRoomRuns) {
				numberOfRoomRuns = customer.getParticipatedRoomRuns().size();
				mostActiveCustomer = customer;
			}
		}
		return mostActiveCustomer.getParticipatedRoomRuns();
	} // ~ END OF getAllParticipatedRoomRunsOfMostActiveCustomer

	/**
	 * This method finds the receptionisnt who sold the biggest amount of
	 * subscriptions this january.
	 * 
	 * @return topJanuaryReceptionist if found, null otherwise
	 */
	public Receptionist getTopJanuaryReceptionist() {
		int numOfAssignments = 0;
		Receptionist topJanuaryReceptionist = null;
		for (Receptionist r : receptionists) {
			if (r.getNumberOfThisYearJanuaryAssignments() > numOfAssignments) {
				numOfAssignments = r.getNumberOfThisYearJanuaryAssignments();
				topJanuaryReceptionist = r;
			}
		}
		return topJanuaryReceptionist;
	} // ~END OF getTopJanuaryReceptionist
	/**
	 * This query returns the most popular room type. Most popular room type is
	 * the type with the highest number of registered roomRuns.
	 * 
	 * @return mostPopularRoomType if found, null otherwise
	 */
	public E_Rooms getTheMostPopularRoomType() {
		HashMap<E_Rooms, Integer> types = new HashMap<>();
		int max = 0;
		E_Rooms toReturn = null;
		for (RoomRun rr : roomRuns) {
			if (rr != null) {
				if (types.containsKey(rr.getRoom().getRoomType())) {
					types.put(rr.getRoom().getRoomType(), types.get(rr.getRoom().getRoomType()) + 1);
				} else
					types.put(rr.getRoom().getRoomType(), new Integer(1));
			}
		}
		for (Map.Entry<E_Rooms, Integer> entry : types.entrySet()) {
			E_Rooms key = entry.getKey();
			Integer value = entry.getValue();
		    if (value > max) {
				max = value;
				toReturn = key;
			}
		}
		return toReturn;
	} // ~END OF getTheMostPopularRoomType
	

	/**
	 * This method finds all of the super senior instructors of this month. An instructor
	 * is considered a super senior instructor if he started working over 15 years
	 * ago and guided at least 2 roomRuns this month
	 * 
	 * @return an array of super senior instructors if found, null otherwise
	 */
	@SuppressWarnings("deprecation")
	public Instructor[] getAllSuperSeniorInstructors() {
		int roomRunsThisMonth = 0;
		Date today = new Date();
		ArrayList<Instructor> superSenior = new ArrayList<Instructor>();
		for (Instructor i : instructors) {
			roomRunsThisMonth = 0;
			if (i != null) {
				for (RoomRun rr : i.getRoomRuns()) {
					if (rr != null && rr.getStartDateTime().getMonth() == today.getMonth()) {
						roomRunsThisMonth++;
					}
				}
				if (i.getEmployeeSeniority() > 15 && roomRunsThisMonth > 1) {
					superSenior.add(i);
				}
			}
		}
		return superSenior.toArray(new Instructor[superSenior.size()]);
	} // ~END OF getAllSuperSeniorInstructors
	
	@SuppressWarnings("deprecation")
	public Date getTheMostActiveDay() {
		HashMap<Date, Integer> dates = new HashMap<>();
		int max = 0;
		Date mostActive = null;
		Date onlyDate = null;
		for (RoomRun rr : roomRuns) {
			if (rr != null) {
				onlyDate = new Date(rr.getStartDateTime().getYear(), rr.getStartDateTime().getMonth(), rr.getStartDateTime().getDate());
				if (dates.containsKey(onlyDate)) {
					dates.put(onlyDate, dates.get(onlyDate) + 1);
				} else
					dates.put(onlyDate, new Integer(1));
			}
		}
		for (Map.Entry<Date, Integer> entry : dates.entrySet()) {
			Date key = entry.getKey();
			Integer value = entry.getValue();
		    if (value > max) {
				max = value;
				mostActive = key;
			}
		}
		return mostActive;
	} /// ~END OF getTheMostActiveDay
}// ~ END OF Class SysData
