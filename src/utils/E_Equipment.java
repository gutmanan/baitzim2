package utils;

import java.util.ArrayList;
import java.util.Arrays;

public enum E_Equipment {
	//-------------------------------------------------------------Values---------------------------------------------------------------------
	// equipment for room types
	Gun(new E_Rooms[]{ E_Rooms.CRIME }),	Pistol(new E_Rooms[]{ E_Rooms.SCI_FI, E_Rooms.HORROR, E_Rooms.MYSTERY }),
	Map(new E_Rooms[]{ E_Rooms.ADVENTURE, E_Rooms.HISTORICAL }), Magnifier(new E_Rooms[]{ E_Rooms.ADVENTURE, E_Rooms.HISTORICAL }),
	Compass(new E_Rooms[]{ E_Rooms.ADVENTURE, E_Rooms.HISTORICAL }), Wand(new E_Rooms[]{ E_Rooms.FANTASY }), SpellBook(new E_Rooms[]{ E_Rooms.FANTASY });
	//-------------------------------------------------------------Class Members----------------------------------------------------------------
	private final ArrayList<E_Rooms> roomTypes;
	
	//-------------------------------------------------------------Constructor------------------------------------------------------------------
	E_Equipment(E_Rooms[] roomTypes){
		this.roomTypes = new ArrayList<>(Arrays.asList(roomTypes));
	}
	
	//-------------------------------------------------------------Methods----------------------------------------------------------------------
	public ArrayList<E_Rooms> getRoomTypes() { 
		return roomTypes; 
	}
}
