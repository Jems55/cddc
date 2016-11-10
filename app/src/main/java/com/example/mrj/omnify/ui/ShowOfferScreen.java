package com.example.mrj.omnify.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrj.omnify.R;
import com.example.mrj.omnify.util.Constant;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by MR.J on 10/4/2016.
 */
public class ShowOfferScreen extends YouTubeBaseActivity
{
    String data,campaignId , campaignName, campaignSpotId, campaignActionType, loyaltyId, campaignStartDate, campaignEndDate, activates,contentCategory,
            contentId,contentName,contentPushText,contentNotifBlockTime,grouName,currentTheme,currentThemeColor,headerName,contentHeaderLabel,imageHeader,
            imageUploader,contentYoutubVideoID,contentTitle,contentSubtitle, contentText, secondButton, buttonColor1,buttonColor2, firstButton, buttonTitle3, buttonTitle4,
            url1, mp3File1, address1, phoneNo1, email1, cta_1_action_6_contentID1,videoUrl1, containUrl1,url2, mp3File2, address2, phoneNo2, email2, cta_2_action_6_contentID2,
            videoUrl2, containUrl2, actionBtn1, actionBtn2, actionBtn3, actionBtn4, url3, mp3File3, address3, phoneNo3, email3, cta_3_action_6_contentID3,videoUrl3, containUrl3,
            url4, mp3File4, address4, phoneNo4, email4, cta_4_action_6_contentID4, videoUrl4, containUrl4, addLanuch,addTouch;

    TextView txtTitle, firstTextView1, firstTextView2, firstTextView3, firstTextView4, secondTextView1, secondTextView2, secondTextView3, secondTextView4, thirdTextView1, thirdTextView2,  thirdTextView3,
            forthTextView1, forthTextView2, forthTextView3, fifthTextView1, fifthTextView2, fifthTextView3, sixthTextView1, sixthTextView2, sixthTextView3, sixthTextView4, seventhTextView1, seventhTextView2, seventhTextView3, seventhTextView4,
            eigthTextView1,eigthTextView2,eigthTextView3, eigthTextView4, ninthTextView1, ninthTextView2, ninthTextView3, tenthTextView1, tenthTextView2, tenthTextView3, tenthTextView4, elevenTextView1, elevenTextView2, elevenTextView3, elevenTextView4;

    LinearLayout firstOffer, secondOffer, firstLinearLayer1, firstLinearLayer2, secondLinearLayout1, secondLinearLayout2, secondLinearLayout3,
            thirdLinearLayout1, thirdLinearLayout2, thirdLinearLayout3, thirdLinearLayout4, forthLinearLayout1, forthLinearLayout2, forthLinearLayout3, forthLinearLayout4, fifthLinearLayout1, fifthLinearLayout2, fifthLinearLayout3, sixthLinearLayout1, sixthLinearLayout2, sixthLinearLayout4, sixthLinearLayout5,
            seventhLinearLayout1, seventhLinearLayout2, seventhLinearLayout4, seventhLinearLayout5,eigthLinearLayout1,
            eigthLinearLayout2, eigthLinearLayout3, ninthLinearLayout1, ninthLinearLayout2, ninthLinearLayout3, tenthLinearLayout1, tenthLinearLayout2, tenthLinearLayout3, tenthLinearLayout4, elevenLinearLayout1, elevenLinearLayout2, elevenLinearLayout3, elevenLinearLayout4,fifteenLinearLayout1,fifteenLinearLayout2,
            sixteenLinearLayout1,sixteenLinearLayout2,sixteenLinearLayout3;

    ImageView firstHeader, firstImage, secondHeader, secondImage, thirdHeader, thirdImage, forthdHeader, forthImage, fifthHeader, fifthImage, sixthImage, sixthHeader, seventhImage, sevethHeader, eigthImage, eigthHeader, tenthHeader, tenthImageView1, elevenHeader, elevenImageView1, fifteenImageView1;

    Button firstBtn1, firstBtn2, firstBtn3, secondBtn1, secondBtn2, secondBtn3, thirdBtn1, thirdBtn2, thirdBtn3, forthBtn1, forthBtn2, forthBtn3,fifthBtn1, fifthBtn2, fifthBtn3,
            sixthBtn1, sixthBtn2, sixthBtn3, seventhBtn1, seventhBtn2, seventhBtn3, eigthBtn1, eigthBtn2, eigthBtn3, ninthBtn1, ninthBtn2, ninthBtn3, tenthBtn1, tenthBtn2, tenthBtn3,
            elevenBtn1, elevenBtn2, elevenBtn3, fifteenBtn1,fifteenBtn2,fifteenBtn3, sixteenBtn1,sixteenBtn2,sixteenBtn3;

    FrameLayout firstFramelayout, firstFramelayout2, secondFramelayout, secondFramelayout2, thirdFrameLayout0, thirdOffer, forthFrameLayout0, forthOffer, fifthFrameLayout1, fifthOffer,sixthOffer,seventhOffer ,eigthOffer,ninthOffer, tenthOffer,elevenOffer, elevenFramelayout1, twelthOffer, thirteenOffer, forteenOffer,
            fifteenOffer, sixteenOffer, seventeenOffer, thirdFrameLayout1, forthFrameLayout1, sixthFramelayout1, sevenFramelayout1, eigthFramelayout1, tenthFramelayout1;

    YouTubePlayerView youtubeView, ninthYoutubeView, sixteenYoutubeView, seventeenYoutubeView;
    ListView lv;

    int dWidth,dHeight;

    private WebView Webview;
    private MediaPlayer mediaPlayer;
    private MediaController mediaControls;

    private int position = 0;
    private ProgressDialog progressDialog;
    private WebView webView1;
    public static final String API_KEY = "AIzaSyAkvCqDDaqxPowfaTKu9z5DbMfPbGIOXoA";

    private YouTubePlayer.OnInitializedListener onInitializedListener;

    JSONObject jsObj;
    JSONObject content;
    JSONObject campaignList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.various_offer);
        intialize();

        data = getIntent().getStringExtra("data");

        try {

            jsObj = new JSONObject(data);
            campaignList = jsObj.getJSONObject("comapaignList").getJSONObject("1");

            campaignId = campaignList.getString("cmp_id");
            campaignName = campaignList.getString("compaign_name");
            campaignSpotId = campaignList.getString("CampaignSpotId");
            campaignActionType = campaignList.getString("CampaignActionType");
            loyaltyId = campaignList.getString("LoyaltyId");
            campaignStartDate = campaignList.getString("CampaignStartDate");
            campaignEndDate = campaignList.getString("CampaignEndDate");
            activates = campaignList.getString("activates");

            content = jsObj.getJSONObject("contentList").getJSONObject("1");

            contentId = content.getString("content_id");
            contentName = content.getString("ContentName");
            contentPushText = content.getString("ContentPushText");
            contentNotifBlockTime = content.getString("ContentNotifBlockTime");
            contentCategory = content.getString("ContentCategory");
            grouName = content.getString("group_name");
            currentTheme = content.getString("currentTheme");
            currentThemeColor = content.getString("currentThemeColor");
            headerName = content.getString("headername");
            contentHeaderLabel = content.getString("ContentHeaderLabel");
            imageHeader = content.getString("imageheader");
            imageUploader = content.getString("image_uploader");
            contentYoutubVideoID = content.getString("ContentYoutubVideoID");
            contentTitle = content.getString("ContentTitle");

            contentSubtitle = content.getString("ContentSubtitle");
            contentText = content.getString("ContentText");
            secondButton = content.getString("secondbutton");

            buttonColor1 = content.getString("buttonColor1");
            buttonColor2 = content.getString("buttonColor2");
            firstButton = content.getString("firstbutton");
            buttonTitle3 = content.getString("buttonTitle3");
            buttonTitle4 = content.getString("buttontitle4");

            url1 = content.getString("url1");
            mp3File1 = content.getString("mp3file1");
            address1 = content.getString("address1");
            phoneNo1 = content.getString("phoneno1");
            email1 = content.getString("email1");
            cta_1_action_6_contentID1 = content.getString("cta_1_action_6_contentID1");
            videoUrl1 = content.getString("videourl1");
            containUrl1 = content.getString("containurl1");

            url2 = content.getString("url2");
            mp3File2 = content.getString("mp3file2");
            address2 = content.getString("address2");
            phoneNo2 = content.getString("phoneno2");
            email2 = content.getString("email2");
            cta_2_action_6_contentID2 = content.getString("cta_2_action_6_contentID2");
            videoUrl2 = content.getString("videourl2");
            containUrl2 = content.getString("containurl2");

            actionBtn1 = content.getString("actionBtn1");
            actionBtn2 = content.getString("actionBtn2");
            actionBtn3 = content.getString("actionBtn3");
            actionBtn4 = content.getString("actionBtn4");

            url3 = content.getString("url3");
            mp3File3 = content.getString("mp3file3");
            address3 = content.getString("address3");
            phoneNo3 = content.getString("phoneno3");
            email3 = content.getString("email3");
            cta_3_action_6_contentID3 = content.getString("cta_3_action_6_contentID3");
            videoUrl3 = content.getString("videourl3");
            containUrl3 = content.getString("containurl3");

            url4 = content.getString("url4");
            mp3File4 = content.getString("mp3file4");
            address4 = content.getString("address4");
            phoneNo4 = content.getString("phoneno4");
            email4 = content.getString("email4");
            cta_4_action_6_contentID4 = content.getString("cta_4_action_6_contentID4");
            videoUrl4 = content.getString("videourl4");
            containUrl4 = content.getString("containurl4");

            addLanuch = content.getString("addlanuch");
            addTouch = content.getString("addtouch");

            imageUploader = Constant.IMAGE_BASE_URL + imageUploader;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            displayContent(currentTheme);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void intialize()
    {
        getDeviceDimen();

        webView1 = (WebView)findViewById(R.id.webView1);
        youtubeView = (YouTubePlayerView)findViewById(R.id.youtubeView);

        firstOfferIntialize();
        secondOfferIntialize();
        thirdOfferIntialize();
        forthOfferInitialize();
        fifthOfferInitialize();
        sixthOfferInitialize();
        seventhOfferInitialize();
        eigthOfferInitialize();
        ninethOfferInitialize();
        tenthOfferInitialize();
        eleventhOfferInitialize();
        twelthOfferInitialize();
        thirteenOfferInitialize();
        forteenOfferInitialize();
        fifteenOfferInitialize();
        sixteenOfferInitialize();
        seventeenOfferInitialize();


        setHightWidthForComponant();
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
        firstContentResize();
        secondContentResize();
        thirdContentResize();
        forthContentResize();
        fifthContentResize();
        sixthContentResize();
        sevenContentResize();
        eigthContentResize();
        ninthContentResize();
        tenthContentResize();
        elevenContentResize();
        fifteenContentResize();
        sixteenContentResize();
    }


    private void firstOfferIntialize()
    {
        firstOffer = (LinearLayout) findViewById(R.id.firstOffer);

        firstFramelayout =  (FrameLayout) findViewById(R.id.firstFramelayout);
        firstFramelayout2 =  (FrameLayout) findViewById(R.id.firstFramelayout2);

        firstHeader =  (ImageView) findViewById(R.id.firstHeader);
        firstImage =  (ImageView) findViewById(R.id.firstImageView1);

        firstTextView1 = (TextView)findViewById(R.id.firstTextView1);
        firstTextView2 = (TextView)findViewById(R.id.firstTextView2);
        firstTextView3 = (TextView)findViewById(R.id.firstTextView3);

        firstLinearLayer1 = (LinearLayout) findViewById(R.id.firstLinearLayer1);
        firstLinearLayer2 = (LinearLayout)findViewById(R.id.firstLinearLayer2);

        firstBtn1 = (Button)findViewById(R.id.firstBtn1);
        firstBtn2 = (Button)findViewById(R.id.firstBtn2);

        firstBtn3 = (Button)findViewById(R.id.firstBtn3);
    }

    private void secondOfferIntialize()
    {
        secondOffer = (LinearLayout) findViewById(R.id.secondOffer);

        secondFramelayout =  (FrameLayout) findViewById(R.id.secondFramelayout);
        secondFramelayout2 =  (FrameLayout) findViewById(R.id.secondFramelayout2);

        secondHeader =  (ImageView) findViewById(R.id.secondHeader);
        secondImage =  (ImageView) findViewById(R.id.secondImageView1);

        secondTextView1 = (TextView)findViewById(R.id.secondTextView1);
        secondTextView2 = (TextView)findViewById(R.id.secondTextView2);
        secondTextView3 = (TextView)findViewById(R.id.secondTextView3);
        secondTextView4 = (TextView)findViewById(R.id.secondTextView4);

        secondLinearLayout1 = (LinearLayout) findViewById(R.id.secondLinearLayout1);
        secondLinearLayout2 = (LinearLayout) findViewById(R.id.secondLinearLayout2);
        secondLinearLayout3 = (LinearLayout) findViewById(R.id.secondLinearLayout3);

        secondBtn1 = (Button)findViewById(R.id.secondBtn1);
        secondBtn2 = (Button)findViewById(R.id.secondBtn2);
        secondBtn3 = (Button)findViewById(R.id.secondBtn3);
    }

    private void thirdOfferIntialize()
    {
        thirdOffer = (FrameLayout)findViewById(R.id.thirdOffer);
        thirdFrameLayout0 = (FrameLayout)findViewById(R.id.thirdFrameLayout0);

        thirdImage = (ImageView)findViewById(R.id.thirdImageView1);
        thirdHeader = (ImageView)findViewById(R.id.thirdHeader);

        thirdTextView1 = (TextView)findViewById(R.id.thirdTextView1);
        thirdTextView2 = (TextView)findViewById(R.id.thirdTextView2);
        thirdTextView3 = (TextView)findViewById(R.id.thirdTextView3);

        thirdFrameLayout1 = (FrameLayout)findViewById(R.id.thirdFrameLayout1);

        thirdLinearLayout1 = (LinearLayout)findViewById(R.id.thirdLinearLayout1);
        thirdLinearLayout2 = (LinearLayout)findViewById(R.id.thirdLinearLayout2);
        thirdLinearLayout3 = (LinearLayout) findViewById(R.id.thirdLinearLayout3);
        thirdLinearLayout4 = (LinearLayout) findViewById(R.id.thirdLinearLayout4);

        thirdBtn1 = (Button)findViewById(R.id.thirdBtn1);
        thirdBtn2 = (Button)findViewById(R.id.thirdBtn2);
        thirdBtn3 = (Button)findViewById(R.id.thirdBtn3);
    }

    private void forthOfferInitialize()
    {
        forthOffer = (FrameLayout)findViewById(R.id.forthOffer);
        forthFrameLayout0 = (FrameLayout)findViewById(R.id.forthFrameLayout0);

        forthdHeader = (ImageView)findViewById(R.id.forthHeader);
        forthImage = (ImageView)findViewById(R.id.forthImageView1);

        forthTextView1 = (TextView)findViewById(R.id.forthTextView1);
        forthTextView2 = (TextView)findViewById(R.id.forthTextView2);
        forthTextView3 = (TextView)findViewById(R.id.forthTextView3);

        forthFrameLayout1 = (FrameLayout)findViewById(R.id.forthFrameLayout1);


        forthLinearLayout1 = (LinearLayout) findViewById(R.id.forthLinearLayout1);
        forthLinearLayout2 = (LinearLayout) findViewById(R.id.forthLinearLayout2);
        forthLinearLayout3 = (LinearLayout) findViewById(R.id.forthLinearLayout3);
        forthLinearLayout4 = (LinearLayout) findViewById(R.id.forthLinearLayout4);

        forthBtn1 = (Button)findViewById(R.id.forthBtn1);
        forthBtn2 = (Button)findViewById(R.id.forthBtn2);
        forthBtn3 = (Button)findViewById(R.id.forthBtn3);
    }

    private void fifthOfferInitialize()
    {
        fifthOffer = (FrameLayout)findViewById(R.id.fifthOffer);
        fifthFrameLayout1 = (FrameLayout)findViewById(R.id.fifthFrameLayout1);

        fifthHeader = (ImageView)findViewById(R.id.fifthHeader);
        fifthImage = (ImageView)findViewById(R.id.fifthImageView1);

        fifthTextView1 = (TextView)findViewById(R.id.fifthTextView1);
        fifthTextView2 = (TextView)findViewById(R.id.fifthTextView2);
        fifthTextView3 = (TextView)findViewById(R.id.fifthTextView3);

        fifthLinearLayout1 = (LinearLayout)findViewById(R.id.fifthLinearLayout1);
        fifthLinearLayout2 = (LinearLayout) findViewById(R.id.fifthLinearLayout2);
        fifthLinearLayout3 = (LinearLayout) findViewById(R.id.fifthLinearLayout3);

        fifthBtn1 = (Button)findViewById(R.id.fifthBtn1);
        fifthBtn2 = (Button)findViewById(R.id.fifthBtn2);
        fifthBtn3 = (Button)findViewById(R.id.fifthBtn3);
    }

    private void sixthOfferInitialize()
    {
        sixthOffer = (FrameLayout)findViewById(R.id.sixthOffer);
        sixthFramelayout1 = (FrameLayout)findViewById(R.id.sixthFramelayout1);


        sixthHeader = (ImageView)findViewById(R.id.sixthHeader);
        sixthImage = (ImageView)findViewById(R.id.sixthImageView1);

        sixthTextView1 = (TextView)findViewById(R.id.sixthTextView1);
        sixthTextView2 = (TextView)findViewById(R.id.sixthTextView2);
        sixthTextView3 = (TextView)findViewById(R.id.sixthTextView3);
        sixthTextView4 = (TextView)findViewById(R.id.sixthTextView4);

        sixthLinearLayout1 = (LinearLayout)findViewById(R.id.sixthLinearLayout1);
        sixthLinearLayout2 = (LinearLayout)findViewById(R.id.sixthLinearLayout2);
        sixthLinearLayout4 = (LinearLayout) findViewById(R.id.sixthLinearLayout4);
        sixthLinearLayout5 = (LinearLayout) findViewById(R.id.sixthLinearLayout5);

        sixthBtn1 = (Button)findViewById(R.id.sixthBtn1);
        sixthBtn2 = (Button)findViewById(R.id.sixthBtn2);
        sixthBtn3 = (Button)findViewById(R.id.sixthBtn3);
    }

    private void seventhOfferInitialize()
    {
        seventhOffer = (FrameLayout)findViewById(R.id.seventhOffer);
        sevenFramelayout1 = (FrameLayout)findViewById(R.id.sevenFramelayout1);

        sevethHeader = (ImageView)findViewById(R.id.sevethHeader);
        seventhImage = (ImageView)findViewById(R.id.seventhImageView1);

        seventhTextView1 = (TextView)findViewById(R.id.seventhTextView1);
        seventhTextView2 = (TextView)findViewById(R.id.seventhTextView2);
        seventhTextView3 = (TextView)findViewById(R.id.seventhTextView3);
        seventhTextView4 = (TextView)findViewById(R.id.seventhTextView4);

        seventhLinearLayout1 = (LinearLayout)findViewById(R.id.seventhLinearLayout1);
        seventhLinearLayout2 = (LinearLayout)findViewById(R.id.seventhLinearLayout2);
        seventhLinearLayout4 = (LinearLayout) findViewById(R.id.sevethLinearLayout4);
        seventhLinearLayout5 = (LinearLayout) findViewById(R.id.sevethLinearLayout5);

        seventhBtn1 = (Button)findViewById(R.id.sevethBtn1);
        seventhBtn2 = (Button)findViewById(R.id.sevethBtn2);
        seventhBtn3 = (Button)findViewById(R.id.sevethBtn3);
    }

    private void eigthOfferInitialize()
    {
        eigthOffer = (FrameLayout)findViewById(R.id.eigthOffer);
        eigthFramelayout1 = (FrameLayout)findViewById(R.id.eigthFramelayout1);

        eigthHeader = (ImageView)findViewById(R.id.eigthHeader);
        eigthImage = (ImageView)findViewById(R.id.eigthImageView1);

        eigthTextView1 = (TextView)findViewById(R.id.eigthTextView1);
        eigthTextView2 = (TextView)findViewById(R.id.eigthTextView2);
        eigthTextView3 = (TextView)findViewById(R.id.eigthTextView3);
        eigthTextView4 = (TextView)findViewById(R.id.eigthTextView4);

        eigthLinearLayout1 = (LinearLayout)findViewById(R.id.eigthLinearLayout1);
        eigthLinearLayout2 = (LinearLayout) findViewById(R.id.eigthLinearLayout2);
        eigthLinearLayout3 = (LinearLayout) findViewById(R.id.eigthLinearLayout3);

        eigthBtn1 = (Button)findViewById(R.id.eigthBtn1);
        eigthBtn2 = (Button)findViewById(R.id.eigthBtn2);
        eigthBtn3 = (Button)findViewById(R.id.eigthBtn3);
    }

    private void ninethOfferInitialize()
    {
        ninthOffer = (FrameLayout)findViewById(R.id.ninthOffer);

        if (mediaControls == null) {
            mediaControls = new MediaController(this);
        }

        ninthYoutubeView  = (YouTubePlayerView) findViewById(R.id.ninthYoutubeView);
        ninthLinearLayout1 = (LinearLayout)findViewById(R.id.ninthLinearLayout1);
        ninthLinearLayout2 = (LinearLayout) findViewById(R.id.ninthLinearLayout2);
        ninthLinearLayout3 = (LinearLayout) findViewById(R.id.ninthLinearLayout3);

        ninthTextView1 = (TextView)findViewById(R.id.ninthTextView1);
        ninthTextView2 = (TextView)findViewById(R.id.ninthTextView2);
        ninthTextView3 = (TextView)findViewById(R.id.ninthTextView3);

        ninthBtn1 = (Button)findViewById(R.id.ninthBtn1);
        ninthBtn2 = (Button)findViewById(R.id.ninthBtn2);
        ninthBtn3 = (Button)findViewById(R.id.ninthBtn3);
    }

    private void tenthOfferInitialize()
    {
        tenthOffer = (FrameLayout)findViewById(R.id.tenthOffer);
        tenthFramelayout1 = (FrameLayout)findViewById(R.id.tenthFramelayout1);

        tenthHeader = (ImageView)findViewById(R.id.tenthHeader);
        tenthImageView1 = (ImageView)findViewById(R.id.tenthImageView1);

        tenthTextView1 = (TextView)findViewById(R.id.tenthTextView1);
        tenthTextView2 = (TextView)findViewById(R.id.tenthTextView2);
        tenthTextView3 = (TextView)findViewById(R.id.tenthTextView3);
        tenthTextView4 = (TextView)findViewById(R.id.tenthTextView4);

        tenthLinearLayout1 = (LinearLayout)findViewById(R.id.tenthLinearLayout1);
        tenthLinearLayout2 = (LinearLayout)findViewById(R.id.tenthLinearLayout2);
        tenthLinearLayout3 = (LinearLayout) findViewById(R.id.tenthLinearLayout3);
        tenthLinearLayout4 = (LinearLayout) findViewById(R.id.tenthLinearLayout4);

        tenthBtn1 = (Button)findViewById(R.id.tenthBtn1);
        tenthBtn2 = (Button)findViewById(R.id.tenthBtn2);
        tenthBtn3 = (Button)findViewById(R.id.tenthBtn3);
    }

    private void eleventhOfferInitialize()
    {
        elevenOffer  = (FrameLayout)findViewById(R.id.elevenOffer);
        elevenFramelayout1 = (FrameLayout)findViewById(R.id.elevenFramelayout1);

        elevenHeader = (ImageView)findViewById(R.id.elevenHeader);
        elevenImageView1 = (ImageView)findViewById(R.id.elevenImageView1);

        elevenTextView1 = (TextView)findViewById(R.id.elevenTextView1);
        elevenTextView2 = (TextView)findViewById(R.id.elevenTextView2);
        elevenTextView3 = (TextView)findViewById(R.id.elevenTextView3);
        elevenTextView4 = (TextView)findViewById(R.id.elevenTextView4);

        elevenLinearLayout1 = (LinearLayout)findViewById(R.id.elevenLinearLayout1);
        elevenLinearLayout2 = (LinearLayout)findViewById(R.id.elevenLinearLayout2);
        elevenLinearLayout3 = (LinearLayout) findViewById(R.id.elevenLinearLayout3);
        elevenLinearLayout4 = (LinearLayout) findViewById(R.id.elevenLinearLayout4);

        elevenBtn1 = (Button)findViewById(R.id.elevenBtn1);
        elevenBtn2 = (Button)findViewById(R.id.elevenBtn2);
        elevenBtn3 = (Button)findViewById(R.id.elevenBtn3);
    }

    private void twelthOfferInitialize()
    {
        twelthOffer = (FrameLayout)findViewById(R.id.twelveOffer);
    }

    private void thirteenOfferInitialize()
    {
        thirteenOffer = (FrameLayout)findViewById(R.id.thirteenOffer);
    }

    private void forteenOfferInitialize()
    {
        forteenOffer = (FrameLayout)findViewById(R.id.forteenOffer);
    }

    private void fifteenOfferInitialize()
    {
        fifteenOffer = (FrameLayout)findViewById(R.id.fifteenOffer);
        fifteenImageView1 = (ImageView)findViewById(R.id.fifteenImageView1);

        fifteenLinearLayout1 = (LinearLayout)findViewById(R.id.fifteenLinearLayout1);
        fifteenLinearLayout2 = (LinearLayout)findViewById(R.id.fifteenLinearLayout2);

        fifteenBtn1 = (Button)findViewById(R.id.fifteenBtn1);
        fifteenBtn2 = (Button)findViewById(R.id.fifteenBtn2);
        fifteenBtn3 = (Button)findViewById(R.id.fifteenBtn3);
    }

    private void sixteenOfferInitialize()
    {
        sixteenOffer = (FrameLayout)findViewById(R.id.sixteenOffer);

        sixteenYoutubeView = (YouTubePlayerView)findViewById(R.id.sixteenYoutubeView);

        sixteenLinearLayout1 = (LinearLayout)findViewById(R.id.sixteenLinearLayout1);
        sixteenLinearLayout2 = (LinearLayout)findViewById(R.id.sixteenLinearLayout2);
        sixteenLinearLayout3 = (LinearLayout)findViewById(R.id.sixteenLinearLayout3);

        sixteenBtn1 = (Button)findViewById(R.id.sixteenBtn1);
        sixteenBtn2 = (Button)findViewById(R.id.sixteenBtn2);
        sixteenBtn3 = (Button)findViewById(R.id.sixteenBtn3);
    }

    private void seventeenOfferInitialize()
    {
        seventeenOffer = (FrameLayout)findViewById(R.id.seventeenOffer);
        seventeenYoutubeView = (YouTubePlayerView)findViewById(R.id.seventeenYoutubeView);
    }




    private void displayContent(String currentThemeSelect) throws IOException
    {
        switch(currentThemeSelect)
        {
            case "1":
                showFirstContent();
                break;
            case "1_3":
                showSecondContent();
                break;
            case "1_4":
                showThirdContent();
                break;
            case "1_5":
                showForthContent();
                break;
            case "2":
                showFifthContent();
                break;
            case "2_2":
                showSixthContent();
                break;
            case "2_3":
                showSeventhContent();
                break;
            case "3_1":
                showEigthContent();
                break;
            case "3_2":
                showNinthContent();
                break;
            case "3_3":
                showTenthContent();
                break;
            case "3_4":
                showElevenContent();
                break;
            case "4_1":
                showTwelthContent();
                break;
            case "5_1":
                showThirteenContent();
                break;
            case "6_1":
                showForteenContent();
                break;
            case "0":
                showFifteenContent();
                break;
            case "0_2":
                showSixteenContent();
                break;
            case "0_3":
                showSeventeenContent();
                break;

        }
    }

    private void invisibleAllOffer()
    {
        webView1.setVisibility(View.GONE);
        youtubeView.setVisibility(View.GONE);

        firstOffer.setVisibility(View.GONE);
        secondOffer.setVisibility(View.GONE);
        thirdOffer.setVisibility(View.GONE);
        forthOffer.setVisibility(View.GONE);
        fifthOffer.setVisibility(View.GONE);
        sixthOffer.setVisibility(View.GONE);
        seventhOffer.setVisibility(View.GONE);
        eigthOffer.setVisibility(View.GONE);
        ninthOffer.setVisibility(View.GONE);
        tenthOffer.setVisibility(View.GONE);
        elevenOffer.setVisibility(View.GONE);

        twelthOffer.setVisibility(View.GONE);
        thirteenOffer.setVisibility(View.GONE);
        forteenOffer.setVisibility(View.GONE);
        fifteenOffer.setVisibility(View.GONE);
        sixteenOffer.setVisibility(View.GONE);
        seventeenOffer.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        stopSound();
    }

    private void onStartAction(String action, int button)
    {
        stopSound();

        int checkSelfPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION);

        if (PackageManager.PERMISSION_GRANTED == checkSelfPermissionResult)
        {
            //already granted
            if(action.equals("1"))
            {
                In_browserlink(button);
            }
            else if(action.equals("2"))
            {
                Play_Sound(button);
            }
            else if(action.equals("3"))
            {
                Show_Maps(button);
            }

            else if(action.equals("4"))
            {
                Make_Call(button);
            }

            else if(action.equals("5"))
            {
                Send_Email(button);
            }
            else if(action.equals("9"))
            {
                Play_Video(button);
            }
            else if(action.equals("11"))
            {
                In_AppLink(button);
            }
        }
        else
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(ShowOfferScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //we should show some explanation for user here
                Toast.makeText(getApplicationContext(), "Location permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            }else {
                //request permission
                ActivityCompat.requestPermissions(ShowOfferScreen.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            }
        }
    }




    private void Play_Video(int btnValue)
    {
//        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));

        String key = null;

        if(btnValue == 1){
            key = videoUrl1;}
        else if(btnValue == 2){
            key = videoUrl2;}
        else if(btnValue == 3){
            key = videoUrl3;}
        else if(btnValue == 4){
            key = videoUrl4;}

        final String finalKey = key;
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
            {
                youTubePlayer.loadVideo(finalKey);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
            {
                System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> onInitializationFailure >>>>> ");
                Toast.makeText(getApplicationContext(),"YouTubePlayer.onInitializationFailure():",Toast.LENGTH_LONG).show();
            }
        };

        youtubeView.setVisibility(View.VISIBLE);
        youtubeView.initialize("API_KEY",onInitializedListener);

    }

    private void Play_Sound(int btnValue)
    {
        String path = "";

        if(btnValue == 1){
            path = mp3File1;}
        else if(btnValue == 2){
            path = mp3File2;}
        else if(btnValue == 3){
            path = mp3File3;}
        else if(btnValue == 4){
            path = mp3File4;}

        // play sound
        String AUDIO_PATH = Constant.BASE_URL+path; //www.stephaniequinn.com/Music/Allegro%20from%20Duet%20in%20C%20Major.mp3";
        try {
            playAudio(AUDIO_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Show_Maps(int btnValue)
    {
        //show maps
//        String uri = String.format(Locale.ENGLISH, "geo:0,0?q=Surat");
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        startActivity(intent);
    }

    private void Send_Email(int btnValue)
    {
        Boolean can_send_mail = false;
        String recipientId = "";

        if(btnValue == 1){
            recipientId = email1;}
        else if(btnValue == 2){
            recipientId = email2;}
        else if(btnValue == 3){
            if(email3 != ""){
                can_send_mail = true;
                recipientId = email3;}}
        else if(btnValue == 4){
            if(email4 != ""){
                can_send_mail = true;
                recipientId = email4;}}

        if(can_send_mail) {
            //for Email
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientId});
            i.putExtra(Intent.EXTRA_SUBJECT, "Omnify welcomes you");
            i.putExtra(Intent.EXTRA_TEXT, "Here is the cool offer for you..!!");
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Make_Call(int btnValue)
    {
        String phn_no = "";

        System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> Make_Call >>>>> "+ phoneNo1);

        if(btnValue == 1){
            phn_no = phoneNo1;
        }else if(btnValue == 2){
            phn_no = phoneNo2;
        }else if(btnValue == 3){
            phn_no = phoneNo3;}
        else if(btnValue == 4){
            phn_no = phoneNo4;}

        //for phone
        Intent my_callIntent = new Intent(Intent.ACTION_CALL);
        my_callIntent.setData(Uri.parse("tel:" + phn_no));
        try {
            startActivity(my_callIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Error while phone call" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void In_browserlink(int btnValue)
    {
        String path = "";

        if(btnValue == 1){
            path = url1;
        }else if(btnValue == 2){
            path = url2;
        }else if(btnValue == 3){
            path = url3;}
        else if(btnValue == 4){
            path = url4;}

        //for browser link
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+path));
        try {
            startActivity(browserIntent);
        }catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No application found for this request."
                    + " Please install a webbrowser",  Toast.LENGTH_LONG).show();
        }
    }

    private void In_AppLink(int btnValue)
    {
        String path = "";
        if(btnValue == 1){
            path = containUrl1;}
        else if(btnValue == 2){
            path = containUrl2;}
        else if(btnValue == 3){
            path = containUrl3;}
        else if(btnValue == 4){
            path = containUrl4;}

        // in App link
        webView1.setVisibility(View.VISIBLE);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setLoadWithOverviewMode(true);
        webView1.getSettings().setUseWideViewPort(true);
        webView1.getSettings().setSupportMultipleWindows(true);
        webView1.getSettings().setSupportZoom(true);
        webView1.setVerticalScrollBarEnabled(true);
        webView1.setHorizontalScrollBarEnabled(true);

        webView1.loadUrl(path);
    }

    private void stopSound()
    {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private void playAudio(String url) throws Exception
    {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    private void killMediaPlayer() {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }





    private void showFirstContent() throws IOException
    {
        invisibleAllOffer();
        firstOffer.setVisibility(View.VISIBLE);

        firstOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        if(!actionBtn1.equals("0") && !actionBtn1.equals("")){
            onStartAction(actionBtn1,1);}

        firstTextView1.setText(contentHeaderLabel);
        System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showFirstContent >>>>> "+ Constant.onSelectHeader(currentThemeColor));
        firstHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));
//        firstTextView1.setBackgroundColor(Color.parseColor("#"+Constant.onSelectColor(currentThemeColor)));

        firstTextView2.setText(contentTitle);
        firstTextView3.setText(contentSubtitle);

        firstLinearLayer1.setVisibility(View.GONE);
        firstLinearLayer2.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            firstLinearLayer1.setVisibility(View.VISIBLE);

            firstBtn1.setText(buttonTitle3);
            firstBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            firstBtn2.setText(buttonTitle4);
            firstBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            firstBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            firstBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);//actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            firstLinearLayer2.setVisibility(View.VISIBLE);

            firstBtn3.setText(buttonTitle3);
            firstBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            firstBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.firstImageView1))
                .execute(imageUploader);
    }

    private void showSecondContent()
    {
        invisibleAllOffer();
        secondOffer.setVisibility(View.VISIBLE);

        secondOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        secondTextView1.setText(contentHeaderLabel);
        secondHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        secondTextView2.setText(contentTitle);
        secondTextView3.setText(contentSubtitle);
//        secondTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        secondTextView4.setText(contentText);

        secondLinearLayout2.setVisibility(View.GONE);
        secondLinearLayout3.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            secondLinearLayout2.setVisibility(View.VISIBLE);

            secondBtn1.setText(buttonTitle3);
            secondBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            secondBtn2.setText(buttonTitle4);
            secondBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            secondBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });


            secondBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showSecondContent >>>>> 2222222222222");
            secondLinearLayout3.setVisibility(View.VISIBLE);
            secondBtn3.setText(buttonTitle3);
            secondBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            secondBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

//        "url3" "www.Facebook.com",
//        "actionBtn3" "1",
//        "buttonTitle3" "Purchase",
//        "buttonColor1" "10"
//
//        "actionBtn4" "1",
//        "buttontitle4" "0",
//        "buttonColor2" "10",
//        "url4" "www.smalltalklab.com"


        new DownloadImageTask((ImageView) findViewById(R.id.secondImageView1))
                .execute(imageUploader);
    }

    private void showThirdContent()
    {
        invisibleAllOffer();
        thirdOffer.setVisibility(View.VISIBLE);

        thirdOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        thirdTextView1.setText(contentHeaderLabel);
        System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showThirdContent >>>>> "+ Constant.onSelectHeader(currentThemeColor));
        thirdHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        thirdTextView2.setText(contentTitle);
        thirdTextView3.setText(contentSubtitle);
//        thirdTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        thirdLinearLayout3.setVisibility(View.GONE);
        thirdLinearLayout4.setVisibility(View.GONE);


        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            thirdLinearLayout3.setVisibility(View.VISIBLE);

            thirdBtn1.setText(buttonTitle3);
            thirdBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            thirdBtn2.setText(buttonTitle4);
            thirdBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            thirdBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });


            thirdBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            thirdLinearLayout4.setVisibility(View.VISIBLE);
            thirdBtn3.setText(buttonTitle3);
            thirdBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            thirdBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }


        new DownloadImageTask((ImageView) findViewById(R.id.thirdImageView1))
                .execute(imageUploader);

    }

    private void showForthContent()
    {
        invisibleAllOffer();
        forthOffer.setVisibility(View.VISIBLE);

        forthOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        forthTextView1.setText(contentHeaderLabel);
        forthdHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        forthTextView2.setText(contentTitle);
        forthTextView3.setText(contentSubtitle);
//        forthTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        forthLinearLayout3.setVisibility(View.GONE);
        forthLinearLayout4.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            forthLinearLayout3.setVisibility(View.VISIBLE);

            forthBtn1.setText(buttonTitle3);
            forthBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            forthBtn2.setText(buttonTitle4);
            forthBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            forthBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            forthBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            forthLinearLayout4.setVisibility(View.VISIBLE);
            forthBtn3.setText(buttonTitle3);
            forthBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            forthBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.forthImageView1))
                .execute(imageUploader);

    }

    private void showFifthContent()
    {
        invisibleAllOffer();
        fifthOffer.setVisibility(View.VISIBLE);

        fifthOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        fifthTextView1.setText(campaignName);
        fifthHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        fifthTextView2.setText(contentTitle);
        fifthTextView3.setText(contentText);

        fifthLinearLayout2.setVisibility(View.GONE);
        fifthLinearLayout3.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            fifthLinearLayout2.setVisibility(View.VISIBLE);

            fifthBtn1.setText(buttonTitle3);
            fifthBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            fifthBtn2.setText(buttonTitle4);
            fifthBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            fifthBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            fifthBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            fifthLinearLayout3.setVisibility(View.VISIBLE);
            fifthBtn3.setText(buttonTitle3);
            fifthBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            fifthBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.fifthImageView1))
                .execute(imageUploader);
    }

    private void showSixthContent()
    {
        invisibleAllOffer();
        sixthOffer.setVisibility(View.VISIBLE);

        sixthOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        sixthTextView1.setText(contentHeaderLabel);
        sixthHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        sixthTextView2.setText(contentTitle);
        sixthTextView3.setText(contentSubtitle);
//        sixthTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        sixthTextView4.setText(contentText);

        sixthLinearLayout4.setVisibility(View.GONE);
        sixthLinearLayout5.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 1");

            sixthLinearLayout4.setVisibility(View.VISIBLE);

            sixthBtn1.setText(buttonTitle3);
            sixthBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            sixthBtn2.setText(buttonTitle4);
            sixthBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            sixthBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            sixthBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            sixthLinearLayout5.setVisibility(View.VISIBLE);
            sixthBtn3.setText(buttonTitle3);
            sixthBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            sixthBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.sixthImageView1))
                .execute(imageUploader);

    }

    private void showSeventhContent()
    {
        invisibleAllOffer();
        seventhOffer.setVisibility(View.VISIBLE);

        seventhOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        seventhTextView1.setText(contentHeaderLabel);
        sevethHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        seventhTextView2.setText(contentTitle);
        seventhTextView3.setText(contentSubtitle);
//        seventhTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        seventhTextView4.setText(contentText);

        seventhLinearLayout4.setVisibility(View.GONE);
        seventhLinearLayout5.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 1");

            seventhLinearLayout4.setVisibility(View.VISIBLE);

            seventhBtn1.setText(buttonTitle3);
            seventhBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            seventhBtn2.setText(buttonTitle4);
            seventhBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            seventhBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            seventhBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            seventhLinearLayout5.setVisibility(View.VISIBLE);
            seventhBtn3.setText(buttonTitle3);
            seventhBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            seventhBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.seventhImageView1))
                .execute(imageUploader);

    }

    private void showEigthContent()
    {
        invisibleAllOffer();
        eigthOffer.setVisibility(View.VISIBLE);

        eigthOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        eigthTextView1.setText(contentHeaderLabel);
        eigthHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        eigthTextView2.setText(contentTitle);
        eigthTextView3.setText(contentSubtitle);
//        eigthTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        eigthTextView4.setText(contentText);

        eigthLinearLayout2.setVisibility(View.GONE);
        eigthLinearLayout3.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 1");

            eigthLinearLayout2.setVisibility(View.VISIBLE);

            eigthBtn1.setText(buttonTitle3);
            eigthBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            eigthBtn2.setText(buttonTitle4);
            eigthBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            eigthBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            eigthBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            eigthLinearLayout3.setVisibility(View.VISIBLE);
            eigthBtn3.setText(buttonTitle3);
            eigthBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            eigthBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.eigthImageView1))
                .execute(imageUploader);

    }

    private void showNinthContent() throws IOException
    {
        invisibleAllOffer();
        ninthOffer.setVisibility(View.VISIBLE);

        ninthOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
            {
                youTubePlayer.loadVideo(contentYoutubVideoID);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
            {
                System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> onInitializationFailure >>>>> ");
                Toast.makeText(getApplicationContext(),"YouTubePlayer.onInitializationFailure():",Toast.LENGTH_LONG).show();
            }
        };

        ninthYoutubeView.initialize("API_KEY",onInitializedListener);

        ninthTextView1.setText(contentTitle);
        ninthTextView2.setText(contentSubtitle);
//        ninthTextView2.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        ninthTextView3.setText(contentText);

        ninthLinearLayout2.setVisibility(View.GONE);
        ninthLinearLayout3.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            ninthLinearLayout2.setVisibility(View.VISIBLE);

            ninthBtn1.setText(buttonTitle3);
            ninthBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            ninthBtn2.setText(buttonTitle4);
            ninthBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            ninthBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            ninthBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            ninthLinearLayout3.setVisibility(View.VISIBLE);
            ninthBtn3.setText(buttonTitle3);
            ninthBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            ninthBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }
    }

    private void showTenthContent()
    {
        invisibleAllOffer();
        tenthOffer.setVisibility(View.VISIBLE);

        tenthOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        tenthTextView1.setText(contentHeaderLabel);
        tenthHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        tenthTextView2.setText(contentTitle);
        tenthTextView3.setText(contentSubtitle);
//        tenthTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        tenthTextView4.setText(contentText);

        tenthLinearLayout3.setVisibility(View.GONE);
        tenthLinearLayout4.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 1");

            tenthLinearLayout3.setVisibility(View.VISIBLE);

            tenthBtn1.setText(buttonTitle3);
            tenthBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            tenthBtn2.setText(buttonTitle4);
            tenthBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            tenthBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            tenthBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            tenthLinearLayout4.setVisibility(View.VISIBLE);
            tenthBtn3.setText(buttonTitle3);
            tenthBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            tenthBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.tenthImageView1))
                .execute(imageUploader);

    }

    private void showElevenContent()
    {
        invisibleAllOffer();
        elevenOffer.setVisibility(View.VISIBLE);

        elevenOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        elevenTextView1.setText(contentHeaderLabel);
        System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showElevenContent >>>>> "+ currentThemeColor);
        elevenHeader.setBackgroundResource(getResources().getIdentifier(Constant.onSelectHeader(currentThemeColor),"drawable",getPackageName()));

        elevenTextView2.setText(contentTitle);

        elevenTextView3.setText(contentSubtitle);
//        elevenTextView3.setTextColor(Color.parseColor(Constant.onSelectColor(currentThemeColor)));

        elevenTextView4.setText(contentText);

        elevenLinearLayout3.setVisibility(View.GONE);
        elevenLinearLayout4.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 1");

            elevenLinearLayout3.setVisibility(View.VISIBLE);

            elevenBtn1.setText(buttonTitle3);
            elevenBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            elevenBtn2.setText(buttonTitle4);
            elevenBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            elevenBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            elevenBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            elevenLinearLayout4.setVisibility(View.VISIBLE);
            elevenBtn3.setText(buttonTitle3);
            elevenBtn3.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            elevenBtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.elevenImageView1))
                .execute(imageUploader);

    }

    private void showTwelthContent()
    {

    }

    private void showThirteenContent()
    {

    }

    private void showForteenContent()
    {

    }

    private void showFifteenContent()
    {
        invisibleAllOffer();
        fifteenOffer.setVisibility(View.VISIBLE);

        fifteenOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        fifteenLinearLayout1.setVisibility(View.GONE);
        fifteenLinearLayout2.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            fifteenLinearLayout1.setVisibility(View.VISIBLE);

            fifteenBtn1.setText(buttonTitle3);
            fifteenBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            fifteenBtn2.setText(buttonTitle4);
            fifteenBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            fifteenBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            fifteenBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            fifteenLinearLayout2.setVisibility(View.VISIBLE);
            fifteenBtn1.setText(buttonTitle3);
            fifteenBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            fifteenBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.fifteenImageView1))
                .execute(imageUploader);
    }

    private void showSixteenContent()
    {
        invisibleAllOffer();
        sixteenOffer.setVisibility(View.VISIBLE);

        sixteenOffer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onStartAction(actionBtn2,2);}
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
            {
                youTubePlayer.loadVideo(contentYoutubVideoID);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
            {
                System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> onInitializationFailure >>>>> ");
                Toast.makeText(getApplicationContext(),"YouTubePlayer.onInitializationFailure():",Toast.LENGTH_LONG).show();
            }
        };
        sixteenYoutubeView.initialize("API_KEY",onInitializedListener);

        sixteenLinearLayout2.setVisibility(View.GONE);
        sixteenLinearLayout3.setVisibility(View.GONE);

        if(!actionBtn3.equals("0") && !actionBtn4.equals("0") && !actionBtn3.equals("") && !actionBtn4.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 1");

            sixteenLinearLayout2.setVisibility(View.VISIBLE);

            sixteenBtn1.setText(buttonTitle3);
            sixteenBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            sixteenBtn2.setText(buttonTitle4);
            sixteenBtn2.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor2),"drawable",getPackageName()));

            sixteenBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });

            sixteenBtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn4,4);
                }
            });
        }
        else if(!actionBtn3.equals("0") && !actionBtn3.equals(""))
        {
            System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> showForthContent >>>>> 2");

            sixteenLinearLayout3.setVisibility(View.VISIBLE);
            sixteenBtn1.setText(buttonTitle3);
            sixteenBtn1.setBackgroundResource(getResources().getIdentifier(""+Constant.onSelectXML(buttonColor1),"drawable",getPackageName()));

            sixteenBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    onStartAction(actionBtn3,3);
                }
            });
        }

        new DownloadImageTask((ImageView) findViewById(R.id.sixteenImageView1))
                .execute(imageUploader);

    }

    private void showSeventeenContent()
    {
        invisibleAllOffer();
        seventeenOffer.setVisibility(View.VISIBLE);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b)
            {
                youTubePlayer.loadVideo("hmjlQ4vPt4c");
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult)
            {
                System.out.println(">> ShowOfferScreen.java >>>>> ShowOfferScreen >>>>> onInitializationFailure >>>>> ");
                Toast.makeText(getApplicationContext(),"YouTubePlayer.onInitializationFailure():",Toast.LENGTH_LONG).show();
            }
        };
        seventeenYoutubeView.initialize("API_KEY",onInitializedListener);
    }





    private void firstContentResize()
    {
        int hD = ((dHeight * 1400) / 1920);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(firstFramelayout.getLayoutParams().width,hD);
        firstFramelayout.setLayoutParams(lp);

        hD = ((dHeight * 200) / 1920);
        FrameLayout.LayoutParams fpl1 = new FrameLayout.LayoutParams(firstFramelayout2.getLayoutParams().width,hD);
        firstFramelayout2.setLayoutParams(fpl1);

        int tS = ((dWidth * 60) / 1080);
        firstTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 80) / 1080);
        firstTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int mT = ((dHeight * 50) / 1920);
        lp = (LinearLayout.LayoutParams) firstTextView2.getLayoutParams();
        lp.setMargins(0,mT,0,0);
        firstTextView2.setLayoutParams(lp);

        tS = ((dWidth * 100) / 1080);
        firstTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int mB = ((dHeight * 20) / 1920);
        lp = (LinearLayout.LayoutParams) firstLinearLayer1.getLayoutParams();
        lp.setMargins(0,0,0,mB);
        firstLinearLayer1.setLayoutParams(lp);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        firstBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        firstBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        lp = (LinearLayout.LayoutParams) firstLinearLayer2.getLayoutParams();
        lp.setMargins(0,0,0,mB);
        firstLinearLayer2.setLayoutParams(lp);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        firstBtn3.setLayoutParams(lp);
    }

    private void secondContentResize()
    {
        // second offer
        int hD = ((dHeight * 1200) / 1920);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(secondFramelayout.getLayoutParams().width, hD);
        secondFramelayout.setLayoutParams(lp);

        hD = ((dHeight * 200) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(secondFramelayout2.getLayoutParams().width, hD);
        secondFramelayout2.setLayoutParams(flp1);

        int tS = ((dWidth * 60) / 1080);
        secondTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        int mT = ((dHeight * 20) / 1920);
        lp = (LinearLayout.LayoutParams) secondLinearLayout1.getLayoutParams();
        lp.setMargins(0, mT, 0, 0);
        secondLinearLayout1.setPadding(pL, 0, pR, 0);
        secondLinearLayout1.setLayoutParams(lp);

        tS = ((dWidth * 60) / 1080);
        secondTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        secondTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 50) / 1080);
        pL = ((dWidth * 50) / 1080);
        hD = ((dHeight * 350) / 1920);
        secondTextView4.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        secondTextView4.setPadding(pL, 0, 0, 0);

        lp = new LinearLayout.LayoutParams(secondTextView4.getLayoutParams().width, hD);
        secondTextView4.setLayoutParams(lp);


        //Buttons
        int mB = ((dHeight * 20) / 1920);
        lp = (LinearLayout.LayoutParams) secondLinearLayout2.getLayoutParams();
        lp.setMargins(0, 0, 0, mB);
        secondLinearLayout2.setLayoutParams(lp);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        secondBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        lp.setMargins(mL, 0, 0, 0);
        secondBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        lp = (LinearLayout.LayoutParams) secondLinearLayout3.getLayoutParams();
        lp.setMargins(0, 0, 0, mB);
        secondLinearLayout3.setLayoutParams(lp);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        secondBtn3.setLayoutParams(lp);
    }

    private void thirdContentResize()
    {
        // third offer
        int hD = ((dHeight * 200) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(thirdFrameLayout0.getLayoutParams().width,hD);
        thirdFrameLayout0.setLayoutParams(flp1);

        int tS = ((dWidth * 60) / 1080);
        thirdTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int mT = ((dHeight * 1000) / 1920);
        flp1 = (FrameLayout.LayoutParams) thirdFrameLayout1.getLayoutParams();
        flp1.setMargins(0,mT,0,0);
        thirdFrameLayout1.setLayoutParams(flp1);

        hD = ((dHeight * 400) / 1920);
        flp1 = new FrameLayout.LayoutParams(thirdLinearLayout1.getLayoutParams().width,hD);
        thirdLinearLayout1.setLayoutParams(flp1);

        mT = ((dHeight * 80) / 1920);
        int mB = ((dHeight * 80) / 1920);
        flp1 = (FrameLayout.LayoutParams) thirdLinearLayout2.getLayoutParams();
        flp1.setMargins(0,mT,0,mB);
        thirdLinearLayout2.setLayoutParams(flp1);

        tS = ((dWidth * 80) / 1080);
        thirdTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 100) / 1080);
        thirdTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        //button
        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) thirdLinearLayout3.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        thirdLinearLayout3.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        LinearLayout.LayoutParams lp  = new LinearLayout.LayoutParams(wD,hD);
        thirdBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        thirdBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) thirdLinearLayout4.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        thirdLinearLayout4.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        thirdBtn3.setLayoutParams(lp);
    }

    private void forthContentResize()
    {
        //forth offer
        int hD = ((dHeight * 200) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(forthFrameLayout0.getLayoutParams().width,hD);
        forthFrameLayout0.setLayoutParams(flp1);

        int tS = ((dWidth * 60) / 1080);
        forthTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int mT = ((dHeight * 1000) / 1920);
        flp1 = (FrameLayout.LayoutParams) forthFrameLayout1.getLayoutParams();
        flp1.setMargins(0,mT,0,0);
        forthFrameLayout1.setLayoutParams(flp1);

        hD = ((dHeight * 400) / 1920);
        flp1 = new FrameLayout.LayoutParams(forthLinearLayout1.getLayoutParams().width,hD);
        forthLinearLayout1.setLayoutParams(flp1);

        mT = ((dHeight * 80) / 1920);
        int mB = ((dHeight * 80) / 1920);
        flp1 = (FrameLayout.LayoutParams) forthLinearLayout2.getLayoutParams();
        flp1.setMargins(0,mT,0,mB);
        forthLinearLayout2.setLayoutParams(flp1);

        tS = ((dWidth * 80) / 1080);
        forthTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 100) / 1080);
        forthTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);


        //button
        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) forthLinearLayout3.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        forthLinearLayout3.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wD,hD);
        forthBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        forthBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) forthLinearLayout4.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        forthLinearLayout4.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        forthBtn3.setLayoutParams(lp);
    }

    private void fifthContentResize()
    {
        int hD = ((dHeight * 700) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(fifthImage.getLayoutParams().width,hD);
        fifthImage.setLayoutParams(flp1);

        hD = ((dHeight * 200) / 1920);
        flp1 = new FrameLayout.LayoutParams(fifthFrameLayout1.getLayoutParams().width,hD);
        fifthFrameLayout1.setLayoutParams(flp1);


        int tS = ((dWidth * 60) / 1080);
        fifthTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 80) / 1080);
        int mT = ((dHeight * 580) / 1920);
        int mL = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) fifthTextView2.getLayoutParams();
        flp1.setMargins(mL,mT,0,0);
        fifthTextView2.setLayoutParams(flp1);
        fifthTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);


        int pR = ((dWidth * 30) / 1080);
        int pL = ((dWidth * 30) / 1080);
        hD = ((dHeight * 860) / 1920);
        mT = ((dHeight * 710) / 1920);
        fifthLinearLayout1.setPadding(pL,0,pR,0);
        flp1 = new FrameLayout.LayoutParams(fifthLinearLayout1.getLayoutParams().width,hD);
        flp1.setMargins(0,mT,0,0);
        fifthLinearLayout1.setLayoutParams(flp1);

        tS = ((dWidth * 50) / 1080);
        fifthTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        //Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) fifthLinearLayout2.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        fifthLinearLayout2.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wD,hD);
        fifthBtn1.setLayoutParams(lp);

        mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        fifthBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) fifthLinearLayout3.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        fifthLinearLayout3.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        fifthBtn3.setLayoutParams(lp);
    }

    private void sixthContentResize()
    {
        //sixth
        int tS = ((dWidth * 60) / 1080);
        sixthTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int hD = ((dHeight * 200) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(sixthFramelayout1.getLayoutParams().width,hD);
        sixthFramelayout1.setLayoutParams(flp1);

        hD = ((dHeight * 640) / 1920);
        int mT = ((dHeight * 950) / 1920);
        flp1 = new FrameLayout.LayoutParams(sixthLinearLayout1.getLayoutParams().width,hD);
        flp1.setMargins(0,mT,0,0);
        sixthLinearLayout1.setLayoutParams(flp1);

        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        mT = ((dHeight * 1000) / 1920);
        flp1 = (FrameLayout.LayoutParams) sixthLinearLayout2.getLayoutParams();
        flp1.setMargins(0,mT,0,0);
        sixthLinearLayout2.setPadding(pL,0,pR,0);
        sixthLinearLayout2.setLayoutParams(flp1);

        tS = ((dWidth * 60) / 1080);
        sixthTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 60) / 1080);
        sixthTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 50) / 1080);
        mT =  ((dHeight * 20) / 1920);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) sixthTextView4.getLayoutParams();
        lp.setMargins(0,mT,0,0);
        sixthTextView4.setLayoutParams(lp);
        sixthTextView4.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        //Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) sixthLinearLayout4.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        sixthLinearLayout4.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        sixthBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        sixthBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) sixthLinearLayout5.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        sixthLinearLayout5.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        sixthBtn3.setLayoutParams(lp);
    }

    private void sevenContentResize()
    {
        //seventh offer
        int tS = ((dWidth * 60) / 1080);
        int hD = ((dHeight * 200) / 1920);
        seventhTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(sevenFramelayout1.getLayoutParams().width,hD);
        sevenFramelayout1.setLayoutParams(flp1);

        hD = ((dHeight * 640) / 1920);
        int mT = ((dHeight * 950) / 1920);
        flp1 = new FrameLayout.LayoutParams(seventhLinearLayout1.getLayoutParams().width,hD);
        flp1.setMargins(0,mT,0,0);
        seventhLinearLayout1.setLayoutParams(flp1);


        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        mT = ((dHeight * 1000) / 1920);
        flp1 = (FrameLayout.LayoutParams) seventhLinearLayout2.getLayoutParams();
        flp1.setMargins(0,mT,0,0);
        seventhLinearLayout2.setPadding(pL,0,pR,0);
        seventhLinearLayout2.setLayoutParams(flp1);

        tS = ((dWidth * 60) / 1080);
        seventhTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 60) / 1080);
        seventhTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 50) / 1080);
        seventhTextView4.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        mT = ((dHeight * 20) / 1920);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) seventhTextView4.getLayoutParams();
        lp.setMargins(0,mT,0,0);
        seventhTextView4.setLayoutParams(lp);


        // Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) seventhLinearLayout4.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        seventhLinearLayout4.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        seventhBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        seventhBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) seventhLinearLayout5.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        seventhLinearLayout5.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        seventhBtn3.setLayoutParams(lp);

    }

    private void eigthContentResize()
    {
        //eigth offer

        int hD = ((dHeight * 650) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(eigthImage.getLayoutParams().width , hD);
        eigthImage.setLayoutParams(flp1);


        int tS = ((dWidth * 60) / 1080);
        eigthTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        hD = ((dHeight * 200) / 1920);
        flp1 = new FrameLayout.LayoutParams(eigthFramelayout1.getLayoutParams().width , hD);
        eigthFramelayout1.setLayoutParams(flp1);

        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        int mT = ((dHeight * 700) / 1920);
        flp1 = (FrameLayout.LayoutParams) eigthLinearLayout1.getLayoutParams();
        flp1.setMargins(0,mT,0,0);
        eigthLinearLayout1.setPadding(pL,0,pR,0);
        eigthLinearLayout1.setLayoutParams(flp1);

        tS = ((dWidth * 60) / 1080);
        eigthTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 60) / 1080);
        eigthTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);


        tS = ((dWidth * 50) / 1080);
        eigthTextView4.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        mT = ((dHeight * 50) / 1920);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) eigthTextView4.getLayoutParams();
        lp.setMargins(0,mT,0,0);
        eigthTextView4.setLayoutParams(lp);

        //Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) eigthLinearLayout2.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        eigthLinearLayout2.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        eigthBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        eigthBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) eigthLinearLayout3.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        eigthLinearLayout3.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        eigthBtn3.setLayoutParams(lp);

    }

    private void ninthContentResize()
    {
        //nine Offer
        int hD = ((dHeight * 700) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(ninthYoutubeView.getLayoutParams().width , hD);
        ninthYoutubeView.setLayoutParams(flp1);

        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        int mT = ((dHeight * 730) / 1920);
        flp1 = (FrameLayout.LayoutParams) ninthLinearLayout1.getLayoutParams();
        flp1.setMargins(0,mT,0,0);
        ninthLinearLayout1.setPadding(pL,0,pR,0);
        ninthLinearLayout1.setLayoutParams(flp1);

        int tS = ((dWidth * 60) / 1080);
        ninthTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 60) / 1080);
        ninthTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 50) / 1080);
        mT = ((dHeight * 50) / 1920);
        ninthTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ninthTextView3.getLayoutParams();
        lp.setMargins(0,mT,0,0);
        ninthTextView3.setLayoutParams(lp);

        //Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) ninthLinearLayout2.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        ninthLinearLayout2.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        ninthBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        lp.setMargins(mL,0,0,0);
        ninthBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) ninthLinearLayout3.getLayoutParams();
        flp1.setMargins(0,0,0,mB);
        ninthLinearLayout3.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD,hD);
        ninthBtn3.setLayoutParams(lp);
    }



    private void tenthContentResize()
    {
        //Tenth offer
        int tS = ((dWidth * 60) / 1080);
        tenthTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int hD = ((dHeight * 200) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(tenthFramelayout1.getLayoutParams().width, hD);
        tenthFramelayout1.setLayoutParams(flp1);

        int mT = ((dHeight * 950) / 1920);
        flp1 = (FrameLayout.LayoutParams) tenthLinearLayout1.getLayoutParams();
        flp1.setMargins(0, mT, 0, 0);
        tenthLinearLayout1.setLayoutParams(flp1);

        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        mT = ((dHeight * 1000) / 1920);
        flp1 = (FrameLayout.LayoutParams) tenthLinearLayout2.getLayoutParams();
        flp1.setMargins(0, mT, 0, 0);
        tenthLinearLayout2.setPadding(pL, 0, pR, 0);
        tenthLinearLayout2.setLayoutParams(flp1);

        tS = ((dWidth * 60) / 1080);
        tenthTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 60) / 1080);
        tenthTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 50) / 1080);
        mT = ((dHeight * 50) / 1920);
        tenthTextView4.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tenthTextView4.getLayoutParams();
        lp.setMargins(0, mT, 0, 0);
        tenthTextView4.setLayoutParams(lp);

        // Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) tenthLinearLayout3.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        tenthLinearLayout3.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        tenthBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        lp.setMargins(mL, 0, 0, 0);
        tenthBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) tenthLinearLayout4.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        tenthLinearLayout4.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        tenthBtn3.setLayoutParams(lp);
    }

    private void elevenContentResize()
    {
        //eleven offer
        int tS = ((dWidth * 60) / 1080);
        elevenTextView1.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        int hD = ((dHeight * 200) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(elevenFramelayout1.getLayoutParams().width, hD);
        elevenFramelayout1.setLayoutParams(flp1);

        int mT = ((dHeight * 950) / 1920);
        flp1 = (FrameLayout.LayoutParams) elevenLinearLayout1.getLayoutParams();
        flp1.setMargins(0, mT, 0, 0);
        elevenLinearLayout1.setLayoutParams(flp1);

        int pL = ((dWidth * 50) / 1080);
        int pR = ((dWidth * 50) / 1080);
        mT = ((dHeight * 1000) / 1920);
        flp1 = (FrameLayout.LayoutParams) elevenLinearLayout2.getLayoutParams();
        flp1.setMargins(0, mT, 0, 0);
        elevenLinearLayout2.setPadding(pL, 0, pR, 0);
        elevenLinearLayout2.setLayoutParams(flp1);

        tS = ((dWidth * 60) / 1080);
        elevenTextView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 60) / 1080);
        elevenTextView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);

        tS = ((dWidth * 50) / 1080);
        mT = ((dHeight * 50) / 1920);
        elevenTextView4.setTextSize(TypedValue.COMPLEX_UNIT_PX, tS);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) elevenTextView4.getLayoutParams();
        lp.setMargins(0, mT, 0, 0);
        elevenTextView4.setLayoutParams(lp);

        //Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) elevenLinearLayout3.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        elevenLinearLayout3.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        elevenBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        lp.setMargins(mL, 0, 0, 0);
        elevenBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) elevenLinearLayout4.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        elevenLinearLayout4.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        elevenBtn3.setLayoutParams(lp);

    }

    private void fifteenContentResize()
    {
        //fifteen offer
        int mB = ((dHeight * 20) / 1920);
        FrameLayout.LayoutParams flp1 = (FrameLayout.LayoutParams) fifteenLinearLayout1.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        fifteenLinearLayout1.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        int hD = ((dHeight * 150) / 1920);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wD, hD);
        fifteenBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        lp.setMargins(mL, 0, 0, 0);
        fifteenBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) fifteenLinearLayout2.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        fifteenLinearLayout2.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        fifteenBtn3.setLayoutParams(lp);
    }

    private void sixteenContentResize()
    {
        //sixteen offer
        int hD = ((dHeight * 700) / 1920);
        FrameLayout.LayoutParams flp1 = new FrameLayout.LayoutParams(sixteenYoutubeView.getLayoutParams().width, hD);
        sixteenYoutubeView.setLayoutParams(flp1);


        int mT = ((dHeight * 700) / 1920);
        flp1 = (FrameLayout.LayoutParams) sixteenLinearLayout1.getLayoutParams();
        flp1.setMargins(0, mT, 0, 0);
        sixteenLinearLayout1.setLayoutParams(flp1);

        //Button
        int mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) sixteenLinearLayout2.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        sixteenLinearLayout2.setLayoutParams(flp1);

        int wD = ((dWidth * 350) / 1080);
        hD = ((dHeight * 150) / 1920);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(wD, hD);
        sixteenBtn1.setLayoutParams(lp);

        int mL = ((dHeight * 20) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        lp.setMargins(mL, 0, 0, 0);
        sixteenBtn2.setLayoutParams(lp);

        mB = ((dHeight * 20) / 1920);
        flp1 = (FrameLayout.LayoutParams) sixteenLinearLayout3.getLayoutParams();
        flp1.setMargins(0, 0, 0, mB);
        sixteenLinearLayout3.setLayoutParams(flp1);

        wD = ((dWidth * 500) / 1080);
        hD = ((dHeight * 150) / 1920);
        lp = new LinearLayout.LayoutParams(wD, hD);
        sixteenBtn3.setLayoutParams(lp);
    }





    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>
    {
        ImageView bmImage;
        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public class listAdapter extends BaseAdapter
    {
        ArrayList result;
        Context context;

        public LayoutInflater inflater=null;

        public listAdapter(ShowOfferScreen offerContext, ArrayList list)
        {
            result = list;
            context = offerContext;
            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View rowView;
            TextView tv1, tv2;

            rowView = inflater.inflate(R.layout.eddaystone_rend, null);

//            tv1 = (TextView) rowView.findViewById(R.id.txtName);
//            tv1.setText(result.get(i));

//            tv2 = (TextView) rowView.findViewById(R.id.txtUniqId);
//            tv2.setText(result.get(i));

            return rowView;
        }
    }
}
