package com.github.vp.example.axon;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.distributed.DistributedCommandBus;
import org.axonframework.jgroups.commandhandling.JGroupsConnector;
import org.axonframework.serialization.Serializer;
import org.axonframework.spring.commandhandling.distributed.jgroups.JGroupsConnectorFactoryBean;
import org.jgroups.stack.GossipRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vimalpar on 08/07/17.
 */
@ConditionalOnProperty("axon.distributed.enabled")
@Configuration
public class DistributedCommandBusConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(DistributedCommandBusConfiguration.class);

    @Primary
    @Bean
    public CommandBus commandBus(JGroupsConnector jGroupsConnector) {
        return new DistributedCommandBus(jGroupsConnector, jGroupsConnector);
    }

    @ConditionalOnProperty("axon.distributed.jgroups.gossip.autoStart")
    @Bean(destroyMethod = "stop")
    public GossipRouter gossipRouter(@Value("${axon.distributed.jgroups.hosts:localhost[12001]}") String hosts) {
        Matcher matcher =
                Pattern.compile("([^[\\[]]*)\\[(\\d*)\\]").matcher(hosts);
        if (matcher.find()) {

            GossipRouter gossipRouter = new GossipRouter(matcher.group(1), Integer.parseInt(matcher.group(2)));
            try {
                gossipRouter.start();
            } catch (Exception e) {
                logger.warn("Unable to autostart start embedded Gossip server: {}", e.getMessage());
            }
            return gossipRouter;
        } else {
            logger.error("Wrong hosts pattern, cannot start embedded Gossip Router: {}", hosts);
        }
        return null;
    }

    @Bean
    public JGroupsConnectorFactoryBean jgroupsConnectorFactoryBean(@Value("${axon.distributed.jgroups.hosts:localhost[12001]}") String hosts,
                                                                   @Value("${axon.distributed.jgroups.bind.address:GLOBAL}") String bindAddress,
                                                                   @Value("${axon.distributed.jgroups.bind.port:7800}") String bindPort,
                                                                   @Value("${axon.distributed.jgroups.cluster:Axon}") String cluster,
                                                                   @Value("${axon.distributed.jgroups.config:default_tcp_gossip.xml}") String configFile,
                                                                   Serializer serializer,
                                                                   @Qualifier("localSegment") CommandBus localSegment) {
        System.setProperty("jgroups.tunnel.gossip_router_hosts", hosts);
        System.setProperty("jgroups.bind_addr", String.valueOf(bindAddress));
        System.setProperty("jgroups.bind_port", String.valueOf(bindPort));

        JGroupsConnectorFactoryBean jGroupsConnectorFactoryBean = new JGroupsConnectorFactoryBean();
        jGroupsConnectorFactoryBean.setClusterName(cluster);
        jGroupsConnectorFactoryBean.setLocalSegment(localSegment);
        jGroupsConnectorFactoryBean.setSerializer(serializer);
        jGroupsConnectorFactoryBean.setConfiguration(configFile);
        return jGroupsConnectorFactoryBean;
    }
}
