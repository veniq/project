package com.veniq.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.veniqs.controller.db.DBConnector;

import application.Main;

public class AuthorGenerator {

	private Random rnd = new Random();
	private String[] firstNameList = { "Bumblebee", "Bandersnatch", "Broccoli", "Rinkydink", "Bombadil", "Boilerdang",
			"Bandicoot", "Fragglerock", "Muffintop", "Congleton", "Blubberdick", "Buffalo", "Benadryl", "Butterfree",
			"Burberry", "Whippersnatch", "Buttermilk", "Beezlebub", "Budapest", "Boilerdang", "Blubberwhale",
			"Bumberstump", "Bulbasaur", "Cogglesnatch", "Liverswort", "Bodybuild", "Johnnycash", "Bendydick",
			"Burgerking", "Bonaparte", "Bunsenburner", "Billiardball", "Bukkake", "Baseballmitt", "Blubberbutt",
			"Baseballbat", "Rumblesack", "Barister", "Danglerack", "Rinkydink", "Bombadil", "Honkytonk", "Billyray",
			"Bumbleshack", "Snorkeldink", "Anglerfish", "Beetlejuice", "Bedlington", "Bandicoot", "Boobytrap",
			"Blenderdick", "Bentobox", "Anallube", "Pallettown", "Wimbledon", "Buttercup", "Blasphemy", "Syphilis",
			"Snorkeldink", "Brandenburg", "Barbituate", "Snozzlebert", "Tiddleywomp", "Bouillabaisse", "Wellington",
			"Benetton", "Bendandsnap", "Timothy", "Brewery", "Bentobox", "Brandybuck", "Benjamin", "Buckminster",
			"Bourgeoisie", "Bakery", "Oscarbait", "Buckyball", "Bourgeoisie", "Burlington", "Buckingham",
			"Barnoldswick" };
	private String[] lastNameList = { "Coddleswort", "Crumplesack", "Curdlesnoot", "Calldispatch", "Humperdinck",
			"Rivendell", "Cuttlefish", "Lingerie", "Vegemite", "Ampersand", "Cumberbund", "Candycrush", "Clombyclomp",
			"Cragglethatch", "Nottinghill", "Cabbagepatch", "Camouflage", "Creamsicle", "Curdlemilk", "Upperclass",
			"Frumblesnatch", "Crumplehorn", "Talisman", "Candlestick", "Chesterfield", "Bumbersplat", "Scratchnsniff",
			"Snugglesnatch", "Charizard", "Carrotstick", "Cumbercooch", "Crackerjack", "Crucifix", "Cuckatoo",
			"Cockletit", "Collywog", "Capncrunch", "Covergirl", "Cumbersnatch", "Countryside", "Coggleswort",
			"Splishnsplash", "Copperwire", "Animorph", "Curdledmilk", "Cheddarcheese", "Cottagecheese", "Crumplehorn",
			"Snickersbar", "Banglesnatch", "Stinkyrash", "Cameltoe", "Chickenbroth", "Concubine", "Candygram",
			"Moldyspore", "Chuckecheese", "Cankersore", "Crimpysnitch", "Wafflesmack", "Chowderpants", "Toodlesnoot",
			"Clavichord", "Cuckooclock", "Oxfordshire", "Cumbersome", "Chickenstrips", "Battleship", "Commonwealth",
			"Cunningsnatch", "Custardbath", "Kryptonite", "Curdlesnoot", "Cummerbund", "Coochyrash", "Crackerdong",
			"Crackerdong", "Curdledong", "Crackersprout", "Crumplebutt", "Colonist", "Coochierash" };

	private String getAuthorName() {
		return getRandomValue(firstNameList) + " " + getRandomValue(lastNameList);
	}

	private String getRandomValue(String[] mas) {
		return mas[rnd.nextInt(mas.length - 1)];
	}

	
	private void createAuthors() {
		
	}
	
	private AuthorGenerator() {
		try {
			for (int i = 0; i < 26; i++) {
			DBConnector connection = new DBConnector();
			Connection c = connection.getConnection();
			//System.out.println("Opened database successfully AddOneEntityHandler");
			Statement stmt = c.createStatement();
			String query = "SELECT create_or_get_author_id('" + getAuthorName() + "');";
			stmt.executeQuery(query);
			/*
			 * System.out.println(query); ResultSet rs =
			 * stmt.executeQuery(query); while (rs.next()) { int id =
			 * rs.getInt(rs.getMetaData().getColumnName(1));
			 * System.out.println(id + " " + rs.getMetaData().getColumnName(1));
			 * } rs.close();
			 */
			stmt.close();
			c.commit();
			c.close();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ОШИБКА benedict " + e.getClass().getName() + ": " + e.getMessage());
		}
		
	}
	
}
