package package1;

public class ListDisques {
	private String Code;
	private String Prix;
	public ListDisques(String code, String prix) {
		super();
		Code = code;
		Prix = prix;
	}

	public String getCode() {
		return Code;
	}
	public String getPrix() {
		return Prix;
	}
	
	public ListDisques(String code) {
		super();
		Code = code;
	}
	

}
