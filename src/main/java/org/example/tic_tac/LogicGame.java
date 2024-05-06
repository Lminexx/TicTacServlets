package org.example.tic_tac;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet(name = "logicGame", value = "/logic")
public class LogicGame  extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession currentSession = req.getSession();
        Field field = extractField(currentSession);
        int index = getIndex(req);
        Sign currentSign = field.getField().get(index);
        //крестик добавляется
        if(Sign.EMPTY != currentSign){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        field.getField().put(index, Sign.CROSS);

        if (checkWin(resp, currentSession, field) || draw(field, currentSession , resp)) {
            return;
        }
        //нолик
        int freeIndex = field.getEmptyFieldIndex();
        if(freeIndex >=0){
            field.getField().put(freeIndex, Sign.NOUGHT);
            if(checkWin(resp, currentSession, field) || draw(field, currentSession , resp)){
                return;
            }
        }
        List<Sign> data = field.getFieldData();;
        currentSession.setAttribute("data", data);
        currentSession.setAttribute("field", field);
        resp.sendRedirect("/tic_tac_war/index.jsp");
    }
    private boolean draw(Field field, HttpSession currentSession,HttpServletResponse response) throws IOException {
        List<Sign> data = field.getFieldData();
        int cnt =0;
        for (int i = 0; i < data.size(); i++) {
            if(data.get(i)==Sign.EMPTY){
                cnt++;
            }
        }
        if(cnt==0){
            currentSession.setAttribute("winner", "Ничья");
            response.sendRedirect("/tic_tac_war/winner.jsp");
            return true;
        }
        return false;
    }

    private boolean checkWin(HttpServletResponse response, HttpSession currentSession, Field field) throws IOException {
        Sign winner = field.checkWin();
        if(winner == Sign.CROSS || winner == Sign.NOUGHT){
            currentSession.setAttribute("winner", winner);
            List<Sign> data = field.getFieldData();
            currentSession.setAttribute("data", data);
            response.sendRedirect("/tic_tac_war/winner.jsp");
            System.out.println("Winner is " + winner);
            return true;
        }
        return false;
    }
    private Field extractField(HttpSession currentSession) {
        Object fieldAttribute = currentSession.getAttribute("field");
        if (Field.class != fieldAttribute.getClass()) {
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }
        return (Field) fieldAttribute;
    }

    private int getIndex(HttpServletRequest req){
        return Integer.parseInt(req.getParameter("click"));
    }

}
