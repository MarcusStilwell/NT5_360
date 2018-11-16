import java.util.HashSet; 
import java.util.List; 
import java.util.ArrayList;
import java.util.Arrays;
  
public class Network { 
	public static<T> T[] subArray(T[] array, int beg, int end) {
		return Arrays.copyOfRange(array, beg, end + 1);
	}
	
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
   
   ArrayList<Node> adj_list;
   
   ArrayList<String[]> bigList;
   
   boolean cycle;
   
   int pathList_size;
   
   public Network (String[] activities) {
		   n_size = activities.length;
		   adj_list = new ArrayList<Node>();
		   for(int i = 0; i < n_size; i++) {
			   Node temp = new Node();
			   temp.set_name(activities[i]);
			   adj_list.add(temp);
		   }
		   bigList = new ArrayList<String[]>();
		   cycle = false;
   }
   public void set_edge(String to, String from) {
	   for(int i = 0; i < n_size; i++) {
		   if(adj_list.get(i).name.equals(to)) {
			   System.out.println(from + " to " + to);
			   adj_list.get(i).next.add(from);
			   System.out.println(adj_list.get(i).next.size());
		   }
	   }
   }
   public void get_paths(String s, String d){
	   
	   ArrayList<String> visited = new ArrayList<String>();
	   
	   String[] pathList = new String[1000];
	   pathList_size = 0;
	   
	   pathList[pathList_size] = s;
	   pathList_size += 1;
	   
	   get_paths_help(s,d,visited,pathList,pathList_size);
	   
	   
   }
   public void get_paths_help(String s, String d, ArrayList<String> visited, String[] pathList, int pathListsize) {
	   if (cycle == true) {
		   return;
	   }
	   visited.add(s);
	   System.out.println(s);
	   System.out.println(d);
	   if(s.equals(d)) {
		   System.out.println("if");
		   System.out.println("pathList size: " + pathListsize);
		   bigList.add(subArray(pathList,0,pathListsize));
		   System.out.println("bigList size: " + bigList.size());
	   } else {
		   System.out.println("else");
		   int s_index = -1;
		   for(int i = 0; i < adj_list.size(); i++) {
			   if(adj_list.get(i).name.equals(s)) {
				   s_index = i;
			   }
		   }
		   System.out.println(adj_list.get(s_index).next.size());
		   for (String i : adj_list.get(s_index).next) {
			   System.out.println("for");
			   if (!(visited.contains(i))) {
				   System.out.println("adding " + i);
				   pathList[pathListsize] = i;
				   pathListsize += 1;
				   System.out.println("pathList size: " + pathListsize);
				   get_paths_help(i, d, visited, pathList, pathListsize);
				   System.out.println("bigList after size: " + bigList.size());
				   if(bigList.size() == 0) {
					   cycle = true;
					   return;
				   }
				   pathListsize -= 1;
			   }else {
				   cycle = true;
				   bigList = new ArrayList<String[]>();
				   return;
			   }
			   visited.remove(visited.size()-1);
			   System.out.println("pathList size: " + bigList.get(0).length);
		   }
	   }
	   
   }
   public ArrayList<String> getEnd() {
	   ArrayList<String> result = new ArrayList<String>();
	   for(int i = 0; i < n_size; i++) {
		   if(adj_list.get(i).next.size() == 0) {
			   result.add(adj_list.get(i).name);
		   }
	   }
	   if(result.size() > 0) {
		   return result;
	   }else {
		   cycle = true;
		   result.add("error");
		   return result;
	   }
   }
   
   
   
   
}