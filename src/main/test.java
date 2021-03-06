package main;

import mechanisms.ExponentialMechanism;
import mechanisms.LaplaceMechanism;
import util.MapUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


//测试函数
public class test {

	public static void main(String[] args) throws Exception {

		// TEST Laplace Mechanism
		testLap();

		// TEST Exponential Mechanism
		testExpMach();
		
		//TEST Exponential-Like mechanism (PDP)
//		testPDP_PE();

	}
	
//	public static void testPDP_PE() throws Exception {
//
//		double e = 1;
//		double sensitivity = 1;
//
//		int stdAndStaffNum = readNumOfForeignStdAndStaff();
//		System.out.println(" answer question  with ε=" + e);
//		System.out.println(LaplaceMechanism.addLaplaceNoise(stdAndStaffNum, e, sensitivity));
//
//		int stdNum = readNumOfForeignStd();
//		System.out.println("\n answer question  with ε=" + e);
//		System.out.println(LaplaceMechanism.addLaplaceNoise(stdNum, e, sensitivity));
//
//	}

	public static void testLap() throws Exception {

		double e = 1;
		double sensitivity = 1;

		int stdAndStaffNum = readNumOfForeignStdAndStaff();
		System.out.println(" answer question  with ε=" + e);
		System.out.println(LaplaceMechanism.addLaplaceNoise(stdAndStaffNum, e, sensitivity));

		int stdNum = readNumOfForeignStd();
		System.out.println("\n answer question  with ε=" + e);
		System.out.println(LaplaceMechanism.addLaplaceNoise(stdNum, e, sensitivity));

	}

	public static void testExpMach() throws ClassNotFoundException, SQLException {

		// read the scores (=age) of each possible output.
		HashMap<String, Integer> name_age = readHashMap_Name_Age();

		double e = 1;
		double sensitivity = 1;

		name_age = (HashMap<String, Integer>) MapUtil.sortByValueDESC(name_age);

		// answer question 8.(3)
		System.out.println("\n answer question  with ε=" + e);
		System.out.println(ExponentialMechanism.r(name_age, e, sensitivity));

		// print the scores
		System.out.println("\n scores :");
		System.out.println(name_age);

		// print the Unnormalized output probabilities
		HashMap<String, Double> hm_weight = ExponentialMechanism.getWeights_hm(name_age, e, sensitivity);
		hm_weight = (HashMap<String, Double>) MapUtil.sortByValueDESC(hm_weight);
		System.out.println("\n Unnormalized output probabilities");
		System.out.println(hm_weight);

		// print the Normalized output probabilities
		HashMap<String, Double> hm_weight_prob = ExponentialMechanism.getNormalizedProb_helper_hm_double(hm_weight);
		hm_weight_prob = (HashMap<String, Double>) MapUtil.sortByValueDESC(hm_weight_prob);
		System.out.println("\n Normalized output probabilities");
		System.out.println(hm_weight_prob);

		// TEST correct rate of exponential mechanism
		// int correctTimes = 0;
		// int testTime = 200000;
		// for (int i = 0; i < testTime; i++) {
		// if (ExponentialMechanism.r(name_age, e,sensitivity).equals("Masatoshi
		// Yoshikawa")) {
		// correctTimes++;
		// }
		// }
		// output the correct rate.
		// System.out.println((double)correctTimes/testTime);

	}

	// read the "scores" from ylab16.sqlite, here the score=age because we want
	// to put the eldest person
	static HashMap<String, Integer> readHashMap_Name_Age() throws SQLException, ClassNotFoundException {

		HashMap<String, Integer> name_age = new HashMap<String, Integer>();

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(db_file);

		Statement statement = conn.createStatement();

		String sql_foreigner_std = "select  NAME, \"APRX-AGE\" from ylab16";
		ResultSet rs = statement.executeQuery(sql_foreigner_std);
		while (rs.next()) {
			String name = rs.getString(1);
			int age = rs.getInt(2);
			name_age.put(name, age);

		}

		closeResultSet(rs);
		closeStatement(statement);
		closeConnection(conn);
		return name_age;

	}

	static String db_file = "jdbc:sqlite:./db/ylab16.sqlite";

	static int readNumOfForeignStd() throws SQLException, ClassNotFoundException {
		int num = 0;

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(db_file);

		Statement statement = conn.createStatement();

		String sql_foreigner_std = "SELECT count(*) from ylab16 where \"FOREIGNER-OR-NOT\"=1 and POSITION=\"student\"";
		ResultSet rs = statement.executeQuery(sql_foreigner_std);
		num = rs.getInt(1);

		closeResultSet(rs);
		closeStatement(statement);
		closeConnection(conn);

		return num;

	}

	static int readNumOfForeignStdAndStaff() throws SQLException, ClassNotFoundException {
		int num = 0;

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(db_file);

		Statement statement = conn.createStatement();

		String sql_foreigner_std = "SELECT count(*) from ylab16 where \"FOREIGNER-OR-NOT\"=1";
		ResultSet rs = statement.executeQuery(sql_foreigner_std);
		num = rs.getInt(1);

		closeResultSet(rs);
		closeStatement(statement);
		closeConnection(conn);

		return num;

	}

	    ArrayList<Integer> readAgeIntArr() throws SQLException, ClassNotFoundException {
		ArrayList<Integer> ages = new ArrayList<Integer>();

		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection(db_file);

		Statement statement = conn.createStatement();

		String sql_foreigner_std = "SELECT count(*) from ylab16 where \"FOREIGNER-OR-NOT\"=1 and POSITION=\"student\"";
		ResultSet rs = statement.executeQuery(sql_foreigner_std);
		while (rs.next()) {

			ages.add(rs.getInt(1));

		}

		closeResultSet(rs);
		closeStatement(statement);
		closeConnection(conn);

		return ages;

	}

	/**
	 * close connection
	 */
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * close Statement
	 */
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * close PreparedStaement
	 */
	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * close ResultSet
	 */
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
