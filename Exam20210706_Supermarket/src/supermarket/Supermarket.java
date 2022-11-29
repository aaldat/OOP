package supermarket;
import java.util.*;

public class Supermarket {

	//R1
	public int addProducts (String categoryName, String productNames, String productPrices) throws SMException {
		return -1;
	}

	public double getPrice (String productName) throws SMException {
		return -1;
	}

	public SortedMap<String,String> mostExpensiveProductPerCategory () {
		return null;
	}

	//R2
	public void setDiscount (String categoryName, int percentage) throws SMException {
	}

	public void setDiscount (int percentage, String... productNames) throws SMException {
	}

	public List<Integer> getDiscountHistoryForCategory(String categoryName) {
		return null;
	}

	public List<Integer> getDiscountHistoryForProduct(String productName) {
		return null;
	}

	//R3
	public int issuePointsCard (String name, String dateOfBirth) throws SMException {
		return -1;
	}



	public void fromPointsToDiscounts (String points, String discounts) throws SMException {
	}

	public SortedMap<Integer, Integer>  getMapPointsDiscounts() {
		return null;
	}

	public int getDiscountFromPoints (int points) {
		return -1;
	}

	//R4

	public int getCurrentPoints (int pointsCardCode) throws SMException {
		return -1;
	}

	public int getTotalPoints (int pointsCardCode) throws SMException {
		return -1;
	}

	public int addPurchase (int pointsCardCode, int pointsRedeemed, String ... productNames) throws SMException {
		return -1;
	}


	public double getPurchasePrice (int purchaseCode) throws SMException {
		return -1;
	}

	public double getPurchaseDiscount (int purchaseCode) throws SMException {
		return -1;
	}

	
	//R5

	public SortedMap<Integer, List<Integer>> pointsCardsPerTotalPoints () {
		return null;
	}


	public SortedMap<String, SortedSet<String>> customersPerCategory () {
		return null;
	}

	public SortedMap<Integer, List<String>> productsPerDiscount() {
		return null;
	}


	// R6

	public int newReceipt() { // return code of new receipt
		return -1;
	}

	public void receiptAddCard(int receiptCode, int PointsCard)  throws SMException { // add the points card info to the receipt
	}

	public int receiptGetPoints(int receiptCode)  throws SMException { // return available points on points card if added before
		return -1;
	}

	public void receiptAddProduct(int receiptCode, String product)  throws SMException { // add a new product to the receipt
	}

	public double receiptGetTotal(int receiptCode)  throws SMException { // return current receipt code
		return -1;
	}

	public void receiptSetRedeem(int receiptCode, int points)  throws SMException { // sets the amount of points to be redeemed
	}

	public int closeReceipt(int receiptCode)  throws SMException { // close the receipt and add the purchase (calls addPurchase() ) and return purchase code (could be the same as receipt code)
		return -1;
	}


}