package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.util.HashMap;
import java.util.Map;

import static exercise.App.getUsers;
import exercise.Users;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        switch (request.getRequestURI()) {
            case "/login" -> login(request, response);
            case "/logout" -> logout(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        String email = request.getParameter("email");
        System.out.println(email);
        String pas = request.getParameter("password");
        // Получаем пользователя по email
        Map<String, String> user = users.findByEmail(email);

        // Получение сессии
        HttpSession session = request.getSession();

        if (user == null || !pas.equals("password")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");

//            request.setAttribute("email", email);
            request.setAttribute("user", user);

            session.setAttribute("flash", "Неверные логин или пароль");
            response.setStatus(422);
            requestDispatcher.forward(request, response);
            return;
        }


        // Установка атрибутов сессии
        // Вход в систему сводится к записи данных пользователя в сессию
        String userId = user.get("id");
        session.setAttribute("userId", userId);
        // Механизм работы флеш-сообщений тоже основан на сессии
        // Устанавливаем в сессию атрибут с текстом сообщения
        // Далее мы сможем получить эти данные в шаблонах
        session.setAttribute("flash", "Вы успешно вошли");

        // Выполняем редирект на главную страницу
        response.sendRedirect("/");
        // END
    }

    private void logout(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {

        // BEGIN
        // Для выхода пользователя из системы нужно удалить его данные из сессии
        HttpSession session = request.getSession();
        // Удаляем атрибут из сессии
        session.removeAttribute("userId");
        session.setAttribute("flash", "Вы успешно вышли");
        response.sendRedirect("/");
        // END
    }
}
