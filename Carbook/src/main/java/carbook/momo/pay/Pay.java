package carbook.momo.pay;

import com.google.gson.Gson;
import carbook.momo.pay.models.*;
import carbook.momo.pay.processor.*;
import carbook.momo.shared.constants.Parameter;
import carbook.momo.shared.constants.RequestType;
import carbook.momo.shared.sharedmodels.Environment;
import carbook.momo.shared.sharedmodels.PartnerInfo;
import carbook.momo.shared.utils.Encoder;
import carbook.momo.shared.utils.LogUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Pay {

	// public static void main(String... args) throws Exception {

	 //       LogUtils.init();

	//        String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAlsL+G4UyFO0UQsQ4cAXuGYcn38d67PluKmeJqS2RcAqnNUFJjQieI5DSCyHVgAmPpUfDZ3CiSw+5NCfnjgChd/p4fq3bnGqSIw2JP78UROQDJYqfAc+WLvT29IgCH4O+P9+lOLUj2EWf8aqHxBwC1YPxtxK+8M+LKdVMAvZd3lXE3MBg9wTDYEcCNODXkNma/SfIKJCvmVWdKeKXd6IwW7yA0oTjdguAeqP8+O8jLjxJH57otRh63iX945vqAX2YAm9qzVoiDcWpv+UubRmbZ9l0moQwkdsDyCtCYPUtcW6kkdxuhlq8rg8RAVcinsz/843CBYHtqaUaAFQU1TO5EXiXT87zx/Oj2Bf4OC+iAJL/UQ4ASeL1vMoOfDSpSE8EnqKPyP+rM/H7oUaJrIin8KkrxmDLGQWKhNcTFO6UNPv3Hh13tEBv0GRy2vktL8+CWhrYHouXF2XwpS8uR/gH/Vl+5HT/HsTv/13gjSoGBQcdfyck9ZyHh5oBrQTds52C2vabCqWCEafRMbpj7lSrDWS2Df+XznR/hGkgewSdSZ/M0VK/DLadJ3x1Yhblv1HVw3jA3xzY1/zlNOZReLuvW6/kdRwJV/Zj5bd9eLJnz9jDPUcB0hAO+JuJYfTVuhZG9Beo1JbQ9+cFx+92ELn/yHDMod6rfrfBjikU9Gkxor0CAwEAAQ==";

	//        PartnerInfo devInfo = new PartnerInfo("MOMOLRJZ20181206", "TNWFx9JWayevKPiB8LyTgODiCSrjstXN", "KqBEecvaJf1nULnhPF5htpG3AMtDIOlD",publicKey);
	 //       //change the endpoint according to the appropriate processes

	 //       // Thông tin cần cung cấp cho việc cấp mã
	        
	   //     String commit = RequestType.CONFIRM_APP_TRANSACTION;
	 //       String rollback = RequestType.CANCEL_APP_TRANSACTION;
	 //       long amount = 10000;
	 //       String partnerRefId = String.valueOf(System.currentTimeMillis());
	 //       String partnerTransId = "1561046083186";
	 //       String requestId = String.valueOf(System.currentTimeMillis());
	 //       String momoTransId = "147938695";
	 //       String customerNumber = "0963181714";
	 //       String partnerName = "1561046083186";
	 //       String storeId = "";
	 //       String storeName = "1561046083186";
	//        String appData = "1561046083186";//data from MoMo app
	//        String description = "Pay with MoMo";
	//        String paymentCode = "MM515023896957011876";


	  //  }
	 
	 
	 // Đã có giao dịch, đã có mã tranid
	    //generate RSA signature from given information
	 
	//    public static String generateRSA(String phoneNumber, String billId, String transId,
	//    		String username, String partnerCode, long amount, String publicKey) throws Exception {
	//        // current version of Parameter key name is 2.0
	//        Map<String, Object> rawData = new HashMap<String, Object>();
	//       rawData.put(Parameter.CUSTOMER_NUMBER, phoneNumber);
	//        rawData.put(Parameter.PARTNER_REF_ID, billId);
	//        rawData.put(Parameter.PARTNER_TRANS_ID, transId);
	 //       rawData.put(Parameter.USERNAME, username);
	//        rawData.put(Parameter.PARTNER_CODE, partnerCode);
	//        rawData.put(Parameter.AMOUNT, amount);

	//        Gson gson = new Gson();
	//        String jsonStr = gson.toJson(rawData);
	//        byte[] testByte = jsonStr.getBytes(StandardCharsets.UTF_8);
	//        String hashRSA = Encoder.encryptRSA(testByte, publicKey);

	//        return hashRSA;
	//    }
	}