package com.training.servlet;

public class NamePwd {
		String Name;
		String Pwd;
		public NamePwd(String name, String pwd) {
			super();
			Name = name;
			Pwd = pwd;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getPwd() {
			return Pwd;
		}
		public void setPwd(String pwd) {
			Pwd = pwd;
		}
		
}
