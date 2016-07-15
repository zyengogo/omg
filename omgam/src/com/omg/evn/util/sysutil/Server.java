package com.omg.evn.util.sysutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerSocket
{
private static final int SERVER_PORT = 10000;

public Server() throws IOException
{
super(SERVER_PORT);

try
{
while (true)
{
Socket socket = accept();
new CreateServerThread(socket);
System.out.println("fuckk");
}
}
catch (IOException e)
{}
finally
{
close();
}
}
//--- CreateServerThread
class CreateServerThread extends Thread
{
private Socket client;
private BufferedReader in;
private PrintWriter out;

public CreateServerThread(Socket s) throws IOException
{
client = s;

in = new BufferedReader(new InputStreamReader(client.getInputStream(), "GB2312"));
out = new PrintWriter(client.getOutputStream(), true);
out.println("--- Welcome ---");
start();
}

public void run()
{
try
{
String line = in.readLine();

while (!line.equals("bye"))
{
String msg = createMessage(line);
System.out.println("----fuck"+msg);
line = in.readLine();
}
System.out.println("--- See you, bye! ---");
client.close();
}
catch (IOException e)
{}
}

private String createMessage(String line)
{
System.out.println("createMessage");
return "fuck";
}
}

//public static void main(String[] args) throws IOException
//{
//new Server();
//}
} 