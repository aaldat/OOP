package discounts;
import java.util.*;
import java.util.stream.Collectors;

public class Discounts {

	private SortedMap<Integer, Card> cards = new TreeMap<>();
	private SortedMap<String, Category> categories = new TreeMap<>();
	private SortedMap<String, Product> products = new TreeMap<>();
	private SortedMap<Integer, Purchase> purchases = new TreeMap<>();
	private int code = 1;
	private int codePurchase = 1;
	
	public Discounts() {
		cards.put(0, new Card(0,"Dummy"));
	}
	
	//R1
	public int issueCard(String name) {
		Card c = new Card(code++, name);
		cards.put(c.getCode(), c);
	    return c.getCode();
	}
	
    public String cardHolder(int cardN) {
        return cards.get(cardN).getUser();
    }
    

	public int nOfCards() {
	       return cards.size()-1;

	}
	
	//R2
	public void addProduct(String categoryId, String productId, double price) 
			throws DiscountsException {
		if(!categories.containsKey(categoryId)) {
			Category c = new Category(categoryId);
			categories.put(categoryId, c);
		}
		if(products.containsKey(productId)) throw new DiscountsException("Il codice del prodotto gia' esiste.");
		Product p = new Product(categories.get(categoryId), productId, price);
		categories.get(categoryId).addProduct(p);
		products.put(productId, p);
	}
	
	public double getPrice(String productId) 
			throws DiscountsException {
		if(!products.containsKey(productId)) throw new DiscountsException("Il codice del prodotto e' indefinito");
        return products.get(productId).getPrice();
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
		if(categories.get(categoryId).getProductsOfCategory().isEmpty()) throw new DiscountsException("La categoria non ha prodotti.");
        int avgInt = (int) Math.round(categories.get(categoryId).getProductsOfCategory().values().stream()
        								.mapToDouble(p -> p.getPrice())
        								.average()
        								.getAsDouble());
        return avgInt;
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
		if(!categories.containsKey(categoryId)) throw new DiscountsException("La categoria non esiste.");
		if(percentage < 0 || percentage > 50) throw new DiscountsException("La percentuale è minore di 0 o maggiore di 50.");
		categories.get(categoryId).setDiscount(percentage);
	}

	public int getDiscount(String categoryId) {
        return categories.get(categoryId).getDiscount();
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
		Purchase purchase = new Purchase();
		for(String s : items) {
			String[] info = s.split(":");
			if(!products.containsKey(info[0])) throw new DiscountsException("Codice prodotto fa riferimento ad un prodotto non esistente.");
			Product product = products.get(info[0]);
			int quantityProduct = Integer.parseInt(info[1]);
			PurchaseLine pl = new PurchaseLine(product, quantityProduct);
			purchase.addLine(pl);
			product.addLine(pl);
		}
		cards.get(cardId).addPurchase(purchase);
		purchase.setId(codePurchase);
		purchases.put(purchase.getId(), purchase);
		boolean yesDiscount = (cardId != 0);
		purchase.setAmount(yesDiscount);
		return codePurchase++;
	}

	public int addPurchase(String... items) throws DiscountsException {
		return addPurchase(0, items);
	}
	
	public double getAmount(int purchaseCode) {
        return purchases.get(purchaseCode).getPurchaseAmount();
	}
	
	public double getDiscount(int purchaseCode)  {
        return purchases.get(purchaseCode).getPurchaseDiscount();
	}
	
	public int getNofUnits(int purchaseCode) {
        return purchases.get(purchaseCode).getQuantity();
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
        return products.values().stream()													//lista di prodotti
        		.filter(p -> p.getNofUnits() > 0)											//prendo tutti quei prodotti che sono stati acquistati almeno una volta
        		.collect(Collectors.groupingBy(												//raggruppo in
        					Product::getNofUnits,											//come chiave: numero di unità acquistate
        					TreeMap::new,													//in una nuova TreeMap
        					Collectors.mapping(Product::getCode, Collectors.toList())));	//come valore: prendo una lista di codici di prodotti
	}
	
	public SortedMap<Integer, Integer> totalPurchasePerCard() {
        return cards.values().stream()														//lista di carte
        		.filter(card -> card.getCode() != 0 && card.getNofPurchases() > 0 )			//prendo tutte quelle carte con id!=0 e che hanno almeno un acquisto
        		.collect(Collectors.toMap(													//rimappo:
        				Card::getCode,														//come chiave: il codice della carta
        				Card::getAmountPurchases, 											//come valore: il prezzo totale degli acquisti
        				(p1, p2) -> p1, 													//prendo la chiave
        				TreeMap::new														//in una nuova TreeMap (probabilmente queste ultime due mi servono
        				));																	//						per passare da Map a TreeMap)
	}
	
	public int totalPurchaseWithoutCard() {
        return cards.get(0).getAmountPurchases();
	}
	
	public SortedMap<Integer, Integer> totalDiscountPerCard() {
		return cards.values().stream()														//lista di carte
				.filter(card -> card.getCode() != 0 && card.getNofPurchases() > 0 )			//prendo tutte quelle carte con id!=0 e che hanno almeno un acquisto
        		.collect(Collectors.toMap(													//rimappo:
        				Card::getCode,														//come chiave: il codice della carta
        				Card::getAmountDiscount, 											//come valore: l'ammontare dello sconto
        				(p1, p2) -> p1, 													//prendo la chiave
        				TreeMap::new														//in una nuova TreeMap (probabilmente queste ultime due mi servono
        				));																	//						per passare da Map a TreeMap)
	}


}
