package Model;

import java.util.Date;
import java.util.HashMap;

import utils.E_Periods;

/**
 * Class Subscription ~ represent a single Subscription in the company Each
 * Subscription belongs to a specific participant
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Subscription {
	// -------------------------------Class Members------------------------------
	private int number;
	private Customer resCustomer;
	private Receptionist receptionist;
	private E_Periods period;
	private Date startDate;
	private HashMap<Integer, RoomRun> roomRuns;

	// -------------------------------Constructors------------------------------
	public Subscription(int number, Customer customer, Receptionist receptionist, E_Periods period, Date startDate) {
		this.number = number;
		this.resCustomer = customer;
		this.receptionist = receptionist;
		this.period = period;
		this.startDate = startDate;
		this.roomRuns = new HashMap<>();
	}

	public Subscription(int number) {
		this.number = number;
	}

	// -------------------------------Getters And Setters------------------------------
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Receptionist getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(Receptionist receptionist) {
		this.receptionist = receptionist;
	}

	public Customer getCustomer() {
		return resCustomer;
	}

	public void setCustomer(Customer resCustomer) {
		this.resCustomer = resCustomer;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public E_Periods getPeriod() {
		return period;
	}

	public void setPeriod(E_Periods period) {
		this.period = period;
	}

	public HashMap<Integer, RoomRun> getRoomRuns() {
		return roomRuns;
	}

	public void setRoomRuns(HashMap<Integer, RoomRun> roomRuns) {
		this.roomRuns = roomRuns;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * This method calculate the last date the subscription is valid for
	 * 
	 * @return lastDate of the subscription if no nulls, null otherwise
	 * @see utils.E_Periods
	 */
	@SuppressWarnings("deprecation")
	public Date getLastDay() {
		if (this.getStartDate() != null && this.getPeriod() != null) {
			Date lastDay = (Date) this.getStartDate().clone();
			lastDay.setMonth(lastDay.getMonth() + this.getPeriod().getNumber());
			return lastDay;
		}
		return null;
	}

	/**
	 * This method adds a roomRun to the subscription's roomRuns array IF its
	 * new for this subscription
	 * 
	 * @param lessonToAdd
	 * @return true if the lesson was added successfully, false otherwise
	 */
	public boolean addRoomRun(RoomRun roomRunToAdd) {
		if (roomRunToAdd == null || roomRuns.containsKey(roomRunToAdd.getRoomRunNum())) {
			return false;
		}
		roomRuns.put(roomRunToAdd.getRoomRunNum(), roomRunToAdd);
		return true;
	}

	/**
	 * This method delete a roomRun from the roomRuns array of this subscription
	 * 
	 * @param lessonToCancel
	 * @return true if the lesson was deleted successfully, false other
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
		result = prime * result + number;
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
		Subscription other = (Subscription) obj;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscription [number=" + number + ", resCustomer=" + resCustomer + ", receptionist=" + receptionist
				+ ", period=" + period + ", startDate=" + startDate + ", roomRuns=" + roomRuns + "]";
	}
}
