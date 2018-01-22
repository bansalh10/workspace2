package com.nagarro.assignment4.data;

public enum Constants {
	UserId("id"), ImageList("imagelist"), ImageUploadPath("/WEB-INF/imgUpload.jsp"), LoginPagePath(
			"login.jsp"), Username("username"), Password(
					"password"), Email("emailid"), SignupPagePath("signup.jsp"), ImageId("id"), Updatedname("imgname");
	private String name;
	public final static int value_1mb = 1048576;

	private Constants(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
