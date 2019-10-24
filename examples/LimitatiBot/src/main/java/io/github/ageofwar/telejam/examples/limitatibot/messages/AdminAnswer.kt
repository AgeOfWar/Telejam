package io.github.ageofwar.telejam.examples.limitatibot.messages

import io.github.ageofwar.telejam.Bot
import io.github.ageofwar.telejam.chats.Chat
import io.github.ageofwar.telejam.messages.*
import io.github.ageofwar.telejam.methods.*
import io.github.ageofwar.telejam.text.Text

class AdminAnswer(private val bot: Bot) : MessageHandler {

    override fun onMessage(message: Message) {
        if (!message.replyToMessage.isPresent) return
        val replyToMessage = message.replyToMessage.get() as? Forward<*> ?: return

        val sendTextMessage = SendMessage()
                .chat(replyToMessage.chat)

        if (message is TextMessage) {
            sendTextMessage.text(Text.parseHtml("👤 <a href=\"tg://user?id=229856560\">SuperMarcomen</a> answered you this:\n\n%answer"
                    .replace("%answer", message.text.toHtmlString())
            ))
        } else {
            val sendMessage = getMessage(message, replyToMessage.chat)
            val messageId = bot.execute(sendMessage)

            sendTextMessage
                    .text(Text.parseHtml("👤 <a href=\"tg://user?id=229856560\">SuperMarcomen</a> answered you with this:"))
                    .replyToMessage(messageId)
        }

        bot.execute(sendTextMessage)
    }

    private fun getMessage(message: Message, chat: Chat): TelegramMethod<out Message> {
        return when (message) {
            is TextMessage -> SendMessage()
                    .text(message.text)
                    .chat(chat)
            is StickerMessage -> SendSticker()
                    .sticker(message.sticker.id)
                    .chat(chat)
            is AudioMessage -> SendAudio()
                    .audio(message.audio.id)
                    .chat(chat)
            is LocationMessage -> SendLocation()
                    .location(message.location)
                    .chat(chat)
            is PhotoMessage -> SendPhoto()
                    .photo(message.photo[0].id)
                    .chat(chat)
            is DocumentMessage -> SendDocument()
                    .document(message.document.id)
                    .chat(chat)
            is VoiceMessage -> SendVoice()
                    .voice(message.voice.id)
                    .chat(chat)
            else -> throw AssertionError()
        }
    }

}