package Model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import utils.Constants;
import utils.E_Levels;

/**
 * Class Customer ~ represent a single customer of the company
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Customer {
	// -------------------------------Class Members------------------------------
	private String Id;
	private String firstName;
	private String lastName;
	private Date birthdate; //Calendar can also be used here
	private String Password;
	private E_Levels level;
	private URL Email;
	private HashMap<Integer, Subscription> subs;
	private Address customerAddress;

	// -------------------------------Constructors------------------------------
	public Customer(String id, String firstName, String lastName, Date birthdate, String password,E_Levels level, URL email,
			Subscription sub, Address customerAddress) {
		this.Id = checkId(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.Password = password;
		this.level = level;
		this.Email = email;
		this.customerAddress = customerAddress;
		this.subs = new HashMap<>();
		addSubscription(sub);
	}

	public Customer(String id, String firstName, String lastName, Date birthdate, String password,E_Levels level, URL email,
			Address customerAddress) {
		this.Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.Password = password;
		this.level = level;
		this.Email = email;
		this.customerAddress = customerAddress;
		this.subs = new HashMap<>();
	}

	public Customer(String id) {
		this.Id = id;
	}

	// -------------------------------Getters And Setters------------------------------
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public E_Levels getLevel() {
		return level;
	}

	public void setLevel(E_Levels level) {
		this.level = level;
	}

	public URL getEmail() {
		return Email;
	}

	public void setEmail(URL email) {
		Email = email;
	}

	public HashMap<Integer, Subscription> getSubs() {
		return subs;
	}

	public void setSubs(HashMap<Integer, Subscription> subs) {
		this.subs = subs;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	// -------------------------------More Methods------------------------------
	/**
	 * Helper method for Query 1: This method finds the number of participated
	 * RoomRuns meaning PAST RoomRuns this customer participated in
	 * 
	 * @return participatedRoomRuns list if there are RoomRuns, empty list otherwise
	 */
	public List<RoomRun> getParticipatedRoomRuns() {
		Date today = new Date();
		List<RoomRun> participatedRoomRuns = new ArrayList<RoomRun>();
		for (Map.Entry<Integer, Subscription> e1 : subs.entrySet()) {
			//Integer k1 = e1.getKey();
			Subscription v1 = e1.getValue();
			if (v1 != null) {
				for (Map.Entry<Integer, RoomRun> e2 : v1.getRoomRuns().entrySet()) {
					//Integer k2 = e2.getKey();
					RoomRun v2 = e2.getValue();
					if (v2 != null && v2.getStartDateTime().before(today)) {
						long minutesDiff = TimeUnit.MINUTES.convert(today.getTime() - v2.getStartDateTime().getTime(), TimeUnit.MILLISECONDS);
						if (!(Math.abs(minutesDiff) < 120)) {
							participatedRoomRuns.add(v2);
						}
					}
				}
			}
		}
		return participatedRoomRuns.isEmpty() ? Collections.<RoomRun>emptyList() : participatedRoomRuns;
	}

	/**
	 * This method adds a new Subscription to the subs array IF the given sub
	 * doesn't already exists in the customer's subs array.
	 * 
	 * @param sub
	 * @return true if this sub was successfully added, false otherwise
	 */
	public boolean addSubscription(Subscription sub) {
		if (sub == null || subs.containsKey(sub.getNumber()))
			return false;
		subs.put(sub.getNumber(), sub);
		return true;
	}

	/**
	 * This method removes an existing subscription from the subs array IF the
	 * sub exists, after deleting him from all related lessons.
	 * 
	 * @param sub
	 * @return true if this sub was removed successfully or false otherwise
	 */
	public boolean removeSubscription(Subscription sub) {
		if (sub != null) {
			if (subs.containsKey(sub.getNumber())) {
				subs.remove(sub.getNumber());
				return true;
			}
		}
		return false;
	}

	/**
	 * This method counts the number of the subscriptions that belongs to the
	 * customer.
	 * 
	 * @return customerSubs number of subscriptions
	 */
	public int getNumOfCustomerSubscriptions() {
		Date today = new Date();
		int customerSubs = 0;
		for (Map.Entry<Integer, Subscription> e1 : subs.entrySet()) {
			//Integer k1 = e1.getKey();
			Subscription v1 = e1.getValue();
			if (v1 != null && v1.getStartDate().before(today) && v1.getLastDay().after(today)) {
				customerSubs++;
			}
		}
		return customerSubs;
	}

	/**
	 * This method adds a RoomRun to the roomRuns array of the customer IF he
	 * has a valid subscription.
	 * 
	 * @param roomRunToAdd
	 * @return true if the lesson was added successfully or false otherwise
	 */
	public boolean addRoomRun(RoomRun roomRunToAdd) {
		if (roomRunToAdd != null) {
			for (Map.Entry<Integer, Subscription> e1 : subs.entrySet()) {
				//Integer k1 = e1.getKey();
				Subscription v1 = e1.getValue();
				if (v1 != null && roomRunToAdd.getStartDateTime().after(v1.getStartDate()) && roomRunToAdd.getFinishDateTime().before(v1.getLastDay())) {
					if (v1.getRoomRuns().containsKey(roomRunToAdd.getRoomRunNum())) {
						continue;
					}
					v1.getRoomRuns().put(roomRunToAdd.getRoomRunNum(), roomRunToAdd);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * this method checks if the customer has a subscription that is registered
	 * to the given roomRun if so, delete it from the subscription roomRuns
	 * array
	 * 
	 * @param roomRunToDelete
	 * @return true if the lesson was canceled successfully or false otherwise
	 */
	public boolean deleteRoomRun(RoomRun roomRunToDelete) {
		if (roomRunToDelete != null) {
			for (Map.Entry<Integer, Subscription> e1 : subs.entrySet()) {
				//Integer k1 = e1.getKey();
				Subscription v1 = e1.getValue();
				if (v1 != null && v1.getRoomRuns().containsKey(roomRunToDelete.getRoomRunNum())) {
					v1.getRoomRuns().remove(roomRunToDelete.getRoomRunNum());
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * this method get a string and check if the string is a valid ID number.
	 * 
	 * @param id
	 * @return id if this is an id, "0" otherwise
	 */
	public static String checkId(String id) {
		if (id.length() == Constants.ID_NUMBER_SIZE) {
			for (int i = 0; i < id.length(); i++) {
				if (!Character.isDigit(id.charAt(i)))
					return "0";
			}
			return id;
		}
		return "0";
	}

	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Customer other = (Customer) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [Id=" + Id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthdate=" + birthdate
				+ ", Password=" + Password + ", level=" + level + ", Email=" + Email + ", subs=" + subs
				+ ", customerAddress=" + customerAddress + "]";
	}

}
