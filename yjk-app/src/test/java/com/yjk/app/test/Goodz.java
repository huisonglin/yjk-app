package com.yjk.app.test;

public class Goodz {

	private String id;
	
	private Integer count;
	
	private Integer size;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goodz other = (Goodz) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		other.setCount(this.getCount()+other.getCount());
		return true;
	}

	@Override
	public String toString() {
		return "Goodz [id=" + id + ", count=" + count + ", size=" + size + "]";
	}
	
	
}
