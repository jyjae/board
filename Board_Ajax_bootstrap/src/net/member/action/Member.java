package net.member.action;

public class Member {
private String id;
private String password;
private String name;
private int age;
private String gender;
private String email;

public Member() {
	// TODO Auto-generated constructor stub
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Member [id=").append(id).append(", password=").append(password).append(", name=").append(name)
			.append(", age=").append(age).append(", gender=").append(gender).append(", email=").append(email)
			.append("]");
	return builder.toString();
}

}
