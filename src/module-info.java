open module JvaFXAndPersistenceAndUnitTests {
	//FX
	requires javafx.base;
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;

	//Persistence
	requires jakarta.persistence;
	requires java.sql;
	requires java.instrument;
	//Unit tests
	requires org.junit.jupiter.api;
	requires org.mockito.junit.jupiter;
	requires org.mockito;
	requires com.opencsv;
	requires org.junit.jupiter.params;
	
	requires jakarta.mail;
	requires java.desktop;
}