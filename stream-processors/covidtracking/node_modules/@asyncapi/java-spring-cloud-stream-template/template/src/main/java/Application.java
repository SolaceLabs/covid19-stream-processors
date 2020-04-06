{# vim: set ts=4 sw=4 sts=4 noexpandtab : #}
{%- include 'partials/java-package' -%}

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
{%- if params.reactive === 'true' %}
import reactor.core.publisher.Flux;
{%- endif %}

import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;

{% set className = [asyncapi.info(), params] | mainClassName %}
@SpringBootApplication
public class {{ className }} {

	private static final Logger logger = LoggerFactory.getLogger({{ className }}.class);

	public static void main(String[] args) {
		SpringApplication.run({{ className }}.class);
	}


{%- set funcs = [asyncapi, params] | functions %}
{% for funcName, funcSpec in funcs %}
	@Bean
	{{ funcSpec.functionSignature | safe }} {
		// Add business logic here.
		return null;
	}
{%- endfor %}

}
