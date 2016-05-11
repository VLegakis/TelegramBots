package org.telegram.telegrambots.api.objects;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class MediaMessage extends Message {
	
	private static final String OK_FIELD = "ok";
    private static final String RESULT_FIELD = "result";
    
    @JsonProperty(OK_FIELD)
    private Boolean ok; ///< Integer	Unique message identifier
    @JsonProperty(RESULT_FIELD)
    private Result result; ///< Optional. Sender, can be empty for messages sent to channels
    
    public MediaMessage() {
        super();
    }

	
	public MediaMessage(JSONObject jsonObject) {
        super();
        this.ok = jsonObject.getBoolean(OK_FIELD);
        if (jsonObject.has(RESULT_FIELD)) {
            this.result = new Result(jsonObject.getJSONObject(RESULT_FIELD));
        }
    }


	public Boolean getOk() {
		return ok;
	}


	public void setOk(Boolean ok) {
		this.ok = ok;
	}


	public Result getResult() {
		return result;
	}


	public void setResult(Result result) {
		this.result = result;
	}
	
	public boolean hasResult() {
        return result != null;
    }
	
	@Override
    public void serialize(JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeBooleanField(OK_FIELD, ok);
        if (result != null) {
            gen.writeObjectField(RESULT_FIELD, result);
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
		return "MediaMessage [" + (ok != null ? "ok=" + ok + ", " : "") + (result != null ? "result=" + result : "") + "]";
	}
    
    

}
