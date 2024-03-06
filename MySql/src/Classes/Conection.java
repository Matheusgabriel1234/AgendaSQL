package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conection {
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/bdagenda","root","Telepa33.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, "Banco de dados não conectado");
		}
		 return null;
	}

}
