package test.one;

public class Student {

	@ColumnName(value = "姓名")
	private String name;
	
	@ColumnName(value = "年龄")
	private Integer age;
	
	@ColumnName(value = "地址")
	private String address;
	
	@ColumnName(value = "学校")
	private String school;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	
}
