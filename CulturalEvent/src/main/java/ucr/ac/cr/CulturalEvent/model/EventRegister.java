/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ucr.ac.cr.CulturalEvent.model;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author allis
 */
public class EventRegister {

private GenericDAO_JSON<Event> eventDAO;

    public EventRegister() {
        Type type= new TypeToken<Event[]>(){}.getType();
        this.eventDAO= new GenericDAO_JSON<Event>("event.json",type);
    }
    
    
       public String getAll(){
        
        String out="event  list: \n";
        for (Event event:this.eventDAO.getAll()){
            out+=event+"\n";
        }
        return out;
    }
       
       
       public String add(Event event){
       
       if (this.eventDAO.save(event)) {
           
           return "The event added successfully";
       }
        
        return "Error saving the event";
       }//fin del metodo add

       
       public String edit(Event eventEdit){
        if(this.eventDAO.update(eventEdit)){
            return "Event is successfully edited";
        }
        return "error editing the event!";
    }
   
   
    public String delete(int id){
        
        if (this.eventDAO.remove(id)){
            
            return "successfully deted event!";
            
        }
         return "error deleting event!";
        
        
    }//fin del metdo delete
   
   
   
    public Event searchById ( int id){
        
        return this.eventDAO.getElement(id);
       
          
        
    }//fin del metodo searchById
   
    public Event searchId(int id) {
        List<Event> events=this.eventDAO.getAll();
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return new Event();
    }
   
   
   
     public String [][] getMatrix() {
        ArrayList<Event>spaceList=this.eventDAO.getAll();
        if(spaceList==null || spaceList.isEmpty()){
            return new String[0] [10];
        }else{
            //crear la matriz
                String [][]matrixUser=new String[spaceList.size()][10];
              //llenar la matriz
              for (int row=0;row<matrixUser.length;row++){
                  for (int colum=0; colum<matrixUser[0].length;colum++){
                      matrixUser[row][colum]=spaceList.get(row).setColumData(colum);
                  }
                    
              }
              return matrixUser;
        }
        
    }
     
    
     
}//end class
