
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.stage.Stage;




public class MainDB extends Application{
 /*
  * In order to run the program you should implement some JAR files 
  * json-simple.jar
  * sqlite-jdbc-3.21.0.jar
  * jfoenix-8.0.1-jar
  * com.fasterxml.jackson.core.jar
  * com.fasterxml.jackson.bind.jar
  * */
    
    public static void main(String[] args) {
    	launch(args);
    }


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox all = new VBox();
		
		
		 map list = new map();
        HBox Upper=new HBox();
        Upper.setPrefSize(1000, 150);
        Upper.setStyle("-fx-background-color: rgb(199, 209, 224);"); 
        
        Upper.setPadding(new Insets(10, 10 ,10 ,10));
        
        Upper.setSpacing(40);
       
		Upper.getChildren().addAll( list.SearchF(), list.SearcByPrice(), list.ViweAll());
		
		
        HBox meddel=new HBox();
        meddel.setPrefSize(1000, 500);
        meddel.setStyle("-fx-background-color: rgb(19, 209, 224);"); 
       
           
        meddel.getChildren().addAll(list.list(), list.update(), list.add() , list.delete());
        HBox down=new HBox();
        down.setPrefSize(1000, 150);
        down.setStyle("-fx-background-color: rgb(150, 209, 224);"); 
        
		down.getChildren().addAll(list.coun());
		all.getChildren().addAll(Upper,meddel,list.bysearch, down);
		
		Scene scene = new Scene(all, 1300, 650);
		primaryStage.setTitle("Auto Show");

        primaryStage.setScene(scene); 
        primaryStage.show();
	}
	
	
	
}
