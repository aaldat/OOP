

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polito.oop.production.BrandException;
import it.polito.oop.production.Carmaker;

public class TestApp {

	@Test
	public void test() throws BrandException {
		
		// R1
		Carmaker fiat = new Carmaker();

		fiat.addModel("FCA001","Panda",2003,(float) 1.2,Carmaker.GASOLINE);
		fiat.addModel("FCA002","Panda",2003,(float) 1.3,Carmaker.DIESEL);
		fiat.addModel("FCA003","F500",2018,(float) 1.2,Carmaker.ELECTRIC);
		assertEquals(3,fiat.countModels());
		String cinquecento = fiat.getModel("FCA003");
		assertEquals("FCA003,F500,2018,1.2,3",cinquecento);

		
		// R2
		fiat.buildFactory("Torino");
		fiat.buildFactory("Milano");
		List<String> factories = fiat.getFactories();
		assertEquals(2,factories.size());
		assertTrue(factories.contains("Torino"));

		fiat.setProductionLines("Torino", "l1:10:0","l2:5:0");
		fiat.setProductionLines("Milano", "l3:10:1", "l4:16:2", "l5:4:2");
		Map<Integer,Integer> capacity = fiat.estimateYearlyProduction("Milano");
		assertEquals(2,capacity.size());
		assertEquals(10,capacity.get(1).intValue());
		assertEquals(20,capacity.get(2).intValue());

		// R3
		fiat.buildStorage("Torino", 10);
		fiat.storeCar("Torino", "FCA001");
		fiat.storeCar("Torino", "FCA001");
		fiat.storeCar("Torino", "FCA002");
		Map<String,Integer> m = fiat.getStorageSummary("Torino");
		assertEquals(2, m.get("FCA001").intValue());
		assertEquals(1, m.get("FCA002").intValue());
		fiat.removeCar("Torino", "FCA001");
		m = fiat.getStorageSummary("Torino");
		assertEquals(1, m.get("FCA001").intValue());

		// R4
		fiat.setISThresholds(10, 100);
		List<String> l = fiat.getModelsSustainability(1);
		//assertEquals("[FCA003]",l.toString());
		
		float sust = fiat.getCarMakerSustainability();
		assertEquals(2.8,sust,0.1);
		
		// R5
//		boolean success = fiat.plan("FCA002:14,FCA001:5");
//		assertTrue(success);
//		
//		assertEquals(10,fiat.getLineCapacity("Torino", "l1"));
		
		// R6
//		assertEquals (0.20,fiat.linesfullyAllocated(),0.01);
//		assertEquals (0.40,fiat.unusuedLines(),0.01);
	}

}
