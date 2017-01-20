/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zmqserver;

/**
 *
 * @author Matt
 */
import org.zeromq.ZMQ;

public class ZMQServer {
    
  public static void main(String[] args)
   {  System.out.println("ZeroMQGreetingServer starting"
         + " with ZeroMQ version " + ZMQ.getVersionString());
      ZMQ.Context context = ZMQ.context(1);  // thread pool size 1
      ZMQ.Socket socket = context.socket(ZMQ.REP);
      socket.bind("tcp://*:8890");
      // receive message from socket
      byte[] request = socket.recv(0); // blocking by default
      String name = new String(request);
      System.out.println("ZeroMQGreetingServer received name "+name);
      // send response
      byte[] response = ("Hello " + name).getBytes();
      socket.send(response, 0);  // blocking by default
      socket.close();
      context.term();
      System.out.println("ZeroMQGreetingServer stopping");
   }
    
}
