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
	requires org.junit.jupiter.params;
	
	exports util;
	exports domain;
	exports gui;
	exports main;
	exports domain.login;
	exports repository;
}