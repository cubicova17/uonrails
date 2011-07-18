package com.uaonrails.mining;


//
import com.uaonrails.mining.support.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
//hello

//import com.cubicova.facebookparse.fb_page_graber.*;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.util.*;

import javax.servlet.http.*;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

@SuppressWarnings("serial")
public class UZParser extends HttpServlet {
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			doPost(req,resp);
	        
	}

	String SendReqtoUZ( String train_id) // e.g. train_id = "Kyiv-Moscow 002"
	{
		String output=new String("");
		URL url;
		try {
			System.out.println(URLEncoder.encode(train_id,"UTF-8"));
			System.out.println(train_id);
			train_id = URLEncoder.encode(train_id,"UTF-8");
			url = new URL(String.format( "http://www.uz.gov.ua/index.php?m=info.info_posagiriv.cini_nayavnist&f=ScheduleOra.Place&lng=uk"));
			
			URLConnection connection = url.openConnection();
			
			connection.setDoOutput(true);
			connection.setRequestProperty( "Host", "uz.gov.ua" );
			connection.setRequestProperty( "User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:2.0) Gecko/20100101 Firefox/4.0");
			connection.setRequestProperty( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" );
			connection.setRequestProperty( "Accept-Language	", "ru-ru,ru;q=0.8,en-us;q=0.5,en;q=0.3" );
			
			connection.setRequestProperty( "Accept-Encoding", "gzip,deflate" );
			
			connection.setRequestProperty( "Accept-Charset	", "windows-1251,utf-8;q=0.7,*;q=0.7" );
			
			connection.setRequestProperty( "Keep-Alive", "115" );
			
			connection.setRequestProperty( "Connection", "keep-alive" );
			connection.setRequestProperty( "Referer", "http://uz.gov.ua/index.php?m=info.info_posagiriv.cini_nayavnist&f=ScheduleOra.Place&lng=uk" );
			//hello
			connection.setRequestProperty( "Cookie","b=b");
			int i=0;
			
			
			String rawData = train_id;
			OutputStreamWriter out = new OutputStreamWriter(
		                              connection.getOutputStream(),"UTF-8");
			out.write(rawData);
			out.flush();
	
			
			//Out HTML
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
					connection.getInputStream(),"Cp1251"));
				
		String decodedString;

		while ((decodedString = in.readLine()) != null) {
		    output+=decodedString;
	    }
		in.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;


	}
	void SetReqProp(URLConnection connection, boolean setCookies, CookiesInJava cm)
	{

	//Cookie	
	connection.setRequestProperty( "Host", "dprc.gov.ua" );
	connection.setRequestProperty( "User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0");
	connection.setRequestProperty( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");//"application/json, text/javascript, */*" );
	connection.setRequestProperty( "Accept-Language	", "ru-ru,ru;q=0.8,en-us;q=0.5,en;q=0.3" );
	
	//connection.setRequestProperty( "Accept-Encoding", "gzip,deflate" );
	
	connection.setRequestProperty( "Accept-Charset	", "windows-1251,utf-8;q=0.7,*;q=0.7" );
	//connection.setRequestProperty( "Content-Type","application/octet-stream; charset=UTF-8");
	//connection.setRequestProperty("X-Requested-With","XMLHttpRequest");
	connection.setRequestProperty( "Keep-Alive", "115" );
	
	connection.setRequestProperty( "Connection", "keep-alive" );
	connection.setRequestProperty( "Referer",	"http://dprc.gov.ua/" );
//	connection.setRequestProperty("Content-Length",	"53");
	
//	if(setCookies) 
//		cm.writeCookies(connection,true);
//	connection.setRequestProperty( "Cookie","PHPSESSID=jehtvpa2dn1322d6imq7vjrbd5");
//	connection.setRequestProperty( "Pragma"	,"no-cache");
//	connection.setRequestProperty( "Cache-Control",	"no-cache");
	}
	
	String SendReqtoPZ( String when_, String from_, String to_) // e.g. train_id = "Kyiv-Moscow 002"
	{
		String output=new String("");
		URL url;
		try {
	
			url = new URL(String.format( "http://dprc.gov.ua/show.php?transport_type=2&src="+from_+
					"&dst="+to_+"&dt="+when_+"&ps=privat_card"));

			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setReadTimeout(100000);
			SetReqProp(connection,false, null);
			
			//CookiesInJava cm =  new CookiesInJava();
			//cm.readCookies(connection, true,false);
			
			//cm.writeCookies(connection,true);
			//connection.;
			//SetReqProp(connection, true, cm);
			
			
			//String rawData = train_id;
			/*OutputStreamWriter out = new OutputStreamWriter(
		                              connection.getOutputStream(),"UTF-8");
			out.write("dst	22218000");
			out.write("dt	2011-07-22");
			out.write("ps	privat_card");
			out.write("src	22200001");
			out.write("transport_type	2");*/
			//kstotpr=2200001&&&time_from=		
			/*out.write("kstotpr=2200001");
			out.write("kstprib=2200600");
			out.write("sdate=1808");
			out.write("time_from=");
			//out.write(rawData);
			out.flush();*/
			/*dst	22218000
			dt	2011-07-22
			ps	privat_card
			src	22200001
			transport_type	2*/
			
			//Out HTML
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
					connection.getInputStream(),"UTF-8"));
				
		String decodedString;

		while ((decodedString = in.readLine()) != null) {
		    output+=decodedString;
	    }
		in.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;


	}
	
		public static void postData(Reader data, URL endpoint, Writer output) throws Exception
		{
		HttpURLConnection urlc = null;
		try
		{
		urlc = (HttpURLConnection) endpoint.openConnection();
		try
		{
		urlc.setRequestMethod("POST");
		} catch (ProtocolException e)
		{
		throw new Exception("Shouldn't happen: HttpURLConnection doesn't support POST??", e);
		}
		urlc.setDoOutput(true);
		urlc.setDoInput(true);
		urlc.setUseCaches(false);
		urlc.setAllowUserInteraction(false);
		urlc.setRequestProperty("Content-type", "text/xml; charset=" + "windows-1251,utf-8;q=0.7,*;q=0.7");

		OutputStream out = urlc.getOutputStream();

		try
		{
		Writer writer = new OutputStreamWriter(out, "Cp1251");
		pipe(data, writer);
		writer.close();
		} catch (IOException e)
		{
		throw new Exception("IOException while posting data", e);
		} finally
		{
		if (out != null)
		out.close();
		}

		InputStream in = urlc.getInputStream();
		try
		{
		Reader reader = new InputStreamReader(in,"Cp1251");
		pipe(reader, output);
		reader.close();
		} catch (IOException e)
		{
		throw new Exception("IOException while reading response", e);
		} finally
		{
		if (in != null)
		in.close();
		}

		} catch (IOException e)
		{
		throw new Exception("Connection error (is server running at " + endpoint + " ?): " + e);
		} finally
		{
		if (urlc != null)
		urlc.disconnect();
		}
		}

		/**
		* Pipes everything from the reader to the writer via a buffer
		*/
		private static void pipe(Reader reader, Writer writer) throws IOException
		{
		char[] buf = new char[1024];
		int read = 0;
		while ((read = reader.read(buf)) >= 0)
		{
		writer.write(buf, 0, read);
		}
		writer.flush();
		}
		 public void parseTrains(InputStream is,PrintWriter os) throws IOException
		 {
			
			 
			HtmlCleaner cleaner = new HtmlCleaner();
				 // final String siteUrl = file_name;
				   UZTrainsParser uzp= new UZTrainsParser();
				  TagNode node = cleaner.clean(is);
				  uzp.parseRows(node);
				  for(UZTrain tmp:uzp.trains)
				  {
					  
					  os.println(tmp.name+" : Departure "+
							  tmp.dep_str+" Arrive " +
							  tmp.arr_str+" Plazkart "+
							  tmp.platzkart+" Kupe "+
							  tmp.kupe+"<br>\n");
				  }
		


		 }
		public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
			resp.setCharacterEncoding("Windows-1251");
			resp.setContentType("text/html");
			String when_ = (String)req.getParameter("when");
			String from_ = (String)req.getParameter("from");
			String to_ = (String)req.getParameter("to");
			
			String res = null;
			if(when_!=null && from_!=null&& to_!=null)
			{
			 res = 			SendReqtoPZ(when_,from_,to_);
			
			//resp.getWriter().println(res);//"015%C2%EC%EE%F1%EA%E2%E0+%EA%E8%E2-%EA%EE%F8%E8%F6%E5++++"));
			 
			parseTrains(new ByteArrayInputStream(res.getBytes()),resp.getWriter());
			}
			resp.setContentType("text/html");
			    PrintWriter out = resp.getWriter();
			    			    out.println("<HTML><BODY BGCOLOR=\"#FDF5E6\">\n" +
			                "<H1 ALIGN=CENTER>equ</H1>\n" +
			                "<B>Request Method: </B>" +
			                req.getMethod() + "<BR>\n" +
			                "<B>Request URI: </B>" +
			                req.getRequestURI() + "<BR>\n" +
			                "<B>Request Protocol: </B>" +
			                req.getProtocol() + "<BR><BR>\n" +
			                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
			                "<TR BGCOLOR=\"#FFAD00\">\n" +
			                "<TH>Header Name<TH>Header Value");
			    Enumeration headerNames = req.getHeaderNames();
			    while(headerNames.hasMoreElements()) {
			      String headerName = (String)headerNames.nextElement();
			      out.println("<TR><TD>" + headerName);
			      out.println("    <TD>" + req.getHeader(headerName));
			    }
			    out.println("</TABLE>\n");
			    
			    out.println("<form name=\"input\" action=\"UZParser\" method=\"POST\">");
			    out.println("Ented date in format 2011-07-25: <input type=\"text\" name=\"when\" />");
			    
		
			    out.println("<select name=\"from\">");
			    out.println("<option value=\"22218000\">Lviv</option>");
			    out.println("<option value=\"22200001\">Kyiv</option>");
			    out.println("<option value=\"22210001\">Simfer</option>");
			    out.println("<option value=\"22210750\">Sevast</option>");
			    out.println("</select>");
			    
			    out.println("<select name=\"to\">");
			    out.println("<option value=\"22218000\">Lviv</option>");
			    out.println("<option value=\"22200001\">Kyiv</option>");
			    out.println("<option value=\"22210001\">Simfer</option>");
			    out.println("<option value=\"22210750\">Sevast</option>");
			    out.println("</select>");
			    out.println("<input type=\"submit\" value=\"Submit\" />");
			    out.println("</form>"); 
			    out.println("</BODY></HTML>");
			    
		}
		public void doPost23(HttpServletRequest req, HttpServletResponse resp)
		throws IOException {
			
		
		//resp.setCharacterEncoding("Windows-1251");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		resp.getWriter().println(req.toString());
		resp.getWriter().println(req.getCharacterEncoding()+ " \n"+ req.getParameterMap().toString() );
		
		if (req.getParameter("np")!=null){
		
			resp.getWriter().println(req.getParameter("np")+"<br>");
			for(byte b:req.getParameter("np").getBytes())
				resp.getWriter().println(b);
			
			for( Object st:req.getParameterMap().entrySet()){
				
				resp.getWriter().println(st.getClass().getName());
			}
		
		}		
		
	//	doGet(req,resp);
		
	
	}
	
        
}
				
	 	

