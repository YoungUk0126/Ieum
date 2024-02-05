package com.ukcorp.ieum.chat.mapper;

import com.ukcorp.ieum.chat.dto.ChatHistoryDto;
import com.ukcorp.ieum.chat.entity.ChatHistory;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatHistoryMapper {

    @Named("chatHistoryToChatHistoryDto")
    @Mapping(target = "id", source = "chatHistoryNo")
    @Mapping(target = "message", source = "messageContent")
    @Mapping(target = "date", source = "chatDate")
    ChatHistoryDto chatHistoryToChatHistoryDto(ChatHistory chatHistory);


    @IterableMapping(qualifiedByName = "chatHistoryToChatHistoryDto")
    List<ChatHistoryDto> chatHistoriesToChatHistoryDto(List<ChatHistory> chatHistories);

}
