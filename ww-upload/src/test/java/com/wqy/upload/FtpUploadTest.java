package com.wqy.upload;


/*

import com.wqy.wwshop.common.util.FtpUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FtpUploadTest {

    @Test
    public void testFtpUpload() throws IOException {
        //创建FTPClient客户端
        FTPClient ftpClient = new FTPClient();
        //创建FTP连接
        ftpClient.connect("10.31.161.28",21);
        //登录
        ftpClient.login("ftpuser","wu13976756473");
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\2.jpg"));
        //配置上传参数
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //上传文件
        ftpClient.storeFile("hello3.jpg",fileInputStream);
        //关闭连接
        fileInputStream.close();
        ftpClient.logout();

    }

    @Test
    public void testUpload() throws IOException {

            FileInputStream fileInputStream = new FileInputStream(new File("d:\\2.jpg"));
           Boolean bool=FtpUtils.uploadFile("10.31.161.28",21,"ftpuser","wu13976756473","/home/ftpuser/www/images","/2017/11/18","hello2.jpg",fileInputStream);
        System.out.println(bool);


    }


}
*/


