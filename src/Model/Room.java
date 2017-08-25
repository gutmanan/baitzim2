package Model;

import utils.E_Rooms;
import java.util.HashMap;

import utils.E_Levels;

/**
 * Class Room ~ represent a single Room in the company Each room belongs to a
 * specific branch
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Room {
	// -------------------------------Class Members------------------------------
	private int roomNum;
	private String name;
	private int maxNumOfParticipants;
	private int minNumOfParticipants;
	private int timeLimit;
	private E_Levels level;
	private E_Rooms roomType;
	private Branch branch;
	private HashMap<Integer, RoomRun> roomRuns;

	// -------------------------------Constructors------------------------------
	
	public Room(int roomNum, String name, int maxNumOfParticipants, int minNumOfParticipants, int timeLimit, E_Levels level, E_Rooms roomType,
			Branch branch) {
		super();
		this.roomNum = roomNum;
		this.name = name;
		this.maxNumOfParticipants = maxNumOfParticipants;
		this.minNumOfParticipants = minNumOfParticipants;
		this.timeLimit = timeLimit;
		this.level = level;
		this.roomType = roomType;
		this.branch = branch;
		this.roomRuns = new HashMap<>();
	}
	
	public Room(int roomNum) {
		this.roomNum = roomNum;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getRoomNum() {
		return roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public int getMaxNumOfParticipants() {
		return maxNumOfParticipants;
	}

	public void setMaxNumOfParticipants(int maxNumOfParticipants) {
		this.maxNumOfParticipants = maxNumOfParticipants;
	}

	public int getMinNumOfParticipants() {
		return minNumOfParticipants;
	}

	public void setMinNumOfParticipants(int minNumOfParticipants) {
		this.minNumOfParticipants = minNumOfParticipants;
	}

	public int getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}

	public E_Rooms getRoomType() {
		return roomType;
	}

	public void setRoomType(E_Rooms roomType) {
		this.roomType = roomType;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public HashMap<Integer, RoomRun> getRoomRuns() {
		return roomRuns;
	}

	public void setRoomRuns(HashMap<Integer, RoomRun> roomRuns) {
		this.roomRuns = roomRuns;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method adds a new roomRun into the roomRuns array of the room only
	 * IF the time slot is free
	 * 
	 * @param roomRunToAdd
	 * @return true if the roomRun was added successfully, false otherwise
	 */
	public boolean addRoomRun(RoomRun roomRunToAdd) {
		if (roomRunToAdd == null || roomRuns.containsKey(roomRunToAdd.getRoomRunNum()))
			return false;
		roomRuns.put(roomRunToAdd.getRoomRunNum(), roomRunToAdd);
		return true;
	}

	/**
	 * This method removes a roomRun if it exist and belongs to this room
	 * 
	 * @param roomRunToDelete
	 * @return true if roomRun was removed successfully, false otherwise
	 */
	public boolean deleteRoomRun(RoomRun roomRunToDelete) {
		if (roomRunToDelete != null && roomRuns.containsKey(roomRunToDelete.getRoomRunNum())) {
			roomRuns.remove(roomRunToDelete.getRoomRunNum());
			return true;
		}
		return false;
	}

	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomNum;
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
		Room other = (Room) obj;
		if (roomNum != other.roomNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", name=" + name + ", maxNumOfParticipants=" + maxNumOfParticipants
				+ ", minNumOfParticipants=" + minNumOfParticipants + ", timeLimit=" + timeLimit + ", level=" + level
				+ ", roomType=" + roomType + ", branch=" + branch + ", roomRuns=" + roomRuns + "]";
	}
}
