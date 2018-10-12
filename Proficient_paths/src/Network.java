import java.util.HashSet; 
import java.util.List; 
import java.util.ArrayList;
  
public class Network { 
	
   public class Node {
	   
	   String name;
	   ArrayList<String> next;
	   
	   public Node() {
		   name = "";
		   next = new ArrayList<String>();
	   }
	   public void set_name(String n) {
		   name = n;
	   }
   }
	   
   int n_size;
   
   Node[] adj_list;
   
   public Network (String[] activities) {
		   n_size = activities.length;
		   adj_list = new Node[n_size];
		   for(int i = 0; i < n_size; i++) {
			   adj_list[i].set_name(activities[i]);
		   }
   }
   public void set_edge(String to, String from) {
	   for(int i = 0; i < n_size; i++) {
		   if(adj_list[i].name.equals(to)) {
			   adj_list[i].next.add(from);
		   }
	   }
   }
   public ArrayList<ArrayList<String>> get_paths(String s, String d){
	   
	   ArrayList<String> visited = new ArrayList<String>();
	   
	   ArrayList<String> pathList = new ArrayList<String>();
	   
	   ArrayList<ArrayList<String>> bigList = new ArrayList<ArrayList<String>>();
	   
	   pathList.add(s);
	   
	   return get_paths_help(s,d,visited,pathList,bigList);
	   
	   
   }
   public ArrayList<ArrayList<String>>get_paths_help(String s, String d, ArrayList<String> visited, ArrayList<String> pathList, 
		   ArrayList<ArrayList<String>> bigList) {
	   ArrayList<ArrayList<String>> error = new ArrayList<ArrayList<String>>();
	   visited.add(s);
	   
	   if(s.equals(d)) {
		   bigList.add(pathList);
		   return bigList;
	   }
	   
	   int s_index = -1;
	   for(int i = 0; i < adj_list.length; i++) {
		   if(adj_list[i].equals(s)) {
			   s_index = i;
		   }
	   }
	   for (String i : adj_list[s_index].next) {
		   if (!(visited.contains(i))) {
			   pathList.add(i);
			   get_paths_help(i, d, visited, pathList, bigList);
		   }
	   }
	   return error;
   }
   
   
   
   
}