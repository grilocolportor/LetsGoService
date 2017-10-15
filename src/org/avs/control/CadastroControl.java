package org.avs.control;

import java.util.List;

import org.avs.bean.Cadastros;
import org.avs.repository.CadastroRepository;

public class CadastroControl {

	public static Cadastros cadastroUser(Cadastros cadastro){
		return CadastroRepository.cadastroUsuario(cadastro);
	}
	
	public static List<Cadastros> getUserByParam(Cadastros cadastro){
		return CadastroRepository.getUserByParam(cadastro);
	}
	
}
