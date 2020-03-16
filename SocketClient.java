import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		try {

			System.out.println("SocketClient");
            //서버 접속
            Socket socket = new Socket("203.254.173.101", 9000);

            BufferedWriter bufWriter;
            BufferedReader bufReader;
            
            while(true)
            {
            	Thread.sleep(5000);
            	//Server에 보낼 데이터 
            	bufWriter = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream()));
            	String message = "hello";
            	bufWriter.write(message);
            	bufWriter.newLine();
            	bufWriter.flush();
            	
            	//Server가 보낸 데이터 출력
            	bufReader = new BufferedReader( new InputStreamReader( socket.getInputStream()));
            	message = bufReader.readLine();
            	System.out.println("responseData : " + message );
            	
            	if(message.equals("finish"))
            	{
            		break;
            	}
            }
            
            socket.close();
            bufReader.close();
            bufWriter.close();
          }

          catch( Exception e ){
            e.printStackTrace();
          }

	}

}
