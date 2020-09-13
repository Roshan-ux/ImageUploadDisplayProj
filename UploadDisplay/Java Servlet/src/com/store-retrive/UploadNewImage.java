package com.store.retrive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.db_connection.DB_Connect;

@WebServlet("/uploadnewimage")
public class UploadNewImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadNewImage() {
        super();

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = getJsonObject(request, response);

		
		
		String imgsrc= jsonObject.getString("image");
		System.out.println(imgsrc);
		
		String result=null;
		
		String query="insert into new_images(imgsrc,name) values(?,?)";
		Connection con = DB_Connect.getConnect();
		
		try {
			PreparedStatement ps =con.prepareStatement(query);
			ps.setString(1, imgsrc);
			ps.setString(2, "image");
			int i=ps.executeUpdate();
			
			con.close();
			ObjectMapper om = new ObjectMapper();
			if(i>0) {
				result="Inserted Successfully";
				result= om.writeValueAsString(result);
			
				response.addHeader("Access-Control-Allow-Origin", "*");
				out.write(result);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static JSONObject getJsonObject(HttpServletRequest request, HttpServletResponse response) {

		StringBuffer sb = new StringBuffer();
		String line = null;
		JSONObject jsonObject = null;

		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null)
				sb.append(line);
			jsonObject = new JSONObject(sb.toString());
			System.out.println(jsonObject);
		} catch (Exception e) {
			System.out.println("Error" + e);
		}

		return jsonObject;
	}

}
