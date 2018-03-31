/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gbvbahia.ecommerce.model.embeddable;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Guilherme
 */
@Embeddable
public class Contact implements Serializable {

	private static final long serialVersionUID = 2945517953195005310L;

	@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`"
            + "{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:"
            + "[a-z0-9-]*[a-z0-9])?")
    @Size(max = 150)
    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @NotNull
    @Size(max = 2, min = 2)
    @Pattern(regexp = "(10)|([1-9][1-9])")
    @Column(name = "ddd1", length = 3)
    private String ddd1;

    @NotNull
    @Pattern(regexp = "[2-9][0-9]{3}[0-9]{4}")
    @Column(name = "phone1", length = 12)
    private String phone1;

    @NotNull
    @Size(max = 2, min = 2)
    @Pattern(regexp = "(10)|([1-9][1-9])")
    @Column(name = "ddd2", length = 3)
    private String ddd2;

    @NotNull
    @Pattern(regexp = "[2-9][0-9]{3}[0-9]{4}")
    @Column(name = "phone2", length = 12)
    private String phone2;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDdd1() {
        return ddd1;
    }

    public void setDdd1(String ddd1) {
        this.ddd1 = ddd1;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getDdd2() {
        return ddd2;
    }

    public void setDdd2(String ddd2) {
        this.ddd2 = ddd2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Contact{" + "email=" + email + ", ddd1=" + ddd1 + ", phone1=" + phone1 + ", ddd2=" + ddd2 + ", phone2=" + phone2 + '}';
    }
    
    
}
