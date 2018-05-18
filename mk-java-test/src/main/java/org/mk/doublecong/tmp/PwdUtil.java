package org.mk.doublecong.tmp;

import sun.security.provider.MD5;

public class PwdUtil {



    /**
     * 加密字符串,原始字符串不能超过32 个字节,超过部分截断 0-9，a-z，A-Z,~!@#$%^&*()_+-=]}{[;'/.,<>
     * 这些可以,用于密码加密 中文和非可见字符不可以,解密不匹配
     * @param s
     * @return 加密后的字符串
     */
    public static String encryptStr(String s) {
        if (s.length() == 0)
            return "";
        try {
            int sum = 0;
            String s1 = "", s2 = "";
            // 32个随机数
            byte[] b = { 21, 29, 18, 9, 13, 26, 28, 12, 25, 20, 29, 21, 16, 10,
                    6, 17, 13, 24, 11, 22, 23, 10, 7, 15, 19, 5, 20, 29, 11, 8,
                    12, 14 };
            // 共11组随机数 每组32个
            byte[] a = { 29, 7, 23, 18, 15, 13, 31, 32, 5, 8, 8, 2, 4, 11, 23,
                    2, 22, 12, 4, 10, 23, 14, 22, 12, 9, 10, 7, 2, 9, 28, 12,
                    27,

                    12, 23, 14, 5, 2, 16, 11, 16, 3, 5, 12, 2, 4, 6, 0, 14, 13,
                    11, 14, 12, 5, 7, 31, 23, 1, 18, 10, 6, 4, 21, 2, 24,

                    9, 7, 5, 14, 17, 8, 11, 12, 3, 9, 9, 2, 4, 11, 8, 34, 8, 7,
                    12, 14, 5, 9, 21, 22, 5, 8, 20, 2, 4, 15, 13, 21,

                    2, 7, 28, 12, 5, 11, 23, 32, 7, 12, 9, 2, 4, 21, 23, 23, 5,
                    7, 22, 19, 5, 16, 25, 16, 3, 10, 19, 2, 4, 11, 23, 12,

                    15, 7, 10, 14, 5, 2, 19, 32, 7, 21, 7, 2, 5, 21, 9, 13, 12,
                    11, 12, 14, 5, 24, 17, 12, 15, 8, 4, 2, 3, 17, 8, 11,

                    4, 7, 13, 14, 0, 8, 11, 18, 25, 22, 8, 2, 1, 10, 3, 10, 10,
                    8, 7, 24, 1, 27, 21, 32, 6, 8, 9, 2, 4, 12, 13, 17,

                    12, 15, 14, 3, 25, 29, 10, 22, 3, 16, 8, 2, 3, 11, 23, 19,
                    17, 9, 8, 14, 8, 17, 6, 12, 1, 8, 10, 2, 14, 13, 5, 10,

                    18, 17, 1, 15, 9, 19, 5, 17, 9, 3, 18, 2, 22, 1, 8, 24, 20,
                    22, 2, 4, 10, 17, 11, 19, 22, 23, 12, 15, 4, 16, 7, 14,

                    7, 11, 24, 13, 8, 14, 10, 22, 3, 16, 14, 9, 3, 22, 3, 10,
                    19, 7, 8, 4, 28, 26, 16, 22, 3, 5, 27, 7, 14, 23, 5, 7,

                    11, 7, 19, 1, 17, 15, 5, 12, 9, 3, 13, 5, 2, 1, 7, 24, 21,
                    23, 7, 0, 12, 7, 28, 9, 14, 13, 11, 17, 14, 6, 22, 8,

                    21, 9, 14, 21, 1, 25, 14, 2, 27, 9, 12, 15, 7, 8, 17, 2,
                    15, 23, 7, 0, 12, 7, 16, 19, 4, 10, 16, 17, 14, 6, 4, 12

            };
            byte[] bs = s.getBytes("GBK");
            int ib = 0;
            int n = bs.length;
            if (n > 32)
                n = 32;
            for (int i = 0; i < n; i++) {
                sum = sum + bs[i];
            }

            sum = sum % 11;
            s1 = Integer.toHexString(sum);
            if (s1.length() == 1)
                s1 = "0" + s1;
            for (int i = 0; i < n; i++) {
                //bs是byte数组 b是随机数byte数组，32个随机数======= a是11组随机数byte数组 每组32个
                ib = bs[i] + b[i] + a[sum * 32 + i];
                if (ib < 0)
                    ib = ib + 128;
                s2 = Integer.toHexString(ib);
                if (s2.length() == 1)
                    s2 = "0" + s2;
                s1 = s1 + s2;
            }
            return s1;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 解密字符串,原始加密串不能超过66字节，超过部分截断
     *
     * @param s
     * @return 解密后的字符串
     */
    public static String  decrypt(String s) {
        String s1, s2, s3 = "";
        // 1组32个随机数
        byte[] b = { 21, 29, 18, 9, 13, 26, 28, 12, 25, 20, 29, 21, 16, 10, 6,
                17, 13, 24, 11, 22, 23, 10, 7, 15, 19, 5, 20, 29, 11, 8, 12, 14 };
        // 共10组随机数 每组32个
        byte[] a = { 29, 7, 23, 18, 15, 13, 31, 32, 5, 8, 8, 2, 4, 11, 23, 2,
                22, 12, 4, 10, 23, 14, 22, 12, 9, 10, 7, 2, 9, 28, 12, 27,

                12, 23, 14, 5, 2, 16, 11, 16, 3, 5, 12, 2, 4, 6, 0, 14, 13, 11,
                14, 12, 5, 7, 31, 23, 1, 18, 10, 6, 4, 21, 2, 24,

                9, 7, 5, 14, 17, 8, 11, 12, 3, 9, 9, 2, 4, 11, 8, 34, 8, 7, 12,
                14, 5, 9, 21, 22, 5, 8, 20, 2, 4, 15, 13, 21,

                2, 7, 28, 12, 5, 11, 23, 32, 7, 12, 9, 2, 4, 21, 23, 23, 5, 7,
                22, 19, 5, 16, 25, 16, 3, 10, 19, 2, 4, 11, 23, 12,

                15, 7, 10, 14, 5, 2, 19, 32, 7, 21, 7, 2, 5, 21, 9, 13, 12, 11,
                12, 14, 5, 24, 17, 12, 15, 8, 4, 2, 3, 17, 8, 11,

                4, 7, 13, 14, 0, 8, 11, 18, 25, 22, 8, 2, 1, 10, 3, 10, 10, 8,
                7, 24, 1, 27, 21, 32, 6, 8, 9, 2, 4, 12, 13, 17,

                12, 15, 14, 3, 25, 29, 10, 22, 3, 16, 8, 2, 3, 11, 23, 19, 17,
                9, 8, 14, 8, 17, 6, 12, 1, 8, 10, 2, 14, 13, 5, 10,

                18, 17, 1, 15, 9, 19, 5, 17, 9, 3, 18, 2, 22, 1, 8, 24, 20, 22,
                2, 4, 10, 17, 11, 19, 22, 23, 12, 15, 4, 16, 7, 14,

                7, 11, 24, 13, 8, 14, 10, 22, 3, 16, 14, 9, 3, 22, 3, 10, 19,
                7, 8, 4, 28, 26, 16, 22, 3, 5, 27, 7, 14, 23, 5, 7,

                11, 7, 19, 1, 17, 15, 5, 12, 9, 3, 13, 5, 2, 1, 7, 24, 21, 23,
                7, 0, 12, 7, 28, 9, 14, 13, 11, 17, 14, 6, 22, 8,

                21, 9, 14, 21, 1, 25, 14, 2, 27, 9, 12, 15, 7, 8, 17, 2, 15,
                23, 7, 0, 12, 7, 16, 19, 4, 10, 16, 17, 14, 6, 4, 12

        };
        int n = (s.length() - 2) / 2;
        if (n <= 0)
            return "";
        if (n > 32)
            n = 32;
        int m = 0;
        byte b1;
        s1 = s.substring(0, 2);
        s2 = Integer.valueOf(s1, 16).toString();
        int sum = Integer.parseInt(s2);
        for (int i = 0; i < n; i++) {
            s1 = s.substring(i * 2 + 2, i * 2 + 4);
            s2 = Integer.valueOf(s1, 16).toString();
            m = Integer.parseInt(s2);
            m = m - b[i] - a[sum * 32 + i];
            if (m < 0)
                m = m + 128;
            b1 = (byte) (m);
            s3 = s3 + (char) (b1);
        }
        return s3;
    }

}
