package org.avs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.avs.bean.Cadastros;


public class CadastroRepository {


	public static Cadastros cadastroUsuario(Cadastros cadastro) {

		
		
		String sql = "INSERT INTO usuario ( idUsuario, nome, estado, cidade, login, senha, email, telefone, celular) "
				+ "	VALUES(" + cadastro.getIdUsuario() + ", '" + cadastro.getNome() 
				+ "', '" + cadastro.getEstado() + "', '" + cadastro.getCidade() + "', '" + cadastro.getLogin() 
						+ "', '" + cadastro.getSenha()  + "', '" + cadastro.getEmail()   + "', '" + cadastro.getTelefone()  + "', '" + cadastro.getCelular() + "')";

		System.out.println(sql);
		try {
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			st.executeUpdate(sql);
			DataBase.FecharConexao();

			// Chamar metodo para carregar e salvar a photo de perfil
			// retorna o caminho

			// retorna o usuario cadastrado
			Cadastros c = new Cadastros();
			List<Cadastros> lu = getUserByParam(cadastro);
			if (lu.size() > 0) {
				c = lu.get(0);
			}

			// chama metodo que atualiza o campo image_path e retorna o usuario
			// atualizado
			//u.setImage_path("teste/criar/pasta/foto.png");
			//u = updateUser(u);
			//listUser.add(c);
			
			return c;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			Cadastros c = new Cadastros();
			// u.setMensages(new Mensagens(Constantes.CODIGO_ERROR_DESCONHECIDO,
			// Constantes.MESSAGE_ERROR_DESCONHECIDO));
			//listUser.add(us);
			return c;
		}

	}
	
	public static List<Cadastros> getUserByParam(Cadastros cadastro) {

		String sql = " SELECT * FROM usuario " + mountSql(cadastro);

		return supportGetUserByParam(sql);

	}
	
	
	private static List<Cadastros> supportGetUserByParam(String sql){
		
		List<Cadastros> lstUser = new ArrayList<>();
		
		try {
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			ResultSet rs = st.executeQuery(sql);
			DataBase.FecharConexao();

			while (rs.next()) {
				Cadastros c = new Cadastros();
				c.setCelular(rs.getString("celular"));
				c.setCidade(rs.getString("cidade"));
				c.setEmail(rs.getString("email"));
				c.setEstado(rs.getString("estado"));
				c.setIdUsuario(rs.getInt("idUsuario"));
				c.setLogin(rs.getString("login"));
				c.setNome(rs.getString("nome"));
				c.setSenha(rs.getString("senha"));
				c.setTelefone(rs.getString("telefone"));
				
				lstUser.add(c);
			}

		} catch (SQLException e) {
			Cadastros c = new Cadastros();
			//Mensagens m = new Mensagens();
			//m.setCod(e.getErrorCode());
			//m.setMensagem("Error!" + " -- " + e.getMessage());
			//c.setMensagem(m);
			lstUser.add(c);
		}

		return lstUser;
		
		
	}
	
	private static String mountSql(Cadastros c) {

		String sql = " WHERE 1=1 ";

		if (c.getCelular() != null && !c.getCelular().isEmpty()) {
			sql = sql + " AND CELULAR = '" + c.getCelular() + "'";
		}
		if (c.getEmail() != null && !c.getEmail().isEmpty()) {
			sql = sql + " AND EMAIL = '" + c.getEmail() + "'";
		}
		if (c.getIdUsuario() != 0) {
			sql = sql + " AND  idusuario = " + c.getIdUsuario();
		}
		if (c.getCidade() != null && !c.getCidade().isEmpty()) {
			sql = sql + " AND CIDADE = '" + c.getCidade() + "'";
		}
		if (c.getEstado() != null && !c.getEstado().isEmpty()) {
			sql = sql + " AND estado = '" + c.getEstado() + "'";
		}
		if (c.getLogin()!= null && !c.getLogin().isEmpty()) {
			sql = sql + " AND login = '" + c.getLogin() + "'";
		}
		if (c.getNome()!= null && !c.getNome().isEmpty()) {
			sql = sql + " AND nome = '" + c.getNome() + "'";
		}
		if (c.getSenha()!= null && !c.getSenha().isEmpty()) {
			sql = sql + " AND senha = '" + c.getSenha() + "'";
		}
		if (c.getTelefone()!= null && !c.getTelefone().isEmpty()) {
			sql = sql + " AND telefone = '" + c.getTelefone() + "'";
		}

		return sql;
	}



	
}
