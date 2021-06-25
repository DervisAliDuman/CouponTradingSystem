/**
 * 
 */
package tr.edu.gtu.cse222.project.group8.coupon;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import tr.edu.gtu.cse222.project.group8.firm.Firm;
import tr.edu.gtu.cse222.project.group8.system.CouponTradingSystem;
import tr.edu.gtu.cse222.project.group8.system.RandomKey;

/**
 * @author selman
 *
 */

public class Coupon implements Comparable{
	
	private enum TicketProgess{
	    waiting,
	    onSale,
	    saled,
	}

	public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
	private String name;


	private String id; 
	private Firm firm;
	private String ownerID;
	
	private Date expireDate;
	private TicketProgess status;
	private int dolar;
	private int cent;
	private String expireDate2;
	
	/**
	 * @param idGenerator
	 * @param firm
	 * @param expireDate
	 * @param price
	 */
	public Coupon(String name, Firm firm, Date expireDate,int dolar, int cent) {
		super();
		this.name = name;
		this.firm = firm;
		this.expireDate = expireDate;
		this.dolar = dolar;
		this.cent = cent;
		this.status = TicketProgess.onSale;
		this.ownerID = null;
		this.id = RandomKey.getAlphaNumericString(CouponTradingSystem.auctionIDSize);

	}
	
	public Coupon(String id, Firm firm, String expireDate,int dolar, int cent, String name) {
		super();
		this.firm = firm;
		try {
			this.expireDate = formatter.parse(expireDate);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.expireDate2 = expireDate;
		this.dolar = dolar;
		this.cent = cent;
		this.name = name;
		this.status = TicketProgess.onSale;
		this.id = id;
		this.ownerID = null;

	}

	public Coupon(String id, Firm firm, String expireDate, int dolar, int cent,String name,String ownerID) {
		super();
		this.firm = firm;
		try {
			this.expireDate = formatter.parse(expireDate);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		this.expireDate2 = expireDate;
		this.dolar = dolar;
		this.cent = cent;
		this.status = TicketProgess.onSale;
		this.id = id;
		this.name = name;
		this.ownerID = ownerID;

	}

	/* ID ; FIRM NAME ; EXPIRE DATE ; PRICE DOLAR ; PRICE CENT ; COUPON NAME ; OWNER ID ; STOCK */
	public Coupon(String id, String firmName, String expireDate, int dolar, int cent, String couponName, String ownerId) {
		this.id = id;
		this.firm = new Firm(firmName);
		try {
			this.expireDate = formatter.parse(expireDate);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		this.dolar = dolar;
		this.cent = cent;
		this.name = couponName;
		this.ownerID = ownerId;
	}

	@Override
	public String toString() {
		return(getFirm().getFirmName() + " - " +  getName() + " - " + "$" + getAmount() + " - EXP: " + formatter.format(getExpireDate()));
	}

	/**
	 * toWrite methods used for writing to the file
	 * @return 
	 */
	public String toWrite() {
		return(getId() + ";" + getFirm().getFirmName() + ";" + formatter.format(getExpireDate()) + ";" + getDolar() + ";" + getCent() + ";" + getName() + ";" + getOwnerID() + ";1");
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Coupon)) {
			return false;
		}
		
		Coupon coupon = (Coupon) o;
		return this.id.equals(coupon.id);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDolar() {
		return this.dolar;
	}

	public void setDolar(int dolar) {
		this.dolar = dolar;
	}

	public int getCent() {
		return this.cent;
	}

	public void setCent(int cent) {
		this.cent = cent;
	}

	public Firm getFirm() {
		return firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}

	public Date getExpireDate(){
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public TicketProgess getStatus() {
		return status;
	}

	public void setStatus(TicketProgess status) {
		this.status = status;
	}

	public void updatePrice(int dolar, int cent){
		setCent(cent);
		setDolar(dolar);
	}	
	
	public void updateStatus(TicketProgess input){
		status = input;
	}

	public String getOwnerID() {
		return this.ownerID;
	}

	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}

	public double getAmount(){
		return ((double)this.getDolar() + (double)this.getCent()/100);
	}
	
	@Override
	public int compareTo(Object o) {
		Coupon temp = (Coupon)o;
		Double amount = getAmount();
		
		return(amount.compareTo(temp.getAmount()));
	}		


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
