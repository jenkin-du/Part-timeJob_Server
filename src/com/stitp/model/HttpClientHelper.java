package com.stitp.model;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
public class HttpClientHelper {
	/**
     * 使用默认的实例将采用允许所有SSL连接的方式
     */
    public static HttpClientHelper INSTANCE = new HttpClientHelper();
    private SSLConnectionSocketFactory sslsf = null;
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private HttpClient client;


    public HttpClientHelper() {
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        try {
            ctx.init(new KeyManager[0],
                    new TrustManager[]{new NoCheckTrustManager()},
                    new SecureRandom());
        } catch (KeyManagementException e1) {
            e1.printStackTrace();
        }
        sslsf = new SSLConnectionSocketFactory(ctx,
                SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslsf)
                .build();
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(300);
        poolingHttpClientConnectionManager.setMaxTotal(100);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .disableAutomaticRetries();
        httpClientBuilder.setSSLSocketFactory(this.sslsf);

        client = httpClientBuilder.build();
    }


    /**
     * 需要证书的接口调用需要用此构造函数传入证书HTTPS请求池初始化
     *
     * @param type         证书类型，如果传入null，则使用PKCS12
     * @param certFilePath 证书文件地址
     * @param certPassword 证书密码
     */
    public HttpClientHelper(String type, String certFilePath, String certPassword) {
        KeyStore keyStore = null;
        FileInputStream instream = null;
        try {
            keyStore = KeyStore.getInstance(type == null ? "PKCS12" : type);
            instream = new FileInputStream(new File(certFilePath));
            keyStore.load(instream, certPassword.toCharArray());
            SSLContext sslcontext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, certPassword.toCharArray())
                    .build();
            // Allow TLSv1 protocol only
            sslsf = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf)
                    .build();
            poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create()
                    .setConnectionManager(poolingHttpClientConnectionManager)
                    .disableAutomaticRetries();
            httpClientBuilder.setSSLSocketFactory(this.sslsf);
            client = httpClientBuilder.build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            try {
                instream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回连接池
     *
     * @return
     */
    public PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
        return poolingHttpClientConnectionManager;
    }

    /**
     * 构建一个连接
     *
     * @return
     */
    public HttpClient getClient() {
        return client;
    }

    public String get(String url, Charset charset) throws IOException {
        HttpResponse httpResponse = get(url, null, null, null, null);
        return EntityUtils.toString(httpResponse.getEntity(), charset == null ? Consts.UTF_8 : charset);
    }

    /**
     * 发起GET请求，返回string
     *
     * @param url
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public String get(String url) throws IOException {
        HttpResponse httpResponse = get(url, null, null, null, null);
        return EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8);
    }

    /**
     * 发起GET请求，返回string
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String get(String url, Map<String, String> params) throws IOException {
        List<NameValuePair> paramsList = null;
        if (params != null) {
            paramsList = new ArrayList<NameValuePair>();
            for (String key : params.keySet()) {
                paramsList.add(new BasicNameValuePair(key, params.get(key)));
            }
        }
        HttpResponse httpResponse = get(url, paramsList, null);
        return EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8);
    }


    /**
     * 发起GET请求
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public HttpResponse get(String url,
                            List<? extends NameValuePair> params,
                            Header[] headers) throws ParseException, IOException {
        return get(url, params, null, null, headers);
    }

    /**
     * 发起GET请求
     *
     * @param url
     * @param params
     * @param formCharset
     * @param cookies
     * @param headers
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public HttpResponse get(String url,
                            List<? extends NameValuePair> params,
                            String formCharset, CookieStore cookies, Header[] headers)
            throws ParseException, IOException {
        HttpResponse resp = null;
        HttpClient client = getClient();
        if (params != null) {
            if (formCharset == null) {
                formCharset = "UTF-8";
            }
            String str = EntityUtils.toString(new UrlEncodedFormEntity(params,
                    formCharset));
            System.out.println(str);

            if (url.indexOf("?") > -1) {
                url += "&" + str;
            } else {
                url += "?" + str;
            }
        }
        HttpGet get = new HttpGet(url);
        get.setHeaders(headers);
        // get.setHeader("User-Agent", AGENT_FIREFOX);

        if (cookies != null) {
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookies);
            resp = client.execute(get, context);
        } else {
            resp = client.execute(get);
        }


        return resp;
    }

    /**
     * 发起POST请求
     *
     * @param url
     * @param params  参数列表
     * @param headers HTTP头
     * @return
     * @throws IOException
     */
    public HttpResponse post(String url,
                             List<? extends NameValuePair> params,
                             Header[] headers) throws IOException {
        return post(url, params, null, null, headers);
    }


    /**
     * 发起POST请求
     * @param url
     * @param stringEntity
     * @param charset
     * @return
     * @throws IOException
     */
    public HttpResponse postString(String url, String stringEntity, String charset) throws IOException {
        return postString(url, stringEntity, charset, null);
    }

    /**
     * 发起POST请求
     *
     * @param url
     * @param stringEntity POST字符串
     * @param charset      请求的字符串编码，默认为使用 UTF-8 编码
     * @return
     * @throws IOException
     */
    public HttpResponse postString(String url, String stringEntity, String charset, Header[] headers) throws IOException {
        HttpResponse resp = null;
        HttpClient client = getClient();
        HttpPost post = new HttpPost();
        post.setHeaders(headers);
        post.setURI(URI.create(url));
        post.setEntity(new StringEntity(
                stringEntity, charset == null ? "UTF-8" : charset));
        resp = client.execute(post);
        return resp;
    }

    /**
     * 发起POST请求返回字符串
     *
     * @param url
     * @param stringEntity POST字符串 使用 UTF-8 编码
     * @return 返回的内容也使用UTF-8编码
     * @throws IOException
     */
    public String post(String url, String stringEntity) throws IOException {
        HttpResponse httpResponse = postString(url, stringEntity, null);
        return EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
    }

    /**
     * 发起POST请求返回字符串
     *
     * @param url
     * @param stringEntity POST字符串 使用 UTF-8 编码
     * @param headers      请求头
     * @return 返回的内容也使用UTF-8编码
     * @throws IOException
     */
    public String post(String url, String stringEntity, Header[] headers) throws IOException {
        HttpResponse httpResponse = postString(url, stringEntity, null, headers);
        return EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
    }


    /**
     * 发出POST请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String post(String url) throws IOException {
        return post(url, null);
    }


    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param charset
     * @param cookies
     * @param headers
     * @return
     * @throws IOException
     */

    public HttpResponse post(String url,
                             List<? extends NameValuePair> params,
                             String charset, CookieStore cookies, Header[] headers) throws IOException {
        HttpResponse resp = null;
        HttpPost post = new HttpPost(url);
        if (params != null) {
            if (charset == null) {
                charset = "UTF-8";
            }
            post.setEntity(new UrlEncodedFormEntity(params, charset));
        }
        post.setHeaders(headers);
        // post.setHeader("User-Agent", AGENT_FIREFOX);
        HttpClient client = getClient();
        if (cookies != null) {
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookies);
            resp = client.execute(post, context);
        } else {
            resp = client.execute(post);
        }
        return resp;
    }

    /**
     * 上传单个文件
     *
     * @param url
     * @param file
     * @return
     */
    public String upload(String url, File file) {
        return upload(url, file.getName(), file, null);
    }

    /**
     * 上传单个文件
     *
     * @param url
     * @param file
     * @param headers
     * @return
     */
    public String upload(String url, File file, Header[] headers) {
        return upload(url, file.getName(), file, headers);
    }

    /**
     * 上传单个文件
     *
     * @param url
     * @param name
     * @param file
     * @return
     */
    public String upload(String url, String name, File file, Header[] headers) {
        HashMap<String, File> stringFileHashMap = new HashMap<String, File>();
        stringFileHashMap.put(name == null ? file.getName() : name, file);
        try {
            HttpResponse httpResponse = this.post(url, null, null, null, headers, stringFileHashMap);
            return EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传文件
     *
     * @param url
     * @param params
     * @param paramCharset
     * @param cookies
     * @param headers
     * @param files
     * @return
     * @throws IOException
     */
    public HttpResponse post(String url,
                             Map<String, String> params, String paramCharset,
                             CookieStore cookies, Header[] headers, Map<String, File> files)
            throws IOException {
        HttpResponse resp = null;
        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
        if (files != null && files.size() > 0) {
            Set<String> keys = files.keySet();
            for (String k : keys) {
                File file = files.get(k);
                if (file != null && file.exists() && file.isFile()) {
                    FileBody fileBody = new FileBody(file);
                    reqEntity.addPart(k, fileBody);
                }
            }
        }

        if (params != null && params.size() > 0) {
            Set<String> keys = params.keySet();
            for (String k : keys) {
                String str = params.get(k);
                StringBody strBody = new StringBody(str, ContentType.create("text/plain", paramCharset == null ? Consts.UTF_8 : Charset.forName(paramCharset))
                );
                reqEntity.addPart(k, strBody);
            }
        }
        post.setEntity(reqEntity.build());
        post.setHeaders(headers);
        // post.setHeader("User-Agent", AGENT_FIREFOX);

        HttpClient client = getClient();
        if (cookies != null) {
            HttpClientContext context = HttpClientContext.create();
            context.setCookieStore(cookies);
            resp = client.execute(post, context);
        } else {
            resp = client.execute(post);
        }
        return resp;
    }

    /**
     * 获取下载的文件的文件名
     *
     * @param response
     * @return
     */
    public String getFileName(HttpResponse response) {

        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        filename = param.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filename;
    }

    /**
     * 下载文件并保存到指定位置
     *
     * @param url
     * @param dist
     * @return
     * @throws IOException
     */
    public boolean downloadFile(String url, File dist) throws IOException {
        return downloadFile(url, null, null, null, null, dist);
    }

    /**
     * 下载文件并保存到指定位置
     *
     * @param url
     * @param params
     * @param formCharset
     * @param cookies
     * @param headers
     * @param dist
     * @return
     * @throws IOException
     */
    public boolean downloadFile(String url,
                                List<? extends NameValuePair> params,
                                String formCharset, CookieStore cookies, Header[] headers, File dist) throws IOException {
        HttpResponse httpResponse = get(url, params, formCharset, cookies, headers);
        saveFile(httpResponse, dist);
        return true;
    }

    /**
     * 保存下载的文件
     *
     * @param response
     * @param dist
     * @return
     */
    public boolean saveFile(HttpResponse response, File dist) throws IOException {

        int cache = 8192;
        InputStream is = response.getEntity().getContent();
        dist.getParentFile().mkdirs();
        FileOutputStream fileout = new FileOutputStream(dist);
        /**
         * 根据实际运行效果 设置缓冲区大小
         */
        byte[] buffer = new byte[cache];
        int ch = 0;
        while ((ch = is.read(buffer)) != -1) {
            fileout.write(buffer, 0, ch);
        }
        is.close();
        fileout.flush();
        fileout.close();
        return true;
    }

    /**
     * 在完全不使用client对象时使用，如果不确定是否使用，则不需要调用
     */
    public void close() {
        HttpClientUtils.closeQuietly(client);
    }


    /**
     * 不检查所有HTTPS证书
     */
    final static class NoCheckTrustManager implements X509TrustManager {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    }
}
