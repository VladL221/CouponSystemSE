package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConnectionPool {

	private static ConnectionPool instance = new ConnectionPool();
    private ArrayList<Connection> connections = new ArrayList<Connection>();
    private static final int MAX_CONNECTIONS = 10;

    
	private ConnectionPool() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			for (int i = 0; i < MAX_CONNECTIONS; i++) {
				connections.add(DriverManager.getConnection("jdbc:mysql://localhost:3306/couponsystem?serverTimezone=UTC","root","61795"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error connecting: "+ e.getMessage());
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}
	
	
	public synchronized Connection getConnection() {
		while(connections.size()==0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		Connection con = connections.get(0);
		connections.remove(0);
		return con;
	}
	
	
	public synchronized void restoreConnection(Connection connection) {
		connections.add(connection);
		notify(); //waking up the waiting thread
	}
	
	public void closeAllConnections() {
		for (Connection con : connections) {
			try {
				con.close();
			} catch (SQLException e) {
			
			}
		}
	}

}
