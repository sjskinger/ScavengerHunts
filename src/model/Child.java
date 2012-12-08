package model;

public class Child {
	private String Name;
	private int Tag;
	
	public Child(String name, int i) {
		super();
		Name = name;
		Tag = i;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getTag() {
		return Tag;
	}

	public void setTag(int Tag) {
		this.Tag = Tag;
	}

}

