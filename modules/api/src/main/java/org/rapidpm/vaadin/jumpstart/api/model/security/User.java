package org.rapidpm.vaadin.jumpstart.api.model.security;

import java.util.Objects;

/**
 * Created by svenruppert on 11.04.17.
 */
public class User {
    private final Integer customerID;
    private final String firstname;
    private final String lastname;
    private final String email;

    public User(Integer customerID, String firstname, String lastname,
        String email) {
        this.customerID = customerID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID, firstname, lastname, email);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (!(o instanceof User))
            return false;
        final User user = (User) o;
        return Objects.equals(customerID, user.customerID) && Objects
            .equals(firstname, user.firstname) && Objects
            .equals(lastname, user.lastname) && Objects
            .equals(email, user.email);
    }

    @Override
    public String toString() {
        return "User{" + "customerID=" + customerID + ", firstname='"
            + firstname + '\'' + ", lastname='" + lastname + '\'' + ", email="
            + email + '}';
    }
}
