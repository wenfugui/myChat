package ChatServer;

import java.io.*;
import java.net.Socket;

public class ChatThread extends Thread{
    private Socket socket;
    private User user;
    private OutputStream ous;
    private InputStream ins;

    public ChatThread(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("新用户加入1");
        ous = this.socket.getOutputStream();
        ins = this.socket.getInputStream();
        sendMsg("欢迎加入");
    }
    public User getUser(){
        return this.user;
    }

    public void sendMsg(String msg) throws IOException{
        msg += "\r\n";
        ous.write(msg.getBytes());
        ous.flush();
    }

    private void processMsg() throws IOException {
        try{

            BufferedReader brd = new BufferedReader(new InputStreamReader(ins));

            sendMsg("欢迎加入聊天室，请输入您的昵称：");
            String userName = brd.readLine();
            System.out.println(userName);
            user = new User();
            user.setName(userName);
            ChatProcess.addSocket(this);

            String msgIn;
            while(true){
                msgIn = brd.readLine();
                ChatProcess.sendAll(this.getUser(),msgIn);
            }
        }catch (IOException e){
            this.socket.close();
            ChatProcess.removeSocket(this);
        }
    }
    public void run(){
        try{
            processMsg();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
