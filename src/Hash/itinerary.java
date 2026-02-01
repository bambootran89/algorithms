package Hash;
/**
 * Airline itinerary reconstruction.
 *
 * @author Algorithms Collection
 */


import java.util.HashMap;
import java.util.Map;

public class itinerary {

  public static void main(String[] args) {
    Map<String, String> dataset = new HashMap<>();
    dataset.put("Chennai", "Banglore");
    dataset.put("Bombay", "Delhi");
    dataset.put("Goa", "Chennai");
    dataset.put("Delhi", "Goa");
    printResult(dataset);
  }  
    
  private static void printResult(Map<String, String> dataSet){
    Map<String, String> reverseMap = new HashMap<>();
    for (Map.Entry<String, String> entry:dataSet.entrySet()){
      reverseMap.put(entry.getValue(), entry.getKey());
    }
    String start = null;
    for (Map.Entry<String, String> entry: dataSet.entrySet()){
      if (!reverseMap.containsKey(entry.getKey())){
        start = entry.getKey();
        break;
      }
    }
    
    String to = dataSet.get(start);
    while(to!=null){
      System.out.print(start +  "->" + to + ", ");
      start = to;
      to = dataSet.get(start);
    }
  }
  

}
