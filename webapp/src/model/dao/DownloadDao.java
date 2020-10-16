package model.dao;

import model.vo.Download;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class DownloadDao {
    public ArrayList<Download> query(){
    	ArrayList<Download> list= new ArrayList<Download>();
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/excise","root","root");
    		  String sql="select * from t_downloadlist "; 
    		  
    		  PreparedStatement pst = con.prepareStatement(sql);
    		  //ִ��sql���
    		//  System.out.print("aaaaaa");
    		  ResultSet rs =pst.executeQuery();
    	
    		  while(rs.next()) {
    			  Download download=new Download();
    			  download.setId(rs.getInt("id"));
    			  download.setName(rs.getString("name"));
    			  download.setPath(rs.getString("path"));
    			  download.setDescription(rs.getString("description"));
    			  long size=rs.getLong("size");
    			  String sizeStr=fileSizeTransfer(size);
    			  download.setSizeStr(sizeStr);
    			  download.setId(rs.getInt("size"));
    			  download.setId(rs.getInt("star"));
    			  download.setId(rs.getInt("image"));
    			  download.setId(rs.getInt("time"));
    			 list.add(download);
    		  }
    		  con.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;
    }
    public Download get(int id) {
    	Download download =null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
  		  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useunicode=ture&character=utf-8","root","00000000");
  		  String sql="select * from t_downloadlist where id=? ";   		 
  		  PreparedStatement pst = con.prepareStatement(sql);
  		  pst.setInt(1, id);
  		  //ִ��sql���
  		  ResultSet rs =pst.executeQuery();
  		  
  		  if(rs.next()) {
  			  download=new Download();
  			  download.setId(rs.getInt("id"));
  			  download.setName(rs.getString("name"));
  			  download.setPath(rs.getString("path"));
  			  download.setDescription(rs.getString("description"));
  			  long size=rs.getLong("size");
  			  String sizeStr=fileSizeTransfer(size);
  			  download.setSizeStr(sizeStr);
  			  download.setId(rs.getInt("size"));
  			  download.setId(rs.getInt("star"));
  			  download.setId(rs.getInt("image"));
  			  download.setId(rs.getInt("time"));
  			 
  		  }
  		  con.close();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return download;
    }
    public String fileSizeTransfer(long fileSize) {
    	String mFileSize;
    	DecimalFormat df=new DecimalFormat("######0.00");
    	double size = (double) fileSize;
    	if(size>1024*1024*1024) {
    		size=size/(1024*1024*1024);
    		mFileSize=df.format(size)+"G";
    	}else if(size>1024*1024) {
    		size=size/(1024*1024);
    		mFileSize=df.format(size)+"MB";
    	}else if(size>1024) {
    		size=size/(1024);
    		mFileSize=df.format(size)+"KB";
    	}else {
    		mFileSize=df.format(size)+" B";
    	}
    	return mFileSize;
    }
}
