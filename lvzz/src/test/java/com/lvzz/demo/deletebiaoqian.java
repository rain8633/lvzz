package com.lvzz.demo;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class deletebiaoqian {

    @Test
    public void parseHtml() {
        String html="<p><img style=\"width: 50%;\" src=\"http://localhost:9001/boot/pics/6d4765217cf74b84933888637ba19c4e.jpg\">111<br></p>";
        int length=html.length();

                /*
                 * <.*?>为正则表达式，其中的.表示任意字符，*?表示出现0次或0次以上，此方法可以去掉    双头标签(双头针对于残缺的标签)
                 * "<.*?"表示<尖括号后的所有字符，此方法可以去掉残缺的标签，及后面的内容
                 * " "，若有多种此种字符，可用同一方法去除
                 */
                html = html.replaceAll("<.*?>", " ").replaceAll("", "");
                html = html.replaceAll("<.*?", "");
                int length1=html.length();
                System.out.println(html.substring(0, length1));
            }

    }
