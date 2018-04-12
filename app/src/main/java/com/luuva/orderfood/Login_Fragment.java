package com.luuva.orderfood;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.luuva.background.SessionManager;
import com.luuva.background.UserSession;
//import com.luuva.model.UserInfo;
import com.luuva.model.User;
import com.luuva.model.Utils;
import com.luuva.util.CheckConnectNetwork;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Fragment extends Fragment implements OnClickListener {
	private static View view;

	private static EditText emailid, password;
	private static Button loginButton;
	private static TextView forgotPassword, signUp;
	private static CheckBox show_hide_password;
	private static LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;
	private static SessionManager sessionManager;
	private UserSession session;
	private ProgressDialog progressDialog;
	private Dialog dialog;
	//private UserInfo userInfo;
	RequestQueue requestQueue;
	private static final String TAG = Login_Fragment.class.getSimpleName();
	String login_url = "https://lebavy1611.000webhostapp.com/loginnew.php";
	public Login_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.login_layout, container, false);
		initViews();
		setListeners();
		progressDialog = new ProgressDialog(this.getContext());
		dialog =  new Dialog(this.getContext());
		return view;
	}

	// Initiate Views
	private void initViews() {
		fragmentManager = getActivity().getSupportFragmentManager();

		emailid = (EditText) view.findViewById(R.id.login_emailid);
		password = (EditText) view.findViewById(R.id.login_password);
		loginButton = (Button) view.findViewById(R.id.loginBtn);
		forgotPassword = (TextView) view.findViewById(R.id.forgot_password);
		signUp = (TextView) view.findViewById(R.id.createAccount);
		show_hide_password = (CheckBox) view
				.findViewById(R.id.show_hide_password);
		loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

		// Load ShakeAnimation
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
				R.anim.shake);

		// Setting text selector over textviews
		@SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
		try {
			ColorStateList csl = ColorStateList.createFromXml(getResources(),
					xrp);

			forgotPassword.setTextColor(csl);
			show_hide_password.setTextColor(csl);
			signUp.setTextColor(csl);
		} catch (Exception e) {
		}
	}

	// Set Listeners
	private void setListeners() {
		loginButton.setOnClickListener(this);
		forgotPassword.setOnClickListener(this);
		signUp.setOnClickListener(this);

		// Set check listener over checkbox for showing and hiding password
		show_hide_password
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton button,
												 boolean isChecked) {

						// If it is checkec then show password else hide
						// password
						if (isChecked) {

							show_hide_password.setText(R.string.hide_pwd);// change
							// checkbox
							// text

							password.setInputType(InputType.TYPE_CLASS_TEXT);
							password.setTransformationMethod(HideReturnsTransformationMethod
									.getInstance());// show password
						} else {
							show_hide_password.setText(R.string.show_pwd);// change
							// checkbox
							// text

							password.setInputType(InputType.TYPE_CLASS_TEXT
									| InputType.TYPE_TEXT_VARIATION_PASSWORD);
							password.setTransformationMethod(PasswordTransformationMethod
									.getInstance());// hide password

						}

					}
				});
	}

	@Override
	public void onClick(View v) {
		final String getEmailId = emailid.getText().toString();
		final String getPassword = password.getText().toString();
		switch (v.getId()) {
			case R.id.loginBtn:
				if(checkValidation()==true){
					CheckConnectNetwork checkInternet =  new CheckConnectNetwork();
					if(checkInternet.checkInternetConenction(v.getContext()))
						login(getEmailId,getPassword,v);
					else {

						progressDialog.setMessage("Không có kết nối Internet, mời kiểm tra lại.");
						progressDialog.show();
					}
				};
				//Intent intent = new Intent(v.getContext(), MainActivity.class);
				//startActivity(intent);


				break;

			case R.id.forgot_password:

				// Replace forgot password fragment with animation
				fragmentManager
						.beginTransaction()
						.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
						.replace(R.id.frameContainer,
								new ForgotPassword_Fragment(),
								Utils.ForgotPassword_Fragment).commit();
				break;
			case R.id.createAccount:

				// Replace signup frgament with animation
				fragmentManager
						.beginTransaction()
						.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
						.replace(R.id.frameContainer, new SignUp_Fragment(),
								Utils.SignUp_Fragment).commit();
				break;
		}

	}

	// Check Validation before login
	private boolean checkValidation() {
		String tag_string_req = "req_login";
		// Get email id and password
		final String getEmailId = emailid.getText().toString();
		final String getPassword = password.getText().toString();
		// Check patter for email id
		Pattern p = Pattern.compile(Utils.regEx);

		Matcher m = p.matcher(getEmailId);

		// Check for both field is empty or not
		if (getEmailId.equals("") || getEmailId.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view,
					"Mời nhập Email và mật khẩu");
			return false;
		}
		// Check if email id is valid or not
		else if (!m.find()) {
			new CustomToast().Show_Toast(getActivity(), view,
					"Email không hợp lệ.");
			return false;
		}
		// Else do login and do your stuff
		else {
			return true;
			//login(getEmailId,getPassword);
		}
	}
	private void login(final String email, final String password, final View v){
		progressDialog.setMessage("Đang đăng nhập...");
		progressDialog.show();
		String type = "login";
		String tag_string_req = "req_login";
		//BackgroundWorker backgroundWorker = new BackgroundWorker(v);
		//backgroundWorker.doInBackground(type, getEmailId, getPassword);
		//backgroundWorker.execute(type, getEmailId, getPassword);
		Log.d("server login","nee");
		StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				//
				String s = response.trim();
				Log.d("server login",s);
				//progressDialog.setMessage(response);
				//progressDialog.show();
				try {
					JSONObject jObj = new JSONObject(response);
					boolean error = jObj.getBoolean("error");
					if (!error) {
						JSONObject user = jObj.getJSONObject("user");
						String idUserLogin = user.getString("id");
						Timestamp dateOfBrith = Timestamp.valueOf(user.getString("date_of_brith"));
						Timestamp dateCreate = Timestamp.valueOf(user.getString("date_create"));
						User userLogin = new User(user.getInt("id"),
								user.getString("username"),
								user.getString("password"),
								user.getString("fullname"),
								user.getString("email"),
								user.getString("phone"),
								user.getString("address"),
								user.getInt("gender"),
								dateOfBrith,
								user.getInt("role_id"),
								dateCreate,
								user.getInt("active"));
						// Inserting row in users table
						session = new UserSession(v.getContext());
						session.setLoggedin(true, userLogin);
						toast("Xin chào "+userLogin.getFullname());

						//intent.putExtra("EMAIL",email);
						startActivity(new Intent(v.getContext(), MainActivity.class));
						//finish();
					}else {
						progressDialog.setMessage("Đăng nhập thất bại.");
						progressDialog.show();
					}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				//Log.e(TAG, "Login Error: " + error.getMessage());
				//toast("Unknown Error occurred");
				//progressDialog.hide();
			}
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String,String> params = new HashMap<>();
				params.put("email",email);
				params.put("password",password);
				return params;
			}
		};
		Log.d("vao k", String.valueOf(stringRequest));
		//requestQueue.add(stringRequest);
		AndroidLoginController.getInstance().addToRequestQueue(stringRequest, tag_string_req);
	}
	private void toast(String x){
		Toast.makeText(this.getContext(), x, Toast.LENGTH_LONG).show();
	}
}
