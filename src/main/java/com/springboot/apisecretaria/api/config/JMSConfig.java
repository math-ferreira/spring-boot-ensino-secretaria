package com.springboot.apisecretaria.api.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;


/**
 * Classe responsavel por realizar a conexao com a fila do componente ActiveMQ
 * 
 * @author matsf
 *
 */

@Configuration
@EnableJms
public class JMSConfig {

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Value("${spring.activemq.user}")
	private String user;

	@Value("${spring.activemq.password}")
	private String password;

	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		if ("".equals(user)) {
			return new ActiveMQConnectionFactory(brokerUrl);
		}
		return new ActiveMQConnectionFactory(user, password, brokerUrl);
	}

	@Bean
	public JmsListenerContainerFactory jmsFactoryTopic(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		factory.setPubSubDomain(true);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(connectionFactory());
	}
}
