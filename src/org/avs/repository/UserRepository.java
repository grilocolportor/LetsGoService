package org.avs.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.avs.bean.Mensagens;
import org.avs.bean.User;
import org.avs.utils.Constantes;

public class UserRepository {

	public static User addUser(User user) {

		List<User> listUser = new ArrayList<User>();
		
		String sql = "INSERT INTO USER (COUNTRY_COD, NAME, EMAIL, PHONE) VALUES( " + "'" + user.getCountry_cod()
				+ "', '" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPhone() + "')";

		
		try {
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			st.executeUpdate(sql);
			DataBase.FecharConexao();

			// Chamar metodo para carregar e salvar a photo de perfil
			// retorna o caminho

			// retorna o usuario cadastrado
			User u = new User();
			List<User> lu = getUserByParam(user);
			if (lu.size() > 0) {
				u = lu.get(0);
			}

			// chama metodo que atualiza o campo image_path e retorna o usuario
			// atualizado
			u.setImage_path("teste/criar/pasta/foto.png");
			u = updateUser(u);
			listUser.add(u);
			return u;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			Mensagens m = new Mensagens();
			m.setCod(e.getErrorCode());
			m.setMensagem("Error" + " : " + e.getMessage());
			User us = new User();
			us.setMensagem(m);
			// u.setMensages(new Mensagens(Constantes.CODIGO_ERROR_DESCONHECIDO,
			// Constantes.MESSAGE_ERROR_DESCONHECIDO));
			listUser.add(us);
			return us;
		}

	}

	public static List<User> getUserByParam(User user) {

		String sql = " SELECT * FROM USER " + mountSql(user);

		return supportGetUserByParam(sql);

	}

	public static User updateUser(User user) {

		String sql = " UPDATE  USER SET  COUNTRY_COD = '" + user.getCountry_cod() + "', EMAIL = '" + user.getEmail()
				+ "', IMAGE_PATH = '" + user.getImage_path() + "', " + " NAME = '" + user.getName() + "', PHONE = '"
				+ user.getPhone() + "' WHERE _ID = " + user.getId() ;

		try {
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			st.executeUpdate(sql);
			DataBase.FecharConexao();

			// retorna o usuario cadastrado
			User u = new User();
			List<User> lu = getUserByParam(user);
			if (lu.size() > 0) {
				u = lu.get(0);
			}

			return u;

		} catch (SQLException e) {
			User u = new User();
			Mensagens m = new Mensagens();
			m.setCod(e.getErrorCode());
			m.setMensagem("Error!" + " -- " + e.getMessage());
			u.setMensagem(m);
			return u;
		}

	}
	
	public static List<User> getFriends(User user){
		
		String sql = " SELECT user, friend FROM  right join  friend " ;

		return supportGetUserByParam(sql);
		
	}
	
	private static List<User> supportGetUserByParam(String sql){
		
		List<User> lstUser = new ArrayList<>();
		
		try {
			Statement st = (Statement) DataBase.getConexaoMySQL().createStatement();
			ResultSet rs = st.executeQuery(sql);
			DataBase.FecharConexao();

			while (rs.next()) {
				User u = new User();
				u.setCountry_cod(rs.getString("COUNTRY_COD"));
				u.setEmail(rs.getString("EMAIL"));
				u.setId(rs.getInt("_ID"));
				u.setImage_path(rs.getString("IMAGE_PATH"));
				u.setName(rs.getString("NAME"));
				u.setPhone(rs.getString("PHONE"));
				Mensagens m = new Mensagens();
				m.setCod(Constantes.COD_OK);
				m.setMensagem(Constantes.MESSAGE_OK);
				u.setMensagem(m);
				lstUser.add(u);
			}

		} catch (SQLException e) {
			User u = new User();
			Mensagens m = new Mensagens();
			m.setCod(e.getErrorCode());
			m.setMensagem("Error!" + " -- " + e.getMessage());
			u.setMensagem(m);
			lstUser.add(u);
		}

		return lstUser;
		
		
	}

	private static String mountSql(User user) {

		String sql = " WHERE 1=1 ";

		if (user.getCountry_cod() != null && !user.getCountry_cod().isEmpty()) {
			sql = sql + " AND COUNTRY_COD = '" + user.getCountry_cod() + "'";
		}
		if (user.getEmail() != null && !user.getEmail().isEmpty()) {
			sql = sql + " AND EMAIL = '" + user.getEmail() + "'";
		}
		if (user.getId() != 0) {
			sql = sql + " AND  _ID = " + user.getId();
		}
		if (user.getImage_path() != null && !user.getImage_path().isEmpty()) {
			sql = sql + " AND IMAGE_PATH = '" + user.getImage_path() + "'";
		}
		if (user.getName() != null && !user.getName().isEmpty()) {
			sql = sql + " AND NAME = '" + user.getName() + "'";
		}
		if (user.getPhone() != null && !user.getPhone().isEmpty()) {
			sql = sql + " AND PHONE = '" + user.getPhone() + "'";
		}

		return sql;
	}

}