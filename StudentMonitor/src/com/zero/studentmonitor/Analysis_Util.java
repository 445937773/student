package com.zero.studentmonitor;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
public class Analysis_Util {
	private static final String NAMESPACE = "http://tempuri.org/";
	private static final String URL = "http://90world.51idctg.com/Service1.asmx";
	public static List<Student> getDetail(String pwd){
		try {
			List<Student> students = new ArrayList<Student>();
			SoapObject request = new SoapObject(NAMESPACE, "GetAllStudents");
			request.addProperty("phone", "18626373423");
			request.addProperty("password", pwd);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
			        SoapEnvelope.VER10);
			envelope.bodyOut = request;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(request);
			HttpTransportSE androidHttpTransport = new HttpTransportSE(URL,20000);
			androidHttpTransport.debug = true;
			androidHttpTransport.call(NAMESPACE + "GetAllStudents", envelope);
			
			SoapObject result = (SoapObject)envelope.bodyIn;
			Object detail = (Object)result.getProperty("GetAllStudents"+"Result");
			if(detail != null){
				JSONArray array = new JSONArray(detail.toString());
				if(array.length() > 0){
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj = array.getJSONObject(i);
						Student student = new Student();
						student.setStuId(obj.getInt("StudentID"));
						student.setStuName(obj.getString("Name"));
						student.setStuPhone(obj.getString("Phone"));
						students.add(student);
					}
				}else{
					return students;
				}
			}else{
				return students;
			}
			
			return students;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
