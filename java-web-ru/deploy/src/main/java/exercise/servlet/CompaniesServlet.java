package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> list = getCompanies();
        PrintWriter out = response.getWriter();

        if (request.getQueryString() == null || request.getParameter("search") == null) {
            for (String l : list) {
                out.write(l + "\n");
            }
        } else {
            String param = request.getParameter("search");
            int count = 0;
            for (String l : list) {
                if (l.contains(param)) {
                    out.write(l + "\n");
                    count++;
                }
            }
            if (count == 0) {
                out.write("Companies not found");
            }
        }
        out.close();
        // END
    }
}
