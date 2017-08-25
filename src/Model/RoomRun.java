package Model;

import java.util.Date;
import java.util.HashMap;

import utils.E_Levels;

/**
 * Class RoomRun ~ represent a single RoomRun in the company
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class RoomRun {
	// -------------------------------Class
	// Members------------------------------
	private int roomRunNum;
	private Date startDateTime;
	private int duration;
	private boolean onTime;
	private E_Levels Level;
	private Instructor instructor;
	private Room room;
	private HashMap<String, Customer> participated;

	// -------------------------------Constructors------------------------------
	public RoomRun(int roomRunNum, Date startDateTime, int duration, Instructor instructor, Room room) {
		this.roomRunNum = roomRunNum;
		this.startDateTime = startDateTime;
		this.room = room;
		this.setInstructor(instructor);
		this.setDuration(duration);
		this.participated = new HashMap<>();
	}

	public RoomRun(int roomRunNum) {
		this.roomRunNum = roomRunNum;
	}

	// -------------------------------Getters And
	// Setters------------------------------
	public int getRoomRunNum() {
		return roomRunNum;
	}

	public void setRoomRunNum(int roomRunNum) {
		this.roomRunNum = roomRunNum;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		boolean flag = duration <= this.getRoom().getTimeLimit();
		if (flag) {
			this.duration = duration;
		} else {
			this.duration = this.getRoom().getTimeLimit();
		}
		this.setOnTime(flag);
	}

	public boolean isOnTime() {
		return onTime;
	}

	public void setOnTime(boolean onTime) {
		this.onTime = onTime;
	}

	public E_Levels getLevel() {
		return Level;
	}

	public void setLevel(E_Levels level) {
		Level = level;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		boolean ableToTeach = false;
		if (instructor.getTypes().contains(this.room.getRoomType())) {
			ableToTeach = true;
		}
		if (instructor.getLevel() <= this.room.getLevel().getLevel() && ableToTeach) {
			this.instructor = instructor;
		}
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public HashMap<String, Customer> getParticipated() {
		return participated;
	}

	public void setParticipated(HashMap<String, Customer> participated) {
		this.participated = participated;
	}

	// -------------------------------More Methods------------------------------
	@SuppressWarnings("deprecation")
	public Date getFinishDateTime() {
		Date finish = (Date) startDateTime.clone();
		finish.setMinutes(finish.getMinutes() + duration);
		return finish;
	}

	/**
	 * this method adds a participant to this room rum if there is enough space
	 * 
	 * @param participantToAdd
	 * @return true if the participant was added successfully, false otherwise
	 */
	public boolean addParticipant(Customer participantToAdd) {		
		if (participantToAdd == null || participated.containsKey(participantToAdd.getId())) {
			return false;
		}
		participated.put(participantToAdd.getId(), participantToAdd);
		return true;
	}

	/**
	 * This method removes a participant from the room run array if he exists
	 * there
	 * 
	 * @param participantToRemove
	 * @return true if the participant was deleted, false otherwise
	 */
	public boolean removeParticipant(Customer participantToRemove) {
		if (participantToRemove != null && participated.containsKey(participantToRemove.getId())) {
			participated.remove(participantToRemove).getId();
			return true;
		}
		return false;
	}
	
	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roomRunNum;
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
		RoomRun other = (RoomRun) obj;
		if (roomRunNum != other.roomRunNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomRun [roomRunNum=" + roomRunNum + ", startDateTime=" + startDateTime + ", duration=" + duration
				+ ", onTime=" + onTime + ", Level=" + Level + ", instructor=" + instructor + ", room=" + room
				+ ", participated=" + participated + "]";
	}
}
