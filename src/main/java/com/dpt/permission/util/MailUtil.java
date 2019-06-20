package com.dpt.permission.util;

import com.dpt.permission.beans.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 邮件工具类.
 */
@Slf4j
public class MailUtil {

	public static final String USER = "dp_tao";
	public static final String PASSWORD = "hxy110120119";
	public static final String FROM = "dp_tao@126.com";

	public static void sendMail(Mail mail) {
		// 1.创建一个程序与邮件服务器会话对象 Session

		Properties props = new Properties();
		try {
			//FileInputStream in = new FileInputStream("email.properties");
			InputStream in = MailUtil.class.getClassLoader().getResourceAsStream("email.properties");
			props.load(in);
		} catch (Exception e) {
			log.error("没有找到指定邮件配置文件：error{}",e);
		}
		// 创建验证器
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASSWORD);
			}
		};

		Session session = Session.getInstance(props, auth);

		try {
			// 2.创建一个Message，它相当于是邮件内容
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(FROM)); // 设置发送者

			message.setRecipient(RecipientType.TO, new InternetAddress(mail.getReceivers())); // 设置发送方式与接收者

			message.setSubject(mail.getSubject());
			// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");

			message.setContent(mail.getMessage(), "text/html;charset=utf-8");

			// 3.创建 Transport用于将邮件发送
			Transport.send(message);
			log.info("{}发送邮件到{}成功！",FROM,mail.getReceivers());
		} catch (Exception e) {
			log.error("邮件发送失败! error:{}",e);
		}
	}

}