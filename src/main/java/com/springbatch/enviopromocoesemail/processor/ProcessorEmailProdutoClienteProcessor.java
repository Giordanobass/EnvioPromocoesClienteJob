package com.springbatch.enviopromocoesemail.processor;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.springbatch.enviopromocoesemail.dominio.InteresseProdutoCliente;

@Component
public class ProcessorEmailProdutoClienteProcessor implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage>{

    @Override
    public SimpleMailMessage process(InteresseProdutoCliente interesseProdutoCliente) throws Exception {
	SimpleMailMessage email = new SimpleMailMessage();
	email.setFrom("xpto@no-replay.com");
	email.setTo(interesseProdutoCliente.getCliente().getEmail());
	email.setSubject("Promoção Imperdível!!!");
	email.setText(gerarTextoPromocao(interesseProdutoCliente));
	Thread.sleep(2000);
	return email;
    }

    private String gerarTextoPromocao(InteresseProdutoCliente interesseProdutoCliente) {
	StringBuilder writer = new StringBuilder();
	writer.append(String.format("Olá, %s!\n\n", interesseProdutoCliente));
	writer.append("Essa promoção");
	writer.append(String.format("%s - %s\n\n", interesseProdutoCliente.getProduto().getNome(), interesseProdutoCliente.getProduto().getDescricao()));
	writer.append(String.format("Por apenas: %s!", NumberFormat.getCurrencyInstance().format(interesseProdutoCliente.getProduto().getPreco())));
	return writer.toString();
    }

}
