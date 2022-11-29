package example;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import discounts.*;

public class TestApp {

@SuppressWarnings("unused")
@Test
public void test() throws DiscountsException {
	Discounts d = new Discounts();
	//R1
	int cId1 = d.issueCard("Mario Rossi"); 
	int cId2 = d.issueCard("Giuseppe Verdi"); 
	int cId3 = d.issueCard("Filippo Neri"); 
	assertEquals(cId1, 1); 
	assertEquals(cId2, 2); 
	assertEquals(3, d.nOfCards());
	
	//R2
    try {
    	d.addProduct("c1", "p4", 10.6); d.addProduct("c1", "p2", 40.8);
    	d.addProduct("c2", "p3", 20.7); d.addProduct("c2", "p5", 30);
    	d.addProduct("c2", "p6", 50); d.addProduct("c2", "p8", 70);
    } catch(Exception ex) {fail("no exception expected");}
	try {
		d.addProduct("c2", "p4", 3.1);
		fail("Expected exception"); //p4 dup
	} catch(Exception ex){} //ok
	try {
		double price = d.getPrice("p4");
		assertEquals(10.6, price, 0.001);
	} catch(Exception ex) {fail("no exception expected");}
	try {
		d.getPrice("p100");
		fail("Expected exception"); //p100 undef
	} catch(Exception ex){} //ok
	try {
		int average = d.getAveragePrice("c1");
		assertEquals(26, average);
	} catch(Exception ex) {fail("no exception expected");}
	try {
		d.getAveragePrice("c100"); //c100 undef
		fail("Expected exception");
	} catch(Exception ex){} //ok
	
	//R3
	try {d.setDiscount("c1", 20);} catch(Exception ex) {fail("no exception expected");}
	try {d.setDiscount("c100", 20);	fail("Expected exception");} //c100 undef
		catch(Exception ex){} //ok
	try {d.setDiscount("c2", 60);	fail("Expected exception");} //wrong discount 60%
		catch(Exception ex){} //ok}
	int discount = 	d.getDiscount("c1");	
	assertEquals(20, discount);
	
	//R4
	int pCode1 = 0; int pCode2 = 0; int pCode3 = 0; int pCode4 = 0;
	pCode1 = d.addPurchase(cId1, "p4:1","p2:3","p3:1"); //with card
	pCode2 = d.addPurchase("p4:1","p2:3","p3:1"); //without card
	pCode3 = d.addPurchase(cId2, "p5:1","p2:1","p3:1"); //with card
	pCode4 = d.addPurchase("p6:2"); //without card
	assertEquals(pCode1, 1); assertEquals(pCode2, 2); assertEquals(pCode3, 3);
	try {d.addPurchase(cId1, "p100:1");	fail("Expected exception");} //p100 undef
	catch(Exception ex){} //ok}

	double purchaseAmount = d.getAmount(pCode1); 
	assertEquals(127.1, purchaseAmount, 0.01);  //p4 and p2 discounted by 20%
	double purchaseDiscount = d.getDiscount(pCode1);
	assertEquals(26.6, purchaseDiscount, 0.01);
	int nOfUnits = d.getNofUnits(pCode1);
	assertEquals(5, nOfUnits);
	
	purchaseAmount = d.getAmount(pCode2); 
	assertEquals(153.7, purchaseAmount, 0.01);
	purchaseDiscount = d.getDiscount(pCode2);
	assertEquals(0, purchaseDiscount, 0.01);
	
	purchaseAmount = d.getAmount(pCode3); 
	assertEquals(83.34, purchaseAmount, 0.01);
	purchaseDiscount = d.getDiscount(pCode3);
	assertEquals(8.16, purchaseDiscount, 0.01);
	
	purchaseAmount = d.getAmount(pCode4); 
	assertEquals(100, purchaseAmount, 0.01);
	
	//R5
	SortedMap<Integer, List<String>> map1 = d.productIdsPerNofUnits();
	assertEquals(map1.toString(), "{1=[p5], 2=[p4, p6], 3=[p3], 7=[p2]}"); //two units for p4 and p6; p8 not considered
	
	SortedMap<Integer, Integer> map2 = d.totalPurchasePerCard();  //two cards
	assertEquals(map2.toString(), "{1=127, 2=83}");
	
	int totalPurchasesWithoutCard = d.totalPurchaseWithoutCard();
	assertEquals(totalPurchasesWithoutCard, 254);
	
	SortedMap<Integer, Integer> map3 = d.totalDiscountPerCard();  //two cards
	assertEquals(map3.toString(), "{1=27, 2=8}");
	
	
}
}
