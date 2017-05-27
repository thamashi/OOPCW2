public class Symbol implements ISymbol {

	String image;
	int value;

	public String getImage() {
		return image;
	}

	public String setImage(String image) {
		this.image = image;
                return image;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}



