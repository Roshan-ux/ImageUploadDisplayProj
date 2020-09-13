package com.store.retrive;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.db_connection.DB_Connect;
import com.pojo.Image;

@WebServlet("/GetImage")
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetImage() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/json");
		PrintWriter out = response.getWriter();
		
		List<Image> imlist = new ArrayList<>();
		
		String query ="select * from new_images order by img_id desc";
		Connection con =DB_Connect.getConnect();
		ResultSet rs=null;
		
		
		try {
			PreparedStatement ps= con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Image img = new Image();
				img.setImg_id(rs.getInt(1));
				img.setImgsrc(rs.getString(2));
				img.setName(rs.getString(3));
				
				imlist.add(img);
				
			}
			
			
			con.close();
			
			HashMap<String,Object> obmap= new HashMap<>();
			obmap.put("result", imlist);
			String result = jsonOb(obmap);
			
			response.addHeader("Access-Control-Allow-Origin", "*");
			out.write(result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public String jsonOb(Object ob) {
		ObjectMapper om = new ObjectMapper();
		
		String result=null;
		try {
			result = om.writeValueAsString(ob);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return result;
		
	}

}
