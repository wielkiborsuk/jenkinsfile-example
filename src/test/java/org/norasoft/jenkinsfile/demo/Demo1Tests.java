package org.norasoft.jenkinsfile.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1Tests {

	@Test
	public void contextLoads1() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue(new Random().nextBoolean());
	}

}

