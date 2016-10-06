package model;

import java.util.Set;

public interface CacheStorageInterface {
	
	// create new Member
	public int storeNewMember (String name, String socSecId);
	
	// remove Member
	public int removeMember (int key);
	
	// update Member
	public int updateMember (int key, String name, String socSecId);

	public Set <Integer> getHashKeysToAllMembers ();
	
	public String getMemberName (int key);
	public int getMemberId (int key);
	public int getMemberNoOfBoats (int key);
	public int getMemberPersonalNumber (int key);

	
	public String getMemberBoatType (int key, int boat);
	public double getMemberBoatLength (int key, int boat);

	// check if memberKey is valid
	public boolean isMemberKeyValid (int key);
	
	// create new boat
	public String [] getValidBoatTypes ();
	public int storeBoat (int key, String boattype,String length);
	public int updateBoat (int key1,int key2, String boattype,String length);
	public int removeBoat (int key1,int key2);

}
