package pageObject.webservices_entity;

public class Reward {

	private String rewardDate;
	private String treatment;
	private String providerName;
	private String providerAddress;
	private String points;


	// Getter Methods 

	public String getRewardDate() {
		return rewardDate;
	}

	public String getTreatment() {
		return treatment;
	}

	public String getProviderName() {
		return providerName;
	}

	public String getProviderAddress() {
		return providerAddress;
	}

	public String getPoints() {
		return points;
	}

	// Setter Methods 

	public void setRewardDate(String rewardDate) {
		this.rewardDate = rewardDate;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public void setProviderAddress(String providerAddress) {
		this.providerAddress = providerAddress;
	}

	public void setPoints(String points) {
		this.points = points;
	}
}

