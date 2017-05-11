package clientServer;

import java.io.*;
import java.io.InputStream;
import java.net.*;
import java.net.Socket;

public class Server1 {
	
	public static void main( String args[ ] ) {

		try {

		ServerSocket soc = new ServerSocket( 5555 );  // 5555 is Port number.

		Socket sc = soc.accept();

		InputStream is = sc.getInputStream();

		BufferedReader br = new BufferedReader( new InputStreamReader( is ));

		String data = br.readLine(); 

		System.out.println( data );

		data = "from server"+data ;

		OutputStream os = sc.getOutputStream();

		PrintStream ps = new PrintStream( os );

		ps.println( data );

		} catch ( Exception e) {
		System.out.println( e );
		}
		}
		

}
