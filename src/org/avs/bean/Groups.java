package org.avs.bean;

public class Groups {
	
	private int id;
	private String name;
	private String image_path;
	private String about;
	private Mensagens mensagem;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Mensagens getMensagem() {
		return mensagem;
	}
	public void setMensagem(Mensagens mensagem) {
		this.mensagem = mensagem;
	}
	

}
