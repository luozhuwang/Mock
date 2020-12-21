package com.mock.tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

public class JsonUtils {

	private  static Boolean flag;
	
	/**
	 * 比较两个JSON是否一致
	 * 这2种数据比较有误差
	 * {"code": 0,"data": ["productId：2020","productId：2021"],"msg": "success","errMsg": null}
	 * {"code": 0,"data": ["productId：2021","productId：2020"],"msg": "success","errMsg": null}
	 * @param info1
	 * @param info2
	 */
	public static Boolean compareTwoDeviceInfo(JSONObject info1,JSONObject info2) {
		if (info1==null && info2!=null) {
			return false;
		}else if(info1!=null&&info2==null) {
			return false;
		}else if (info1.size()!=info2.size()) {
			return false;
		}
		for(String key: info1.keySet()) {
			try {
				JSONObject sonJSON1 = info1.getJSONObject(key);
				JSONObject sonJSON2 = info2.getJSONObject(key);
				flag=compareTwoDeviceInfo(sonJSON1, sonJSON2);
				if(flag == false) {
					return flag;
				}
			}catch (Exception e) {	
				try {
					JSONArray arr1 = info1.getJSONArray(key);
					JSONArray arr2 = info2.getJSONArray(key);
//					JSONArray newarr1=Jsonsort(arr1);
//					JSONArray newarr2=Jsonsort(arr2);
					flag=compareTwoDeviceInfoArry(arr1,arr2);
					if(flag == false) {
						return flag;
					}
				} catch (Exception e2) {
					if(!info1.getString(key).equals(info2.getString(key))) {
						System.err.println(info1.getString(key));
						System.err.println(info2.getString(key));
						return false;
					}else {
						flag=true;
					}
				}
			}
		}
		for(String key: info2.keySet()) {
 
				try {
					JSONObject sonJSON1 = info1.getJSONObject(key);
					JSONObject sonJSON2 = info2.getJSONObject(key);
					flag=compareTwoDeviceInfo(sonJSON1, sonJSON2);
					if(flag == false) {
						return flag;
					}
				}catch (Exception e) {	
					try {
						JSONArray arr1 = info1.getJSONArray(key);
						JSONArray arr2 = info2.getJSONArray(key);
						flag=compareTwoDeviceInfoArry(arr1,arr2);
						if(flag == false) {
							return flag;
						}
					} catch (Exception e2) {
						
						if(!info1.getString(key).equals(info2.getString(key))) {
							System.err.println(info1.getString(key));
							System.err.println(info2.getString(key));
							return false;
						}else {
							flag = true;
						}
					}
				}
 
		}
		return flag;
	}
	
	private static  boolean compareTwoDeviceInfoArry(JSONArray arr1,JSONArray arr2) {
		if(arr1==null && arr2!=null) {
			return false;
		}
		else if(arr1!=null && arr2==null) {
			return false;
		}
		else if(arr1==null && arr2==null) {
			return true;
		}
		else if(arr1.size()!=arr2.size()){
			return false;
		}
		for(int i=0;i<arr1.size();i++) {
			try {
				JSONObject json1 = arr1.getJSONObject(i);
				JSONObject json2 = arr2.getJSONObject(i);
				flag=compareTwoDeviceInfo(json1, json2);
				if(flag == false) {
					return flag;
				}
			} catch (Exception e) {
				try {
					JSONArray sonArr1 = arr1.getJSONArray(i);
					JSONArray sonArr2 = arr2.getJSONArray(i);
					flag=compareTwoDeviceInfoArry(sonArr1,sonArr2);
					if(flag == false) {
						return flag;
					}
				} catch (Exception e2) {
					
					if(!arr1.getString(i).equals(arr2.getString(i))) {
						System.err.println(arr1.getString(i));
						System.err.println(arr2.getString(i));
						return false;
					}else {
						flag=true;
					}
				}
			}
			
		}
		return flag;
	}

	private JSONArray Jsonsort( JSONArray jsonArr) {
		JSONArray sortedJsonArray = new JSONArray();

	    List<JSONObject> jsonValues = new ArrayList<JSONObject>();
	    for (int i = 0; i < jsonArr.size(); i++) {
	        jsonValues.add(jsonArr.getJSONObject(i));
	    }
	    Collections.sort( jsonValues, new Comparator<JSONObject>() {
	        //You can change "Name" with "ID" if you want to sort by ID
	        private static final String KEY_NAME = "Name";

	        @Override
	        public int compare(JSONObject a, JSONObject b) {
	            String valA = new String();
	            String valB = new String();

	            try {
	                valA = (String) a.get(KEY_NAME);
	                valB = (String) b.get(KEY_NAME);
	            } 
	            catch (JSONException e) {
	                //do something
	            }

	            return valA.compareTo(valB);
	            //if you want to change the sort order, simply use the following:
	            //return -valA.compareTo(valB);
	        }
	    });

	    for (int i = 0; i < jsonArr.size(); i++) {
	        sortedJsonArray.add(jsonValues.get(i));
	    }
	    return sortedJsonArray;
	}
	
	public static void main(String args[]) {
		JsonUtils util=new JsonUtils();
		String json="{\"list\":[\"productId：2021\",\"productId：2020\"]}";
		 JSONArray jsonArr = JSON.parseArray(json);
		 JSONArray jsonArr2=util.Jsonsort(jsonArr);
		 for(int i=0;i<jsonArr2.size();i++) {
			 System.out.println(jsonArr2.getJSONObject(i).toString());
		 }
		 
	}
}
