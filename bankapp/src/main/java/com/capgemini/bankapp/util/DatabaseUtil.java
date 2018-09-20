package com.capgemini.bankapp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:bankapp.properties")
public class DatabaseUtil {

	
	@Autowired
 Environment env;
	
	@Value("${db.driver}")
	private String driver;
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driver);
			System.out.println(driver);
			connection = DriverManager.getConnection((env.getProperty("db.url")),(env.getProperty("db.user")) ,(env.getProperty("db.password")) );
			if(connection != null)
				System.out.println("--connected -- ");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
