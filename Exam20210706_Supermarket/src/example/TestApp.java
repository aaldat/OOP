package example;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import supermarket.*;

public class TestApp {
	//@SuppressWarnings("unused")
	@Test
	public void test() throws SMException {
		Supermarket sm = new Supermarket();

		//R1
		int r = 0;
		//double price = 0;
		r = sm.addProducts ("cat1", "pa1,pa2,pa3,qa1,qa2,qa3", "10.00,5.23,12.45,4.00,15.50,14.50");
		assertEquals(r, 6);

		try {
			r = sm.addProducts ("cat1", "ra1,ra2", "14.00,15.50");
			fail("dup category");
		} catch(SMException ex) {} //System.out.println("dup cat1");

		try {
			r = sm.addProducts ("cat2", "ra1,ra2,qa2", "14.00,15.50,11.15");
			fail("dup product");
		} catch(SMException ex) {} //System.out.println("dup prod qa2");

		try {
			r = sm.addProducts ("cat2", "xa1,xa2,ya2", "14.00,15.50");
			fail("mismatch between numbers");
		} catch(SMException ex) {} //System.out.println("mismatch between numbers");

		assertEquals(sm.getPrice("pa2"), 5.23, 0.1);

		r = sm.addProducts ("categ2", "ra1,ra2,ra3,sa1", "10.20,5.23,12.45,14.00");
		assertEquals(r, 4);

		SortedMap<String,String> map1 = sm.mostExpensiveProductPerCategory ();
		//System.out.println(map1);
		assertEquals(map1.toString(), "{cat1=qa2, categ2=sa1}");

		//R2
		sm.setDiscount ("cat1", 10);
		sm.setDiscount ("categ2", 20);
		sm.setDiscount ("cat1", 15);

		try {
			sm.setDiscount ("cat1", 50);
			fail("wrong percentage");
		} catch(SMException ex) {}

		sm.setDiscount (20, "pa1", "pa2", "ra1");
		sm.setDiscount (25, "pa1", "qa2");

		List<Integer> li = sm.getDiscountHistoryForCategory("cat1");
		assertEquals(li.toString(), "[0, 10, 15]");
		//System.out.println(li);
		li = sm.getDiscountHistoryForProduct("pa1");
		assertEquals(li.toString(), "[0, 10, 15, 20, 25]");
		//System.out.println(li);

		//R3
		int code = 0;
		code = sm.issuePointsCard ("xyz", "19901006");
		assertEquals(code, 1000);

		try {
			code = sm.issuePointsCard ("xyz", "19901006");
			fail("dup name and dateOfBirth");
		} catch(SMException ex) {}

		sm.fromPointsToDiscounts ("50,100,150,200", "2,5,7,10");

		assertEquals(sm.getDiscountFromPoints (100), 5);
		assertEquals(sm.getDiscountFromPoints (80), 0);

		try {
			sm.fromPointsToDiscounts ("600,900,1200", "2,5");
			fail("mismatch between numbers");
		} catch(SMException ex) {}


		//R4
		int purchaseCode = 0;
		sm.setDiscount ("cat1", 0);

		try {
			purchaseCode = sm.addPurchase(1000, 50, "pa1", "pa2", "pa3", "qa1", "qa2", "qa3");
			fail ("pointsRedeemed > currentPoints");
		} catch(SMException ex) {}

		purchaseCode = sm.addPurchase(1000, 0, "pa1", "pa2", "pa3", "qa1", "qa2", "qa3");
		assertEquals(purchaseCode, 100);

		assertEquals(sm.getPurchasePrice (100), 61.68, 0.1);
		assertEquals (sm.getPurchaseDiscount(100), 0.0, 0.1);
		assertEquals (sm.getCurrentPoints(1000), 62);
		assertEquals (sm.getTotalPoints(1000), 62);


		purchaseCode = sm.addPurchase(1000, 52, "pa1", "pa2", "pa3", "qa1", "qa2", "qa3");

		purchaseCode = sm.addPurchase(1000, 50, "pa1", "pa2", "pa3", "qa1", "qa2", "qa3");
		assertEquals(purchaseCode, 102);

		assertEquals(sm.getPurchasePrice (101), 61.68, 0.1);
		assertEquals (sm.getPurchaseDiscount(101), 0, 0.1);
		assertEquals (sm.getCurrentPoints(1000), 134);
		assertEquals (sm.getTotalPoints(1000), 184);

		//R5
		code = sm.issuePointsCard ("abc", "19900308");
		assertEquals(code, 1001);

		purchaseCode = sm.addPurchase(1001, 0, "pa1", "pa2", "pa3", "qa1", "qa2", "qa3");

		code = sm.issuePointsCard ("fgh", "19900803");
		assertEquals(code, 1002);

		purchaseCode = sm.addPurchase(1002, 0, "pa1", "pa2", "pa3", "qa1", "qa2", "qa3");

		SortedMap<Integer, List<Integer>> statMap1 = sm.pointsCardsPerTotalPoints ();
		//System.out.println (statMap1);
		assertEquals(statMap1.toString(), "{62=[1001, 1002], 184=[1000]}");


		purchaseCode = sm.addPurchase(1002, 0, "ra1", "ra2");


		SortedMap<String, SortedSet<String>> statMap2 = sm.customersPerCategory ();
		//System.out.println ("statMap2 " + statMap2);
		assertEquals(statMap2.toString(), "{cat1=[abc, fgh, xyz], categ2=[fgh]}");

		SortedMap<Integer, List<String>> statMap3 = sm.productsPerDiscount();
		//System.out.println ("statMap3 " + statMap3);
		assertEquals(statMap3.toString(), "{25=[pa1, qa2], 20=[pa2, ra1, ra2, ra3, sa1], 15=[pa3, qa1, qa3]}");

		// R6

		int rc = sm.newReceipt();

		sm.receiptAddCard(rc, 1000);
		assertEquals(134, sm.receiptGetPoints(rc));

		sm.receiptAddProduct(rc, "pa2");
		assertEquals(5.23, sm.receiptGetTotal(rc), 0.01);

		sm.receiptSetRedeem(rc, 50);
		assertEquals(5.23-2.00, sm.receiptGetTotal(rc), 0.01);

		sm.receiptAddProduct(rc, "ra1");
		assertEquals(5.23-2.00+10.20*0.80, sm.receiptGetTotal(rc), 0.01);

		purchaseCode = sm.closeReceipt(rc);

		assertEquals(10.20*0.20 + 2.00, sm.getPurchaseDiscount(purchaseCode), 0.01);
	}



}

