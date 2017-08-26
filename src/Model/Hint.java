package Model;

public class Hint {
	// -------------------------------Class  Members------------------------------
	private int hintNum;
	private String text;
	private Room belongRoom;
	
	// -------------------------------Constructors------------------------------
	public Hint(int hintNum, String text, Room belongRoom) {
		this.hintNum = hintNum;
		this.text = text;
		this.belongRoom = belongRoom;
	}
	
	public Hint(int hintNum, Room belongRoom) {
		this.hintNum = hintNum;
		this.belongRoom = belongRoom;
	}
	
	// -------------------------------Getters And Setters------------------------------
	public int getHintNum() {
		return hintNum;
	}

	public void setHintNum(int hintNum) {
		this.hintNum = hintNum;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Room getBelongRoom() {
		return belongRoom;
	}

	public void setBelongRoom(Room belongRoom) {
		this.belongRoom = belongRoom;
	}
	
	// -------------------------------More Methods------------------------------
	// -------------------------------hashCode equals & toString------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hintNum;
		result = prime * result + ((belongRoom == null) ? 0 : belongRoom.hashCode());
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
		Hint other = (Hint) obj;
		if (hintNum != other.hintNum)
			return false;
		if (belongRoom == null) {
			if (other.belongRoom != null)
				return false;
		} else if (!belongRoom.equals(other.belongRoom))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Hint [hintNum=" + hintNum + ", text=" + text + ", belongRoom=" + belongRoom + "]";
	}	
}
