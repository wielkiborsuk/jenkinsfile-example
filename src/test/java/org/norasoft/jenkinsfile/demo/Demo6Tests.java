package org.norasoft.jenkinsfile.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo6Tests {

	@Test
	public void contextLoads6() throws InterruptedException {
		Thread.sleep(30000);
	}

}

