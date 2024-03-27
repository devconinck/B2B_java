package domain;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;
import util.Validation;

@Embeddable
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private String country;
    private String city;
    private String zipCode;
    private String street;
    private String number;
    
    // Moet public zijn voor de testen
    public Address() {
    }

    public Address(String country, String city, String zipCode, String street, String number) {
        setCountry(country);
        setCity(city);
        setZipCode(zipCode);
        setStreet(street);
        setNumber(number);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        this.country = country.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        this.city = city.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        if (zipCode == null || zipCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Zip code cannot be null or empty");
        }
        this.zipCode = zipCode.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("Street cannot be null or empty");
        }
        this.street = street.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number == null || number.trim().isEmpty()) {
            throw new IllegalArgumentException("House number cannot be null or empty");
        }
        if (!number.matches(Validation.HOUSE_NUMBER_REGEX)) {
            throw new IllegalArgumentException("Invalid house number format");
        }
        this.number = number.trim();
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, number, street, zipCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        return Objects.equals(city, other.city) && Objects.equals(country, other.country)
                && Objects.equals(number, other.number) && Objects.equals(street, other.street)
                && Objects.equals(zipCode, other.zipCode);
    }

    @Override
    public String toString() {
        return street + ", " + number + ", " + zipCode + ", " + city + ", " + country;
    }
}