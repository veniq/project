package com.veniqs.controller.librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.veniqs.controller.db.DBConnector;
import com.veniqs.model.BookPublisher;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PublisherTableCreator {

	private TableView<BookPublisher> dataTable;
	private ObservableList<BookPublisher> dataList;

	private TableColumn<BookPublisher, Integer> colID;
	private TableColumn<BookPublisher, String> colName;

	public PublisherTableCreator() {
		// create table + set items
		dataList = getData();
		dataTable = new TableView<BookPublisher>();

		// create cols
		TableColumn idCol = new TableColumn("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<BookPublisher, Integer>("id"));
		idCol.setPrefWidth(50);

		TableColumn nameCol = new TableColumn("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<BookPublisher, String>("name"));
		nameCol.setPrefWidth(300);

		// set columbs
		dataTable.getColumns().setAll(idCol, nameCol);
		dataTable.setPrefWidth(350);
		dataTable.setPrefHeight(300);

		dataTable.setItems(dataList);

		// dataTable.getSelectionModel().selectedIndexProperty().addListener(new
		// RowSelectChangeListener());
	}

	private ObservableList<BookPublisher> getData() {
		ObservableList<BookPublisher> data = FXCollections.observableArrayList();
		try {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			Statement stmt = c.createStatement();
			String query = "SELECT * FROM publisher;";
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				BookPublisher bp = new BookPublisher(id, name);
				data.add(bp);
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

	public TableView<BookPublisher> getTable() {
		return dataTable;
	}
}
