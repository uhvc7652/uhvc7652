package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import AI.InterfaceToJs;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/MyWebServlet")
public class MyWebServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dataStr1 = req.getParameter("data1");
		String dataStr2 = req.getParameter("data2");
		String diceStr = req.getParameter("dice");
		if (diceStr == null) {
			return;
		}
		int dice = Integer.parseInt(diceStr);
		JSONObject dataJSON1 = (JSONObject)JSONObject.toJSON(JSON.parse(dataStr1));
		JSONObject dataJSON2 = (JSONObject)JSONObject.toJSON(JSON.parse(dataStr2));
		int[][] dataArray1 = new int[3][3];
		int[][] dataArray2 = new int[3][3];
		dataArray1[0][0] = dataJSON1.getInteger("0");
		dataArray1[0][1] = dataJSON1.getInteger("1");
		dataArray1[0][2] = dataJSON1.getInteger("2");
		dataArray1[1][0] = dataJSON1.getInteger("3");
		dataArray1[1][1] = dataJSON1.getInteger("4");
		dataArray1[1][2] = dataJSON1.getInteger("5");
		dataArray1[2][0] = dataJSON1.getInteger("6");
		dataArray1[2][1] = dataJSON1.getInteger("7");
		dataArray1[2][2] = dataJSON1.getInteger("8");
		dataArray2[0][0] = dataJSON2.getInteger("0");
		dataArray2[0][1] = dataJSON2.getInteger("1");
		dataArray2[0][2] = dataJSON2.getInteger("2");
		dataArray2[1][0] = dataJSON2.getInteger("3");
		dataArray2[1][1] = dataJSON2.getInteger("4");
		dataArray2[1][2] = dataJSON2.getInteger("5");
		dataArray2[2][0] = dataJSON2.getInteger("6");
		dataArray2[2][1] = dataJSON2.getInteger("7");
		dataArray2[2][2] = dataJSON2.getInteger("8");
		for (int[] is : dataArray1) {
			for (int is2 : is) {
				System.out.print(is2);
			}
			System.out.println();
		}
		for (int[] is : dataArray2) {
			for (int is2 : is) {
				System.out.print(is2);
			}
			System.out.println();
		}

		PrintWriter w = resp.getWriter();
		w.print(Integer.toString(new InterfaceToJs(dataArray2, dataArray1, dice).getPosition()));
		w.flush();
		w.close();
	}

}
