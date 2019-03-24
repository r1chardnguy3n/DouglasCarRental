
public class Reservation {

	public String make, model, year, type, cusName, cusPhone, startDate, endDate;
	double totalPrice;
	int extraDriver;
	int unlimitedKms;
	int insurance;
	int price;
	int cusId;
	int resid;
	

	public Reservation(){
		
	}
	
	public Reservation(String make, String model, String year, String type, int price, int insurance,
			int cusId, String cusName, String cusPhone, String startDate, String endDate, int unlimitedKms,
			int extraDriver, double totalPrice) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.type = type;
		this.price = price;
		this.insurance = insurance;
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusPhone = cusPhone;
		this.startDate = startDate;
		this.endDate = endDate;
		this.unlimitedKms = unlimitedKms;
		this.extraDriver = extraDriver;
		this.totalPrice = totalPrice;
	}
	

	public Reservation(int resId, String make, String model, String year, String type, String startDate, String endDate,
			double totalPrice) {
		super();
		this.resid = resId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getUnlimitedKms() {
		return unlimitedKms;
	}

	public void setUnlimitedKms(int unlimitedKms) {
		this.unlimitedKms = unlimitedKms;
	}

	public int getExtraDriver() {
		return extraDriver;
	}

	public void setExtraDriver(int extraDriver) {
		this.extraDriver = extraDriver;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	@Override
	public String toString() {
		return "Reservation [make=" + make + ", model=" + model + ", year=" + year + ", type=" + type + ", price="
				+ price + ", insurance=" + insurance + ", cusId=" + cusId + ", cusName=" + cusName + ", cusPhone="
				+ cusPhone + ", startDate=" + startDate + ", endDate=" + endDate + ", unlimitedKms=" + unlimitedKms
				+ ", extraDriver=" + extraDriver + ", totalPrice=" + totalPrice + "]";
	}
	
	


}
