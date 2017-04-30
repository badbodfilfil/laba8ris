/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MessageClient", urlPatterns = {"/MessageClient"})
public class MessageClient extends HttpServlet {

    @Resource(mappedName = "jms/dest")
    private Queue dest;
    private StringBuilder sb =new StringBuilder();
    @Inject
    @JMSConnectionFactory("jms/queue")
    private JMSContext context;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String str=request.getParameter("msg");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>JMS</title>");            
            out.println("</head>");
            out.println("<body>");
            if(sb.length()>9){
            out.println("<h1>Your message has been sent to the server</h1>");
        sendJMSMessageToDest(sb.toString());
            }
            else{
            out.println("<h1>Your message " + str + " has been added</h1>");
            sb.append(str);}
            out.println("<form method=\"POST\" action=\"MessageClient\">\n" +
"            <table border=\"1\">\n" +
"                <thead>\n" +
"                    <tr>\n" +
"                        <th></th>\n" +
"                        <th></th>\n" +
"                    </tr>\n" +
"                    \n" +
"                </thead>\n" +
"                <tbody>\n" +
"                    <tr>\n" +
"                    <td>Your message:</td>\n" +
"                    <td>\n" +
"                        <input type=\"text\" name=\"msg\" value=\"\" size=\"50\"/>\n" +
"                    </td>\n" +
"                </tr>\n" +
"                    \n" +
"                </tbody>\n" +
"                \n" +
"            </table>\n" +
"            <input type=\"submit\" name=\"submit\" value=\"Submit\"/>\n" +
"        </form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void sendJMSMessageToDest(String messageData) {
        context.createProducer().send(dest, messageData);
    }

}
