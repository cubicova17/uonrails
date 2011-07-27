package com.uaonrails.mining;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.htmlcleaner.CommentNode;
import org.htmlcleaner.HtmlNode;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.TagNodeVisitor;

import com.uaonrails.mining.support.UZTrain;

import java.util.logging.Logger;

public  class UZTrainsParser {
	   private static final Logger log = Logger.getLogger(UZParser.class.getName());

	public List<UZTrain> trains;
	int num_row=0;
	
	public UZTrainsParser() {
		// TODO Auto-generated constructor stub
		trains=new ArrayList<UZTrain>();
	}
	public String readDate(String str, int index )
	{
		return str.substring(index-2, index+3);
		
	}
	 public void parseRows(TagNode node)
	 {
		
		  node.traverse(new TagNodeVisitor() {
		      public boolean visit(TagNode tagNode, HtmlNode htmlNode) {
		    	
		          if (htmlNode instanceof TagNode) {
		           
		        	  TagNode tag = (TagNode) htmlNode;
		              String tagName = tag.getName();
		              if ("tr".equals(tagName)) {
		                  String src = tag.getAttributeByName("id");
		                  
		                  if (src != null&&src.equals(("row_"+num_row))  ) {

		                	  try {
		                		  String Labelname = new String(tag.getText().toString());
		                		 // System.out.println(tag.getText());
		                	  //
		                		  int ind1 = tag.getText().indexOf(":");
		                		  int ind2 = tag.getText().indexOf(":",ind1);
		                		  int ind3 = tag.getText().indexOf(":",ind2);
		                		  //log.info(new String("Осталось свободных мест: "));
		                		  //log.info(Labelname);
		                		  if(Labelname.indexOf(new String("Осталось свободных мест: ")) != -1)
		                	  {
		                		  //System.out.println("OOH!");
		                		  //System.out.println(Labelname);
		                		  UZTrain traine = new UZTrain(); 
		                		  traine.name =new String(tag.getText().toString()).substring(0,ind1-2);
		               	       //System.out.println("!!!" + traine.name);
		                		log.info(traine.name);
	                	        //log.info(new String(tag.getText().toString().getBytes(),"UTF-8").substring(0,ind1-2));
	                	        //for(int ooo=0;ooo<traine.name.getBytes().length;ooo++	) 
	                	        //log.info(Byte.toString(tag.getText().toString().getBytes()[ooo]));
	                	        

		                		  traine.dep_str =readDate(tag.getText().toString(),ind1);
          		
		                		  
		                		  traine.arr_str =readDate(tag.getText().toString(),ind3);
		                		  
		                		  parse1Row(tag,"c_1030",traine);
		                		  parse1Row(tag,"c_1020",traine);
		                		  parse1Row(tag,"c_1050",traine);
		                		  parse1Row(tag,"c_1040",traine);
		                		  trains.add(traine);
		                	  }
		                	  } catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                  num_row++;
		                  }
		              }
		          } else if (htmlNode instanceof CommentNode) {
		              CommentNode comment = ((CommentNode) htmlNode); 
		              comment.getContent().append(" -- By HtmlCleaner");
		          }
		          // tells visitor to continue traversing the DOM tree
		       return true; 
		      }
		    	  
		  });
		  } 
		
	 
	 private void parse1Row(TagNode node,final String vagonType, final UZTrain train)
	 {
		  node.traverse(new TagNodeVisitor() {
		      public boolean visit(TagNode tagNode, HtmlNode htmlNode) {
		         try{
		    	  if (htmlNode instanceof TagNode) {
		              TagNode tag = (TagNode) htmlNode;
		              String tagName = tag.getName();
		              if ("td".equals(tagName)) {
		                  String src = tag.getAttributeByName("class");
		                  
		                  if(src.contains(vagonType) && !src.contains("empty"))
		                  {
		                	  String Labelname = null;
						
								Labelname = new String(tag.getText().toString().getBytes(),"UTF-8");
  	  
	                	  
		                	  int num_seats =Integer.parseInt(Labelname.substring(Labelname.indexOf(
		                			  new String("Осталось свободных мест: ".getBytes(),"UTF-8"))
		                			  +"Осталось свободных мест: ".length()));
		                	  //tag.indexOf(new String("Осталось свободных мест: ".getBytes(),"UTF-8")) 
		                	  train.updateNum(vagonType,num_seats);
		                	  //System.out.println(vagonType + "  " + num_seats);
		                  }
		                  //System.out.println("Name:" + tag.getText());
		                  //System.out.println("Link:" + src);
		                  //membersList.add(src);

		              }
		          } else if (htmlNode instanceof CommentNode) {
		              CommentNode comment = ((CommentNode) htmlNode); 
		              comment.getContent().append(" -- By HtmlCleaner");
		          }
		          // tells visitor to continue traversing the DOM tree
		       
		         }catch(Exception e){e.printStackTrace();}
		         return true; 
		      }
		  });
	 }
}
