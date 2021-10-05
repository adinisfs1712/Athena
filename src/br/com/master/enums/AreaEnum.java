package br.com.master.enums;

public enum AreaEnum {

	U("Urbana"), R("Rural");

	private String area;

	private AreaEnum(String area) {
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	@Override
	public String toString() {
		return getArea();

	}
}
