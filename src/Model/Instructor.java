package Model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import utils.E_Rooms;

/**
 * Class Instructor ~ represent a single Instructor of the company, inheritor of
 * Employee
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Instructor extends Employee {
	// -------------------------------Class
	// Members------------------------------
	private HashSet<E_Rooms> Types;
	private int Level;
	private HashMap<Integer, RoomRun> roomRuns;

	// -------------------------------Constructors------------------------------
	public Instructor(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, int level, Branch workBranch, Address address, E_Rooms[] types) {
		super(empNum, firstName, lastName, birthdate, startWorkingDate, password, address);
		this.Types = new HashSet<>(Arrays.asList(types));
		setLevel(level);
		this.roomRuns = new HashMap<>();
		setWorkBranch(workBranch);
	}

	public Instructor(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, int level, Address address, E_Rooms[] types) {
		super(empNum, firstName, lastName, birthdate, startWorkingDate, password, address);
		this.Types = new HashSet<>(Arrays.asList(types));
		setLevel(level);
		this.roomRuns = new HashMap<>();
	}

	public Instructor(int empNum, String firstName, String lastName, Date birthdate, Date startWorkingDate,
			String password, int level, Address address) {
		super(empNum, firstName, lastName, birthdate, startWorkingDate, password, address);
		setLevel(level);
		this.roomRuns = new HashMap<>();
	}

	public Instructor(int empNum) {
		super(empNum);
	}

	// -------------------------------Getters And
	// Setters------------------------------
	public int getLevel() {
		return Level;
	}

	public HashSet<E_Rooms> getTypes() {
		return Types;
	}

	public void setTypes(HashSet<E_Rooms> types) {
		Types = types;
	}

	public HashMap<Integer, RoomRun> getRoomRuns() {
		return roomRuns;
	}

	public void setRoomRuns(HashMap<Integer, RoomRun> roomRuns) {
		this.roomRuns = roomRuns;
	}

	public void setLevel(int level) {
		if (level > 0 && level < 4) {
			this.Level = level;
			return;
		}
		this.Level = 1;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds a RoomRun to this instructor roomRuns if he is available
	 * at the time, roomRun's level not above his level and roomRun's type is
	 * included in his types. Attention - a RoomRun duration depends on it's
	 * room
	 * 
	 * @param roomRun
	 * @return true if the roomRun was successfully added, false otherwise
	 */
	public boolean addRoomRun(RoomRun roomRun) {
		if (roomRun == null || roomRuns.containsKey(roomRun.getRoomRunNum()))
			return false;
		if (!Types.contains(roomRun.getRoom().getRoomType()))
			return false;
		roomRuns.put(roomRun.getRoomRunNum(), roomRun);
		return true;
	}

	/**
	 * This method removes a roomRun if it exist and belongs to this instructor
	 * 
	 * @param roomRun
	 * @return true if the roomRun was successfully deleted, false otherwise
	 */
	public boolean deleteRoomRun(RoomRun roomRun) {
		if (roomRun != null && roomRun.getInstructor().equals(this) && roomRuns.containsKey(roomRun.getRoomRunNum())) {
			roomRuns.remove(roomRun.getRoomRunNum());
			return true;
		}
		return false;
	}
}
