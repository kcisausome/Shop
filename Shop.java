//Name: Casey Au
//class : cs241
//date: 10-12-16

import java.util.*;
import java.util.Scanner;
import java.io.*;


public class Shop {
  Map <String,Integer> items = new TreeMap<>();
	Map <String, ArrayList<String>> carts = new TreeMap<>();
  
  public static void main(String[] args) throws Exception{
    if(args.length != 2)
      {
       exit();  
      }
      new Shop(args[0], args[1]);
  	}
  public static void exit(){
    System.out.println("Usage:Java shop <item.file><item.file>");
    System.exit(0);
    }
    
  public Shop(String items_file, String orders_file) throws Exception
    {
       carts.put("", new ArrayList<String>());//puts something in the map/instantiates it so no null pointers

       Scanner items_scanner = new Scanner(new File(items_file));//puts things in items map
       while(items_scanner.hasNext())
       {
          items.put(items_scanner.next() , Integer.parseInt(items_scanner.next().substring(1)));
       }

       Scanner orders_scanner = new Scanner(new File(orders_file));
       while(orders_scanner.hasNext())
       {
          String order = orders_scanner.next();
          //System.out.println(order);//checking what prints out after each iterations
          switch(order){
          	case "add" :add(orders_scanner.next(),orders_scanner.next());
          				break;
            case "delete" : delete(orders_scanner.next(),orders_scanner.next());
            			break;
            case "cart" : cart(orders_scanner.next());
            			break;
            case "checkout" : checkout(orders_scanner.next());
            			break;
            default : System.out.println("invalid entry");
            			break;
          }
       }

    }

	private void add(String name, String object) {
		
    if(carts.containsKey(name) ) {//checks if someone already has a cart
   			ArrayList<String> cart = carts.get(name);
          if(cart.contains(object)){//if someone already has an object reject them
            System.out.println(name + " already has a " + object);
          }else{//otherwise add
            cart.add(object);
            System.out.println(object + " added to " + name + "'s cart");
          }
        
   	}else{//if they dont have a cart, give them one
   	  	carts.put(name, new ArrayList<String>());
        carts.get(name).add(object);
        System.out.println(object + " added to " + name + "'s cart");
   	}

  
  }

	public void delete(String name, String object){
		if(carts.containsKey(name) && carts.containsValue(object)) {//checks if the name and object are valid or not
			carts.get(name).remove(object);
		}else if(carts.containsKey(name)&& !carts.containsValue(object)){//checks if the object is valid
			System.out.println(object + " not found in "+ name + "'s cart");
		}else{//checks if the person has a cart or not
			System.out.println(name + " doesn't have a shopping cart so nothing can be removed");
		}
	}

	public void cart(String name) {
		//System.out.println(carts.get(name).size());//checked to see how many items were in the cart
    System.out.println("View " + name + "'s shopping cart");//formatting stuff ew
    String heading1 = "item";
		String heading2 = "price";
		System.out.println("-------------------------------");
		System.out.printf( "%-15s %15s %n", heading1, heading2);
		System.out.println("-------------------------------");
		for(int i = 0; i< carts.get(name).size();i++){
			String object = carts.get(name).get(i); 
			String price = "$" +items.get(carts.get(name).get(i));//made price into string so i could put the dollar sign in, cause the formatting made it impossible 
			System.out.printf("%-15s %15s", object, price);
			System.out.println();
		}
	}

	public void checkout(String name) {
		int total = 0;
		for(int i = 0; i< carts.get(name).size();i++){//prints total amount of dollars of things from the cart
			total += items.get(carts.get(name).get(i));
		}
		System.out.println(name + " checked out. Total = $" + total);
	}
}


