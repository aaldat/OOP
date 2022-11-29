package groups;
import java.util.List;
import java.util.SortedMap;


public class GroupHandling {
//R1	
	public void addProduct (String productTypeName, String... supplierNames) 
			throws GroupHandlingException {
	}
	
	public List<String> getProductTypes (String supplierName) {
		return null;
	}
	
//R2	
	public void addGroup (String name, String productName, String... customerNames) 
			throws GroupHandlingException {
	}
	
	public List<String> getGroups (String customerName) {
        return null;
	}

//R3
	public void setSuppliers (String groupName, String... supplierNames)
			throws GroupHandlingException {
	}
	
	public void addBid (String groupName, String supplierName, int price)
			throws GroupHandlingException {
	}
	
	public String getBids (String groupName) {
        return null;
	}
	
	
//R4	
	public void vote (String groupName, String customerName, String supplierName)
			throws GroupHandlingException {
	}
	
	public String  getVotes (String groupName) {
        return null;
	}
	
	public String getWinningBid (String groupName) {
        return null;
	}
	
//R5
	public SortedMap<String, Integer> maxPricePerProductType() { //serve toMap
        return null;
	}
	
	public SortedMap<Integer, List<String>> suppliersPerNumberOfBids() {
        return null;
	}
	
	public SortedMap<String, Long> numberOfCustomersPerProductType() {
        return null;
	}
	
}
