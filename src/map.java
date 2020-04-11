import java.io.PrintWriter
;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.sqlite.SQLiteConfig;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

	 
	public class map  {
		public static Connection conn;
		public static PrintWriter writer = null;
		public static DBs database;
		public int rowNum;
	    public static final String Column1MapKey = "A";
	    public static final String Column2MapKey = "B";
	    public static final String Column3MapKey = "C";
	    public static final String Column4MapKey = "D";
	    public static final String Column5MapKey = "E";
	    public static final String Column6MapKey = "F";
	    public static final String Column7MapKey = "G";
	    public static final String Column8MapKey = "I";
	    public JFXTextField textField = new JFXTextField ();
		public JFXButton button = new JFXButton("Search");
		public HBox search = new HBox() ;
		public String searchCatorge;
		public Text bysearch = new Text();
	    public TableView table_view ;
	    public int countNumber;
	    public  ObservableList<Map> allData = null ;
	    public Text t = new Text();
	    public VBox list() throws SQLException {
	      
	        
	        final Label label = new Label("Auto Show ");
	        label.setFont(new Font("Arial", 20));
	 
	         TableColumn<Map, String> firstDataColumn = new TableColumn<>("ID");
		     TableColumn<Map, String> secondDataColumn = new TableColumn<>("Model");
		     TableColumn<Map, String> threeDataColumn = new TableColumn<>("Make");
		     TableColumn<Map, String> fourDataColumn = new TableColumn<>("Price");
		     TableColumn<Map, String> fifeDataColumn = new TableColumn<>("Year");
		     TableColumn<Map, String> sixDataColumn = new TableColumn<>("Engin_Size");
		     TableColumn<Map, String> sevenDataColumn = new TableColumn<>("Cylinder");
		     TableColumn<Map, String> eightDataColumn = new TableColumn<>("Color");
	        
	        firstDataColumn.setCellValueFactory(new MapValueFactory(Column1MapKey));
	        firstDataColumn.setMinWidth(130);
	        secondDataColumn.setCellValueFactory(new MapValueFactory(Column2MapKey));
	        secondDataColumn.setMinWidth(130);
	        threeDataColumn.setCellValueFactory(new MapValueFactory(Column3MapKey));
	        threeDataColumn.setMinWidth(130);
	        fourDataColumn.setCellValueFactory(new MapValueFactory(Column4MapKey));
	        fourDataColumn.setMinWidth(130);
	        fifeDataColumn.setCellValueFactory(new MapValueFactory(Column5MapKey));
	        fifeDataColumn.setMinWidth(130);
	        sixDataColumn.setCellValueFactory(new MapValueFactory(Column6MapKey));
	        sixDataColumn.setMinWidth(130);
	        sevenDataColumn.setCellValueFactory(new MapValueFactory(Column7MapKey));
	        sevenDataColumn.setMinWidth(130);
	        eightDataColumn.setCellValueFactory(new MapValueFactory(Column8MapKey));
	        eightDataColumn.setMinWidth(130);
	        
	        firstDataColumn.setSortable(false);
	        secondDataColumn.setSortable(false);
	        threeDataColumn.setSortable(false);
	        fourDataColumn.setSortable(false);
	        fifeDataColumn.setSortable(false);
	        sixDataColumn.setSortable(false);
	        sevenDataColumn.setSortable(false);
	        eightDataColumn.setSortable(false);
	        
	        
	        
	        firstDataColumn.setEditable(false);
	        secondDataColumn.setEditable(false);
	        threeDataColumn.setEditable(false);
	        fourDataColumn.setEditable(false);
	        fifeDataColumn.setEditable(false);
	        sixDataColumn.setEditable(false);
	        sevenDataColumn.setEditable(false);
	        eightDataColumn.setEditable(false);
	        
	        table_view= new TableView<>(generateDataInMap());
	 
	        table_view.setEditable(true);
	        table_view.getSelectionModel().setCellSelectionEnabled(true);
	        table_view.getColumns().setAll(firstDataColumn, secondDataColumn,threeDataColumn,fourDataColumn,fifeDataColumn ,sixDataColumn,sevenDataColumn,eightDataColumn);
	        
	        Callback<TableColumn<Map, String>, TableCell<Map, String>>
	            cellFactoryForMap = new Callback<TableColumn<Map, String>,
	                TableCell<Map, String>>() {
	                    @Override
	                    public TableCell call(TableColumn p) {
	                        return new TextFieldTableCell(new StringConverter() {
	                            @Override
	                            public String toString(Object t) {
	                                return t.toString();
	                            }
	                            @Override
	                            public Object fromString(String string) {
	                                return string;
	                            }  
	                            
	                        });
	                    }
	        };
	       
	        firstDataColumn.setCellFactory(cellFactoryForMap);
	        secondDataColumn.setCellFactory(cellFactoryForMap);
	        threeDataColumn.setCellFactory(cellFactoryForMap);
	        fourDataColumn.setCellFactory(cellFactoryForMap);
	        fifeDataColumn.setCellFactory(cellFactoryForMap);
	        sixDataColumn.setCellFactory(cellFactoryForMap);
	        sevenDataColumn.setCellFactory(cellFactoryForMap);
	        eightDataColumn.setCellFactory(cellFactoryForMap);
	     
	        
	        final VBox vbox = new VBox();
	 
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(label, table_view);
	        
	        
	        
	        return vbox;
	       
	    }
	 
	    
	    
	    
	    
	    
	    
	    
	    private ObservableList<Map> generateDataInMap() throws SQLException {
	    	conn = null;
	        int max = 10;
	        SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
           
       try {
		Class.forName("org.sqlite.JDBC");
	    
   
            conn = DriverManager.getConnection("jdbc:sqlite:ass.db1", config.toProperties());

            database = new DBs(conn);
   
            database.clearTables();
            database.createTables(); 
           database.InsertData(); 

	       allData = FXCollections.observableArrayList();
	       
	      // data();
	      ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*,  Color.* , Make.*  FROM  Model "
	      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
	      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
	      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID ");
	     
	     Display(rs2);
	      
	       
	       countProcess ();
	       search();
	        
       } catch (ClassNotFoundException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
   	}
         
	        return allData;
	    }
	    
	  
	   public Text sText () {
		   return bysearch;
	   }
	   
	   
	
	   public Button ViweAll() {
		   
		    JFXButton viweAll = new JFXButton("Viwe All");
		   
		    viweAll.setTranslateX(500);
		    viweAll.setTranslateY(20);
		    viweAll.setStyle("-fx-background-color: #81a6e2");
		      
		    viweAll.setOnAction(actionEvent -> {
				allData.clear();
				
				 ResultSet rs2;
				try {
					rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*,  Color.* , Make.*  FROM  Model "
					      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
					      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
					      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID ");
					 Display(rs2);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				     
				    
				
			});
			
		   
		   return viweAll;
	   }
	   
	 public void search() throws SQLException {
		 
	
		 ComboBox emailComboBox2 = new ComboBox();
		 emailComboBox2.setTranslateY(20);
		 emailComboBox2.setPromptText("Categories");
		 emailComboBox2.getItems().addAll("Model", "Make","Year","Engin Size","Color");
		 
		 emailComboBox2.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		        	searchCatorge = t1;
		        
		        }
		    });
  			textField.setTranslateY(20);
  			button.setTranslateY(20);
  			button.setStyle("-fx-background-color: #81a6e2");
	      
			button.setOnAction(actionEvent ->  {
		try {

			 allData.clear();
			
			
			
			if (searchCatorge == "Model") {
						
				
				 ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*,  Make.*  FROM  Model "
				      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
				      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
				      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID "
				      		+ "WHERE Model_Name = '"+textField.getText()+"'");
				 
				 
				  Display(rs2);
				 
			}else if (searchCatorge == "Make") {
				
				
				ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*,  Make.*  FROM  Model "
			      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
			      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
			      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID "
			      		+ "WHERE Compaine_Name = '"+textField.getText()+"'");
			 
			      Display(rs2);
				
				
			

			}else if (searchCatorge =="Year") {
				
				
				ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*,  Make.*  FROM  Model "
			      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
			      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
			      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID "
			      		+ "WHERE Year = '"+textField.getText()+"'");
			 
			      Display(rs2);
			
				 
				
			}else if (searchCatorge == "Color") {
				ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*,  Make.*  FROM  Model "
			      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
			      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
			      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID "
			      		+ "WHERE Color_Name = '"+textField.getText()+"'");
			 
			      Display(rs2);
			
				 
				 
			}else if (searchCatorge== "Engin Size") {
				
				ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*, Make.*  FROM  Model "
			      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
			      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
			      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID "
			      		+ "WHERE Engin_Size = '"+textField.getText()+"'");
			 
			      Display(rs2);
			

			}
			
			
			 bysearch.setText(countNumber+ " Found ");
			
		  
		 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		  Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Erorr");
	 
	        
	        alert.setHeaderText(null);
	        alert.setContentText(textField.getText()+" is not exist!");
	 
	        alert.showAndWait();
	    
	 
	}
	   textField.clear();
	});
			search.setSpacing(20);
		  search.getChildren().addAll(emailComboBox2, textField, button);
	 }   
	    
	
	    
	 public void countProcess () {
	    	
		    
	    	
	    	try {
	    	ResultSet rs2 = conn.createStatement().executeQuery( "select count(*) FROM Model") ; 
	    	
	    			t.setText("The number of exists cars "+rs2.getInt(1));
	 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	
	    }
	    
		
		@SuppressWarnings("rawtypes")
		public Button  update () {
			
		
			
			 @SuppressWarnings("unchecked")
			final ObservableList<TablePosition> selectedCells = table_view.getSelectionModel().getSelectedCells();
		        
		        selectedCells.addListener(new ListChangeListener<TablePosition>() {
		            @Override
		            public void onChanged(Change change) {
		                for (TablePosition pos : selectedCells) {
		                
		                	 rowNum = pos.getRow()+1;
		                	 table_view.editingCellProperty();
		                }
		            };
		        });
	   Text updataT = new Text();
			updataT.setText("Updata info ");
			
			
			 Stage stage = new Stage();		
   JFXButton  button4 = new JFXButton ("Edite Data");
   			  button4.setAlignment(Pos.TOP_RIGHT);
			  button4.setTranslateX(30);
			  button4.setTranslateY(30);
			  button4.setFont(Font.font("Verdana", 15));
			  button4.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
			  button4.setRipplerFill(Color.web("rgb(87,56,97)"));
			  button4.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));

			  
			  
			  Button updata = new Button("Ok");
			  updata.setPadding(new Insets(10, 20, 10, 20));
			  updata.setTranslateY(20);
			  updata.setTranslateX(110);;
			  button4.setOnMouseClicked((event) -> {
			
					 HBox vB3 = new HBox();
						
						vB3.setSpacing(30);
						vB3.setStyle("-fx-background-color: rgb(183, 200, 226);"); 
						VBox second = new VBox ();
						VBox first = new VBox ();
						first.setSpacing(10);
						second.setSpacing(10);
					if (rowNum >0 ) {
					  try {
						  
					  ResultSet rsMo = conn.createStatement().executeQuery( " SELECT * FROM Model WHERE Model_ID="+ rowNum);		
		  			  ResultSet rsM = conn.createStatement().executeQuery( " SELECT * FROM Make WHERE Compaine_ID="+ rsMo.getString("Compaine_ID"));
					  ResultSet rsE = conn.createStatement().executeQuery( " SELECT * FROM Engin WHERE Model_ID="+ rsMo.getString("Model_ID"));
					  ResultSet rsC = conn.createStatement().executeQuery( " SELECT * FROM Color WHERE Color_ID="+ rsMo.getString("Color_ID"));

					 
					Label label1 = new Label("Model:");
					JFXTextField textField1 = new JFXTextField ();
					textField1.setMaxWidth(180);
					textField1.setText(rsMo.getString("Model_Name"));
					Label label2 = new Label("Make:");	
					JFXTextField textField2 = new JFXTextField ();
					textField2.setMaxWidth(180);
					textField2.setText(rsM.getString("Compaine_Name"));
					Label label3 = new Label("Year:");	
					JFXTextField textField3 = new JFXTextField ();
					textField3.setMaxWidth(180);
					textField3.setText(rsMo.getString("Year"));
					Label label4 = new Label("Engin size:");	
					JFXTextField textField4 = new JFXTextField ();
					textField4.setMaxWidth(180);
					textField4.setText(rsE.getString("Engin_Size"));
					Label label5 = new Label("Cylinder:");	
					JFXTextField textField5 = new JFXTextField ();
					textField5.setMaxWidth(180);
					textField5.setText(rsE.getString("Cylinder"));
					Label label6 = new Label("Color:");	
					JFXTextField textField6 = new JFXTextField ();
					textField6.setMaxWidth(180);
					textField6.setText(rsC.getString("Color_Name"));
					Label label7 = new Label("Price:");	
					JFXTextField textField7 = new JFXTextField ();
					textField7.setMaxWidth(180);
					textField7.setText(rsMo.getString("Price"));
					
					
					
						updata.setOnMouseClicked((event1) -> {
					
							Statement ModelSt;
							try {
								
								
								
								int mcint1 =0;
								  ResultSet rsMake = conn.createStatement().executeQuery( " SELECT * FROM Make ");
								
								while (rsMake.next()) {
									
									
									String nam =  rsMake.getString("Compaine_Name") ;
									
									String nam1 = textField2.getText() ;
									if (nam1.equals(nam) ) {
										
										mcint1 = rsMake.getInt("Compaine_ID");
										
										
										break;
									}else {
										mcint1= rowNum;
									}
					           
					            
								} 					
							
								
								
								
								int ColorNum= 0;
								
								ResultSet rsC1 = conn.createStatement().executeQuery( " SELECT * FROM Color ");
					            
								while (rsC1.next()) {
									
									String nam =  rsC1.getString("Color_Name") ;
									
									String nam1 = textField6.getText() ;
									if (nam1.equals(nam) ) {
										
										ColorNum = rsC1.getInt("Color_ID");
										
										
										break;
									}else {
										ColorNum= rowNum;
									}
					           
					            
								} 
								
								
								
			ModelSt = conn.createStatement();
							
			ModelSt.executeUpdate( "UPDATE Model SET Model_Name = '"+textField1.getText()+"', Price = "+textField7.getText()+ ", Year = "+textField3.getText()+", Compaine_ID =  "+mcint1+",  Color_ID ="+ ColorNum+" WHERE Model_ID = " +rowNum  );
			
			
			if (mcint1 == rowNum) {
			
			 Statement makeSt =  conn.createStatement();
			 makeSt.executeUpdate( "UPDATE Make SET Compaine_Name  = '"+textField2.getText()+"' WHERE Compaine_ID = " +rowNum);
	
			}
             
			 Statement EnginSt =  conn.createStatement();
			 EnginSt.executeUpdate( "UPDATE Engin SET Engin_Size = '"+textField4.getText()+"', Cylinder = '"+textField5.getText()+"' WHERE Model_ID = " +rowNum);
             
             
			 
            
            
			 	if (ColorNum == rowNum) {
			 		Statement ColorlSt =  conn.createStatement();
                      ColorlSt.executeUpdate( "UPDATE Color SET Color_Name = '"+textField6.getText()+"' WHERE Color_ID = " +rowNum);
           
			 	}
			 	
			 	 conn.commit();
            allData.clear();
            ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*,  Make.*  FROM  Model "
    	      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
    	      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
    	      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID");
    	     
    	      Display(rs2);
							
    	      
    	      
    	    
 	       	stage.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}});
					  
						first.getChildren().addAll(label1, textField1,label2 ,textField2,label3, textField3,label4, textField4);
						
						second.getChildren().addAll(label5, textField5,label6, textField6,label7, textField7, updata);
						vB3.getChildren().addAll(first, second);
					
						vB3.setPadding(new Insets(30, 0 ,0 ,30));
					
					
						
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				        Scene scenes = new Scene(vB3, 450, 340);
				       
				        stage.setTitle("Edite data");
				        stage.setScene(scenes);
				        stage.show();
					}else {
						 Alert alert = new Alert(AlertType.INFORMATION);
					        alert.setTitle("Erorr");
					 
					        // Header Text: null
					        alert.setHeaderText(null);
					        alert.setContentText("Please select row to Edite");
					 
					        alert.showAndWait();
						
					}
					
				   
				});
			  
			
				
				
				
			  return button4;
			
		}
	    
		
		public int mcint ;
		
		public Button add() {
			
			
			
			 Stage stage = new Stage();		
			 JFXButton  button4 = new JFXButton ("Add");
			 button4.setAlignment(Pos.TOP_RIGHT);
			  button4.setTranslateX(50);
			  button4.setTranslateY(30);
			  button4.setFont(Font.font("Verdana", 15));
			  button4.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
			  button4.setRipplerFill(Color.web("rgb(87,56,97)"));
			  button4.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));

			  
			  
			  Button updata = new Button("Ok");
			  updata.setPadding(new Insets(10, 20, 10, 20));
			  updata.setTranslateY(20);
			  updata.setTranslateX(110);;
			  button4.setOnMouseClicked((event) -> {
				  

				
				
				
			   HBox vB3 = new HBox();
					vB3.setSpacing(30);
					vB3.setStyle("-fx-background-color: rgb(183, 200, 226);"); 
					
					VBox first = new VBox ();
					VBox second = new VBox ();
					first.setSpacing(10);
					second.setSpacing(10);
					Label label1 = new Label("Model:");
					
					JFXTextField textField1 = new JFXTextField ();
					textField1.setMaxWidth(180);
					Label label2 = new Label("Make:");	
					JFXTextField textField2 = new JFXTextField ();
					textField2.setMaxWidth(180);
					Label label3 = new Label("Year:");	
					JFXTextField textField3 = new JFXTextField ();
					textField3.setMaxWidth(180);
					Label label4 = new Label("Engin size:");	
					JFXTextField textField4 = new JFXTextField ();
					textField4.setMaxWidth(180);
					Label label5 = new Label("Cylinder:");	
					JFXTextField textField5 = new JFXTextField ();
					textField5.setMaxWidth(180);
					Label label6 = new Label("Color:");	
					JFXTextField textField6 = new JFXTextField ();
					textField6.setMaxWidth(180);
					Label label7 = new Label("Price:");	
					JFXTextField textField7 = new JFXTextField ();
					textField7.setMaxWidth(180);
					
						updata.setOnMouseClicked((event1) -> {
					
		try {
			
			
			 ResultSet rc= conn.createStatement().executeQuery("SELECT * FROM Model ORDER BY Model_ID DESC LIMIT 1");
		     int IdNum = rc.getInt("Model_ID") +1;
			int Cnum =0;
			  ResultSet rsM = conn.createStatement().executeQuery( " SELECT * FROM Make ");
			
			while (rsM.next()) {
				
				String nam =  rsM.getString("Compaine_Name") ;
				
				String nam1 = textField2.getText() ;
				if (nam1.equals(nam) ) {
					
					mcint = rsM.getInt("Compaine_ID");
					
					
					break;
				}else {
					mcint= IdNum;
				}
           
            
			} 
            
			ResultSet rsC = conn.createStatement().executeQuery( " SELECT * FROM Color ");
            
			while (rsC.next()) {
			
				String nam =  rsC.getString("Color_Name") ;
				
				String nam1 = textField6.getText() ;
				if (nam1.equals(nam) ) {
					
					Cnum = rsC.getInt("Color_ID");
					
					
					break;
				}else {
					Cnum= IdNum;
				}
           
            
			} 
            
         
            
            Statement MakeSt =  conn.createStatement();   
			
            MakeSt.executeUpdate( "INSERT OR IGNORE INTO Make VALUES("+mcint+" , '"+textField2.getText()+"')");
            Statement ColorlSt =  conn.createStatement();
            ColorlSt.executeUpdate("INSERT OR IGNORE INTO Color VALUES("+Cnum+",'"+textField6.getText()+"' )");
            
            Statement ModelSt =  conn.createStatement(); 
            ModelSt.executeUpdate("INSERT OR IGNORE INTO Model VALUES("+IdNum+","+Cnum+","+mcint+", '"+textField1.getText()+"',"+textField7.getText()+","+textField3.getText() +" )");
          
               
            Statement EnginSt =  conn.createStatement();
            EnginSt.executeUpdate("INSERT OR IGNORE INTO Engin VALUES("+textField4.getText()+", '"+textField5.getText()+"' , "+IdNum+")");
                        
            
           
           conn.commit();
           allData.clear();
          

           ResultSet rs2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*, Make.*  FROM  Model "
   	      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
   	      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
   	      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID");
   	     
   	      Display(rs2);
	       countProcess ();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
				}
						stage.close();
					});
					
					first.getChildren().addAll(label1, textField1,label2 ,textField2,label3, textField3,label4, textField4);
					second.getChildren().addAll(label5, textField5,label6, textField6,label7, textField7, updata);
					vB3.getChildren().addAll(first, second);
					vB3.setPadding(new Insets(30, 0 ,0 ,30));
					
					
				        Scene scenes = new Scene(vB3, 450,340);
				        stage.setTitle("Add a new record");
				        stage.setScene(scenes);
				        stage.show();
				   
				});
			  	
			  return button4;
			
		}
		public int deleteRow= 0;
		public Button delete () {
			JFXButton delete = new JFXButton ("Delete");
			delete.setTranslateY(400);
			delete.setFont(Font.font("Verdana", 15));
			delete.setButtonType(com.jfoenix.controls.JFXButton.ButtonType.FLAT);
			delete.setRipplerFill(Color.web("rgb(87,56,97)"));
			delete.setBackground(new Background(new BackgroundFill(Color.web("rgb(223,223,223)"), null, null)));
			
			@SuppressWarnings("unchecked")
			final ObservableList<TablePosition> selectedCells = table_view.getSelectionModel().getSelectedCells();
		        
		        selectedCells.addListener(new ListChangeListener<TablePosition>() {
		            @Override
		            public void onChanged(Change change) {
		                for (TablePosition pos : selectedCells) {
		                
		                	deleteRow = pos.getRow()+1;
		                	 table_view.editingCellProperty();
		                }
		            };
		        });
		        
		        delete.setOnMouseClicked((event1) -> {
		        
			
			   try {
				
				
				   Statement ModelSt =  conn.createStatement(); 
		            ModelSt.executeUpdate("DELETE FROM Model WHERE Model_ID=" + deleteRow);
				
				
				        allData.clear();
				        
				        ResultSet r2 = conn.createStatement().executeQuery("  SELECT  Model.* , Engin.*, Color.*, Make.*  FROM  Model "
				   	      		+ "INNER JOIN Engin ON  Model.Model_ID=Engin.Model_ID "
				   	      		+ "INNER JOIN Color ON  Model.Color_ID=Color.Color_ID "
				   	      		+ "INNER JOIN Make ON  Model.Compaine_ID=Make.Compaine_ID");
				   	     
				   	      Display(r2);
				        
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}});
			   return delete;
		}
		
		public HBox SearcByPrice() {
			
			HBox hold = new HBox();
			VBox add = new VBox();
			hold.setSpacing(20);
		
			Label price= new Label("Price");
			JFXTextField startPrice= new JFXTextField ();
			startPrice.setMaxWidth(60);
			startPrice.setPromptText("From");
			JFXTextField endPrice= new JFXTextField ();
			endPrice.setMaxWidth(60);
			endPrice.setTranslateY(20);
			endPrice.setPromptText("To");;
			JFXButton select = new JFXButton ("Select");
			select.setTranslateY(20);
			select.setStyle("-fx-background-color: #81a6e2");
			add.getChildren().addAll(price, startPrice);
			hold.getChildren().addAll(add,  endPrice, select);
			select.setOnMouseClicked((event) -> {
				allData.clear();
				try {
					ResultSet rsMo =  conn.createStatement().executeQuery("	SELECT * FROM Model WHERE Model.Price BETWEEN  "+startPrice.getText()+" AND "+endPrice.getText());
					
					
					  int num =0;
					  while(rsMo.next()) {
						  num++;
						  ResultSet rsM = conn.createStatement().executeQuery( " SELECT * FROM Make WHERE Compaine_ID="+ rsMo.getString("Compaine_ID"));
						  ResultSet rsE = conn.createStatement().executeQuery( " SELECT * FROM Engin WHERE Model_ID="+ rsMo.getString("Model_ID"));
						  ResultSet rsC = conn.createStatement().executeQuery( " SELECT * FROM Color WHERE Color_ID="+ rsMo.getString("Color_ID"));
				    	   Map<String, String>  dataRow1 = new HashMap<>(); 	 
						
						
						
						dataRow1.put(Column1MapKey, rsMo.getString("Model_ID"));
			           dataRow1.put(Column2MapKey, rsMo.getString("Model_Name"));
			           dataRow1.put(Column3MapKey, rsM.getString("Compaine_Name"));
				        dataRow1.put(Column4MapKey,rsMo.getString("Price"));
				        dataRow1.put(Column5MapKey,rsMo.getString("Year"));
				        dataRow1.put(Column6MapKey,rsE.getString("Engin_Size"));
				        dataRow1.put(Column7MapKey,rsE.getString("Cylinder"));
				        dataRow1.put(Column8MapKey,rsC.getString("Color_Name"));
				        
				        
				        
			        //   dataRow1.put(Column3MapKey,name);

			           allData.add(dataRow1);
			          
				       }
					  bysearch.setText(num + " Found ");
						
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
			return hold;
		}
		
		
		public void Display (ResultSet rs2) {
			
			 
		       Map<String, String> dataRow1 = null ;
		       int num = 1;
		       try {
				while(rs2.next() ){
				       //Retrieve by column name
					countNumber=num++;
					 dataRow1 = new HashMap<>();
				 
				 
				       
				        dataRow1.put(Column1MapKey, rs2.getString("Model_ID"));
				        dataRow1.put(Column2MapKey, rs2.getString("Model_Name"));
				        dataRow1.put(Column3MapKey, rs2.getString("Compaine_Name"));
				        dataRow1.put(Column4MapKey,rs2.getString("Price"));
				        dataRow1.put(Column5MapKey,rs2.getString("Year"));
				        dataRow1.put(Column6MapKey,rs2.getString("Engin_Size"));
				        dataRow1.put(Column7MapKey,rs2.getString("Cylinder"));
				        dataRow1.put(Column8MapKey,rs2.getString("Color_Name"));

				        allData.add(dataRow1);
				  
				    }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		public Text coun() {
	    	return t;
	    }
		public HBox SearchF() {
			
			return search;
		}
	
	}