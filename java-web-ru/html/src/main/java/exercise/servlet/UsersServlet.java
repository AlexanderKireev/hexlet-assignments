package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        String path = "src/main/resources/users.json";
        List<Map<String, String>> l = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        Path fullPath = Paths.get(path).toAbsolutePath().normalize();
        String json = "";
        try {
            json = Files.readString(fullPath);
            l = mapper.readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> l = getUsers();
        response.setContentType("text/html;charset=UTF-8");
        StringBuilder body = new StringBuilder();

        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                    <body>
                        <table>
                """);
        for (Map<String, String> m : l) {
            body.append("<tr>\n<td>" + m.get("id") + "</td>\n");
            body.append("<td>"
                    + "<a href=\"/users/" + m.get("id") + "\">"
                    + m.get("firstName") + " "
                    + m.get("lastName")
                    + "</td>\n</tr>\n");
        }

        body.append("""
                        </table>
                    </body>
                </html>
                """);

        PrintWriter out = response.getWriter();
        out.println(body.toString());

//        return;
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> l = getUsers();
        String fN = "";
        String lN = "";
        String em = "";
        for (Map<String, String> m : l) {
            if (id.equals(m.get("id"))) {
                fN = m.get("firstName");
                lN = m.get("lastName");
                em = m.get("email");
            }
        }

        if (fN.equals("")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        StringBuilder body = new StringBuilder();

        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application</title>
                        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"
                              rel=\"stylesheet\"
                              integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"
                              crossorigin=\"anonymous\">
                    </head>
                    <body>
                        <table>
                """);

        body.append("<tr>\n<td>" + id + "</td>");
        body.append("<td>" + fN + "</td>");
        body.append("<td>" + lN + "</td>");
        body.append("<td>" + em + "</td>\n");
        body.append("</tr>\n");

                body.append("""
                                </table>
                            </body>
                        </html>
                        """);








        PrintWriter out = response.getWriter();
        out.println(body.toString());






        // END
    }
}
