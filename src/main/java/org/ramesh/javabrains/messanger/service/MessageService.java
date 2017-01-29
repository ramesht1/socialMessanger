package org.ramesh.javabrains.messanger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.ramesh.javabrains.messanger.Database.DatabaseClass;
import org.ramesh.javabrains.messanger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "Book1", "Ramesh1"));
		messages.put(2L, new Message(2, "Book2", "Ramesh2"));
	}

	public List<Message> getAllMessages() {

		return new ArrayList<Message>(messages.values());

	}

	public void addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);

	}

	public void removeMessage(Long id) {
		messages.remove(id);
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}

		messages.put(message.getId(), message);
		return message;

	}

	public Message getMessage(Long id) {
		return messages.get(id);
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);

			}
		}

		return messagesForYear;

	}
	
	public List<Message> getAllMessagesPaginated(int start,int size) {
		List<Message> list = new ArrayList<>(messages.values());
		if(start+size >=list.size() ) return new ArrayList<Message>();
		return list.subList(start, start+size);
		
	

	}
	
	
	

}
