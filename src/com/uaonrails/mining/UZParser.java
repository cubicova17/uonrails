package com.uaonrails.mining;

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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;


//import com.cubicova.facebookparse.fb_page_graber.*;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.util.*;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class UZParser extends HttpServlet {
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
				
		resp.setCharacterEncoding("Windows-1251");
		//resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");




		//String data = SendReqtoUZ("np=016√ã√≥√¶√£√Æ√∞√Æ√§ -√¨√Æ√±√™√¢√† √™√®√¢");//new String("np=016√ã√≥√¶√£√Æ√∞√Æ√§ -√¨√Æ√±√™√¢√† √™√®√¢".toString().getBytes(),"UTF-8"));

		
		
		//resp.getWriter().println(data);
		
		String file_name = "result.html";

		URL formAction = new URL( "http://localhost:8888/UZParser");//"http://uz.gov.ua/index.php?m=info.info_posagiriv.cini_nayavnist&f=ScheduleOra.Place&lng=uk");
		//FileWriter fw = new FileWriter(file_name);
		try {
			postData(new StringReader("np=169Êêè¿â ïàñ -ëüâ³â "), formAction, resp.getWriter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	        
	}

		String SendReqtoUZ( String groupname)
	{
		String output=new String("");
		URL url;
		try {
			//System.out.println(groupname);
			url = new URL(String.format( "http://uz.gov.ua/index.php?m=info.info_posagiriv.cini_nayavnist&f=ScheduleOra.Place&lng=uk"));

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
			
			
			String rawData = groupname;
			OutputStreamWriter out = new OutputStreamWriter(
		                              connection.getOutputStream());
			out.write(rawData);
			
	
			
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
				
	 	

