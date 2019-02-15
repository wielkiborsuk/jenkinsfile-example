package org.norasoft.jenkinsfile.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo5Tests {

	@Test
	public void contextLoads5() throws InterruptedException {
		Thread.sleep(25000);
	}

}

