import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name ="servlet2", urlPatterns = {"/Cookie1"})
public class servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        /*Duyệt hết cookie được tạo ở servlet1 rồi xem thời gian sống của nó */
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            printWriter.println(cookie.getName()+ " : " + cookie.getValue() + "<br/>");

            /*Cách xóa :Nếu name ="age" thì xóa, servlet1 ta có tạo age, nên sẽ xóa */
            if(cookie.getName().equals("age")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }else if(cookie.getName().equals("name")){
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }



        }
    }
}
