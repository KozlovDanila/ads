package com.example.demo;

//<NumCode>036</NumCode>
//<CharCode>AUD</CharCode>
//<Nominal>1</Nominal>
//<Name>Австралийский доллар</Name>
//<Value>47,3482</Value>

public class Currencies {

	private String numCode;
	private String charCode;
	private String nominal;
	private String name;
	private String value;

	public String getNumCode() {
		return numCode;
	}

	public void setNumCode(String numCode) {
		this.numCode = numCode;
	}

	public String getCharCode() {
		return charCode;
	}

	public void setCharCode(String charCode) {
		this.charCode = charCode;
	}

	public String getNominal() {
		return nominal;
	}

	public void setNominal(String nominal) {
		this.nominal = nominal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Currencies{" +
				"numCode='" + numCode + '\'' +
				", charCode='" + charCode + '\'' +
				", nominal='" + nominal + '\'' +
				", name='" + name + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}
