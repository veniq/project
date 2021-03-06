package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.BookGenre;
import com.veniqs.model.Language;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LanguageTableCreator implements TableCreator {

	private TableView<Language> dataTable;
	private ObservableList<Language> dataList;

	private TableColumn<Language, Integer> colID;
	private TableColumn<Language, String> colName;

	private Language selectedLanguage;

	public LanguageTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<Language>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Language, Integer>("id"));
		idCol.setPrefWidth(50);

		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Language, String>("name"));
		nameCol.setPrefWidth(550);

		// set columbs
		dataTable.getColumns().setAll(idCol, nameCol);
		dataTable.setPrefWidth(600);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);
		dataTable.getSelectionModel().selectedItemProperty().addListener(getListener());

	}

	public TableView<Language> getDataTable(String str) {
		dataTable.setItems(getData(str));
		return dataTable;
	}
	
	public Language getSelectedLanguage() {
		return selectedLanguage;
	}

	private ChangeListener<Language> getListener() {
		ChangeListener<Language> listener = new ChangeListener<Language>() {

			@Override
			public void changed(ObservableValue<? extends Language> observable, Language oldValue, Language newValue) {
				selectedLanguage = newValue;
			}
		};

		return listener;
	}

	public ObservableList<Language> getData() {
		ObservableList<Language> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM language;";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Language ap = new Language(id, name);
				data.add(ap);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
		}

		return data;
	}

	public ObservableList<Language> getData(String n) {
		ObservableList<Language> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM language where name like '%" + n + "%';";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Language ap = new Language(id, name);
				data.add(ap);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ОШИБКА " + e.getClass().getName() + ": " + e.getMessage());
		}

		return data;
	}

	public TableView<Language> getTable() {
		return dataTable;
	}
}
