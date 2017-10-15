package org.avs.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private int id;
	private String country_cod;
	private String name;
	private String email;
	private String phone;
	private String image_path;
	private Mensagens mensagem;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountry_cod() {
		return country_cod;
	}
	public void setCountry_cod(String country_cod) {
		this.country_cod = country_cod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public Mensagens getMensagem() {
		return mensagem;
	}
	public void setMensagem(Mensagens mensagem) {
		this.mensagem = mensagem;
	}
	

}
