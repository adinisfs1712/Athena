package br.com.master.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract Long getId();

    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Long.signum(getId() ^ (getId() >>> 32));
	return result;
    }

    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	BaseEntity other = (BaseEntity) obj;
	if (getId() != other.getId())
	    return false;
	return true;
    }

}
