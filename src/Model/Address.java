package Model;

import java.util.Arrays;

import utils.E_Cities;

/**
 * Class Address ~ represent a single Address of a Branch, Employee or Customer
 * 
 * @author Java Course Team 2017 - Shai Gutman
 * @author University Of Haifa - Israel
 */
public class Address {
	// -------------------------------Class Members------------------------------
	private E_Cities city;
	private String country;
	private int housNumber;
	private String[] phoneNumber;
	private String street;

	// -------------------------------Constructors-----------------------------
	public Address(String country, E_Cities city, String street, int housNumber, String[] phoneNumber) {
		super();
		this.city = city;
		this.country = country;
		this.housNumber = housNumber;
		this.phoneNumber = new String[1];
		this.phoneNumber = phoneNumber;
		this.street = street;
	}

	// -------------------------------Getters And Setters------------------------------
	public E_Cities getCity() {
		return city;
	}

	public void setCity(E_Cities city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getHousNumber() {
		return housNumber;
	}

	public void setHousNumber(int housNumber) {
		this.housNumber = housNumber;
	}

	public String[] getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String[] phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	// -------------------------------More Methods------------------------------

	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + housNumber;
		result = prime * result + Arrays.hashCode(phoneNumber);
		result = prime * result + ((street == null) ? 0 : street.hashCode());
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
		Address other = (Address) obj;
		if (city != other.city)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (housNumber != other.housNumber)
			return false;
		if (!Arrays.equals(phoneNumber, other.phoneNumber))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", city=" + city + ", street=" + street + ", housNumber=" + housNumber
				+ ", phoneNumber= " + Arrays.toString(phoneNumber).replace("[", "{").replace("]", "}") + " ]";
	}
} // ~ END OF Class Address
