package clientServer;


import java.io.*;
import java.net.*;

public class Client1 {

	public static void main( String args[ ] ) {

		try {

		String data = "Hallo";

		Socket sc = new Socket("nit6",5555);

		OutputStream os = sc.getOutputStream();

		PrintStream ps = new PrintStream( os );

		ps.println( data );

		InputStream is = sc.getInputStream();

		BufferedReader br = new BufferedReader(new InputStreamReader( is ));

		String rep = br.readLine();

		System.out.println( rep );

		} catch( Exception e ) {
		System.out.println( e );
		}
		}
		
}
