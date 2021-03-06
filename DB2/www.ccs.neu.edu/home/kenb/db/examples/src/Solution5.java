import java.sql.*;
import java.util.*;

/**
 * Apartment Ownership Methods.
 * The methods assume that all id primary keys are generated by auto incrementation.
 * <p>
 * Copyright &#169; 2010 Ken Baclawski. All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * <ol>
 * <li>Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimer.
 * <li>Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 * </ol>
 * <p>
 * THIS SOFTWARE IS PROVIDED BY KEN BACLAWSKI ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL KEN BACLAWSKI OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * @author Ken Baclawski
 */
public class Solution5 {
    /**
     * The database connection.
     */
    private Connection connection;

    /**
     * Apartment information.
     */
    public class Apartment {
	private int id;
	private String number;
	private List<Person> owners = new ArrayList<Person>();
	/**
	 * Construct an empty apartment object.
	 */
	public Apartment() {}
	/**
	 * Get the identifier of the apartment.
	 * @return The apartment id.
	 */
	public int getId() {
	    return id;
	}
	/**
	 * Set the identifier of an apartment.
	 * @param id The apartment identifier.
	 */
	public void setId(int id) {
	    this.id = id;
	}
	/**
	 * Get the number of the apartment.
	 * @return The apartment number.
	 */
	public String getNumber() {
	    return number;
	}
	/**
	 * Set the number of an apartment.
	 * @param number The apartment number.
	 */
	public void setNumber(String number) {
	    this.number = number;
	}
	/**
	 * Get the owners of the apartment.
	 * @return The list of apartment owners.
	 */
	public List<Person> getOwners() {
	    return owners;
	}
	/**
	 * Show the apartments and their owners.
	 * @return The apartments and their owners as a string.
	 */
	public String toString() {
	    StringBuilder b = new StringBuilder("Apartment id ");
	    b.append(id).append(" number ").append(number);
	    for (Person p : owners) {
		b.append("\n  owned by ").append(p);
	    }
	    return b.toString();
	}
    }

    /**
     * Person information.
     */
    public class Person {
	private int id;
	private String name;
	/**
	 * Construct an empty person object.
	 */
	public Person() {}
	/**
	 * Get the identifier of the person.
	 * @return The person id.
	 */
	public int getId() {
	    return id;
	}
	/**
	 * Set the identifier of an person.
	 * @param id The person identifier.
	 */
	public void setId(int id) {
	    this.id = id;
	}
	/**
	 * Get the name of the person.
	 * @return The person name.
	 */
	public String getName() {
	    return name;
	}
	/**
	 * Set the name of an person.
	 * @param name The person name.
	 */
	public void setName(String name) {
	    this.name = name;
	}
	/**
	 * Show the person
	 * @return The person as a string.
	 */
	public String toString() {
	    return "Person id " + id + " name " + name;
	}
    }

    /**
     * Construct an ownership object.
     * @param driver The driver class name.
     * @param url The database URL.
     * @param user The database user.
     * @param password The database password.
     */
    public Solution5(String driver, String url, String user, String password) {
	try {
	    Class.forName(driver);
	    connection = DriverManager.getConnection(url, user, password);
	} catch (Exception e) {
	    System.err.println("Unable to connect to the database due to " + e);
	}
    }

    /**
     * Create a new building.
     * @param address The building address.
     * @param apartmentCount The number of apartments to create.
     * @throws SQLException if a database error occurs.
     */
    public void createBuilding(String address, int apartmentCount) throws SQLException {
	PreparedStatement createBuilding = connection.prepareStatement("insert into Building(address) values(?);");
	createBuilding.setString(1, address);
	createBuilding.executeUpdate();
	ResultSet rs = createBuilding.getGeneratedKeys();
	rs.first();
	int buildingId = rs.getInt(1);
	PreparedStatement createApartment = connection.prepareStatement("insert into Apartment(number,building) values('A',?);");
	PreparedStatement updateApartment = connection.prepareStatement("update Apartment set number=? where id=?;");
	for (int i = 0; i < apartmentCount; ++i) {
	    createApartment.setInt(1, buildingId);
	    createApartment.executeUpdate();
	    rs = createApartment.getGeneratedKeys();
	    rs.first();
	    int apartmentId = rs.getInt(1);
	    System.out.println("Updating apartment number to " + apartmentId);
	    updateApartment.setString(1, "A" + apartmentId);
	    updateApartment.setInt(2, apartmentId);
	    updateApartment.executeUpdate();
	}
    }

    /**
     * Create a new person.
     * @param name The name of the person.
     * @throws SQLException if a database error occurs.
     */
    public void createPerson(String name) throws SQLException {
	PreparedStatement createPerson = connection.prepareStatement("insert into Person(name) values(?);");
	createPerson.setString(1, name);
	createPerson.executeUpdate();
    }

    /**
     * Acquire a new apartment.
     * @param person The name of the person who will be an owner of the apartment.
     * @param address The address of the building.
     * @param apartment The apartment number.
     * @throws SQLException if a database error occurs.
     */
    public void acquireApartment(String person, String address, String apartment) throws SQLException {
	PreparedStatement createOwnership = connection.prepareStatement
	    ("insert ignore into Owner(person,apartment) select p.id, a.id from Building b, Apartment a, Person p " +
	     "where b.address=? and b.id=a.building and a.number=? and p.name=?");
	createOwnership.setString(1, address);
	createOwnership.setString(2, apartment);
	createOwnership.setString(3, person);
	int updateCount = createOwnership.executeUpdate();
	System.out.println("Number of ownership records inserted is " + updateCount);
    }

    /**
     * Dispose ownership in an apartment by all owners.
     * @param address The address of the building.
     * @param apartment The apartment number.
     * @throws SQLException if a database error occurs.
     */
    public void disposeApartment(String address, String apartment) throws SQLException {
	PreparedStatement deleteOwnership = connection.prepareStatement
	    ("delete from Owner where exists(select * from Building b, Apartment a where b.address=? and b.id=a.building and a.number=? and apartment=a.id)");
	deleteOwnership.setString(1, address);
	deleteOwnership.setString(2, apartment);
	deleteOwnership.executeUpdate();
    }

    /**
     * List apartments and their owners.
     * @param buildingId The building identifier.
     * @return A list of all apartments in the building together with their owners.
     * @throws SQLException if a database error occurs.
     */
    public List<Apartment> listApartments(int buildingId) throws SQLException {
	PreparedStatement findApartments = connection.prepareStatement
	    ("select a.id, a.number from Apartment a where a.building=?");
	findApartments.setInt(1, buildingId);
	PreparedStatement findOwners = connection.prepareStatement
	    ("select p.id, p.name from Owner o, Person p where o.apartment=? and o.person=p.id");
        ResultSet apartments = findApartments.executeQuery();
	List<Apartment> apartmentList = new ArrayList<Apartment>();
	while (apartments.next()) {
	    Apartment apartment = new Apartment();
	    apartment.setId(apartments.getInt(1));
	    apartment.setNumber(apartments.getString(2));
	    List<Person> ownerList = apartment.getOwners();
	    findOwners.setInt(1, apartment.getId());
	    ResultSet owners = findOwners.executeQuery();
	    while (owners.next()) {
		Person person = new Person();
		person.setId(owners.getInt(1));
		person.setName(owners.getString(2));
		ownerList.add(person);
	    }
	    apartmentList.add(apartment);
	}
	return apartmentList;
    }

    /**
     * Test program for the methods.
     */
    public static void main(String... args) throws Exception {
	Solution5 solution5 = new Solution5("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/a", "root", "abcd");
	solution5.createBuilding("100 Main Street", 2);
	solution5.createPerson("Fred");
	solution5.createPerson("Fred");
	solution5.acquireApartment("Fred", "100 Main Street", "A1");
	for (Apartment a : solution5.listApartments(1)) {
	    System.out.println(a);
	}
	solution5.disposeApartment("100 Main Street", "A1");
	for (Apartment a : solution5.listApartments(1)) {
	    System.out.println(a);
	}
    }
}
