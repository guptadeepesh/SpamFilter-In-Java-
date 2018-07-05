
public class Table {
	private int label;
	private String text;
	public int getLabel() {
		return label;
	}
	public void setId(int label) {
		this.label = label;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String toString(){
		return "label = " + getLabel() + " :::: text = " + getText() + "\n";
	}

}
