/*
 * MIT License
 *
 * Copyright (c) 2016 BotMill.io
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package co.aurasphere.botmill.fb.demo.behavior;

import java.math.BigDecimal;
import co.aurasphere.botmill.fb.AbstractFbBot;
import co.aurasphere.botmill.fb.autoreply.AutoReply;
import co.aurasphere.botmill.fb.autoreply.MessageAutoReply;
import co.aurasphere.botmill.fb.event.FbBotMillEventType;
import co.aurasphere.botmill.fb.model.annotation.BotMillController;
import co.aurasphere.botmill.fb.model.incoming.MessageEnvelope;
import co.aurasphere.botmill.fb.model.outcoming.FbBotMillResponse;
import co.aurasphere.botmill.fb.model.outcoming.factory.ButtonFactory;
import co.aurasphere.botmill.fb.model.outcoming.factory.ReplyFactory;

/**
 * The Class TemplateBehavior.
 */
public class AnnotatedTemplatedBehaviour extends AbstractFbBot {
	
	@BotMillController(eventType=FbBotMillEventType.MESSAGE, text="text message", caseSensitive = true)
	public void replyText() {
		reply(new MessageAutoReply("simple text message"));
	}
	
	@BotMillController(eventType=FbBotMillEventType.MESSAGE, text="button template", caseSensitive = false)
	public void replyWithButtonTemplate() {
		reply(new AutoReply() {
			@Override
			public FbBotMillResponse createResponse(MessageEnvelope envelope) {
				return ReplyFactory.addButtonTemplate("Test button template")
						.addPostbackButton("postback button", "postback button payload")
						.addPhoneNumberButton("phone number button", "+393541247844")
						.addUrlButton("web url button", "https://github.com/BotMill/fb-botmill").build(envelope);
			}
		});
	}
	
	@BotMillController(eventType=FbBotMillEventType.MESSAGE, text="list template", caseSensitive = false)
	public void replyWithLisTemplate() {
		reply(new AutoReply() {
			@Override
			public FbBotMillResponse createResponse(MessageEnvelope envelope) {
				return ReplyFactory.addListTemplate()
						.addElement("Classic T-Shirt Collection")
							.setSubtitle("See all our colors")
								.addButton(ButtonFactory.createUrlButton("View",
										"https://peterssendreceiveapp.ngrok.io/collection"))
								.setImageUrl("https://peterssendreceiveapp.ngrok.io/img/collection.png")
								.setDefaultAction(ButtonFactory.createDefaultActionButton(
										"https://peterssendreceiveapp.ngrok.io/shop_collection"))
										.endElement()
						.addElement("Classic White T-Shirt")
								.setSubtitle("100% Cotton, 200% Comfortable")
								.addButton(ButtonFactory.createUrlButton("Shop Now",
										"https://peterssendreceiveapp.ngrok.io/shop?item=100"))
								.setImageUrl("https://peterssendreceiveapp.ngrok.io/img/white-t-shirt.png")
								.setDefaultAction(ButtonFactory.createDefaultActionButton(
										"https://peterssendreceiveapp.ngrok.io/view?item=100")).endElement()
						.addElement("Classic Blue T-Shirt")
								.setSubtitle("100% Cotton, 200% Comfortable")
								.addButton(ButtonFactory.createUrlButton("Shop Now",
										"https://peterssendreceiveapp.ngrok.io/shop?item=101"))
								.setImageUrl("https://peterssendreceiveapp.ngrok.io/img/blue-t-shirt.png")
								.setDefaultAction(ButtonFactory.createDefaultActionButton(
										"https://peterssendreceiveapp.ngrok.io/view?item=101")).endElement()
						.addElement("Classic Black T-Shirt")
								.setSubtitle("100% Cotton, 200% Comfortable")
								.addButton(ButtonFactory.createUrlButton("Shop Now",
										"https://peterssendreceiveapp.ngrok.io/shop?item=102"))
								.setImageUrl("https://peterssendreceiveapp.ngrok.io/img/black-t-shirt.png")
								.setDefaultAction(ButtonFactory.createDefaultActionButton(
										"https://peterssendreceiveapp.ngrok.io/view?item=102")).endElement()
						.addButton(ButtonFactory.createPostbackButton("View more", "view")).build(envelope);
			}
		});
	}
	
	@BotMillController(eventType=FbBotMillEventType.MESSAGE, text="quick replies", caseSensitive = false)
	public void replywithQuickReplies() {
		reply(new AutoReply() {

			@Override
			public FbBotMillResponse createResponse(MessageEnvelope envelope) {
				return ReplyFactory.addTextMessageOnly("Text message with quick replies")
						.addQuickReply("Quick reply 1", "Payload for quick reply 1").build(envelope);
			}
		}, new AutoReply() {

			@Override
			public FbBotMillResponse createResponse(MessageEnvelope envelope) {
				return ReplyFactory.addTextMessageOnly("Text message with quick replies")
						.addQuickReply("Quick reply 2", "Payload for quick reply 1").build(envelope);
			}
		}, new AutoReply() {

			@Override
			public FbBotMillResponse createResponse(MessageEnvelope envelope) {
				return ReplyFactory.addTextMessageOnly("Text message with quick replies")
						.addQuickReply("Quick reply 3", "Payload for quick reply 1").build(envelope);
			}
		});
	}
	
	@BotMillController(eventType=FbBotMillEventType.MESSAGE, text="receipt template", caseSensitive = true)
	public void replyWithReceiptTemplate() {
		reply(new AutoReply() {
			@Override
			public FbBotMillResponse createResponse(MessageEnvelope envelope) {
				return ReplyFactory.addReceiptTemplate("Donato Rimenti", "15", "EUR", "Visa 1234")
						.setSummary(new BigDecimal(201), new BigDecimal(10), new BigDecimal(13), new BigDecimal(240))
						.setMerchantName("Aurasphere co").addElement("Element 1").setCurrency("EUR").setQuantity(29)
						.setPrice(new BigDecimal(200)).setSubtitle("Element 1 subtitle").endElement()
						.setTimestamp("1243").build(envelope);
			}
		});
	}
 
}