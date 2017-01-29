package org.ramesh.javabrains.messanger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ramesh.javabrains.messanger.model.Message;
import org.ramesh.javabrains.messanger.service.MessageService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/messages")
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
    
	public List<Message> getAllMessages(@QueryParam("year") int year,
										@QueryParam("start") int start,
										@QueryParam("size") int size) {
		
		if(year>0) {
			return messageService.getAllMessagesForYear(year);
			
		}
		if(start>=0 && size >0) {
			return messageService.getAllMessagesPaginated(start, size);
			
		}
		
		return messageService.getAllMessages();

	}

	@GET
	@Path("/{messageId}")

	public Message getMessage(@PathParam("messageId") Long messageId) {

		return messageService.getMessage(messageId);

	}

	@POST

	public void addMessage(Message message) {

		messageService.addMessage(message);

	}

	@PUT

	@Path("/{messageId}")
	public void putMessage(@PathParam("messageId") Long id, Message message) {
		message.setId(id);
		messageService.updateMessage(message);

	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") Long id) {

		messageService.removeMessage(id);

	}
}
