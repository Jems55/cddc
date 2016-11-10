package com.example.mrj.omnify.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrj.omnify.App;
import com.example.mrj.omnify.R;
import com.example.mrj.omnify.util.Constant;
import com.example.mrj.omnify.service.ServiceManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MR.J on 9/3/2016.
 */

class FBlogin extends Activity implements View.OnClickListener {
    LoginButton btnFBLogin;
    TextView btnSignUp, btnLogIn;
    LinearLayout enterLayout, dotslayout;
    RelativeLayout loginLayout, signupLayout;
    LinearLayout animLinear1,animLinear2, animSignUpText, animLogInText, loginLinearLayout;
    Boolean hideDetailText = false , isSignUpViewOpen = false, isLogInViewOpen = false;
    int current = 0, dWidth,dHeight, dotlaywidth, dotlayheigth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    TextView txtName, txtPassword1, txtEmail, txtLogInEmail, txtLogInPassword, userLogin;

    ImageButton backSignUp, backLogIn;
    CallbackManager callbackManager;

    static Handler handler;

    ViewPager viewPager;
    PagerAdapter adapter;
    String[] txtArray;
    int[] images;

    ImageView dotSelect1,dotSelect2,dotSelect3,dotNoselect1,dotNoselect2,dotNoselect3,blackScreen,imageView1,imageView2,imageView3,imageView4,imageView5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.fb_signup);

        initialize();
        initHandler();

        getDeviceDimen();

        btnFBLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                System.out.println("Return Data : " + object);
                                String email = "", name = "", gender= "", birthday="", id = "";

                                try {
                                    id = (String) object.get("id");
                                    App.setUserId(name);
                                    name = (String) object.get("name");
                                    App.setName(name);
                                    email = (String) object.get("email");
                                    App.setEmail(email);

                                    if(object.has("gender"))
                                    {
                                        gender = (String) object.getString("gender");
                                        App.setGender(gender);
                                    }

                                    if(object.has("birthday"))
                                    {
                                        birthday = (String) object.getString("birthday");
                                        App.setBirthYear(birthday);
                                    }

                                    finish();
                                    OpenMainAcitivity();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                                System.out.println("Gender : " + gender);
//                                App.setBirthYear(birthYear);
                            }
                        }
                );
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                System.out.println("cancel");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("Error");
            }

        });

        // Locate the ViewPager in viewpager_main.xml
        viewPager = (ViewPager) findViewById(R.id.pager);
        // Pass results to ViewPagerAdapter Class
        adapter = new CustomPagerAdapter(FBlogin.this, txtArray, images);
        // Binds the Adapter to the ViewPager
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                dotSelect1.setVisibility(View.GONE);
                dotSelect2.setVisibility(View.GONE);
                dotSelect3.setVisibility(View.GONE);
                switch (position)
                {
                    case 0:
                        dotSelect1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        dotSelect2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        dotSelect3.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setHightWidthForComponant();
    }

    private void initialize()
    {
        // Main layout
        btnSignUp = (TextView) findViewById(R.id.txtSignUp);
        btnLogIn = (TextView) findViewById(R.id.txtLogIn);
        enterLayout = (LinearLayout) findViewById(R.id.enterLayout);
        dotslayout = (LinearLayout) findViewById(R.id.dotslayout);

        ViewTreeObserver vto = dotslayout.getViewTreeObserver();

        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout()
            {
                dotslayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                dotlaywidth  = dotslayout.getMeasuredWidth();
                dotlayheigth = dotslayout.getMeasuredHeight();

            }
        });

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        dotSelect1 = (ImageView) findViewById(R.id.dotSelect1);
        dotSelect2 = (ImageView) findViewById(R.id.dotSelect2);
        dotSelect3 = (ImageView) findViewById(R.id.dotSelect3);
        dotNoselect1 = (ImageView) findViewById(R.id.dotNoselect1);
        dotNoselect2 = (ImageView) findViewById(R.id.dotNoselect2);
        dotNoselect3 = (ImageView) findViewById(R.id.dotNoselect3);

        // signup layout
        backSignUp = (ImageButton) findViewById(R.id.backSignUpbtn);
        backSignUp.setOnClickListener(this);

        signupLayout = (RelativeLayout) findViewById(R.id.signupLayout);
        animLinear1 = (LinearLayout) findViewById(R.id.animPart);
        animSignUpText = (LinearLayout) findViewById(R.id.animSignUpText);

        txtName = (TextView) findViewById(R.id.txtName);
        txtPassword1 = (TextView) findViewById(R.id.textPassword1);
        txtEmail = (TextView) findViewById(R.id.txtEmail);

//        userSignUp = (TextView) findViewById(R.id.txtUserSignUp);
//        userSignUp.setOnClickListener(this);


        //login layout
        backLogIn = (ImageButton) findViewById(R.id.backLogInbtn);
        backLogIn.setOnClickListener(this);

        loginLayout = (RelativeLayout) findViewById(R.id.loginLayout);
        animLogInText = (LinearLayout) findViewById(R.id.animLogInText);
        animLinear2 = (LinearLayout) findViewById(R.id.animPart2);
        txtLogInEmail = (TextView) findViewById(R.id.txtLogInEmail);
        txtLogInPassword = (TextView) findViewById(R.id.txtLogInPassword);

//        userLogin = (TextView) findViewById(R.id.txtUserLogIn);
//        userLogin.setOnClickListener(this);

        txtArray = new String[]{"This is Test", "This is Test2", "This is Test3"};
        images = new int[]{R.drawable.login_b, R.drawable.login_c, R.drawable.login_d};

        btnFBLogin = (LoginButton) findViewById(R.id.btnFBlogin);
        btnFBLogin.setReadPermissions("email");

        blackScreen =(ImageView)findViewById(R.id.blackScreen);



        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        loginLinearLayout = (LinearLayout)findViewById(R.id.loginLinearLayout);
    }

    protected void getDeviceDimen()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
    }

    protected void setHightWidthForComponant()

    {
        int mB = ((dHeight*50)/1920);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) dotslayout.getLayoutParams();
        lp.setMargins(0,0,0,mB);
        dotslayout.setLayoutParams(lp);

        int wD = ((dWidth * 40) / 1080);
        int hD = ((dHeight * 40) / 1920);
        FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(wD,hD);
        dotSelect1.setLayoutParams(lp2);
        dotSelect2.setLayoutParams(lp2);
        dotSelect3.setLayoutParams(lp2);
        dotNoselect1.setLayoutParams(lp2);
        dotNoselect2.setLayoutParams(lp2);
        dotNoselect3.setLayoutParams(lp2);


        int pB = ((dHeight * 50) / 1920);
        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        enterLayout.setPadding(pL,0,pR,pB);

        int tS = ((dWidth * 60) / 1080);
        btnSignUp.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(btnSignUp.getLayoutParams().width,hD);
        btnSignUp.setLayoutParams(lp);
        int mT = ((dHeight*20)/1920);
        btnLogIn.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        lp = new LinearLayout.LayoutParams(btnLogIn.getLayoutParams().width,hD);
        lp.setMargins(0,mT,0,0);
        btnLogIn.setLayoutParams(lp);

        System.out.println(">> FBlogin.java >>>>> FBLogin >>>>> setHightWidthForComponant >>>>> ");

        mT = ((dHeight*50)/1920);
        int mL = ((dWidth * 50) / 1080);

//        lp = (LinearLayout.LayoutParams) animSignUpText.getLayoutParams();
//        lp.setMargins(mL,mT,0,0);
//        animSignUpText.setLayoutParams(lp);




//        <LinearLayout
//        android:id="@+id/animSignUpText"
//        android:orientation="horizontal"
//        android:layout_width="match_parent"
//        android:paddingLeft="50px"
//        android:paddingTop="50px"
//        android:layout_height="wrap_content"
//        android:visibility="gone">
//
//        <ImageButton
//        android:layout_marginTop="20px"
//        android:layout_width="70px"
//        android:layout_height="50px"
//        android:id="@+id/backSignUpbtn"
//        android:background="@drawable/btnback"/>
//
//        <TextView
//        android:text="SIGN UP"
//        android:textSize="60px"
//        android:id="@+id/textView6"
//        android:layout_marginLeft="100px"
//        android:layout_width="wrap_content"
//        android:layout_height="wrap_content"
//        android:textAppearance="?android:attr/textAppearanceMedium"
//        android:textColor="@color/cardview_light_background" />
//        </LinearLayout>




        pL = ((dWidth * 50) / 1080);
        int pT = ((dHeight * 50) / 1920);
        animSignUpText.setPadding(pL,pT,0,0);


        mT = ((dHeight* 20)/1920);
        wD = ((dWidth * 70) / 1080);
        hD = ((dHeight * 50) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(0,mT,0,0);
        backSignUp.setLayoutParams(lp);

         pT = ((dHeight * 250) / 1920);
        pL = ((dWidth * 70) / 1080);
        pR = ((dWidth * 70) / 1080);
        animLinear1.setPadding(pL,pT,pR,0);

        wD = ((dWidth * 50) / 1080);
        hD = ((dHeight * 50) / 1920);
        mT = ((dHeight * 40) / 1920);

        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(wD,hD);
        lp1.setMargins(0,mT,0,0);
        imageView1.setLayoutParams(lp1);

        mT = ((dHeight * 85) / 1920);
        lp1 = new RelativeLayout.LayoutParams(wD,hD);
        lp1.setMargins(0,mT,0,0);
        imageView2.setLayoutParams(lp1);

        mT = ((dHeight * 80) / 1920);
        lp1 = new RelativeLayout.LayoutParams(wD,hD);
        lp1.setMargins(0,mT,0,0);
        imageView3.setLayoutParams(lp1);

        tS = ((dWidth * 50) / 1080);
        txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        hD = ((dHeight * 120) / 1920);
        mL = ((dWidth * 70) / 1080);
        lp1 = new RelativeLayout.LayoutParams(txtName.getLayoutParams().width,hD);
        lp1.setMargins(mL,0,0,0);
        txtName.setLayoutParams(lp1);

        mT = ((dHeight * 50) / 1920);
        lp1 = new RelativeLayout.LayoutParams(txtEmail.getLayoutParams().width,hD);
        lp1.setMargins(mL,mT,0,0);
        txtEmail.setLayoutParams(lp1);
        txtEmail.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        lp1 = new RelativeLayout.LayoutParams(txtPassword1.getLayoutParams().width,hD);
        lp1.setMargins(mL,mT,0,0);
        txtPassword1.setLayoutParams(lp1);
        txtPassword1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        pL = ((dWidth * 50) / 1080);
        pT = ((dHeight * 50) / 1920);
        loginLinearLayout.setPadding(pL,pT,0,0);


        mT = ((dHeight* 20)/1920);
        wD = ((dWidth * 70) / 1080);
        hD = ((dHeight * 50) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(0,mT,0,0);
        backLogIn.setLayoutParams(lp);

        pT = ((dHeight * 40) / 1920);
        pB = ((dHeight * 40) / 1920);
        mL = ((dWidth * 70) / 1080);
        mT = ((dHeight * 150) / 1920);
        int mR = ((dWidth * 70) / 1080);
        tS = ((dWidth * 60) / 1080);
        btnFBLogin.setPadding(0,pT,0,pB);
        btnFBLogin.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        lp = (LinearLayout.LayoutParams) btnFBLogin.getLayoutParams();
        lp.setMargins(mL,mT,mR,0);
        btnFBLogin.setLayoutParams(lp);


        pT = ((dHeight * 100) / 1920);
        pL = ((dWidth * 70) / 1080);
        pR = ((dWidth * 70) / 1080);
        animLinear2.setPadding(pL,pT,pR,0);


        wD = ((dWidth * 50) / 1080);
        hD = ((dHeight * 50) / 1920);
        mT = ((dHeight * 40) / 1920);

        lp1 = new RelativeLayout.LayoutParams(wD,hD);
        lp1.setMargins(0,mT,0,0);
        imageView4.setLayoutParams(lp1);

        mT = ((dHeight * 80) / 1920);
        lp1 = new RelativeLayout.LayoutParams(wD,hD);
        lp1.setMargins(0,mT,0,0);
        imageView5.setLayoutParams(lp1);

        tS = ((dWidth * 50) / 1080);
        txtLogInEmail.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        txtLogInPassword.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        mL = ((dWidth * 70) / 1080);
        hD = ((dHeight * 120) / 1920);
        lp1 = new RelativeLayout.LayoutParams(txtLogInEmail.getLayoutParams().width,hD);
        lp1.setMargins(mL,0,0,0);
        txtLogInEmail.setLayoutParams(lp1);

        mT = ((dHeight * 50) / 1920);
        lp1 = new RelativeLayout.LayoutParams(txtLogInPassword.getLayoutParams().width,hD);
        lp1.setMargins(mL,mT,0,0);
        txtLogInPassword.setLayoutParams(lp1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.txtSignUp:
                showSignUpView();
                break;
            case R.id.txtLogIn:
                showLogInView();
                break;
            case R.id.backSignUpbtn:
                showLoginScreenView();
                break;
            case R.id.backLogInbtn:
                showLoginScreenFromLoginView();
                break;
//            case R.id.txtUserSignUp:
//                onSignUpUser();
//                break;
//            case R.id.txtUserLogIn:
//                onSignInUser();
//                break;
        }
    }

    @Override
    public void onBackPressed()
    {
//   ++     if (enterLayout.getVisibility() == View.VISIBLE)
//            finish();
//        else
//            showLoginScreenView();

    }


    private void onSignUpUser()
    {
        String name, email, password;

        name = txtName.getText().toString().trim();
        password = txtPassword1.getText().toString().trim();
        email = txtEmail.getText().toString().trim();

        if(name.length()>0) {
            if (email.length() > 0) {
                if (email.matches(emailPattern)) {
                    if (password.length() > 0) {
                        ServiceManager.userRegistration(handler, name, email, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "Password can't be null", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Email address can't be null", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Name can't be null", Toast.LENGTH_SHORT).show();

    }

    private void onSignInUser()
    {
        String email, password;

        email = txtLogInEmail.getText().toString().trim();
        password = txtLogInPassword.getText().toString().trim();

        if(email.length()>0){

            if (email.matches(emailPattern)) {
                if(password.length()>0) {
                    ServiceManager.autoLogin(handler, email, password);
                } else {
                    Toast.makeText(getApplicationContext(), "Password can't be null", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Email address can't be null", Toast.LENGTH_SHORT).show();
        }

    }

    private void initHandler()
    {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == Constant.SERVICE_SIGNUP_RESPONSE)
                {
                   String data = message.obj.toString();
                    try {
                        JSONObject jsObj = new JSONObject(data);

                        Integer success = jsObj.getInt("success");
                        String msg = jsObj.getString("message");


                        if(success == 1)
                        {
                            JSONObject userList = jsObj.getJSONObject("userList");
                            String userId = userList.getString("user_id");
                            String name = userList.getString("name");
                            String email = userList.getString("emailid");

                            App.setUserId(userId);
                            System.out.println(">> FBlogin.java >>>>> FBLogin >>>>> handleMessage >>>>> "+ userId);
                            App.setName(name);
                            App.setEmail(email);

                            finish();
                            OpenMainAcitivity();

                            Toast.makeText(getApplicationContext(), "Signup success", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Signup failed, Please try later!!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                else if (message.what == Constant.SERVICE_LOGIN_RESPONSE)
                {
                    String data = message.obj.toString();
                    try {
                        JSONObject jsObj = new JSONObject(data);

                        Integer success = jsObj.getInt("success");
                        String msg = jsObj.getString("message");

                        if(success == 1)
                        {
                            JSONObject userList = jsObj.getJSONObject("userList");
                            String userId = userList.getString("user_id");
                            String name = userList.getString("name");
                            String email = userList.getString("emailid");
                            String gender = userList.getString("gender");
                            String birthYear = userList.getString("bod");

                            JSONObject categoryList = jsObj.getJSONObject("categoryList");
                            App.seCategory(categoryList.toString());

                            App.setUserId(userId);
                            System.out.println(">> FBlogin.java >>>>> FBLogin >>>>> handleMessage >>>>> "+ userId);
                            App.setName(name);
                            App.setEmail(email);
                            App.setGender(gender);
                            App.setBirthYear(birthYear);


                            finish();
                            OpenMainAcitivity();

                            Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Login failed, Please check login details!!", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    private void OpenMainAcitivity()
    {
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
    }


    private void showSignUpView()
    {
        if(isSignUpViewOpen == false) {
            clearTextField();
            isSignUpViewOpen = true;
            dotslayout.setVisibility(View.INVISIBLE);
            btnLogIn.setVisibility(View.INVISIBLE);
            blackScreen.setVisibility(View.VISIBLE);

            hideDetailText = true;
            adapter = new CustomPagerAdapter(FBlogin.this, txtArray, images);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(current);

            viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

            TranslateAnimation  trAnim = new TranslateAnimation(0, 0, 0, ((dHeight*-600)/1920));
            trAnim.setDuration(1000);
            btnSignUp.startAnimation(trAnim);

            trAnim.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    animSignUpText.setVisibility(View.VISIBLE);
                    animLinear1.setVisibility(View.VISIBLE);

                    TranslateAnimation trAnim = new TranslateAnimation(0, 0, -100, 0);
                    trAnim.setDuration(500);
                    trAnim.setFillAfter(true);
                    animSignUpText.startAnimation(trAnim);

                    System.out.println(">>>>>>>>>>>>>>>>>>>> onAnimationEnd2 : " + animSignUpText.getY());

                    AlphaAnimation trAnim1 = new AlphaAnimation(0, 1);
                    trAnim1.setDuration(1500);
                    trAnim1.setFillAfter(true);
                    animLinear1.startAnimation(trAnim1);

//                    LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(200,100);
//                    parm.setMargins(0, ((dHeight*-300)/1920), 0, 0);
//                    btnSignUp.setLayoutParams(parm);

                    btnSignUp.setVisibility(View.GONE);

                   final Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            btnSignUp.setVisibility(View.VISIBLE);

                            LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(btnSignUp.getWidth(),btnSignUp.getHeight());
                            parm.setMargins(0, ((dHeight*-750)/1920), 0, 0);
                            btnSignUp.setLayoutParams(parm);
                        }
                    }, 1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        else
        {
            onSignUpUser();
        }
    }

    private void showLogInView()
    {
        if(isLogInViewOpen == false)
        {
            System.out.println(">> FBlogin.java >>>>> FBLogin >>>>> showLogInView >>>>> "+ isLogInViewOpen);
            //hideText
            clearTextField();
            isLogInViewOpen = true;
            hideDetailText = true;
            adapter = new CustomPagerAdapter(FBlogin.this, txtArray, images);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(current);

            viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return true;
                }
            });

            final Animation trAnim = new TranslateAnimation(0, 0, 0, ((dHeight*-770)/1920));
            trAnim.setDuration(1000);
            btnLogIn.startAnimation(trAnim);

            trAnim.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animLogInText.setVisibility(View.VISIBLE);
                    animLinear2.setVisibility(View.VISIBLE);

                    TranslateAnimation trAnim = new TranslateAnimation(0, 0, -600, 0);
                    trAnim.setDuration(1000);
                    trAnim.setFillAfter(true);
                    animLogInText.startAnimation(trAnim);

                    AlphaAnimation trAnim1 = new AlphaAnimation(0, 1);
                    trAnim1.setDuration(2000);
                    trAnim1.setFillAfter(true);
                    animLinear2.startAnimation(trAnim1);

                    btnLogIn.setVisibility(View.GONE);

                    final Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            btnLogIn.setVisibility(View.VISIBLE);

                            LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(btnSignUp.getWidth(),btnSignUp.getHeight());
                            parm.setMargins(0, ((dHeight*-920)/1920), 0, 0);
                            btnLogIn.setLayoutParams(parm);
                        }
                    }, 1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            dotslayout.setVisibility(View.INVISIBLE);
            btnSignUp.setVisibility(View.INVISIBLE);
            blackScreen.setVisibility(View.VISIBLE);
        }
        else
        {
            System.out.println(">> FBlogin.java >>>>> FBLogin >>>>> showLogInView >>>>> "+isLogInViewOpen);
            onSignInUser();
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void clearTextField()
    {
        txtName.setText("");
        txtEmail.setText("");
        txtPassword1.setText("");
        txtLogInEmail.setText("");
        txtLogInPassword.setText("");
    }

    private void showLoginScreenView()
    {
        closeKeyboard();
        isSignUpViewOpen = false;
        blackScreen.setVisibility(View.INVISIBLE);

        btnSignUp.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(btnSignUp.getWidth(),btnSignUp.getHeight());
        parm.setMargins(0, ((dHeight*150)/1920), 0, 0);
        btnSignUp.setLayoutParams(parm);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                btnSignUp.setVisibility(View.VISIBLE);

                Animation trAnim = new TranslateAnimation(0,0,((dHeight*-600)/1920),0);
                trAnim.setDuration(1000);
                btnSignUp.startAnimation(trAnim);

                trAnim.setAnimationListener(new Animation.AnimationListener()
                {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        dotslayout.setVisibility(View.VISIBLE);
                        btnLogIn.setVisibility(View.VISIBLE);

                        //hideText
                        hideDetailText = false;
                        adapter = new CustomPagerAdapter(FBlogin.this, txtArray, images);
                        viewPager.setAdapter(adapter);
                        viewPager.setCurrentItem(current);

                        viewPager.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return false;
                            }
                        });

                        LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(btnSignUp.getWidth(),btnSignUp.getHeight());
                        parm.setMargins(0, 0, 0, 0);
                        btnSignUp.setLayoutParams(parm);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation)
                    {

                    }
                });
            }
        }, 1);



        TranslateAnimation trAnim1 = new TranslateAnimation(0,0,0,-100);
        trAnim1.setDuration(500);
        animSignUpText.startAnimation(trAnim1);
        animSignUpText.setVisibility(View.GONE);

        AlphaAnimation trAnim2 = new AlphaAnimation(1,0);
        trAnim2.setDuration(500);
        trAnim2.setFillAfter(true);
        animLinear1.startAnimation(trAnim2);
        animLinear1.setVisibility(View.GONE);


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void closeKeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
//            inputManager.hideSoftInputFromWindow(new View(this).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void showLoginScreenFromLoginView()
    {
        closeKeyboard();
        isLogInViewOpen = false;
        blackScreen.setVisibility(View.INVISIBLE);

        btnLogIn.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(btnSignUp.getWidth(),btnSignUp.getHeight());
        parm.setMargins(0, ((dHeight*150)/1920), 0, 0);
        btnLogIn.setLayoutParams(parm);


        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {

                btnLogIn.setVisibility(View.VISIBLE);

                Animation trAnim = new TranslateAnimation(0,0,((dHeight*-770)/1920),0);
                trAnim.setDuration(1000);
                btnLogIn.startAnimation(trAnim);

                trAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        dotslayout.setVisibility(View.VISIBLE);
                        btnSignUp.setVisibility(View.VISIBLE);

                        //hideText
                        hideDetailText = false;
                        adapter = new CustomPagerAdapter(FBlogin.this, txtArray, images);
                        viewPager.setAdapter(adapter);
                        viewPager.setCurrentItem(current);

                        viewPager.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                return false;
                            }
                        });

                        LinearLayout.LayoutParams parm = new LinearLayout.LayoutParams(btnLogIn.getWidth(),btnLogIn.getHeight());
                        parm.setMargins(0, ((dHeight*20)/1920), 0, 0);
                        btnLogIn.setLayoutParams(parm);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        }, 1);


        TranslateAnimation trAnim1 = new TranslateAnimation(0,0,0,-600);
        trAnim1.setDuration(500);
        animLogInText.startAnimation(trAnim1);
        animLogInText.setVisibility(View.GONE);

        AlphaAnimation trAnim2 = new AlphaAnimation(1,0);
        trAnim2.setDuration(500);
        trAnim2.setFillAfter(true);
        animLinear2.startAnimation(trAnim2);
        animLinear2.setVisibility(View.GONE);


    }


    public class CustomPagerAdapter extends PagerAdapter
    {
        int[] images;
        Context context;
        String[] txtArray;
        LayoutInflater inflater;

        public CustomPagerAdapter(Context context, String[] txtArray, int[] images)
        {
            this.context = context;
            this.txtArray = txtArray;
            this.images = images;
        }

        @Override
        public int getCount() {
            return txtArray.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // Remove viewpager_item.xml from ViewPager
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            TextView instText;
            ImageView scrollImage;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.infopages, container,false);

            scrollImage = (ImageView) itemView.findViewById(R.id.scrollImage);
            scrollImage.setBackgroundResource(images[position]);

            instText = (TextView) itemView.findViewById(R.id.instText);
            instText.setText(txtArray[position]);
            instText.setVisibility(View.VISIBLE);

            if(hideDetailText) {
                instText.setVisibility(View.GONE);
            }

            // Add viewpager_item.xml to ViewPager
            container.addView(itemView);

            return itemView;
        }
    }
}


















































//    package com.example.mrj.omnify.ui;
//
//    import android.app.Activity;
//    import android.content.Context;
//    import android.content.Intent;
//    import android.content.SharedPreferences;
//    import android.graphics.Point;
//    import android.os.Bundle;
//    import android.os.Message;
//    import android.support.v4.widget.DrawerLayout;
//    import android.util.Log;
//    import android.util.TypedValue;
//    import android.view.Display;
//    import android.view.Gravity;
//    import android.view.View;
//    import android.widget.Button;
//    import android.widget.ImageView;
//    import android.widget.LinearLayout;
//    import android.widget.TextView;
//
//    import com.example.mrj.omnify.R;
//    import com.facebook.AccessToken;
//    import com.facebook.CallbackManager;
//    import com.facebook.FacebookCallback;
//    import com.facebook.FacebookException;
//    import com.facebook.FacebookSdk;
//    import com.facebook.GraphRequest;
//    import com.facebook.GraphResponse;
//    import com.facebook.Profile;
//    import com.facebook.login.LoginResult;
//    import com.facebook.login.widget.LoginButton;
//
//    import org.json.JSONException;
//    import org.json.JSONObject;
//
//    import java.util.Arrays;
//
//    /**
//     * Created by MR.J on 9/3/2016.
//     */
//    public class FBLogin extends Activity implements View.OnClickListener
//    {
//        Button btnSkip;
//        int dWidth,dHeight;
//        TextView txtName,txtSurname,txtEmail, txtSignUp, txtOptional;
//        ImageView omnifyLogo;
//        LinearLayout linlayout1;
//
//        TextView[] textViewArray = new TextView[4];
//
//        LoginButton  btnFBLogin;
//        CallbackManager callbackManager;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            FacebookSdk.sdkInitialize(getApplicationContext());
//            setContentView(R.layout.fb_signup);
//
//            txtName = (TextView)findViewById(R.id.txtName);
//            txtSurname = (TextView)findViewById(R.id.txtSurname);
//            txtEmail = (TextView)findViewById(R.id.txtEmail);
//            btnSkip = (Button)findViewById(R.id.btnSkip);
//
//            // for layout design
//            omnifyLogo = (ImageView)findViewById(R.id.omnifyLogo);
//            linlayout1 = (LinearLayout) findViewById(R.id.linlayout);
//            textViewArray[0] = (TextView)findViewById(R.id.textView3);
//            textViewArray[1] = (TextView)findViewById(R.id.textView4);
//            textViewArray[2] = (TextView)findViewById(R.id.textView5);
//            textViewArray[3] = (TextView)findViewById(R.id.textView6);
//            txtSignUp = (TextView)findViewById(R.id.textView);
//            txtOptional = (TextView)findViewById(R.id.textView2);
//
//            callbackManager = CallbackManager.Factory.create();
//            btnSkip.setOnClickListener(this);
//
//            btnFBLogin = (LoginButton)findViewById(R.id.btnFBlogin);
//            btnFBLogin.setReadPermissions("email");
//            btnFBLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
//            {
//                @Override
//                public void onSuccess(LoginResult loginResult)
//                {
//                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),new GraphRequest.GraphJSONObjectCallback()
//                            {
//                                @Override
//                                public void onCompleted(
//                                        JSONObject object,
//                                        GraphResponse response) {
//                                    String email = "" ,name="", fname = "", lname = "";
//                                    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
//                                    SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                                    Profile profile = Profile.getCurrentProfile();
//                                    try {
//                                        name = (String) object.get("name");
//                                        email = (String) object.get("email");
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//
//                                    txtName.setText(name);
////                                txtSurname.setText(Profile.getCurrentProfile().getLastName());
//                                    txtEmail.setText(email);
//
//                                    if(!sharedPreferences.contains("FirstName")){
//                                        editor.putString("FirstName",name);
//                                    }
////                                if(!sharedPreferences.contains("LastName")){
////                                    editor.putString("LastName",Profile.getCurrentProfile().getLastName());
////                                }
//                                    if(!sharedPreferences.contains("Email")){
//                                        editor.putString("Email",email);
//                                    }
//                                    editor.commit();
//                                }
//                            }
//                    );
//                    Bundle parameters = new Bundle();
//                    parameters.putString("fields", "id,name,email,gender,birthday");
//                    request.setParameters(parameters);
//                    request.executeAsync();
//                }
//
//                @Override
//                public void onCancel() {
//                    System.out.println("cancel");
//                }
//
//                @Override
//                public void onError(FacebookException error) {
//                    System.out.println("Error");
//                }
//
//            });
//
//            setPreferences();
//            getDeviceDimen();
//            setHightWidthForComponant();
//        }
//
//        protected void getDeviceDimen()
//        {
//            Display display = getWindowManager().getDefaultDisplay();
//            Point size = new Point();
//            display.getSize(size);
//            dWidth = size.x;
//            dHeight = size.y;
//        }
//
//        protected void setHightWidthForComponant()
//        {
//            int wD = ((dWidth * 650) / 1080);
//            int hD = ((dHeight * 250) / 1920);
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wD,hD);
//            omnifyLogo.setLayoutParams(lp);
//
//            int tP = ((dHeight * 300) / 1920);
//            linlayout1.setPadding(0,tP,0,0);
//
//            int tS = ((dWidth * 65) / (1080));
//            for(int i = 0; i < 4; i++) {
//                textViewArray[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
//            }
//
//            tS = ((dWidth * 80) / (1080));
//            txtSignUp.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
//
//            tS = ((dWidth * 55) / (1080));
//            txtOptional.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
//
//            wD = ((dWidth * 250) / 1080);
//            hD = ((dHeight * 150) / 1920);
//            lp = new LinearLayout.LayoutParams(wD,hD);
//            lp.gravity = Gravity.RIGHT;
//            btnSkip.setLayoutParams(lp);
//        }
//
//        protected void setPreferences()
//        {
//
//        }
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            callbackManager.onActivityResult(requestCode, resultCode, data);
//        }
//
//
//        @Override
//        public void onClick(View view)
//        {
//            switch (view.getId()) {
//                case R.id.btnSkip:
//                    finishActivityStartScan();
//                    break;
//            }
//        }
//
//        protected void finishActivityStartScan()
//        {
//            finish();
//            Message msg = new Message();
//            msg.what = 002;
//
//            if (MainActivity.handler != null) {
//                MainActivity.handler.sendMessage(msg);
//            }
//        }

