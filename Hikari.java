package com.tstar.mc;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * HikariDataSource
 * @author kthuang
 *
 */
public class Hikari {
 
    private static HikariConfig configUat = new HikariConfig();
    private static HikariConfig configProd = new HikariConfig();
    private static HikariDataSource dsUat;
    private static HikariDataSource dsProd;
 
    static {
    	//UAT
    	configUat.setJdbcUrl( "jdbc:jtds:sqlserver://172.16.8.108:1443/MCPRODSQL" );
    	configUat.setUsername( "KTHUANG" );
    	configUat.setPassword( "tstar@0908" );
    	configUat.addDataSourceProperty( "cachePrepStmts" , "true" );
    	configUat.addDataSourceProperty( "prepStmtCacheSize" , "250" );
    	configUat.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
    	configUat.setConnectionTestQuery("SELECT 1");
		// 池中最小空閒鏈接數量
    	configUat.setMinimumIdle(10);
		// 池中最大鏈接數量
    	configUat.setMaximumPoolSize(50);
    	dsUat = new HikariDataSource( configUat );
		
        //PROD
		configProd.setJdbcUrl( "jdbc:jtds:sqlserver://172.16.8.108:1443/MCPRODSQL" );
		configProd.setUsername( "KTHUANG" );
		configProd.setPassword( "tstar@0908" );
        
		configProd.addDataSourceProperty( "cachePrepStmts" , "true" );
		configProd.addDataSourceProperty( "prepStmtCacheSize" , "250" );
		configProd.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        configProd.setConnectionTestQuery("SELECT 1");
		// 池中最小空閒鏈接數量
        configProd.setMinimumIdle(10);
		// 池中最大鏈接數量
        configProd.setMaximumPoolSize(50);
        dsProd = new HikariDataSource( configProd );
        
//        System.out.println("++++++++++++++++++++++++++");
//        System.out.println("現在查詢的是 " + " PROD " + " 資料庫");
//        System.out.println("++++++++++++++++++++++++++");
    }
 
    private Hikari() {}
 
    public static Connection getConnection(String env) throws SQLException {
    	if("prod".equals(env)){
    		return dsProd.getConnection();
    	}else if("uat".equals(env)){
    		return dsUat.getConnection();
    	}
        return null;
    }
}