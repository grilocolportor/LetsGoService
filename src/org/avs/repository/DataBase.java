package org.avs.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase {
	public static String status = "Não conectou...";

	public DataBase() {

	}

	public static java.sql.Connection getConexaoMySQL() {
		Connection connection = null;

		try {
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			String serverName = "localhost:3306";
			String dataBase = "letsgo";
			String url = "jdbc:mysql://" + serverName + "/" + dataBase + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			//String url = "jdbc:mysql://" + serverName + "/" + dataBase;
			System.out.println(url);
			String userName = "root";
			String password = "root";

			connection = DriverManager.getConnection(url, userName, password);

			if (connection != null) {
				status = "STATUS--->conectado com sucesso!";
			} else {
				status = "STATUS--->Não foi possivel realizar conexão!";
			}
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");

			return null;
		} catch (SQLException e) {
			System.out.println(e.toString());
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");

			return null;
		}

	}

	public static String statusConnection() {
		return status;
	}

	public static boolean FecharConexao() {

		try {

			DataBase.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	public static java.sql.Connection ReiniciarConexao() {

		FecharConexao();

		return DataBase.getConexaoMySQL();

	}

}
