package com.github.vp.example.axon;

import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by vimalpar on 08/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(properties = "axon.distributed.enabled=true")
public class DistributedConfigurationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void shouldHaveDistributedCommandBus() {
        assertThat(applicationContext.getBean("commandBus")).isInstanceOf(DistributedCommandBus.class);
    }

    @Test
    public void shouldHaveDisruptorCommandBus() {
        assertThat(applicationContext.getBean("localSegment")).isInstanceOf(DisruptorCommandBus.class);
    }
}
