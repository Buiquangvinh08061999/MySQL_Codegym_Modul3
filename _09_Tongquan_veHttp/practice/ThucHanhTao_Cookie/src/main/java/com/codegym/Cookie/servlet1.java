import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="servlet1", urlPatterns = {"/Cookie"})
public class servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.setContentType("text/html");
       PrintWriter printWriter = resp.getWriter();
       printWriter.println("<h1>Cookie</h1>");

       Cookie cookie1 = new Cookie("name","Codegym");
       cookie1.setMaxAge(25);
       resp.addCookie(cookie1);

       Cookie cookie2 = new Cookie("age","22");
       cookie2.setMaxAge(25);
       resp.addCookie(cookie2);

        Cookie cookie3 = new Cookie("Address","Hue");
        cookie3.setMaxAge(25);
        resp.addCookie(cookie3);
    }
}
