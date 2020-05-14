package com.company.project.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {
	
    /**
     * 图片转化成base64字符串
     * @param imgPath
     * @return
     */
    public static String GetImageStr(String imgPath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = imgPath;// 待处理的图片
        InputStream in = null;
        byte[] data = null;
        String encode = null; // 返回Base64编码过的字节数组字符串
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        try {
            // 读取图片字节数组
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            encode = encoder.encode(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return encode;
    }

    /**
     * base64字符串转化成图片
     * 
     * @param imgData
     *            图片编码
     * @param imgFilePath
     *            存放到本地路径
     * @return
     * @throws IOException
     */
    @SuppressWarnings("finally")
    public static boolean GenerateImage(String imgData, String imgFilePath) throws IOException { // 对字节数组字符串进行Base64解码并生成图片
        if (imgData == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream out = null;
        try {
            out = new FileOutputStream(imgFilePath);
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgData);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            out.write(b);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
            return true;
        }
    }
    
    private static String strNetImageToBase64;
    /**
     * 网络图片转换Base64的方法
     *
     * @param netImagePath     
     */
    public static void netImageToBase64(String netImagePath) {
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(netImagePath);
            final byte[] by = new byte[1024];
            // 创建链接
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = conn.getInputStream();
                        // 将内容读取内存中
                        int len = -1;
                        while ((len = is.read(by)) != -1) {
                            data.write(by, 0, len);
                        }
                        // 对字节数组Base64编码
                        BASE64Encoder encoder = new BASE64Encoder();
                        strNetImageToBase64 = encoder.encode(data.toByteArray());
                        System.out.println("网络图片转换Base64:" + strNetImageToBase64);
                        // 关闭流
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    
    public static void imgToStr() throws Exception{
    	String imgStr = "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";
    	Base64Utils.GenerateImage(imgStr, "D://generator-output//aimg//out/tjp.jpg");
    }
    
    
    public static void main(String[] args) {
    	try {
//    		String imageStr = Base64Utils.GetImageStr("D://generator-output//aimg//in//skfq.jpg");
//    		System.out.println(imageStr);
//    		Base64Utils.GenerateImage(imageStr, "D://generator-output//aimg//out//new_skfq.jpg");
//    		imgToStr();
    		netImageToBase64("https://img.alicdn.com/bao/uploaded/i1/O1CN01t9tclP2E44NUtXVAQ_!!0-paimai.jpg_400x400.jpg");
    		if(strNetImageToBase64 != ""){
    			Base64Utils.GenerateImage(strNetImageToBase64, "D://generator-output//aimg//out//qmzm.jpg");
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
