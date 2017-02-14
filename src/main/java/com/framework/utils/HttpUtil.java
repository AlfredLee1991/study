package com.framework.utils;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * http工具类
 * 
 * @author lixu
 */
public class HttpUtil{

    /**
     * 日志记录器
     */
    private static Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {
    }

    /**
     * 方法描述：https方式post请求 ，重试3次<br/>
     *
     * #author lixu<br/>
     * #date 2015年12月30日 下午8:16:28<br/>
     * #since 1.0.0<br/>
     * 
     * @param urlToRead
     * @param o
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static String postHttpsJSON(String urlToRead, Object o) throws ClientProtocolException, IOException {
        String result = "";
        HttpClient httpClient = getClient(true);
        try {
            LOGGER.debug("===========请求URL===========：" + urlToRead);
            int retryCount = 0;
            while (retryCount < 4) {
                try {
                    HttpPost httpPost = new HttpPost(urlToRead);
                    httpPost.setHeader("Content-type", "application/json");
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(o);
                    LOGGER.debug("jsonString = " + jsonString);
                    StringEntity params = new StringEntity(jsonString, "UTF-8");
                    params.setContentType("application/json");
                    params.setContentEncoding("UTF-8");
                    httpPost.setEntity(params);
                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    result = httpClient.execute(httpPost, responseHandler);
                    break;
                } catch (Exception e) {
                    if (retryCount > 0) {
                        LOGGER.warn("请求" + urlToRead + "重试第" + retryCount + "次", e);
                    }
                    retryCount++;
                }
            }
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        LOGGER.debug("result" + result);
        return result;
    }

    /**
     * 方法描述：https方式post提交json数据，需要用户验证，重试3次 <br/>
     *
     * #author lixu<br/>
     * #date 2015年12月18日 上午9:46:50<br/>
     * #since 1.0.0<br/>
     * 
     * @param urlToRead
     * @param o
     * @param token
     * @return
     */
    public static Map<String, Object> postHttpsJSONWithToken(String urlToRead, Object o, String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpClient httpClient = getClient(true);
        LOGGER.debug("===========请求URL===========：" + urlToRead);
        try {
            HttpPost httpPost = new HttpPost(urlToRead);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + token);

            Gson gson = new Gson();
            String jsonString = gson.toJson(o);
            LOGGER.debug("jsonString = " + jsonString);
            StringEntity params = new StringEntity(jsonString, "UTF-8");
            params.setContentType("application/json");
            params.setContentEncoding("UTF-8");
            httpPost.setEntity(params);
            HttpResponse response = httpClient.execute(httpPost);
            map.put("statusCode", response.getStatusLine().getStatusCode());
            String responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
            EntityUtils.consume(response.getEntity());
            map.put("entity", responseContent);
        } catch (Exception e) {
            LOGGER.error("https请求调用失败", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return map;
    }

    /**
     * 方法描述：https方式get提交json数据，需要用户验证，重试3次<br/>
     *
     * #author lixu<br/>
     * #date 2016年1月6日 下午7:08:11<br/>
     * #since 1.0.0<br/>
     * 
     * @param urlToRead
     * @param o
     * @param token
     * @return
     */
    public static Map<String, Object> getHttpsJSONWithToken(String urlToRead, String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpClient httpClient = getClient(true);
        try {
            LOGGER.debug("===========请求URL===========：" + urlToRead);
            int retryCount = 0;
            while (retryCount < 4) {
                try {
                    HttpGet httpGet = new HttpGet(urlToRead);
                    httpGet.setHeader("Content-type", "application/json");
                    httpGet.setHeader("Authorization", "Bearer " + token);
                    HttpResponse response = httpClient.execute(httpGet);

                    map.put("statusCode", response.getStatusLine().getStatusCode());
                    String responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
                    EntityUtils.consume(response.getEntity());
                    map.put("entity", responseContent);
                    break;
                } catch (Exception e) {
                    if (retryCount > 0) {
                        LOGGER.warn("请求" + urlToRead + "重试第" + retryCount + "次", e);
                    }
                    retryCount++;
                }
            }
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return map;
    }

    /**
     * 方法描述：https方式put提交json数据，重试3次 <br/>
     *
     * #author lixu<br/>
     * #date 2015年12月30日 下午8:51:59<br/>
     * #since 1.0.0<br/>
     * 
     * @param urlToRead
     * @param o
     * @param token
     * @return
     */
    public static Map<String, Object> putHttpsJSONWithToken(String urlToRead, Object o, String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpClient httpClient = getClient(true);
        LOGGER.debug("===========请求URL===========：" + urlToRead);
        try {
            HttpPut httpPut = new HttpPut(urlToRead);
            httpPut.setHeader("Content-type", "application/json");
            httpPut.setHeader("Authorization", "Bearer " + token);

            Gson gson = new Gson();
            String jsonString = gson.toJson(o);
            LOGGER.debug("jsonString = " + jsonString);
            StringEntity params = new StringEntity(jsonString, "UTF-8");
            params.setContentType("application/json");
            params.setContentEncoding("UTF-8");
            httpPut.setEntity(params);
            HttpResponse response = httpClient.execute(httpPut);

            map.put("statusCode", response.getStatusLine().getStatusCode());
            String responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
            EntityUtils.consume(response.getEntity());
            map.put("entity", responseContent);
        } catch (Exception e) {
            LOGGER.error("https请求调用失败", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return map;
    }

    /**
     * 方法描述：https方式delete提交json数据，需要用户验证，重试3次<br/>
     *
     * #author lixu<br/>
     * #date 2015年12月18日 下午2:04:54<br/>
     * #since 1.0.0<br/>
     * 
     * @param urlToRead
     * @param o
     * @param token
     * @return
     */
    public static Map<String, Object> deleteHttpsJSONWithToken(String urlToRead, Object o, String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpClient httpClient = getClient(true);
        LOGGER.debug("===========请求URL===========：" + urlToRead);
        try {
            HttpDelete httpDelete = new HttpDelete(urlToRead);
            httpDelete.setHeader("Content-type", "application/json");
            httpDelete.setHeader("Authorization", "Bearer " + token);

            Gson gson = new Gson();
            String jsonString = gson.toJson(o);
            LOGGER.debug("jsonString = " + jsonString);
            HttpResponse response = httpClient.execute(httpDelete);

            map.put("statusCode", response.getStatusLine().getStatusCode());
            String responseContent = EntityUtils.toString(response.getEntity(), "UTF-8");
            EntityUtils.consume(response.getEntity());
            map.put("entity", responseContent);
        } catch (Exception e) {
            LOGGER.error("https请求调用失败", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return map;
    }

    /**
     * 
     * 方法描述：post请求表单 <br/>
     *
     * #author jpj-焦彭举<br/>
     * #date 2015年11月30日 下午1:39:50<br/>
     * #since 1.0.0<br/>
     * 
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            LOGGER.debug("===========请求URL===========：" + url);
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 对象转换
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                LOGGER.debug("response.getStatusLine() = " + response.getStatusLine());
                HttpEntity entity = response.getEntity();
                LOGGER.debug("内容类型:" + entity.getContentType());
                LOGGER.debug("内容的编码格式:" + entity.getContentEncoding());
                LOGGER.debug("内容的长度:" + entity.getContentLength());
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 
     * 方法描述：Post表单请求 <br/>
     *
     * #author 8106567-孙月军<br/>
     * #date 2016年5月25日 上午11:17:39<br/>
     * #since 1.0.0<br/>
     * 
     * @param url
     * @param headers
     *            请求Header里面的属性集合
     * @param params
     *            请求参数集合
     * @return
     */
    public static String doPost(String url, Map<String, Object> headers, Map<String, Object> params) {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            LOGGER.debug("===========请求URL===========：" + url);
            HttpPost httpPost = new HttpPost(url);
            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, Object> entry : headers.entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 对象转换
            if (params != null && params.size() > 0) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                LOGGER.debug("response.getStatusLine() = " + response.getStatusLine());
                HttpEntity entity = response.getEntity();
                LOGGER.debug("内容类型:" + entity.getContentType());
                LOGGER.debug("内容的编码格式:" + entity.getContentEncoding());
                LOGGER.debug("内容的长度:" + entity.getContentLength());
                result = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * get请求
     * 
     * @param url
     *            url地址
     * 
     * @author jiaopengju
     * @since 1.0.0
     * @date 2015-10-08
     */
    public static String doGet(String url) throws Exception {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            LOGGER.debug("===========请求URL===========：" + url);
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpGet);
            try {
                LOGGER.debug("response.getStatusLine() = " + response.getStatusLine());
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "utf8");
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            httpclient.close();
        }
        return result;
    }

    /**
     * Create a httpClient instance
     * 
     * @param isSSL
     * @return HttpClient instance
     */
    public static HttpClient getClient(boolean isSSL) {

        HttpClient httpClient = new DefaultHttpClient();
        if (isSSL) {
            X509TrustManager xtm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            try {
                SSLContext ctx = SSLContext.getInstance("TLS");

                ctx.init(null, new TrustManager[] { xtm }, null);

                SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);

                httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        return httpClient;
    }
    
    
    public static HttpEntity getEntity(HttpResponse response){
        
        HttpEntity entity = response.getEntity();
        Header header = entity.getContentEncoding();
        if(header != null)
        {
            HeaderElement arr[] = header.getElements();
            int len = arr.length;
            int i = 0;
            do
            {
                if(i >= len)
                    break;
                HeaderElement element = arr[i];
                if(element.getName().toLowerCase().indexOf("gzip") != -1)
                {
                    entity = new GzipDecompressingEntity(entity);
                    break;
                }
                i++;
            } while(true);
        }
        return entity;
    }

}
