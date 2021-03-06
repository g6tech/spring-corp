package spring.corp.framework.email;

import java.util.HashMap;
import java.util.Map;

import spring.corp.framework.configuracao.ManagerSetting;
import spring.corp.framework.i18n.ManagerMessage;

public class Email {
    
	public static void main(String argv[]) throws Exception {
		try {
			String subject = "Teste envio de email";
			String message = "Texto do email aqui.";
			String nameSender = ManagerSetting.getSetting("name.user");
			String sender = ManagerSetting.getSetting("mail.user");
			Map<String, String> recipients = new HashMap<String, String>();
			recipients.put("alberto.cerqueira1990@gmail.com", "Alberto Cerqueira");
			ManagerEmail ge = ManagerEmail.builderInstance()
				.recipients(recipients)
				.subject(subject)
				.message(message) /*Se nao tiver mensagem vai ser enviado o HTML*/
				.name(nameSender)
				.from(sender)
				.build();
			Thread t = new Thread(ge);
			t.start();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(ManagerMessage.getMessage(ManagerMessage.ERRO_GERAL));
		}
		System.out.println(ManagerMessage.getMessage("view.mensagem.enviada.com.sucesso"));
	}
}