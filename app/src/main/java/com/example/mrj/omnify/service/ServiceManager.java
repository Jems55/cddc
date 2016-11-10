package com.example.mrj.omnify.service;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;

import com.example.mrj.omnify.util.Constant;

public class ServiceManager
{

	//AUTO LOGIN SERVICE
//	public static void getSenderId(Handler handler) {
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", "project_no");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.USER_LOGIN);
//		jthread.start();
//	}

	public static void autoLogin(Handler handler, String email, String pass)
	{
		System.out.println("Hello");
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("event", Constant.SERVICE_LOGIN);
			jObject.put(Constant.PARAM_EMAIL,email);
			jObject.put(Constant.PARAM_PASS,pass);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.SERVICE_LOGIN_RESPONSE);
		jthread.start();

	}

	//REGISTRATION SERVICE
	public static void userRegistration(Handler handler, String name, String email, String pass) {
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("event", Constant.USER_SIGNUP);
			jObject.put(Constant.PARAM_NAME,name);
			jObject.put(Constant.PARAM_EMAIL,email);
			jObject.put(Constant.PARAM_PASS,pass);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.SERVICE_SIGNUP_RESPONSE);
		jthread.start();

	}

	//GET CAMPAIGN FROM THE EDDAYSTONE ID
	public static void getCampaign(Handler handler, String eddayStoneId) {
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("event", Constant.COMPAINGNS);
			jObject.put(Constant.PARAM_EDDAYSTONE_ID,eddayStoneId);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.SERVICE_COMPAINGNS_RESPONSE);
		jthread.start();

	}

	// settingsService
	public static void onSettingsChanged(Handler handler, String userId, String gender, String birthYear) {
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("event", Constant.SETTING_USER);
			jObject.put(Constant.USER_ID,userId);
			jObject.put(Constant.GENDER,gender);
			jObject.put(Constant.BIRTH_DATE,birthYear);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.SERVICE_SETTING_RESPONSE);
		jthread.start();

	}

	public static void onSettingsChangedCat(Handler handler, int userId, String category) {
		JSONObject jObject = new JSONObject();
		try {
			jObject.put("event", Constant.SETTING_CAT);
			jObject.put(Constant.USER_ID,userId);
			jObject.put(Constant.CATEGORY,category);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.SERVICE_SETTING_RESPONSE_OTHER);
		jthread.start();

	}




//	// TO GET LIKE DATA[USE XX COIN TO GET XX LIKES]
//		public static void getLikeData(Handler handler)
//		{
//			JSONObject jObject = new JSONObject();
//			try {
//				jObject.put("action", Constant.SERVICE_GET_LIKE_DATA);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//
//			jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_GET_LIKE_DATA);
//			jthread.start();
//		}
//
//	// TO del removed access token from instagram
//			public static void delAt(Handler handler, String at)
//			{
//				JSONObject jObject = new JSONObject();
//				try {
//					jObject.put("action", "delete_user");
//					jObject.put("access_token", at);
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//
//				jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_DEL_AT);
//				jthread.start();
//			}
//
//		// TO del removed mediaid from instagram
//		public static void delMid(Handler handler, String id)
//		{
//			JSONObject jObject = new JSONObject();
//			try {
//				jObject.put("action", "delete_media");
//				jObject.put("media_id", id);
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//
//			jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_DEL_MID);
//			jthread.start();
//		}
//	// TO GET FOLLOWERS DATA[USE XX COIN TO GET XX FOLLOWERS]
//	public static void getFollowersData(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_GET_FOLLOWERS_DATA);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_GET_FOLLOWER_DATA);
//		jthread.start();
//	}
//
//
//	// TO GET NUMBER OF COIN PER LIKE_FOLLOW
//	public static void getCoinPerLikeFollow(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_GET_CONFIG);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_GET_COIN_PER_LIKE_FOLLOW);
//		jthread.start();
//	}
//
//	// TO GET LIKER
//	public static void getFollowers(Handler handler, int coins, int follower)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_GET_FOLLOW);
//			jObject.put(Constant.PARAM_USER_ID, Constant.USER_ID);
//			jObject.put(Constant.PARAM_COIN, String.valueOf(coins));
//			jObject.put(Constant.PARAM_FOLLOWER, String.valueOf(follower));
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_FOLLOWER_SENT);
//		jthread.start();
//	}
//
//	// TO GET FOLLOWER
//	public static void getLikes(Handler handler, int coins, int likes, String media_id)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_GET_LIKES);
//			jObject.put(Constant.PARAM_USER_ID, Constant.USER_ID);
//			jObject.put(Constant.PARAM_COIN, String.valueOf(coins));
//			jObject.put(Constant.PARAM_LIKE, String.valueOf(likes));
//			jObject.put(Constant.PARAM_MEDIA_ID, media_id);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_LIKE_SENT);
//		jthread.start();
//	}
//
//	// TO GET ACCOMPLISH DATA
//	public static void getAccomplishData(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_ACCOMPLISH);
//			jObject.put(Constant.PARAM_ACCESS_TOKEN, Constant.ACCESS_TOKEN);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_ACCOMP);
//		jthread.start();
//	}
//
//	// TO GET ALL ACCOUNT
//	public static void getAllAcc(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_GET_GROUP);
//			jObject.put(Constant.PARAM_ACCESS_TOKEN, Constant.ACCESS_TOKEN);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_COMMON_FOR_GET_ACC);
//		jthread.start();
//	}
//
//	// TO DELETE ACCOUNT
//	public static void delAcc(Handler handler, String at)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_DEL_ACC);
//			jObject.put(Constant.PARAM_ACCESS_TOKEN, at);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("get all acc"+jObject.toString());
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_COMMON_DID_DEL);
//		jthread.start();
//	}
//
//	// TO GET ALL COIN PARICE
//	public static void coinPrice(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_COIN_PRICE);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_COIN_PRICE);
//		jthread.start();
//	}
//
//	// TO BLOCK MEDIA
//	public static void blockMedia(Handler handler, String media_id, String user_id)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_BLOCK_MEDIA);
//			jObject.put("user_id", user_id);
//			jObject.put("media_id", media_id);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_BLOCK);
//		jthread.start();
//	}
//
//	// TO BLOCK USER PROFILE
//	public static void blockProfile(Handler handler, String follower_id, String user_id)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_BLOCK_PROFILE);
//			jObject.put("user_id", user_id);
//			jObject.put("follower_id", follower_id);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_BLOCK);
//		jthread.start();
//	}
//
//	// TO CUT COIN FOR UNFOLLOW OR DISLIKE
//	public static void unFollowDisLike(Handler handler, int coin)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_UN_FOLLOW);
//			jObject.put("access_token", Constant.ACCESS_TOKEN);
//			jObject.put("coins", coin);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_CUTCOIN);
//		jthread.start();
//	}
//
//	// TO GET LIST OF LAST WEEK FOLLOWING
//	public static void weeklyFollow(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_LAST_WEEK_FOLLOW);
//			jObject.put("access_token", Constant.ACCESS_TOKEN);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_LAST_WEEK_FOLLOW);
//		jthread.start();
//	}
//
//	// TO GET LIST OF LAST WEEK LIKE
//	public static void weeklyLike(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_LAST_WEEK_LIKE);
//			jObject.put("access_token", Constant.ACCESS_TOKEN);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_LAST_WEEK_LIKE);
//		jthread.start();
//	}
//
//	// ON SUCCESS OF LIKE
//	public static void likeTrack(Handler handler, String media_id)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_LIKE_TRACK);
//			jObject.put("user_id", Constant.USER_ID);
//			jObject.put("like_user_id", "0");
//			jObject.put("post_id", media_id);
//			jObject.put("coins", Constant.GET_COIN_PER_LIKE);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_LIKE_TRACK);
//		jthread.start();
//	}
//
//	// ON SUCCESS OF FOLLOWING
//	public static void followTrack(Handler handler, String id)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", Constant.SERVICE_FOLLOWER_TRACK);
//			jObject.put("user_id", Constant.USER_ID);
//			jObject.put("follower_id", id);
//			jObject.put("coins", Constant.GET_COIN_PER_FOLLOW);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(Constant.BASE_URL, jObject, handler, Constant.RESP_FOLLOW_TRACK);
//		jthread.start();
//	}
//
//
/////////////////////////// INSTAGRAM API SECTION /////////////////////////////////////////
//
//	//TO RETRIEVE ALL POST [PHOTOS-VIDEOES] USING "GET" METHOD
//	public static void getAllPostByUserInInstaGram(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod("https://api.instagram.com/v1/users/"+Constant.USER_ID+"/media/recent?access_token="+Constant.ACCESS_TOKEN, jObject,
//				handler, Constant.RESP_SERVICE_GET_LIKES);
//		jthread.start();
//	}
//
//	//TO RETRIEVE NEXT PAGE OF ALL POST [PHOTOS-VIDEOES] USING "GET" METHOD
//	public static void getNextPagePostByUserInInstaGram(Handler handler, String url)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod(url, jObject,
//				handler, Constant.NEXT_PAGE_RESP);
//		jthread.start();
//	}
//
//	//TO RETRIEVE NEXT PAGE OF ALL POST [PHOTOS-VIDEOES] USING "GET" METHOD
//	public static void getMedia(Handler handler, String media_id)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod("https://api.instagram.com/v1/media/"+media_id+"?access_token="+Constant.ACCESS_TOKEN, jObject,
//				handler, Constant.RESP_GET_MEDIA);
//		jthread.start();
//	}
//
//	//USER BASIC INFORMATION
//	public static void getUserData(Handler handler)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod("https://api.instagram.com/v1/users/"+Constant.USER_ID+"?access_token="+Constant.ACCESS_TOKEN, jObject,
//				handler, Constant.RESP_GET_USER_DATA);
//		jthread.start();
//	}
//
//	//USER BASIC INFORMATION
//		public static void getUserData(Handler handler, String id)
//		{
//			JSONObject jObject = new JSONObject();
//			try {
//			jObject.put("action", "");
//			} catch (JSONException e) {
//			e.printStackTrace();
//			}
//
//			jsonThreadGetMethod jthread= new jsonThreadGetMethod("https://api.instagram.com/v1/users/"+id+"?access_token="+Constant.ACCESS_TOKEN, jObject,
//					handler, Constant.RESP_COMMON_FOR_GET_URL);
//			jthread.start();
//		}
//
//	//TO GET DATA OF USER THAT DID REQUEST TO OTHER
//	public static void getRequestedUserData(Handler handler, String access_token)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod("https://api.instagram.com/v1/users/"+access_token.substring(0, access_token.indexOf("."))+"?access_token="+access_token, jObject,
//				handler, Constant.RESP_GET_DATA);
//		jthread.start();
//	}
//
//	//TO RECENT DATA OF USER THAT DID REQUEST TO OTHER
//	public static void getRecentPostOfUser(Handler handler, String access_token)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod("https://api.instagram.com/v1/users/"+access_token.substring(0, access_token.indexOf("."))+"/media/recent?access_token="+Constant.ACCESS_TOKEN, jObject,
//				handler, Constant.RESP_GET_USER_REQ_MEDIA);
//		jthread.start();
//	}
//
//	//TO CHECK USER HAS LIKED MEDIA OR NOT
//	public static void checkUserHasLikedMediaOrNot(Handler handler, String media_id)
//	{
//		System.out.println("global like or not");
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod
//	   ("https://api.instagram.com/v1/media/"+media_id+"/likes"+"?access_token="+Constant.ACCESS_TOKEN, jObject,
//				handler, Constant.RESP_GET_LIKES_OR_NOT);
//		jthread.start();
//	}
//
//	//TO CHECK USER HAS LIKED MEDIA OR NOT
//	public static void checkUserHasFollowedCurrentReqOrNot(Handler handler)
//	{
//
//		System.out.println("global follow or not");
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod
//	   ("https://api.instagram.com/v1/users/self/follows"+"?access_token="+Constant.ACCESS_TOKEN , jObject,
//				handler, Constant.RESP_CHECK_FOLLOWING_OR_NOT);
//		jthread.start();
//	}
//
//	//TO CHECK USER HAS LIKED MEDIA OR NOT
//	public static void checkNextPageOfFollowedCurrentReqOrNot(Handler handler, String url)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod
//	   (url, jObject, handler, Constant.RESP_CHECK_FOLLOWING_OR_NOT);
//		jthread.start();
//	}
//
//	//TO HIT LIKE TO MEDIA
//	public static void hitLike(Handler handler, String media_id)
//	{
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		jsonThreadPostMethod jthread= new jsonThreadPostMethod
//	   ("https://api.instagram.com/v1/media/"+media_id+"/likes"+"?access_token="+Constant.ACCESS_TOKEN, jObject, handler, Constant.RESP_HIT_LIKE);
//		jthread.start();
//	}
//
//	//TO HIT FOLLOW TO USER
//	public static void hitFollow(Handler handler, String user_id) {
//		JSONObject jObject = new JSONObject();
//		try {
//			jObject.put("action", "follow");
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		jsonThreadPostMethod jthread = new jsonThreadPostMethod(
//				"https://api.instagram.com/v1/users/"+user_id+"/relationship"+"?access_token="+Constant.ACCESS_TOKEN, jObject, handler,
//				Constant.RESP_HIT_FOLLOW);
//		jthread.start();
//	}
//
//	//TO CHECK PUBLIC PRIVATE
//	public static void checkPublicPrivate(Handler handler) {
//		JSONObject jObject = new JSONObject();
//		try {
//		jObject.put("action", "");
//		} catch (JSONException e) {
//		e.printStackTrace();
//		}
//		jsonThreadGetMethod jthread= new jsonThreadGetMethod("https://api.instagram.com/v1/users/"+Constant.USER_ID+"/relationship?access_token="+Constant.ACCESS_TOKEN, jObject,
//				handler, Constant.RESP_CHECK_PROFILE_TYPE);
//		jthread.start();
//	}

}

