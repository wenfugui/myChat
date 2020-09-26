package ChatServer;

import java.io.IOException;
import java.util.ArrayList;

public class ChatProcess {
    private static ArrayList<ChatThread> ctList = new ArrayList<>();
    private ChatProcess(){}

    public static void addSocket(ChatThread chatThread) throws IOException{
        System.out.println("新用户加入2");
        ctList.add(chatThread);
        sendAll(chatThread.getUser(),"我上线了！目前人数:"+ctList.size());
    }
    public static void sendAll(User user,String msg) throws IOException{
        msg = user.getName() + "\t\t" + DateDemo.getDate() + ":\n" + msg;
        for (ChatThread ct:ctList
             ) {
            ct.sendMsg(msg);
        }
    }
    public static void removeSocket(ChatThread chatThread){
        ctList.remove(chatThread);
    }
}
