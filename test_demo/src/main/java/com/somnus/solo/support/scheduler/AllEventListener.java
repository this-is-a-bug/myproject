package com.somnus.solo.support.scheduler;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AllEventListener {

	@Async
	@EventListener
	public void handleEmail(EmailEvent event) {
		try {
			System.out.println("让程序暂停1分钟，相当于执行一个很耗时的任务");
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("需要发送邮件的接收地址为:"+event.getAddress());
          
        System.out.println("需要发送邮件的邮件正文是:"+event.getText());
	}
	
}
