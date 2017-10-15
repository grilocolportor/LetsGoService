package org.avs.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mensagens {
	
	private int cod;
	private String mensagem;
	
	
	public Mensagens(){
		
	}
	
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
