package ChatClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private InputStream ins;
    private OutputStream ous;
    private Thread thread;

    public static void main(String[] args)  {
        Client client = new Client();
        client.connect();
    }

    private void connect() {
        try{
            this.socket = new Socket("127.0.0.1",8888);
            this.thread = new Thread(new ChatThread());

            ins = this.socket.getInputStream();
            ous = this.socket.getOutputStream();

            thread.start();

            Scanner scn = new Scanner(System.in);
            while(scn.hasNext()){
                String str = scn.next();
                str += "\r\n";
                ous.write(str.getBytes());
                ous.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private class ChatThread implements Runnable{
        @Override
        public void run() {
            try{
                BufferedReader brd = new BufferedReader(new InputStreamReader(ins));
                while(true){
                    String str = brd.readLine();
                    System.out.println(str);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
