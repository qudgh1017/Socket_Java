import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		try 
		{
			System.out.println("SocketServer Start");
            //서버 생성
            ServerSocket serverSocket = new ServerSocket(9000);

            Socket socket;
            BufferedReader bufferedReader; 
            BufferedWriter bufferedWriter;

            //client 접속 accept
            socket = serverSocket.accept();
            System.out.println("Client address: " + socket.getInetAddress());
            
            while(true){
            	
            	try
            	{
            		//client가 보낸 데이터 출력
            		bufferedReader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            		String message = bufferedReader.readLine();
            		if(message!=null)
            		{
            			System.out.println("requestData : " + message );
            		}
            		
            		
        			//client에 데이터 전송
        			bufferedWriter = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
        			bufferedWriter.write(message);
        			bufferedWriter.newLine();
        			bufferedWriter.flush();            		

        			if(message.equals("finish"))
        			{
        				break;
        			}
            		
            		
            		
            	}
            	catch(NullPointerException e)
            	{
            		continue;
            		
            	}
            }
            socket.close();
            serverSocket.close();
            bufferedReader.close();
            bufferedWriter.close();

		}
        catch( Exception e )
		{
            e.printStackTrace();
		}
	}
}
