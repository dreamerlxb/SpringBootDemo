package com.lxb.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private com.lxb.demo.mail.MailService mailService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("424151385@qq.com", "test simple mail", " hello this is simple mail");
	}

	@Test
	public void testHTMLMail() throws Exception {
		String content = "<html>" +
							"<body>" +
								"<h3>hello world ! 这是一封Html邮件!</h3>" +
								"<a href='https://baidu.com'>This is baidu</a>" +
							"</body>" +
						 "</html>";
		mailService.sendHtmlMail("424151385@qq.com", "Test html mail", content);
	}
	
	@Test
	public void testAttachmentMail() {
		String content = "<html>" +
				"<body>" +
					"<h3>Hello! This is a Attachment HTML mail!!</h3>" +
					"<a href='https://baidu.com'>This is baidu</a>" +
				"</body>" +
			 "</html>";
		mailService.sendAttachmentMail("424151385@qq.com", "Test Attachment mail", content);
	}
}
