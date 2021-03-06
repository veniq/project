package com.veniqs.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Language {

	private SimpleIntegerProperty id;
	private SimpleStringProperty name;

	public Language(int idC, String nameC) {
		id = new SimpleIntegerProperty(idC);
		name = new SimpleStringProperty(nameC);
	}

	public int getId() {
		return id.get();
	}

	public String getName() {
		return name.get();
	}

}
