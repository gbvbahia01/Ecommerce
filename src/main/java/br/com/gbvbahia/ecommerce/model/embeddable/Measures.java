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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Guilherme
 */
@Embeddable
public class Measures implements Serializable {

	private static final long serialVersionUID = -2259688669683405596L;

	@DecimalMin(value = "0.0")
    @Column(name = "height", precision = 2, scale = 10)
    @NotNull
    private Float height;

    @DecimalMin(value = "0.0")
    @Column(name = "width", precision = 2, scale = 10)
    @NotNull
    private Float width;

    @DecimalMin(value = "0.0")
    @Column(name = "depth", precision = 2, scale = 10)
    @NotNull
    private Float depth;

    @DecimalMin(value = "0.0")
    @Column(name = "weight", precision = 2, scale = 10)
    @NotNull
    private Float weight;

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getDepth() {
        return depth;
    }

    public void setDepth(Float depth) {
        this.depth = depth;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "Measures{" + "height=" + height + ", width=" + width + ", depth=" + depth + ", weight=" + weight + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.height);
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
        final Measures other = (Measures) obj;
        if (Float.floatToIntBits(this.width) != Float.floatToIntBits(other.width)) {
            return false;
        }
        if (Float.floatToIntBits(this.depth) != Float.floatToIntBits(other.depth)) {
            return false;
        }
        if (Float.floatToIntBits(this.weight) != Float.floatToIntBits(other.weight)) {
            return false;
        }
        if (!Objects.equals(this.height, other.height)) {
            return false;
        }
        return true;
    }
}
