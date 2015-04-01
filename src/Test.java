import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2015/1/2813:32
 */

public class Test {
    public  static  void main(String[] args) throws UnsupportedEncodingException {

        String source = "中国";

//        String charSet1 = "gb2312";
//        byte b1[] = getBytesArray(source, charSet1);
//        System.out.println("'" + source + "'" + "的" + charSet1 + "编码:" + byte2hex(b1));
        String str= new String(source.getBytes(), "utf-8");
        System.out.println(new String(str.getBytes(), "GBK"));
        System.out.println("------------------------");
    }

    public static byte[] getBytesArray(String str, String changeToCharset)
            throws UnsupportedEncodingException {
        return str.getBytes(changeToCharset);
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }


    public static byte[] hex2byte(String content) {
        byte[] mtemp = new byte[content.length() / 2];
        int j = 0;
        for (int n = 0; n < content.length(); n = n + 2) {
            String sTemp = content.substring(n, n + 2);
            mtemp[j] = (byte) Integer.parseInt(sTemp, 16);
            j++;
        }
        return mtemp;
    }
}
