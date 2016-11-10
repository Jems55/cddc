package com.example.mrj.omnify.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrj.omnify.App;
import com.example.mrj.omnify.R;
import com.example.mrj.omnify.service.ServiceManager;
import com.example.mrj.omnify.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by MR.J on 9/10/2016.
 */
public class Settings extends Activity implements View.OnClickListener
{
    int dWidth,dHeight;

    LinearLayout _linearLayout, _mainlayout, _linear1lay1,_linear1lay2,_linear2lay1,_linear3lay1, _linear1lay3 , _linear2lay2, _linear2lay3, _linear2lay4, _linear2lay5, _linear3lay2, _linear3lay3, _linearlay0;
    TextView _textTitleSettings, _textViewNeeds, _textGender, _textOptional, _textYearBirth, _textSelectAll, _textDotDisturb;
    TextView _txtProfile,_txtCategory,_txtGeneral, _textOptional2, txtSignUp,txtOptional, textView3, textView5, txtName, txtEmail, _textPrivacyPolicy;
    ImageView _imageView1,_imageView2,_imageView3,_imageView4, _image11Category, _image21Category, _image31Category, _image41Category, _image51Category, _image61Category,
            _image1Category,_image2Category,_image3Category,_image4Category,_image5Category,_image6Category, _omnifyLogo, _imageView00;
    EditText _editText2;
    FrameLayout maleSelector, femaleSelector, _frameLayout1,_frameLayout2,_frameLayout3,_frameLayout4,_frameLayout5,_frameLayout6;

    static Handler handler;
    String gender, yearStr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_screen);

        _textTitleSettings = (TextView) findViewById(R.id.textTitleSettings);

        _linearLayout = (LinearLayout)findViewById(R.id.linearLayout);

        _imageView00 = (ImageView)findViewById(R.id.imageView00);
        _imageView00.setOnClickListener(this);

        _mainlayout = (LinearLayout)findViewById(R.id.mainlayout);

        _linear1lay1 = (LinearLayout)findViewById(R.id.linear1lay1);
        _textViewNeeds = (TextView) findViewById(R.id.textViewNeeds);

        _linear1lay2 = (LinearLayout)findViewById(R.id.linear1lay2);
        _textGender = (TextView) findViewById(R.id.textGender);
        _textOptional = (TextView) findViewById(R.id.textOptional);

        _linear1lay3 = (LinearLayout)findViewById(R.id.linear1lay3);
        _textYearBirth = (TextView) findViewById(R.id.textYearBirth);
        _textOptional2 = (TextView) findViewById(R.id.textOptional2);
        _editText2 = (EditText) findViewById(R.id.editText2);
        _editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                String newYear = editable.toString();

                if (editable.length() == 4)
                {
                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);

                    if((Integer.parseInt(newYear) < 1920) || (Integer.parseInt(newYear) > year))
                    {
                        _editText2.setText("");
                        Toast.makeText(getApplicationContext(),"Please provide your birth year",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        yearStr = newYear;
                    }
                }
            }
        });

        _txtProfile = (TextView)findViewById(R.id.txtProfile);
        _txtCategory = (TextView)findViewById(R.id.txtCategories);
        _txtGeneral = (TextView)findViewById(R.id.txtGeneral);

        _linear2lay1 = (LinearLayout)findViewById(R.id.linear2lay1);
        _linear2lay2  = (LinearLayout)findViewById(R.id.linear2lay2);
        _linear2lay3 = (LinearLayout)findViewById(R.id.linear2lay3);
        _linear2lay4 = (LinearLayout)findViewById(R.id.linear2lay4);
        _linear2lay5 = (LinearLayout)findViewById(R.id.linear2lay5);

        _image1Category = (ImageView)findViewById(R.id.image1Category);
        _image2Category = (ImageView)findViewById(R.id.image2Category);
        _image3Category = (ImageView)findViewById(R.id.image3Category);
        _image4Category = (ImageView)findViewById(R.id.image4Category);
        _image5Category = (ImageView)findViewById(R.id.image5Category);
        _image6Category = (ImageView)findViewById(R.id.image6Category);

        _image11Category = (ImageView)findViewById(R.id.image11Category);
        _image21Category = (ImageView)findViewById(R.id.image21Category);
        _image31Category = (ImageView)findViewById(R.id.image31Category);
        _image41Category = (ImageView)findViewById(R.id.image41Category);
        _image51Category = (ImageView)findViewById(R.id.image51Category);
        _image61Category = (ImageView)findViewById(R.id.image61Category);

        _frameLayout1 = (FrameLayout)findViewById(R.id.frameLayout1);
        _frameLayout2 = (FrameLayout)findViewById(R.id.frameLayout2);
        _frameLayout3 = (FrameLayout)findViewById(R.id.frameLayout3);
        _frameLayout4 = (FrameLayout)findViewById(R.id.frameLayout4);
        _frameLayout5 = (FrameLayout)findViewById(R.id.frameLayout5);
        _frameLayout6 = (FrameLayout)findViewById(R.id.frameLayout6);

        _frameLayout1.setOnClickListener(this);
        _frameLayout2.setOnClickListener(this);
        _frameLayout3.setOnClickListener(this);
        _frameLayout4.setOnClickListener(this);
        _frameLayout5.setOnClickListener(this);
        _frameLayout6.setOnClickListener(this);

        _textSelectAll = (TextView)findViewById(R.id.textSelectAll);
        _textSelectAll.setOnClickListener(this);

        _textDotDisturb = (TextView)findViewById(R.id.textDotDisturb);
        _textDotDisturb.setOnClickListener(this);

        _linear3lay1 = (LinearLayout)findViewById(R.id.linear3lay1);

        _imageView1 = (ImageView)findViewById(R.id.imageView1);
        _imageView2 = (ImageView)findViewById(R.id.imageView2);
        _imageView3 = (ImageView)findViewById(R.id.imageView3);
        _imageView4 = (ImageView)findViewById(R.id.imageView4);

        _linear3lay2 = (LinearLayout)findViewById(R.id.linear3lay2);
        _linear3lay3 = (LinearLayout)findViewById(R.id.linear3lay3);

        maleSelector = (FrameLayout)findViewById(R.id.maleSelector);
        femaleSelector = (FrameLayout)findViewById(R.id.femaleSelector);

        maleSelector.setOnClickListener(this);
        femaleSelector.setOnClickListener(this);

        txtSignUp = (TextView)findViewById(R.id.txtSignUp);
        txtOptional = (TextView)findViewById(R.id.txtOptional);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView5 = (TextView)findViewById(R.id.textView5);
        txtName = (TextView)findViewById(R.id.txtName);
        txtEmail = (TextView)findViewById(R.id.txtEmail);

        _linearlay0 = (LinearLayout)findViewById(R.id.linearlay0);

        _textPrivacyPolicy = (TextView)findViewById(R.id.textPrivacyPolicy);
        _omnifyLogo = (ImageView)findViewById(R.id.omnifyLogo);

        getDeviceDimen();
        setHightWidthForComponant();

        _txtProfile.setOnClickListener(this);
        _txtCategory.setOnClickListener(this);
        _txtGeneral.setOnClickListener(this);

        initHandler();
        onSelectProfile();
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
        int mT, tP = 0, tS, lP = 0;

        lP = ((dWidth * 50) / 1080);
        tP = ((dHeight * 50) / 1920);
        _linearLayout.setPadding(lP,tP,0,0);

        int wD = ((dWidth * 70) / 1080);
        int hD = ((dHeight * 60) / 1920);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wD,hD);
        _imageView00.setLayoutParams(lp);

        mT = ((dHeight * 50)/1920);
        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) _mainlayout.getLayoutParams();
        lp1.topMargin = mT;

        tS = ((dWidth * 60)/1080);
        int pB = ((dHeight * 30) / 1920);
        _textTitleSettings.setPadding(0,0,0,pB);
        _textTitleSettings.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        hD = ((dHeight * 850) / 1920);
        lp1 = new LinearLayout.LayoutParams(_textTitleSettings.getLayoutParams().width,hD);

        tP = ((dHeight * 100)/1920);
        _linear1lay1.setPadding(0,tP,0,0);
        _linear1lay2.setPadding(0,tP,0,0);
        tS = ((dWidth * 50)/1080);
        _textViewNeeds.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        tS = ((dWidth * 70)/1080);
        _textGender.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        tS = ((dWidth * 50)/1080);
        _textOptional.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        wD = ((dWidth * 250) / 1080);
        hD = ((dHeight * 100) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        _txtProfile.setLayoutParams(lp);
        _txtGeneral.setLayoutParams(lp);

        wD = ((dWidth * 300) / 1080);
        hD = ((dHeight * 100) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        _txtCategory.setLayoutParams(lp);

        tS = ((dWidth * 50)/1080);
        _txtProfile.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        _txtCategory.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        _txtGeneral.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        wD = ((dWidth * 200) / 1080);
        hD = ((dHeight * 400) / 1920);
        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(wD,hD);
        _imageView1.setLayoutParams(flp);
        _imageView2.setLayoutParams(flp);

        int mL = ((dWidth * 200) / 1080);
        lp = (LinearLayout.LayoutParams) maleSelector.getLayoutParams();
        lp.setMargins(mL,0,0,0);
        maleSelector.setLayoutParams(lp);

        wD = ((dWidth * 150) / 1080);
        hD = ((dHeight * 400) / 1920);
        flp = new FrameLayout.LayoutParams(wD,hD);
        _imageView3.setLayoutParams(flp);
        _imageView4.setLayoutParams(flp);

        _linearlay0.setPadding(0,50,0,0);

        tP = ((dHeight * 100)/1920);
        _linear1lay3.setPadding(0,tP,0,0);
        tS = ((dWidth * 80)/1080);
        _textYearBirth.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        tS = ((dWidth * 50)/1080);
        _textOptional2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        tS = ((dWidth * 80)/1080);
        _editText2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);


        // 2nd Tab
        mT = ((dHeight * 100)/1920);
        LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) _linear2lay2.getLayoutParams();
        lp2.topMargin = mT;

        lp2 = (LinearLayout.LayoutParams) _linear2lay3.getLayoutParams();
        lp2.topMargin = mT;

        lp2 = (LinearLayout.LayoutParams) _linear2lay4.getLayoutParams();
        lp2.topMargin = mT;

        wD = ((dWidth * 320) / 1080);
        hD = wD;
        flp = new FrameLayout.LayoutParams(wD,hD);

        _image1Category.setLayoutParams(flp);
        _image2Category.setLayoutParams(flp);
        _image3Category.setLayoutParams(flp);
        _image4Category.setLayoutParams(flp);
        _image5Category.setLayoutParams(flp);
        _image6Category.setLayoutParams(flp);

        _image11Category.setLayoutParams(flp);
        _image21Category.setLayoutParams(flp);
        _image31Category.setLayoutParams(flp);
        _image41Category.setLayoutParams(flp);
        _image51Category.setLayoutParams(flp);
        _image61Category.setLayoutParams(flp);



        mL = ((dWidth * 80)/1080);
        flp = (FrameLayout.LayoutParams) _image2Category.getLayoutParams();
        flp.leftMargin = mL;

        flp = (FrameLayout.LayoutParams) _image21Category.getLayoutParams();
        flp.leftMargin = mL;

        flp = (FrameLayout.LayoutParams) _image4Category.getLayoutParams();
        flp.leftMargin = mL;

        flp = (FrameLayout.LayoutParams) _image41Category.getLayoutParams();
        flp.leftMargin = mL;

        flp = (FrameLayout.LayoutParams) _image6Category.getLayoutParams();
        flp.leftMargin = mL;

        flp = (FrameLayout.LayoutParams) _image61Category.getLayoutParams();
        flp.leftMargin = mL;

        mT = ((dHeight * 120)/1920);
        lp2 = (LinearLayout.LayoutParams) _linear2lay5.getLayoutParams();
        lp2.topMargin = mT;

        tS = ((dWidth * 50)/1080);
        _textSelectAll.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        mL = ((dWidth * 120)/1080);
        lp2 = (LinearLayout.LayoutParams) _textSelectAll.getLayoutParams();
        lp2.leftMargin = mL;

        tS = ((dWidth * 50)/1080);
        _textDotDisturb.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        mL = ((dWidth * 80)/1080);
        lp2 = (LinearLayout.LayoutParams) _textDotDisturb.getLayoutParams();
        lp2.leftMargin = mL;


        // 3rd
        tP = ((dHeight * 70)/1920);
        int tL = ((dWidth * 70)/1080);;
        int tR = ((dWidth * 70)/1080);;
        _linear3lay2.setPadding(tL,tP,tR,0);

        tS = ((dWidth * 70)/1080);
        txtSignUp.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 40)/1080);
        txtOptional.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 50)/1080);
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        textView5.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        txtEmail.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tP = ((dHeight * 300)/1920);
        _linear3lay3.setPadding(0,tP,0,0);
        _textPrivacyPolicy.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        wD = ((dWidth * 650) / 1080);
        hD = ((dHeight * 250) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        _omnifyLogo.setLayoutParams(lp);
    }

    @Override
    public void onClick(View view)
    {
        System.out.println(">> Settings.java >>>>> Settings >>>>> onClick >>>>> "+ view.getId());
        switch (view.getId())
        {
            case R.id.txtProfile:
                onSelectProfile();
                break;
            case R.id.txtCategories:
                onSelectCategory();
                break;
            case R.id.txtGeneral:
                onSelectGeneral();
                break;
            case R.id.femaleSelector:
                onSelectDeselectFemale();
                break;
            case R.id.maleSelector:
                onSelectDeselectMale();
                break;
            case R.id.textSelectAll:
                onClickTextSelectAll();
                break;
            case R.id.textDotDisturb:
                onClickTextDotDisturb();
                break;
            case R.id.frameLayout1:
                onClickImage1();
                break;
            case R.id.frameLayout2:
                onClickImage2();
                break;
            case R.id.frameLayout3:
                onClickImage3();
                break;
            case R.id.frameLayout4:
                onClickImage4();
                break;
            case R.id.frameLayout5:
                onClickImage5();
                break;
            case R.id.frameLayout6:
                onClickImage6();
                break;
            case R.id.imageView00:
                onSettingsChange();
                break;
        }
    }

    private void onClickImage1()
    {
        if(_image1Category.getVisibility() == View.VISIBLE)
        {
            System.out.println(">> Settings.java >>>>> Settings >>>>> onClickImage1 >>>>> ");
            _image1Category.setVisibility(View.GONE);
            _image11Category.setVisibility(View.VISIBLE);
        }
        else if(_image11Category.getVisibility() == View.VISIBLE)
        {
            System.out.println(">> Settings.java >>>>> Settings >>>>> onClickImage1 >>>>> ");
            _image11Category.setVisibility(View.GONE);
            _image1Category.setVisibility(View.VISIBLE);
        }

        getCategoryData();
    }

    String selectedCat;

    private void getCategoryData()
    {
        String category = "";
        char first = 0;

        if(_image1Category.getVisibility() == View.VISIBLE) {
            category = category + "," + "c1";
        }
        if(_image2Category.getVisibility() == View.VISIBLE) {
            category = category + "," + "c2";
        }
        if(_image3Category.getVisibility() == View.VISIBLE) {
            category = category + "," + "c3";
        }
        if(_image4Category.getVisibility() == View.VISIBLE) {
            category = category + "," + "c4";
        }
        if(_image5Category.getVisibility() == View.VISIBLE){
            category = category + "," + "c5";
        }
        if(_image6Category.getVisibility() == View.VISIBLE) {
            category = category + "," + "c6";
        }


        if(!category.equals("")) {
            first = category.charAt(0);
        }

        String substr = "";

        if(first == ','){
            substr = category.substring(1,category.length());}


        selectedCat = substr;
    }

    private void onClickImage2()
    {
        if(_image2Category.getVisibility() == View.VISIBLE)
        {
            _image2Category.setVisibility(View.GONE);
            _image21Category.setVisibility(View.VISIBLE);
        }
        else if(_image21Category.getVisibility() == View.VISIBLE)
        {
            _image21Category.setVisibility(View.GONE);
            _image2Category.setVisibility(View.VISIBLE);
        }

        getCategoryData();
    }

    private void onClickImage3()
    {
        if(_image3Category.getVisibility() == View.VISIBLE)
        {
            _image3Category.setVisibility(View.GONE);
            _image31Category.setVisibility(View.VISIBLE);
        }
        else if(_image31Category.getVisibility() == View.VISIBLE)
        {
            _image31Category.setVisibility(View.GONE);
            _image3Category.setVisibility(View.VISIBLE);
        }

        getCategoryData();
    }

    private void onClickImage4()
    {
        if(_image4Category.getVisibility() == View.VISIBLE)
        {
            _image4Category.setVisibility(View.GONE);
            _image41Category.setVisibility(View.VISIBLE);
        }
        else if(_image41Category.getVisibility() == View.VISIBLE)
        {
            _image41Category.setVisibility(View.GONE);
            _image4Category.setVisibility(View.VISIBLE);
        }

        getCategoryData();
    }

    private void onClickImage5()
    {
        if(_image5Category.getVisibility() == View.VISIBLE)
        {
            _image5Category.setVisibility(View.GONE);
            _image51Category.setVisibility(View.VISIBLE);
        }
        else if(_image51Category.getVisibility() == View.VISIBLE)
        {
            _image51Category.setVisibility(View.GONE);
            _image5Category.setVisibility(View.VISIBLE);
        }

        getCategoryData();
    }

    private void onClickImage6()
    {
        if(_image6Category.getVisibility() == View.VISIBLE)
        {
            _image6Category.setVisibility(View.GONE);
            _image61Category.setVisibility(View.VISIBLE);
        }
        else if(_image61Category.getVisibility() == View.VISIBLE)
        {
            _image61Category.setVisibility(View.GONE);
            _image6Category.setVisibility(View.VISIBLE);
        }

        getCategoryData();
    }

    private void onClickTextDotDisturb()
    {
        _image1Category.setVisibility(View.GONE);
        _image2Category.setVisibility(View.GONE);
        _image3Category.setVisibility(View.GONE);
        _image4Category.setVisibility(View.GONE);
        _image5Category.setVisibility(View.GONE);
        _image6Category.setVisibility(View.GONE);

        _image11Category.setVisibility(View.VISIBLE);
        _image21Category.setVisibility(View.VISIBLE);
        _image31Category.setVisibility(View.VISIBLE);
        _image41Category.setVisibility(View.VISIBLE);
        _image51Category.setVisibility(View.VISIBLE);
        _image61Category.setVisibility(View.VISIBLE);

        getCategoryData();
    }

    private void onClickTextSelectAll()
    {
        _image1Category.setVisibility(View.VISIBLE);
        _image2Category.setVisibility(View.VISIBLE);
        _image3Category.setVisibility(View.VISIBLE);
        _image4Category.setVisibility(View.VISIBLE);
        _image5Category.setVisibility(View.VISIBLE);
        _image6Category.setVisibility(View.VISIBLE);

        _image11Category.setVisibility(View.GONE);
        _image21Category.setVisibility(View.GONE);
        _image31Category.setVisibility(View.GONE);
        _image41Category.setVisibility(View.GONE);
        _image51Category.setVisibility(View.GONE);
        _image61Category.setVisibility(View.GONE);

        getCategoryData();
    }

    private void onSelectDeselectFemale()
    {
        if(_imageView1.getVisibility() == View.VISIBLE)
        {
            _imageView1.setVisibility(View.GONE);
            _imageView2.setVisibility(View.VISIBLE);

            _imageView4.setVisibility(View.GONE);
            _imageView3.setVisibility(View.VISIBLE);

            gender = "female";
        }
    }

    private void onSelectDeselectMale()
    {
        if(_imageView3.getVisibility() == View.VISIBLE)
        {
            _imageView3.setVisibility(View.GONE);
            _imageView4.setVisibility(View.VISIBLE);

            _imageView2.setVisibility(View.GONE);
            _imageView1.setVisibility(View.VISIBLE);

            gender = "male";
        }
    }

    protected void onSelectProfile()
    {
        onSelectDefaultImage();
        _linear1lay1.setVisibility(View.VISIBLE);
        _txtProfile.setBackgroundColor(Color.parseColor("#FF1393"));

        renderProfileData();
    }

    private void renderProfileData()
    {
        String gen = App.getGender();

        if(gen.equals("female") || gen.equals("f"))
        {
            _imageView2.setVisibility(View.VISIBLE);
            _imageView1.setVisibility(View.INVISIBLE);
        }
        else if(gen.equals("male") || gen.equals("m"))
        {
            _imageView4.setVisibility(View.VISIBLE);
            _imageView3.setVisibility(View.INVISIBLE);
        }

        if(!App.getBirthYear().equals("0000-00-00"))
        {
            _editText2.setText(App.getBirthYear());
        }
    }

    protected void  onSelectCategory()
    {
        onSelectDefaultImage();
        _linear2lay1.setVisibility(View.VISIBLE);
        _txtCategory.setBackgroundColor(Color.parseColor("#FF1393"));

        renderCategoryData();
        getCategoryData();
    }

    private void renderCategoryData()
    {
        try {

            JSONObject obj = new JSONObject(App.getCategory());

            String c1 = obj.getString("c1");
            String c2 = obj.getString("c2");
            String c3 = obj.getString("c3");
            String c4 = obj.getString("c4");
            String c5 = obj.getString("c5");
            String c6 = obj.getString("c6");

            onClickTextDotDisturb();

            if(c1.equals("1")){
                _image1Category.setVisibility(View.VISIBLE);
                _image11Category.setVisibility(View.GONE);}
            if(c1.equals("1")){
                _image2Category.setVisibility(View.VISIBLE);
                _image21Category.setVisibility(View.GONE);}
            if(c1.equals("1")){
                _image3Category.setVisibility(View.VISIBLE);
                _image31Category.setVisibility(View.GONE);}
            if(c1.equals("1")){
                _image4Category.setVisibility(View.VISIBLE);
                _image41Category.setVisibility(View.GONE);}
            if(c1.equals("1")){
                _image5Category.setVisibility(View.VISIBLE);
                _image51Category.setVisibility(View.GONE);}
            if(c1.equals("1")){
                _image6Category.setVisibility(View.VISIBLE);
                _image61Category.setVisibility(View.GONE);}
        }
        catch (Throwable t) {
        }

        System.out.println(">> Settings.java >>>>> Settings >>>>> renderCategoryData >>>>> "+ App.getCategory());
    }

    protected void onSelectGeneral()
    {
        onSelectDefaultImage();
        _linear3lay1.setVisibility(View.VISIBLE);
        _txtGeneral.setBackgroundColor(Color.parseColor("#FF1393"));

        setGeneralData();
    }

    private void setGeneralData()
    {
        txtName.setText(App.getName());
        txtEmail.setText(App.getEmail());
    }

    protected void onSelectDefaultImage()
    {
        _linear1lay1.setVisibility(View.GONE);
        _linear2lay1.setVisibility(View.GONE);
        _linear3lay1.setVisibility(View.GONE);

        _txtProfile.setBackgroundResource(R.drawable.back);
        _txtCategory.setBackgroundResource(R.drawable.back);
        _txtGeneral.setBackgroundResource(R.drawable.back);
    }

    private void initHandler()
    {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message)
            {
                if (message.what == Constant.SERVICE_SETTING_RESPONSE)
                {
                    String settingData = message.obj.toString();
                    JSONObject jsObj = null;

                    try {
                        jsObj = new JSONObject(settingData);
                        Integer success = jsObj.getInt("success");
                        String msg = jsObj.getString("message");

                        if(success == 1)
                        {
                            JSONObject userList = jsObj.getJSONObject("userList");
                            App.setGender(userList.getString("gender"));
                            App.setBirthYear(userList.getString("bod"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ServiceManager.onSettingsChangedCat(handler,App.getUserId(),App.getCategory());
                }
                else  if (message.what == Constant.SERVICE_SETTING_RESPONSE_OTHER)
                {

                }
                return false;
            }
        });
    }

    protected void onSettingsChange()
    {
//        ServiceManager.onSettingsChanged(handler,String.valueOf(App.getUserId()),gender,yearStr);
        ServiceManager.onSettingsChangedCat(handler,App.getUserId(),selectedCat);
    }
}
