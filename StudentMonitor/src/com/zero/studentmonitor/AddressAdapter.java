package com.zero.studentmonitor;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AddressAdapter extends BaseAdapter {

	Context content;
	List<Student> address;
	private LayoutInflater inflater;
	public AddressAdapter(Context content,List<Student> address) {
		// TODO Auto-generated constructor stub
		this.inflater=LayoutInflater.from(content);
		this.address = address;
		this.content = content;
	}
	@Override
	public int getCount() {
		return address.size();
		}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return address.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder holder=null;
		if(arg1==null){
			holder = new ViewHolder();
			arg1=inflater.inflate(R.layout.item, null);
			holder.text1 = (TextView) arg1.findViewById(R.id.textView1);
			holder.text2 = (TextView) arg1.findViewById(R.id.textView2);
			holder.text3 = (TextView) arg1.findViewById(R.id.textView3);
			arg1.setTag(holder);
		}else{
			holder = (ViewHolder) arg1.getTag();
		}
		Student info =address.get(arg0);
		arg1.setVisibility(View.VISIBLE);
		holder.text1.setText(info.getStuId() + "");
		holder.text2.setText(info.getStuName());
		holder.text3.setText(info.getStuPhone());
		return arg1;
	}
	private class ViewHolder{
		TextView text1,text2,text3;
	}
	
}
