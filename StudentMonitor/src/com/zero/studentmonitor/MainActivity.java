package com.zero.studentmonitor;


import java.util.List;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private List<Student> students;
	protected Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				showStudent();
				break;

			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	protected void showStudent() {
		TextView text1 = (TextView) findViewById(R.id.textView1);
		TextView text2 = (TextView) findViewById(R.id.textView2);
		TextView text3 = (TextView) findViewById(R.id.textView3);
		text1.setText("Total=" + students.size());
		SharedPreferences userInfo = getSharedPreferences("last_id", 0);
		text2.setText("LastId=" + userInfo.getInt("last_id", 0));
		userInfo.edit().putInt("last_id", students.get(0).getStuId()).commit();
		text3.setText("New=" + ((students.get(0).getStuId()) - userInfo.getInt("last_id", 0)));
		AddressAdapter adapter = new AddressAdapter(this, students);
		ListView list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(adapter);
	}
	public void showLoginDialog(){
		final EditText tv_pwd = new EditText(this);
		tv_pwd.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);
		Builder builder = new AlertDialog.Builder(this);
		builder.setView(tv_pwd);
		builder.setTitle("输入密码");
		builder.setPositiveButton("获取", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String pwd = tv_pwd.getText().toString();
				if(pwd != null && !"".equals(pwd)){
					getStudent(pwd);
				}
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		});
		builder.create().show();
	}
	public void getStudent(final String pwd) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				students = Analysis_Util.getDetail(pwd);
				if(students != null && students.size() > 0){
					Message msg = new Message();
					msg.what = 1;
					handler .sendMessage(msg);
				}
			}
		}).start();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.login:
			showLoginDialog();
			break;
		case 1:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
