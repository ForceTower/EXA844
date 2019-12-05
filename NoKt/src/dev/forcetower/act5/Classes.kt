package dev.forcetower.act5

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet("/add")
class Rocket : HttpServlet() {
    override fun service(req: HttpServletRequest, resp: HttpServletResponse) {
        val email = req.getParameter("email") ?: ""

        if (!email.contains("@")) {
            resp.sendRedirect("create?inv=true")
            return
        }

        resp.writer.run {
            println("<html>")
            println("<body>")
            println("Cadastro concluido!")
            println("</body>")
            println("</html>")
        }
    }
}

@WebServlet("/create")
class Ship : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val error = req.getParameter("inv")?.toBooleanOrNull() ?: false
        resp.writer.run {
            println("<html>")
            println("<body>")
            if (error) {
                println("<h3>Email inválido</h3>")
            }
            println("<form action=\"add\" method=\"POST\">\n" +
                    "    <label>\n" +
                    "        Nome: <input type=\"text\" name=\"name\" />\n" +
                    "    </label><br />\n" +
                    "    <label>\n" +
                    "        E-mail: <input type=\"text\" name=\"email\" />\n" +
                    "    </label><br />\n" +
                    "    <input type=\"submit\" value=\"Enviar\" />\n" +
                    "</form>")
            println("</body>")
            println("</html>")
        }
    }
}

fun String.toBooleanOrNull() = try { toBoolean() } catch (t: Throwable) { null }