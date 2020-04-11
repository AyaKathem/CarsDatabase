

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class DBs {
	
	
	String Make_Table = "Make";
	String Model_Table = "Model";
	String Engin_Table = "Engin";
	String Color_Table = "Color";

	
	  
	    private PrintWriter writer= null;
	    private Connection conn;
	    private String c1 = "CREATE TABLE IF NOT EXISTS ";
	    DBs(Connection conn) {
	        this.conn = conn;
	    }
	    public void print(PrintWriter writerfile) {
		writer  = writerfile;
		}
	    
	    
	   
	    public String Make() { 	
	    	return Make_Table;
	    }
	    public String Color() { 	
	    	return Color_Table;
	    }
	    
   public String model() {
	    	
	    	return Model_Table;
	    }
	    

	    public void createTables() {
	    
	        Statement CreateSt;
	      
	        try {
	        	
	        	   
	            
	        	
	        	
	        	 CreateSt = conn.createStatement();
		            CreateSt.executeUpdate( c1+ Model_Table +
		                    " (Model_ID INT PRIMARY KEY," +
		            		"Color_ID INT NOT NULL ,"+
		            		"Compaine_ID INT NOT NULL ,"+
		                    "Model_Name TEXT NOT NULL ,"+
		                    "Price INT NOT NULL , "+
		                    "Year INT NOT NULL , "+
		                    "FOREIGN KEY (Color_ID) REFERENCES " + Color_Table + "(Color_ID) ON DELETE CASCADE , "+
		                    "FOREIGN KEY (Compaine_ID) REFERENCES " + Make_Table + "(Compaine_ID) ON DELETE CASCADE) ");

		                    
		                  

		            CreateSt.close();
		            
		            
		          
		         
	            CreateSt = conn.createStatement();
	            CreateSt.executeUpdate( c1+ Make_Table +
	                    " (Compaine_ID INT PRIMARY KEY," +	
	                     	"Compaine_Name TEXT NOT NULL UNIQUE )");
	            CreateSt.close();
	            
	           
	            
	            
	            
	            CreateSt = conn.createStatement();
	            CreateSt.executeUpdate( c1+ Color_Table +
	            		" (Color_ID INT PRIMARY KEY,"+
	                    " Color_Name TEXT NOT NULL UNIQUE )");	                    
	            CreateSt.close();
	            
	            
	            CreateSt = conn.createStatement();
	            CreateSt.executeUpdate( c1+ Engin_Table +
	                    " (Engin_Size INT NOT NULL  ,"
	                    + "Cylinder TEXT NOT NULL  ,"+
	                       "Model_ID TEXT NOT NULL ,"+
	                    "FOREIGN KEY (Model_ID) REFERENCES " + Model_Table + "(Model_ID) ON DELETE CASCADE)");
	                    

	                    
	            CreateSt.close();
	            
	          
	            
	                
	            
	       
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }


	    public void InsertData() {
	      
	       
	        try {
	        	//BufferedReader fileReader = new BufferedReader(new FileReader(file));
	            conn.setAutoCommit(false);       
	            Statement MakeSt =  conn.createStatement();
	            
	            String sq1 = "INSERT OR IGNORE INTO " + Make_Table + " VALUES(1 , 'Volvo')";
	            MakeSt.executeUpdate(sq1);
	            MakeSt.executeUpdate( "INSERT OR IGNORE INTO " + Make_Table + " VALUES(2 , 'Aude')");
	           
	            MakeSt.executeUpdate( "INSERT OR IGNORE INTO " + Make_Table + " VALUES(3, 'mc')");
	            
	            
	            Statement ColorlSt =  conn.createStatement();
	            
	 	       
	            ColorlSt.executeUpdate("INSERT OR IGNORE INTO " +Color_Table + " VALUES( 1 , 'Red' )");
	            ColorlSt.executeUpdate("INSERT OR IGNORE INTO " +Color_Table + " VALUES( 2 , 'black' )");
	            ColorlSt.executeUpdate("INSERT OR IGNORE INTO " +Color_Table + " VALUES( 3 , 'blue' )");
	            
	            Statement ModelSt =  conn.createStatement();
	            
	            ModelSt.executeUpdate("INSERT OR IGNORE INTO " + Model_Table + " VALUES(1 , 1, 2, 'Aude6','30000' , 1992)");
	            ModelSt.executeUpdate( "INSERT OR IGNORE INTO " + Model_Table + " VALUES(2 , 2, 2, 'Aude8', '40000' , 2009)");
	            ModelSt.executeUpdate( "INSERT OR IGNORE INTO " + Model_Table + " VALUES(3 , 2, 3, 'Aude8','50000' , 2018 )");	  
	           // ModelSt.executeUpdate( "INSERT OR IGNORE INTO " + Model_Table + " VALUES(4 , 4, 'Aude19','aya13' )");
	           // ModelSt.executeUpdate( "INSERT OR IGNORE INTO " + Model_Table + " VALUES(4 , 4, 'Aude10', 'aya4' )");

	            
	            
	          
	            
	            
	            
	            Statement EnginSt =  conn.createStatement();
	            
	           
	            EnginSt.executeUpdate("INSERT OR IGNORE INTO " + Engin_Table + " VALUES(200, 'AAA' , 1)");
	            EnginSt.executeUpdate("INSERT OR IGNORE INTO " + Engin_Table + " VALUES(300, 'bbb' , 2)");
	            EnginSt.executeUpdate("INSERT OR IGNORE INTO " + Engin_Table + " VALUES(400, 'ccc' , 3)");
	            
	            
	            
	           
	          
  
	            conn.commit();
	            conn.setAutoCommit(false);         
	          
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	    }

	 
	   
	    
	    public void clearTables() {
	    
	        Statement st;
	        String clear;

	        try {
	        
	            st = conn.createStatement();
	            clear = "DROP TABLE IF EXISTS " + Make_Table;
	            st.execute(clear);
	            
	            st = conn.createStatement();
	            clear = "DROP TABLE IF EXISTS " + Model_Table;
	            st.execute(clear);
	            
	           st = conn.createStatement();
	            clear = "DROP TABLE IF EXISTS " + Engin_Table;
	            st.execute(clear);
	            
	            st = conn.createStatement();
	            clear = "DROP TABLE IF EXISTS " + Color_Table;
	            st.execute(clear);
	            
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public int ResultSet(ResultSet rs)  {
	    	 int total = 0;
	        ResultSetMetaData rsmd;
			try {
			rsmd = rs.getMetaData();
	        int columns = rsmd.getColumnCount();
	       
	        while (rs.next()) {
	            for (int i = 1; i <= columns; i++) {
	                writer.print(rs.getString(i));
	                writer.print("- ");
	            }
	            total++;
	        }
	        writer.println();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return total;
	        
	    }

}
