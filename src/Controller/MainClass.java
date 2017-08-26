package Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;

import Model.Address;
import Model.Customer;
import Model.Instructor;
import Model.Receptionist;
import Model.RoomRun;
import utils.E_Cities;
import utils.E_Equipment;
import utils.E_Levels;
import utils.E_Periods;
import utils.E_Rooms;
import utils.MyFileLogWriter;

/**
 * This MainClass object - represents the program runner
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class MainClass {

	/**
	 * The command read from the input file
	 */
	private static String command;

	/**
	 * To check if the command updated the objects
	 */
	private static boolean isUpdated;

	/**
	 * The date format
	 */
	private static DateFormat df;

	/**
	 * The date & time format
	 */
	private static DateFormat dft;

	/**
	 * The main object for the program
	 */
	private static SysData SysData;

	/**
	 * Scanner for input
	 */
	private static Scanner input;

	/**
	 * The main method of this system, gets input from input.txt and Writes
	 * output to output.txt file
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ParseException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException, ParseException, ClassNotFoundException {
		// Create Scanner for the text file named "input.txt"
		input = new Scanner(new File("input.txt"));
		// Define Date format
		df = new SimpleDateFormat("dd/MM/yyyy");
		df.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
		// Define Date & Time format
		dft = new SimpleDateFormat("dd/MM/yyyy;HH:mm");
		dft.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));
		// Writer buffer creation used after update
		MyFileLogWriter.initializeMyFileWriter();
		// if the file has next - not empty
		if (input.hasNext()) {
			SysData = new SysData();
		}
		/*
		 * read the commands. loop while input file [input.hasnext()] and the
		 * SysData is not null
		 */
		while (input.hasNext() && SysData != null) {
			/*
			 * read the command, must be first at line because command value
			 * determine which method will be activated in SysData object.
			 */
			command = input.next();
			isUpdated = false;
			// ================ Building Command ================
			if (command.equals("addBranch")) {
				// create and add new Branch to IEscape
				int branchNumber = Integer.parseInt(input.next());
				String branchName = input.next();
				String country = input.next();
				E_Cities city = E_Cities.valueOf(input.next());
				String street = input.next();
				int housNumber = Integer.parseInt(input.next());
				String[] phoneNumber = { input.next() };

				isUpdated = SysData.addBranch(branchNumber, branchName, city, country, street, housNumber, phoneNumber);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine(
							"addBranch returns: " + "Successfully added Branch " + branchNumber + " to SysData");
				} else {
					MyFileLogWriter.writeToFileInSeparateLine(
							"addBranch returns: " + "Failed to add new Branch " + branchNumber + " to SysData");
				}
			}

			// ================ Building Command ================
			else if (command.equals("addInstructor")) {
				// create and add new Instructor to IEscape
				int employeeNumber = Integer.parseInt(input.next());
				String firstName = input.next();
				String lastName = input.next();
				Date birthDate = df.parse(input.next());
				Date startWorkingDate = df.parse(input.next());
				String password = input.next();
				int level = Integer.parseInt(input.next());
				String[] temp = input.next().split(",");
				E_Rooms[] types = new E_Rooms[temp.length];
				for (int i = 0; i < temp.length; i++)
					types[i] = E_Rooms.valueOf(temp[i]);
				String country = input.next();
				E_Cities city = E_Cities.valueOf(input.next());
				String street = input.next();
				int housNumber = Integer.parseInt(input.next());
				String[] phoneNumber = { input.next() };
				Address address = new Address(country, city, street, housNumber, phoneNumber);

				isUpdated = SysData.addInstructor(employeeNumber, firstName, lastName, birthDate, startWorkingDate,
						password, level, address, types);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addInstructor returns: "
							+ "Successfully added instructor " + employeeNumber + " to SysData");
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("addInstructor returns: "
							+ "Failed to add new instructor " + employeeNumber + " to SysData");
				}
			}

			// ================ Building Command ================
			else if (command.equals("addReceptionist")) {
				// create and add new Receptionist to IEscape
				int employeeNumber = Integer.parseInt(input.next());
				String firstName = input.next();
				String lastName = input.next();
				Date birthDate = df.parse(input.next());
				Date startWorkingDate = df.parse(input.next());
				String password = input.next();
				String country = input.next();
				E_Cities city = E_Cities.valueOf(input.next());
				String street = input.next();
				int housNumber = Integer.parseInt(input.next());
				String[] phoneNumber = { input.next() };
				Address address = new Address(country, city, street, housNumber, phoneNumber);

				isUpdated = SysData.addReceptionist(employeeNumber, firstName, lastName, birthDate, startWorkingDate,
						password, address);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addReceptionist returns: "
							+ "Successfully added receptionist " + employeeNumber + " to SysData");
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("addReceptionist returns: "
							+ "Failed to add new receptionist " + employeeNumber + " to SysData");
				}
			}

			// ================ Building Command ================
			else if (command.equals("addCustomer")) {
				// create and add new Customer to IEscape
				String id = input.next();
				String firstName = input.next();
				String lastName = input.next();
				Date birthDate = df.parse(input.next());
				String password = input.next();
				E_Levels level = E_Levels.valueOf(input.next());
				URL email = new URL(input.next());
				String country = input.next();
				E_Cities city = E_Cities.valueOf(input.next());
				String street = input.next();
				int housNumber = Integer.parseInt(input.next());
				String[] phoneNumber = { input.next() };
				Address address = new Address(country, city, street, housNumber, phoneNumber);

				isUpdated = SysData.addCustomer(id, firstName, lastName, birthDate, password, level, email, address);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine(
							"addCustomer returns: " + "Successfully added Customer with id " + id + " to SysData");
				} else {
					MyFileLogWriter.writeToFileInSeparateLine(
							"addCustomer returns: " + "Failed to add new Customer with id " + id + " to SysData");
				}
			}

			// ================ Building Command ================
			else if (command.equals("addSubToCustomer")) {
				// create and add new subscription to customer
				int subNumber = Integer.parseInt(input.next());
				String custId = input.next();
				int receptNumber = Integer.parseInt(input.next());
				E_Periods period = E_Periods.valueOf(input.next());
				Date startDate = df.parse(input.next());
				isUpdated = SysData.addSubToCustomer(subNumber, custId, receptNumber, period, startDate);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addSubToCustomer returns: "
							+ "Successfully added subscription " + subNumber + " to customer " + custId);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("addSubToCustomer returns: "
							+ "Failed to add new subscription " + subNumber + " to customer " + custId);
				}
			}

			// ================ Building Command ================
			else if (command.equals("connectInstructorToBranch")) {
				// Connect between an existing instructor and an existing branch
				int instructorNumber = Integer.parseInt(input.next());
				int branchNumber = Integer.parseInt(input.next());

				isUpdated = SysData.connectInstructorToBranch(instructorNumber, branchNumber);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine(
							"connectInstructorToBranch returns: " + "Successfully connected between instructor "
									+ instructorNumber + " and branch " + branchNumber);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine(
							"connectInstructorToBranch returns: " + "Failed to connected between instructor "
									+ instructorNumber + " and branch " + branchNumber);
				}
			}

			// ================ Building Command ================
			else if (command.equals("connectReceptionistToBranch")) {
				// Connect between an existing receptionist and an existing
				// branch
				int receptionistNumber = Integer.parseInt(input.next());
				int branchNumber = Integer.parseInt(input.next());

				isUpdated = SysData.connectReceptionistToBranch(receptionistNumber, branchNumber);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine(
							"connectReceptionistToBranch returns: " + "Successfully connected between receptionist "
									+ receptionistNumber + " and branch " + branchNumber);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine(
							"connectReceptionistToBranch returns: " + "Failed to connected between receptionist "
									+ receptionistNumber + " and branch " + branchNumber);
				}
			}

			// ================ Building Command ================
			else if (command.equals("addRoomToBranch")) {
				// add a room to an existing branch
				int roomNum = Integer.parseInt(input.next());
				String name = input.next();
				int maxNumOfParticipants = Integer.parseInt(input.next());
				int minNumOfParticipants = Integer.parseInt(input.next());
				int timeLimit = Integer.parseInt(input.next());
				E_Levels level = E_Levels.valueOf(input.next());
				E_Rooms roomType = E_Rooms.valueOf(input.next());
				int branchNum = Integer.parseInt(input.next());

				isUpdated = SysData.addRoomToBranch(roomNum, name, maxNumOfParticipants, minNumOfParticipants,
						timeLimit, level, roomType, branchNum);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addRoomToBranch returns: " + "Successfully added room "
							+ roomNum + " to branch " + branchNum);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine(
							"addRoomToBranch returns: " + "Failed to add room " + roomNum + " to branch " + branchNum);
				}
			}

			// ================ Building Command ================
			else if (command.equals("addHintToRoom")) {
				int branchNum = Integer.parseInt(input.next());
				int roomNum = Integer.parseInt(input.next());
				int hintNum = Integer.parseInt(input.next());
				String text = input.next();
				text = text.replace('-', ' ');

				isUpdated = SysData.addHintToRoom(branchNum, roomNum, hintNum, text);
				if (isUpdated) { // if adding successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addHintToRoom returns: " + "Successfully added hint "
							+ hintNum + " to room " + roomNum + " in branch " + branchNum);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("addHintToRoom returns: " + "Failed to add hint "
							+ roomNum + " to room " + roomNum + " in branch " + branchNum);
				}
			}

			// ================ Building Command ================
			else if (command.equals("addRoomRun")) {
				// add a roomRun to an existing room in a specific branch
				int roomRunNum = Integer.parseInt(input.next());
				Date dateTime = dft.parse(input.next());
				int duration = Integer.parseInt(input.next());
				int instructorNum = Integer.parseInt(input.next());
				int branchNum = Integer.parseInt(input.next());
				int roomNum = Integer.parseInt(input.next());

				isUpdated = SysData.addRoomRun(roomRunNum, dateTime, duration, instructorNum, branchNum, roomNum);
				if (isUpdated) { // if added successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addRoomRun returns: " + "Successfully added RoomRun "
							+ roomRunNum + " at " + dateTime + " to room " + roomNum + " in branch " + branchNum);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("addRoomRun returns: " + "Failed to add RoomRun "
							+ roomRunNum + " at " + dateTime + " to room " + roomNum + " in branch " + branchNum);
				}
			}

			// ================ Building Command ================
			else if (command.equals("addEquipmentToRoomRun")) {
				// add an equipment to an existing roomRun
				int roomRunNum = Integer.parseInt(input.next());
				E_Equipment equipment = E_Equipment.valueOf(input.next());

				isUpdated = SysData.addEquipmentToRoomRun(roomRunNum, equipment);
				if (isUpdated) { // if added successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addEquipmentToRoomRun returns: "
							+ "Successfully added equipment " + equipment + " to roomRun " + roomRunNum);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("addEquipmentToRoomRun returns: "
							+ "Failed to add equipment " + equipment + " to roomRun " + roomRunNum);
				}
			}

			// ================ Building Command ================
			else if (command.equals("removeEquipmentFromRoomRun")) {
				// add an equipment to an existing roomRun
				int roomRunNum = Integer.parseInt(input.next());
				E_Equipment equipment = E_Equipment.valueOf(input.next());

				isUpdated = SysData.removeEquipmentFromRoomRun(roomRunNum, equipment);
				if (isUpdated) { // if added successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("removeEquipmentFromRoomRun returns: "
							+ "Successfully removed equipment " + equipment + " to roomRun " + roomRunNum);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("removeEquipmentFromRoomRun returns: "
							+ "Failed to remove equipment " + equipment + " to roomRun " + roomRunNum);
				}
			}

			// ================ Building Command ================
			else if (command.equals("addCustomerToRoomRun")) {
				// add a customer to an existing roomRun
				String custNum = input.next();
				int roomRunNum = Integer.parseInt(input.next());

				isUpdated = SysData.addCustomerToRoomRun(custNum, roomRunNum);
				if (isUpdated) { // if added successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("addCustomerToRoomRun returns: "
							+ "Successfully added customer " + custNum + " to roomRun " + roomRunNum);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("addCustomerToRoomRun returns: "
							+ "Failed to add customer " + custNum + " to roomRun " + roomRunNum);
				}
			}

			// ================ Building Command ================
			else if (command.equals("giveAHint")) {
				// add a customer to an existing roomRun
				int roomRunNum = Integer.parseInt(input.next());
				int hintNum = Integer.parseInt(input.next());

				isUpdated = SysData.giveAHint(roomRunNum, hintNum);
				if (isUpdated) { // if added successfully, then true returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("giveAHint returns: "
							+ "Successfully added a hint " + hintNum + " to roomRun " + roomRunNum);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine("giveAHint returns: "
							+ "Failed to add a hint " + hintNum + " to roomRun " + roomRunNum);
				}
			}

			// ================ Building Command ================
			else if (command.equals("changeCustomerAddress")) {
				// Change an existing customer's address
				String customerId = input.next();
				String country = input.next();
				E_Cities city = E_Cities.valueOf(input.next());
				String street = input.next();
				int housNumber = Integer.parseInt(input.next());
				String[] phoneNumber = { input.next() };

				isUpdated = SysData.changeCustomerAddress(customerId, country, city, street, housNumber, phoneNumber);
				if (isUpdated) { // if changing successfully, then true
									// returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine("changeCustomerAddress returns: "
							+ "Successfully changed address of customer " + customerId + " "
							+ SysData.getCustomers().get(SysData.getCustomers().indexOf(new Customer(customerId))));
				} else {
					MyFileLogWriter.writeToFileInSeparateLine(
							"changeCustomerAddress returns: " + "Failed to change address of customer " + customerId);
				}
			}

			// ================ Building Command ================
			else if (command.equals("removeSubscription")) {
				// Cancel an existing subscription
				int subNumber = Integer.parseInt(input.next());

				isUpdated = SysData.removeSubscription(subNumber);
				if (isUpdated) { // if canceling successfully, then true
									// returned,
					// the following message is written to the output file
					MyFileLogWriter.writeToFileInSeparateLine(
							"removeSubscription returns: " + "Successfully removed subscription " + subNumber);
				} else {
					MyFileLogWriter.writeToFileInSeparateLine(
							"removeSubscription returns: " + "Failed to remove subscription " + subNumber);
				}
			}

			// ================ Query Command ================
			else if (command.equals("getAllParticipatedRoomRunsOfMostActiveCustomer")) {
				List<RoomRun> mostRoomRuns = SysData.getAllParticipatedRoomRunsOfMostActiveCustomer();
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("Query_1");
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("getAllParticipatedRoomRunsOfMostActiveCustomer returns:");
				if (mostRoomRuns.size() != 0) {
					MyFileLogWriter.writeToFileInSeparateLine("the following roomRuns:");
					int i = 1;
					for (RoomRun rr : mostRoomRuns)
						if (rr != null)
							MyFileLogWriter.writeToFileInSeparateLine(
									(i++) + "\t- " + rr.getRoomRunNum() + " on " + rr.getStartDateTime());
				} else
					MyFileLogWriter.writeToFileInSeparateLine("No customers were found");
			}

			else if (command.equals("getTopJanuaryReceptionist")) {
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("Query_2");
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("getTopJanuaryReceptionist returns:");
				Receptionist top = SysData.getTopJanuaryReceptionist();
				if (top != null) {
					MyFileLogWriter.writeToFileInSeparateLine("the top january receptionist is: "
							+ top.getEmployeeNumber() + ", " + top.getFirstName() + " " + top.getLastName());
				} else
					MyFileLogWriter.writeToFileInSeparateLine("No top january receptionist was found");
			}

			else if (command.equals("getTheMostPopularRoomType")) {
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("Query_3");
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("getTheMostPopularRoomType returns:");
				E_Rooms most = SysData.getTheMostPopularRoomType();
				if (most != null) {
					MyFileLogWriter.writeToFileInSeparateLine("the most popular room type is: " + most);
				} else
					MyFileLogWriter.writeToFileInSeparateLine("No room type was found");
			}

			else if (command.equals("getAllSuperSeniorInstructors")) {
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("Query_4");
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("getAllSuperSeniorInstructors returns:");
				Instructor[] ssenior = SysData.getAllSuperSeniorInstructors();
				if (ssenior != null) {
					int i = 1;
					for (Instructor ins : ssenior)
						if (ins != null)
							MyFileLogWriter.writeToFileInSeparateLine((i++) + "\t- " + ins.getEmployeeNumber() + ", "
									+ ins.getFirstName() + " " + ins.getLastName());
				} else
					MyFileLogWriter.writeToFileInSeparateLine("No super senior instructors were found");
			}

			else if (command.equals("getTheMostActiveDay")) {
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("Query_5");
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("getTheMostActiveDay returns:");
				Date most = SysData.getTheMostActiveDay();
				if (most != null) {
					MyFileLogWriter.writeToFileInSeparateLine("the most active day is: " + most);
				} else
					MyFileLogWriter.writeToFileInSeparateLine("No active day was found");
			}
			
			else if (command.equals("getAllRoomRunsWithoutHints")) {
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("Query_6");
				MyFileLogWriter.writeToFileInSeparateLine("=======");
				MyFileLogWriter.writeToFileInSeparateLine("getAllRoomRunsWithoutHints returns:");
				ArrayList<RoomRun> roomRuns = SysData.getAllRoomRunsWithoutHints();
				if (roomRuns.size() != 0) {
					MyFileLogWriter.writeToFileInSeparateLine("the following roomRuns:");
					int i = 1;
					for (RoomRun rr : roomRuns)
						if (rr != null)
							MyFileLogWriter.writeToFileInSeparateLine(
									(i++) + "\t- " + rr.getRoomRunNum() + " on " + rr.getStartDateTime());
				} else
					MyFileLogWriter.writeToFileInSeparateLine("No roomRuns were found");
			}
			// ================ Separate Command ================
			else if (command.equals("//")) {
				// Separate line command
				MyFileLogWriter.writeToFileInSeparateLine("\n");
				input.nextLine();
				// ignores line starts by '//' the result is empty lines in the
				// output.txt
			}
			// ================ Command ================
			else
				System.out.printf("Command %s does not exist\n", command);
		} // ~ end of clause - while input has next
		MyFileLogWriter.saveLogFile(); // save the output file
		input.close(); // close connection to input file
		System.out.println("[End Of process]");
		System.out.println("\n NOTICE:\n\t\"End of process\" " + "does NOT mean that all your methods are correct.\n"
				+ "\n==>\t You NEED to check the \"output.txt\" file");
	}// END OF ~ main
}// ~ END OF Class MainClass
