package org.telegram.telegrambots.api.objects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.telegram.telegrambots.api.interfaces.IBotApiObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class Result implements IBotApiObject{
	
    private static final String MESSAGEID_FIELD = "message_id";
    private static final String FROM_FIELD = "from";
    private static final String DATE_FIELD = "date";
    private static final String CHAT_FIELD = "chat";
    private static final String PHOTO_FIELD = "photo";
    
    @JsonProperty(MESSAGEID_FIELD)
    private Integer messageId; ///< Integer	Unique message identifier
    @JsonProperty(FROM_FIELD)
    private User from; ///< Optional. Sender, can be empty for messages sent to channels
    @JsonProperty(DATE_FIELD)
    private Integer date; ///< Optional. Date the message was sent in Unix time
    @JsonProperty(CHAT_FIELD)
    private Chat chat; ///< Conversation the message belongs to
    @JsonProperty(PHOTO_FIELD)
    private List<PhotoSize> photo; ///< Optional. Message is a photo, available sizes of the photo

    public Result() {
        super();
    }

    public Result(JSONObject jsonObject) {
        super();
        this.messageId = jsonObject.getInt(MESSAGEID_FIELD);
        if (jsonObject.has(FROM_FIELD)) {
            this.from = new User(jsonObject.getJSONObject(FROM_FIELD));
        }
        if (jsonObject.has(DATE_FIELD)) {
            this.date = jsonObject.getInt(DATE_FIELD);
        }
        if (jsonObject.has(PHOTO_FIELD)) {
            this.photo = new ArrayList<>();
            JSONArray photos = jsonObject.getJSONArray(PHOTO_FIELD);
            for (int i = 0; i < photos.length(); i++) {
                this.photo.add(new PhotoSize(photos.getJSONObject(i)));
            }
        }
    }

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public List<PhotoSize> getPhoto() {
		return photo;
	}

	public void setPhoto(List<PhotoSize> photo) {
		this.photo = photo;
	}
    
	@Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField(MESSAGEID_FIELD, messageId);
        if (from != null) {
            gen.writeObjectField(FROM_FIELD, from);
        }
        if (date != null) {
            gen.writeNumberField(DATE_FIELD, date);
        }
        gen.writeObjectField(CHAT_FIELD, chat);
        
        if (photo != null && photo.size() > 0) {
            gen.writeArrayFieldStart(PHOTO_FIELD);
            for (PhotoSize photoSize : photo) {
                gen.writeObject(photoSize);
            }
            gen.writeEndArray();
        }
        gen.writeEndObject();
        gen.flush();
    }

    @Override
    public void serializeWithType(JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
        serialize(gen, serializers);
    }

	@Override
	public String toString() {
		return "Result [" + (messageId != null ? "messageId=" + messageId + ", " : "") + (from != null ? "from=" + from + ", " : "")
				+ (date != null ? "date=" + date + ", " : "") + (chat != null ? "chat=" + chat + ", " : "") + (photo != null ? "photo=" + photo : "") + "]";
	}

}